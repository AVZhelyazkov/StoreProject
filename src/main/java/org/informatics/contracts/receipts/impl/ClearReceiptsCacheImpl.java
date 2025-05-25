package org.informatics.contracts.receipts.impl;

import lombok.RequiredArgsConstructor;
import org.informatics.models.RetailStore;
import org.informatics.contracts.receipts.contracts.ClearReceiptsCache;

@RequiredArgsConstructor
public class ClearReceiptsCacheImpl implements ClearReceiptsCache {
    private final RetailStore store;
    @Override
    public void clearCheckoutReceipts() {
        store.getCheckouts()
                .forEach(storeCheckoutMachine -> storeCheckoutMachine.setCachedReceipts(null));
    }
}
