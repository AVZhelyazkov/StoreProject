package org.informatics.contracts.receipts.serialization;

import lombok.RequiredArgsConstructor;
import org.informatics.contracts.receipts.contracts.ClearReceiptsCache;
import org.informatics.models.Receipt;
import org.informatics.models.RetailStore;
import org.informatics.contracts.receipts.contracts.ReceiptSerialization;
import org.informatics.utils.ReceiptsStoragePath;

import java.io.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@RequiredArgsConstructor
public class ReceiptSerializationImpl implements ReceiptSerialization {
    private final RetailStore store;
    private final ClearReceiptsCache clearReceiptsCache;
    @Override
    public void saveReceiptsInDailyFile(boolean shouldClearCache) {
        LocalDate date = LocalDate.now();
        File file = new File(
                String.format(ReceiptsStoragePath.getReceiptsStoragePath(), date)
        );
        file.getParentFile().mkdirs();

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {

            Set<Receipt> allReceipts = new HashSet<>();

            oos.writeObject(allReceipts);

            store.getCheckouts()
                    .forEach(checkout -> allReceipts.addAll(checkout.getCachedReceipts()));

            if (shouldClearCache)
                clearReceiptsCache.clearCheckoutReceipts();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
