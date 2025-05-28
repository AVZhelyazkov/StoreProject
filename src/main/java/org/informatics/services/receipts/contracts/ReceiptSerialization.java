package org.informatics.services.receipts.contracts;

public interface ReceiptSerialization {
    void saveReceiptsInDailyFile(boolean shouldClearCache);

}
