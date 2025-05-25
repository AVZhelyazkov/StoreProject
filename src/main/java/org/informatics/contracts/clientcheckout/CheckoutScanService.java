package org.informatics.contracts.clientcheckout;

import lombok.RequiredArgsConstructor;
import org.informatics.exceptions.NoProductsFoundException;
import org.informatics.exceptions.NoSuchCashoutException;
import org.informatics.exceptions.UnrecognizedItemException;
import org.informatics.models.Product;
import org.informatics.models.RetailStore;
import org.informatics.contracts.clientcheckout.contracts.CheckoutScan;
import org.informatics.contracts.products.ProductExpirationImpl;
import org.informatics.utils.ErrorMessagesManager;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;

@RequiredArgsConstructor
public class CheckoutScanService implements CheckoutScan {

    private final RetailStore store;
    private final ProductExpirationImpl expirationService;
    @Override
    public void scanProductsOnCheckout(Map<Product, BigDecimal> productsForScan, int atCheckoutMachine) {
        if (productsForScan.isEmpty())
            throw new NoProductsFoundException(
                    ErrorMessagesManager.getErrorMessage("error.no_products_found", atCheckoutMachine));
        else if (atCheckoutMachine < 0 || atCheckoutMachine >= store.getCheckouts().size())
            throw new NoSuchCashoutException(
                    ErrorMessagesManager.getErrorMessage("error.checkout_not_found", atCheckoutMachine));

        store.getCheckouts().get(atCheckoutMachine).setScannedProducts(productsForScan);

        Iterator<Map.Entry<Product, BigDecimal>> iterator = productsForScan.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<Product, BigDecimal> entry = iterator.next();

            if (!store.getProductsInStock().containsKey(entry.getKey()))
                throw new UnrecognizedItemException("error.unrecognized_item");

            if (expirationService.isExpired(entry.getKey())) {
                iterator.remove();
                expirationService.removeProductFromStore(entry.getKey());
            } else {
                if (store.getProductsInStock().get(entry.getKey()).compareTo(entry.getValue()) >= 0) {
                    BigDecimal remainingStock = store.getProductsInStock().get(entry.getKey())
                            .subtract(entry.getValue());

                    store.getProductsInStock().put(entry.getKey(), remainingStock);
                }
            }
        }
    }
}