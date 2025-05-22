package org.informatics.services.clientcheckout;

import lombok.RequiredArgsConstructor;
import org.informatics.enums.ProductCategory;
import org.informatics.models.Product;
import org.informatics.models.RetailStore;
import org.informatics.services.clientcheckout.interfaces.ClientProductSelectionInterface;
import org.informatics.utils.ErrorMessagesManager;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@RequiredArgsConstructor
public class ClientProductSelectionService implements ClientProductSelectionInterface {
    final private RetailStore store;
    @Override
    public Set<Product> productSelection(int amountOfProducts) {
        if (amountOfProducts < 1)
            throw new IllegalArgumentException(
                    ErrorMessagesManager.getErrorMessage("error.invalid_product_amount")
            );
        else if (amountOfProducts > store.getProductsInStock().size())
            throw new IllegalArgumentException(
                    ErrorMessagesManager.getErrorMessage("error.not_enough_stock")
            );

        Set<Product> selectedProducts = new HashSet<>();

        Random random = new Random();
        random.setSeed(System.currentTimeMillis());

        while (selectedProducts.size() < amountOfProducts) {
            selectedProducts.add(
                    store.getProductsInStock().get(
                            random.nextInt(store.getProductsInStock().size())
                    )
            );
        }

        return selectedProducts;
    }

    @Override
    public Set<Product> productFromCategorySelection(ProductCategory selectedCategory, int amountOfProducts) {
        if (amountOfProducts < 1)
            throw new IllegalArgumentException(
                    ErrorMessagesManager.getErrorMessage("error.invalid_product_amount")
            );
        else if (amountOfProducts > store.getProductsInStock().size())
            throw new IllegalArgumentException(
                    ErrorMessagesManager.getErrorMessage("error.not_enough_stock")
            );

        Set<Product> selectedProducts = new HashSet<>();

        Random random = new Random();
        random.setSeed(System.currentTimeMillis());

        Product currentProduct;

        while (selectedProducts.size() < amountOfProducts) {
            currentProduct = store.getProductsInStock().get(random.nextInt(store.getProductsInStock().size()));
            if (currentProduct.getProductCategory().equals(selectedCategory))
                selectedProducts.add(currentProduct);
        }

        return selectedProducts;
    }
}
