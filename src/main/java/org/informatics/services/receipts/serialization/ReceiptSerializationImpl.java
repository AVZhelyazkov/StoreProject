package org.informatics.services.receipts.serialization;

import lombok.RequiredArgsConstructor;
import org.informatics.models.Receipt;
import org.informatics.models.RetailStore;
import org.informatics.services.receipts.contracts.ClearReceiptsCache;
import org.informatics.services.receipts.contracts.ReceiptSerialization;
import org.informatics.utils.FileStoragePaths;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class ReceiptSerializationImpl implements ReceiptSerialization {
    private final RetailStore store;
    private final ClearReceiptsCache clearReceiptsCache;
    @Override
    public void saveReceiptsInDailyFile(boolean shouldClearCache) {
        LocalDate date = LocalDate.now();
        File file = new File(
                String.format(FileStoragePaths.getReceiptsStoragePath(), date)
        );
        file.getParentFile().mkdirs();

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {

            Set<Receipt> allReceipts = new HashSet<>();

            store.getCheckouts().values()
                    .forEach(checkout -> allReceipts.addAll(checkout.getCachedReceipts()));

            oos.writeObject(allReceipts);

            if (shouldClearCache)
                clearReceiptsCache.clearCheckoutReceipts();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
