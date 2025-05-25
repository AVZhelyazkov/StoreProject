package org.informatics.contracts.clientcheckout.contracts;

import org.informatics.enums.ProductCategory;
import org.informatics.models.Product;

import java.math.BigDecimal;
import java.util.Map;

public interface ClientProductSelection {
    Map.Entry<Product, BigDecimal> productSelectionByName(String name, BigDecimal productAmount);
    Map.Entry<Product, BigDecimal> productSelectionByCategory(ProductCategory productCategory, BigDecimal productAmount);
}
