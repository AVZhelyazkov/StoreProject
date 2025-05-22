package org.informatics.services.clientcheckout.interfaces;

import org.informatics.models.Product;

import java.util.Set;

public interface CheckoutScanInterface {
    void scanProductsOnCheckout(Set<Product> productForScan, int atCheckoutMachine);
}
