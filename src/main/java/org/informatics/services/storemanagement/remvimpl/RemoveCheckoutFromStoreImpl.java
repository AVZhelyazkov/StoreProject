package org.informatics.services.storemanagement.remvimpl;

import lombok.RequiredArgsConstructor;
import org.informatics.models.RetailStore;
import org.informatics.services.storemanagement.contracts.RemoveCheckoutFromStore;

@RequiredArgsConstructor
public class RemoveCheckoutFromStoreImpl implements RemoveCheckoutFromStore {
    final private RetailStore store;
    @Override
    public void removeCheckoutFromStore(int checkoutId) {
        store.getCheckouts().remove(checkoutId);
    }
}
