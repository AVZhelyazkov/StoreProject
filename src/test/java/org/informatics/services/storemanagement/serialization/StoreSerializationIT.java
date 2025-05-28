package org.informatics.services.storemanagement.serialization;

import org.informatics.enums.ProductCategory;
import org.informatics.models.RetailStore;
import org.informatics.services.storemanagement.contracts.SerializeStore;
import org.informatics.utils.FileStoragePaths;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.FileAlreadyExistsException;

import static org.junit.jupiter.api.Assertions.*;

public class StoreSerializationIT {

    private RetailStore store;
    private String storeName;
    private int checkoutsAmount;

    @BeforeEach
    void setUp() {
        storeName = "Store1";
        checkoutsAmount = 2;

        store = new RetailStore(storeName, checkoutsAmount);

        store.setCategoryMarkupPercent(ProductCategory.CONSUMABLE, BigDecimal.valueOf(10.00));
        store.setCategoryMarkupPercent(ProductCategory.NON_CONSUMABLE, BigDecimal.valueOf(25.00));
    }

    @Test
    @Order(1)
    void whenStoreIsSerializedAndDeserialized_thenDataIsRetained() throws IOException {
        SerializeStoreImpl serializer = new SerializeStoreImpl();
        serializer.serializeStore(store, true);

        File file = new File(String.format(FileStoragePaths.getStoresPath(), store.getStoreName()));

        assertTrue(file.exists(), "Store file does not exist before serialization.");

        DeserializeStoreImpl deserializer = new DeserializeStoreImpl();
        RetailStore deserializedStore = deserializer.deserializeStore(file);

        assertEquals(store.getStoreName(), deserializedStore.getStoreName(), "Data is not retained correctly after deserialization.");
    }

    @Test
    @Order(2)
    void whenStoreDeserialized_thenCategoryMarkupIsRetained() {
        BigDecimal expected1 = BigDecimal.valueOf(10.00);
        BigDecimal expected2 = BigDecimal.valueOf(25.00);

        BigDecimal actual1 = store.getCategoryMarkupPercent().get(ProductCategory.CONSUMABLE);
        BigDecimal actual2 = store.getCategoryMarkupPercent().get(ProductCategory.NON_CONSUMABLE);

        assertEquals(expected1.stripTrailingZeros(), actual1.stripTrailingZeros());
        assertEquals(expected2.stripTrailingZeros(), actual2.stripTrailingZeros());
    }

    @Test
    @Order(3)
    void whenStoreDeserialized_thenCheckoutCountIsCorrect() {
        int expectedCheckouts = 2;

        assertEquals(expectedCheckouts, store.getCheckouts().size(), "Checkout count mismatch after deserialization.");
    }

    @Test
    @Order(4)
    void whenStoreSerializedWithoutOverwrite_throwsFileAlreadyExistsException() {
        SerializeStore serializer = new SerializeStoreImpl();
        assertThrows(
                FileAlreadyExistsException.class,
                () -> serializer.serializeStore(store, false));
    }
}
