package org.informatics.services.clientcheckout;

import lombok.RequiredArgsConstructor;
import org.informatics.exceptions.NoProductsFoundException;
import org.informatics.exceptions.NoSuchCashoutException;
import org.informatics.models.Product;
import org.informatics.models.RetailStore;
import org.informatics.services.clientcheckout.interfaces.CheckoutScanInterface;
import org.informatics.utils.ErrorMessagesManager;

import java.util.Set;

@RequiredArgsConstructor
public class CheckoutScanService implements CheckoutScanInterface {

    private final RetailStore store;
    @Override
    public void scanProductsOnCheckout(Set<Product> productsForScan, int atCheckoutMachine) {
        if (productsForScan.isEmpty())
            throw new NoProductsFoundException(ErrorMessagesManager.getErrorMessage("error.no_products_found", atCheckoutMachine));
        else if (atCheckoutMachine < 0 || atCheckoutMachine >= store.getCheckouts().length)
            throw new NoSuchCashoutException(ErrorMessagesManager.getErrorMessage("error.checkout_not_found", atCheckoutMachine));

        store.getCheckouts()[atCheckoutMachine].setScannedProducts(productsForScan);
        store.getProductsInStock().removeAll(productsForScan);
    }
}
