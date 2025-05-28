package org.informatics.services.receipts.serialization;

import org.informatics.enums.ProductCategory;
import org.informatics.models.Product;
import org.informatics.models.Receipt;
import org.informatics.models.RetailStore;
import org.informatics.services.receipts.impl.ClearReceiptsCacheImpl;
import org.informatics.utils.FileStoragePaths;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReceiptSerializationIT {

    @Test
    void whenReceiptsAreSerializedAndDeserialized_returnsReceiptsAmount() throws IOException {
        RetailStore store = new RetailStore("Store2", 1);

        store.getCheckouts().get(0).setCachedReceipts(
                Set.of(
                        new Receipt(
                                UUID.randomUUID(),
                                List.of(
                                        new Product("Product1", ProductCategory.CONSUMABLE, LocalDate.now().plusDays(10), BigDecimal.valueOf(50))
                                ),
                                BigDecimal.valueOf(50)
                        ),
                        new Receipt(
                                UUID.randomUUID(),
                                List.of(
                                        new Product("Product2", ProductCategory.NON_CONSUMABLE, LocalDate.now().plusDays(6), BigDecimal.valueOf(34))
                                ),
                                BigDecimal.valueOf(34)
                        )
                ));

        ClearReceiptsCacheImpl clearCache = new ClearReceiptsCacheImpl(store);

        ReceiptSerializationImpl serializer = new ReceiptSerializationImpl(store, clearCache);
        ReceiptDeserializationImpl deserializer = new ReceiptDeserializationImpl();

        serializer.saveReceiptsInDailyFile(false);
        File file = new File(String.format(FileStoragePaths.getReceiptsStoragePath(), LocalDate.now()));

        assertTrue(file.exists(), "Receipt file should exist after serialization");

        Set<Receipt> receipts = deserializer.deserializeReceiptFile(file);

        int expectedSize = 2;

        assertEquals(expectedSize, receipts.size());
    }
}