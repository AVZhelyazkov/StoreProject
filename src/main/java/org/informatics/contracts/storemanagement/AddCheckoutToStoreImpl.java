package org.informatics.contracts.storemanagement;

import lombok.RequiredArgsConstructor;
import org.informatics.contracts.storemanagement.contracts.AddCheckoutToStore;
import org.informatics.models.RetailStore;
import org.informatics.models.StoreCheckoutMachine;

@RequiredArgsConstructor
public class AddCheckoutToStoreImpl implements AddCheckoutToStore {
    private final RetailStore store;
    @Override
    public void addCheckoutToStore() {
        store.getCheckouts().addLast(new StoreCheckoutMachine());
    }
}
