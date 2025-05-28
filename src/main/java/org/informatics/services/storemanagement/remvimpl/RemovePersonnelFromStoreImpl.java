package org.informatics.services.storemanagement.remvimpl;

import lombok.RequiredArgsConstructor;
import org.informatics.models.RetailStore;
import org.informatics.models.StaffPersonnel;
import org.informatics.services.storemanagement.contracts.RemovePersonnelFromStore;

@RequiredArgsConstructor
public class RemovePersonnelFromStoreImpl implements RemovePersonnelFromStore {
    private final RetailStore store;
    @Override
    public boolean removePersonnelFromStore(StaffPersonnel personnel) {
        return store.getPersonnel().remove(personnel);
    }
}
