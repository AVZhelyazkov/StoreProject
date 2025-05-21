package org.informatics.services.products;

import lombok.RequiredArgsConstructor;
import org.informatics.models.RetailStore;
import org.informatics.services.products.interfaces.ProductValidatorInterface;

import java.util.UUID;

@RequiredArgsConstructor
public class ProductValidatorService implements ProductValidatorInterface {
    private RetailStore store;

    @Override
    public void isProductIdPresent(UUID id) {

    }
}
