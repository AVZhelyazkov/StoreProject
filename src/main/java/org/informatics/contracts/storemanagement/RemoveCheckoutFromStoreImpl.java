package org.informatics.contracts.storemanagement;

import lombok.RequiredArgsConstructor;
import org.informatics.contracts.storemanagement.contracts.RemoveCheckoutFromStore;
import org.informatics.models.RetailStore;

@RequiredArgsConstructor
public class RemoveCheckoutFromStoreImpl implements RemoveCheckoutFromStore {
    final private RetailStore store;
    @Override
    public void removeCheckoutFromStore() {
        store.getCheckouts().removeFirst();
    }
}
