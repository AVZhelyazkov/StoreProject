package org.informatics.services.clientcheckout.contracts;

import org.informatics.models.Product;

import java.math.BigDecimal;
import java.util.Map;

public interface CheckoutScan {
    void scanProductsOnCheckout(Map<Product, BigDecimal> productForScan, int atCheckoutMachine);
}
