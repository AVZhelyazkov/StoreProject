package org.informatics.services.storemanagement.contracts;

import org.informatics.models.Product;

import java.math.BigDecimal;

public interface AddProductToStoreStock {
    void addProductToStoreStock(Product product, BigDecimal quantity);
}
