package org.informatics.services.products;

import lombok.RequiredArgsConstructor;
import org.informatics.exceptions.NoProductsFoundException;
import org.informatics.exceptions.NoSuchCashoutException;
import org.informatics.models.Product;
import org.informatics.models.RetailStore;
import org.informatics.services.products.interfaces.ProductsTotalCalcInterface;
import org.informatics.utils.ErrorMessagesManager;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

@RequiredArgsConstructor
public class ProductsTotalCalcService implements ProductsTotalCalcInterface {
    private final RetailStore store;

    private static final short H_PERCENTAGE = 100;

    private Set<Product> getValidatedScannedProducts(int checkoutId) {
        if (checkoutId < 0 || checkoutId >= store.getCheckouts().length)
            throw new NoSuchCashoutException(ErrorMessagesManager.getErrorMessage("error.checkout_not_found", checkoutId));
        if (store.getCheckouts()[checkoutId].getScannedProducts().isEmpty())
            throw new NoProductsFoundException(ErrorMessagesManager.getErrorMessage("error.no_products_found", checkoutId));
        return store.getCheckouts()[checkoutId].getScannedProducts();
    }

    @Override
    public BigDecimal calculateTotalWithDiscount(int checkoutId) {
        try {
            return getValidatedScannedProducts(checkoutId).stream()
                    .map(product -> {
                        BigDecimal deliveryPrice = product.getProductDeliveryPrice();
                        Integer percentageIncrease = store.getDiscountPercent().get(product.getProductCategory());
                        return deliveryPrice.multiply(BigDecimal.valueOf((percentageIncrease + H_PERCENTAGE)).divide(BigDecimal.valueOf(H_PERCENTAGE), 2, RoundingMode.HALF_EVEN));
                    })
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } catch (NoSuchCashoutException | NoProductsFoundException e) {
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal calculateBaseTotal(int checkoutId) {
        try {
            return getValidatedScannedProducts(checkoutId).stream()
                    .map(Product::getProductDeliveryPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } catch (NoSuchCashoutException | NoProductsFoundException e) {
            return BigDecimal.ZERO;
        }
    }
}
