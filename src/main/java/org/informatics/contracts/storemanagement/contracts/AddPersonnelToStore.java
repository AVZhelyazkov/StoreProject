package org.informatics.contracts.storemanagement.contracts;

import lombok.RequiredArgsConstructor;
import org.informatics.models.RetailStore;
import org.informatics.models.StaffPersonnel;

import java.util.UUID;

public interface AddPersonnelToStore {
    boolean addPersonnelToStore(StaffPersonnel personnel);
}
