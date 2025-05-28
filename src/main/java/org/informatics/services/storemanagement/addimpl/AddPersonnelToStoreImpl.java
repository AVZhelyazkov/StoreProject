package org.informatics.services.storemanagement.addimpl;

import lombok.RequiredArgsConstructor;
import org.informatics.models.RetailStore;
import org.informatics.models.StaffPersonnel;
import org.informatics.services.storemanagement.contracts.AddPersonnelToStore;

@RequiredArgsConstructor
public class AddPersonnelToStoreImpl implements AddPersonnelToStore {
    private final RetailStore store;
    @Override
    public boolean addPersonnelToStore(StaffPersonnel personnel) {
        return store.getPersonnel().add(personnel);
    }
}
