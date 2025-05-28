package org.informatics.services.products;

import lombok.RequiredArgsConstructor;
import org.informatics.exceptions.NoProductsFoundException;
import org.informatics.exceptions.NoSuchCashoutException;
import org.informatics.models.Product;
import org.informatics.models.RetailStore;
import org.informatics.services.products.contracts.ProductExpiration;
import org.informatics.services.products.contracts.ProductsTotalCalc;
import org.informatics.utils.ErrorMessagesManager;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Map;

@RequiredArgsConstructor
public class ProductsTotalCalcImpl implements ProductsTotalCalc {
    private final RetailStore store;
    private final ProductExpiration expirationService;

    private static final short H_PERCENTAGE = 100;
    private Map<Product, BigDecimal> getValidatedScannedProducts(int checkoutId) {
        if (checkoutId < 0 || checkoutId >= store.getCheckouts().size())
            throw new NoSuchCashoutException(ErrorMessagesManager.getErrorMessage("error.checkout_not_found", checkoutId));
        if (store.getCheckouts().get(checkoutId).getScannedProducts().isEmpty())
            throw new NoProductsFoundException(ErrorMessagesManager.getErrorMessage("error.no_products_found", checkoutId));

        return store.getCheckouts().get(checkoutId).getScannedProducts();
    }

    @Override
    public BigDecimal calculateTotalWithDiscount(int checkoutId) {
        try {
            return getValidatedScannedProducts(checkoutId)
                    .entrySet().stream()
                    .filter(entry -> !expirationService.isExpired(entry.getKey()))
                    .map(entry -> {
                        Product product = entry.getKey();
                        BigDecimal productAmount = entry.getValue();

                        BigDecimal basePrice = product.getProductDeliveryPrice();
                        BigDecimal categoryMarkupPercent = store.getCategoryMarkupPercent().get(product.getProductCategory());

                        boolean isWithinExpiryDays = product.getProductExpiryDate()
                                .isBefore(LocalDate.now().plusDays(store.getProductExpiryDiscountDays()));
                        BigDecimal expiryDiscountPercent = isWithinExpiryDays ? store.getProductExpiryDiscountInPercent() : BigDecimal.ZERO;

                        return basePrice.multiply(
                                BigDecimal.valueOf(H_PERCENTAGE)
                                        .add(categoryMarkupPercent)
                                        .subtract(expiryDiscountPercent)
                                        .divide(BigDecimal.valueOf(H_PERCENTAGE), 2, RoundingMode.HALF_EVEN)
                        );
                    })
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } catch (NoSuchCashoutException | NoProductsFoundException e) {
            return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal calculateBaseTotal(int checkoutId) {
        try {
            return getValidatedScannedProducts(checkoutId)
                    .keySet().stream()
                    .map(Product::getProductDeliveryPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } catch (NoSuchCashoutException | NoProductsFoundException e) {
            return BigDecimal.ZERO;
        }
    }
}
