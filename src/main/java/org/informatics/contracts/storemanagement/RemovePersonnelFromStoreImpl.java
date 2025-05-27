package org.informatics.contracts.storemanagement;

import lombok.RequiredArgsConstructor;
import org.informatics.contracts.storemanagement.contracts.RemovePersonnelFromStore;
import org.informatics.models.RetailStore;
import org.informatics.models.StaffPersonnel;

@RequiredArgsConstructor
public class RemovePersonnelFromStoreImpl implements RemovePersonnelFromStore {
    private final RetailStore store;
    @Override
    public boolean removePersonnelFromStore(StaffPersonnel personnel) {
        return store.getPersonnel().remove(personnel);
    }
}
