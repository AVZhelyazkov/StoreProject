package org.informatics.services.products.interfaces;

import java.util.UUID;

public interface ProductValidatorInterface {
    /*
    Validates a product's existence in the store.
     */
    void isProductIdPresent(UUID id);
}
