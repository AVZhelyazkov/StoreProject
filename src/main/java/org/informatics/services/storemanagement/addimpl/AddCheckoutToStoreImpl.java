package org.informatics.services.storemanagement.addimpl;

import lombok.RequiredArgsConstructor;
import org.informatics.models.RetailStore;
import org.informatics.models.StoreCheckoutMachine;
import org.informatics.services.storemanagement.contracts.AddCheckoutToStore;

@RequiredArgsConstructor
public class AddCheckoutToStoreImpl implements AddCheckoutToStore {
    private final RetailStore store;
    @Override
    public void addCheckoutToStore() {
        store.getCheckouts().put(store.getCheckouts().size(), new StoreCheckoutMachine());
    }
}
