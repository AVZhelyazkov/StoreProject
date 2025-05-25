package org.informatics.contracts.clientcheckout;

import lombok.RequiredArgsConstructor;
import org.informatics.enums.ProductCategory;
import org.informatics.models.Product;
import org.informatics.models.RetailStore;
import org.informatics.contracts.clientcheckout.contracts.ClientProductSelection;
import org.informatics.contracts.products.ProductExpirationImpl;
import org.informatics.utils.ErrorMessagesManager;

import java.math.BigDecimal;
import java.util.*;

@RequiredArgsConstructor
public class ClientProductSelectionService implements ClientProductSelection {
    final private RetailStore store;
    final private ProductExpirationImpl productExpirationService;

    @Override
    public Map.Entry<Product, BigDecimal> productSelectionByName(String name, BigDecimal productAmount) {
        if (productAmount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException(
                    ErrorMessagesManager.getErrorMessage("error.invalid_product_amount")
            );

        return store.getProductsInStock().entrySet().stream()
                .filter(pair -> pair.getKey().getProductName().contains(name))
                .filter(pair -> pair.getValue().compareTo(productAmount) >= 0)
                .filter(pair -> !productExpirationService.isExpired(pair.getKey()))
                .max(Comparator.comparing(keyPair -> keyPair.getKey().getProductExpiryDate()))
                .map(entry -> new AbstractMap.SimpleImmutableEntry<>(entry.getKey(), productAmount))
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                ErrorMessagesManager.getErrorMessage("error.not_enough_stock")
                        )
                );
    }

    @Override
    public Map.Entry<Product, BigDecimal> productSelectionByCategory(ProductCategory selectedCategory, BigDecimal productAmount) {
        if (productAmount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException(
                    ErrorMessagesManager.getErrorMessage("error.invalid_product_amount")
            );

        return store.getProductsInStock().entrySet().stream()
                .filter(pair -> pair.getKey().getProductCategory().equals(selectedCategory))
                .filter(pair -> pair.getValue().compareTo(productAmount) >= 0)
                .filter(pair -> !productExpirationService.isExpired(pair.getKey()))
                .max(Comparator.comparing(keyPair -> keyPair.getKey().getProductExpiryDate()))
                .map(entry -> new AbstractMap.SimpleImmutableEntry<>(entry.getKey(), productAmount))
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                ErrorMessagesManager.getErrorMessage("error.not_enough_stock")
                        )
                );
    }

}
