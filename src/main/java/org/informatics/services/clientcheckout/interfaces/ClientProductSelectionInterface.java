package org.informatics.services.clientcheckout.interfaces;

import org.informatics.enums.ProductCategory;

public interface ClientProductSelectionInterface {
    void productSelection();
    void productSelection(int amountOfProducts);
    void productFromCategorySelection(ProductCategory productCategory, int amountOfProducts);
}
