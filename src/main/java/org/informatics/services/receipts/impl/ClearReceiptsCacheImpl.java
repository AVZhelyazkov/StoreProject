package org.informatics.services.receipts.impl;

import lombok.RequiredArgsConstructor;
import org.informatics.models.RetailStore;
import org.informatics.services.receipts.contracts.ClearReceiptsCache;

@RequiredArgsConstructor
public class ClearReceiptsCacheImpl implements ClearReceiptsCache {
    private final RetailStore store;
    @Override
    public void clearCheckoutReceipts() {
        store.getCheckouts().values()
                .forEach(storeCheckoutMachine -> storeCheckoutMachine.setCachedReceipts(null));
    }
}
