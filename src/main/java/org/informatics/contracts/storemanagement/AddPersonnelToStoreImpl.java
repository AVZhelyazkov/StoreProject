package org.informatics.contracts.storemanagement;

import lombok.RequiredArgsConstructor;
import org.informatics.contracts.storemanagement.contracts.AddPersonnelToStore;
import org.informatics.models.RetailStore;
import org.informatics.models.StaffPersonnel;

@RequiredArgsConstructor
public class AddPersonnelToStoreImpl implements AddPersonnelToStore {
    private final RetailStore store;
    @Override
    public boolean addPersonnelToStore(StaffPersonnel personnel) {
        return store.getPersonnel().add(personnel);
    }
}
