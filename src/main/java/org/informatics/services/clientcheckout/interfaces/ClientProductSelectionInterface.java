package org.informatics.services.clientcheckout.interfaces;

import org.informatics.enums.ProductCategory;
import org.informatics.models.Product;

import java.util.Set;

public interface ClientProductSelectionInterface {
    Set<Product> productSelection(int amountOfProducts);
    Set<Product> productFromCategorySelection(ProductCategory productCategory, int amountOfProducts);
}
