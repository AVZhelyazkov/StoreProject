package org.informatics.StoreTest;

import org.informatics.models.Product;
import org.informatics.models.RetailStore;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

public class RetailStoreTest {

    @BeforeEach
    void setup() {
        RetailStore store = Mockito.mock(RetailStore.class);

        Product product = Mockito.mock(Product.class);
        Product product2 = Mockito.mock(Product.class);
        Product product3 = Mockito.mock(Product.class);
    }
}
