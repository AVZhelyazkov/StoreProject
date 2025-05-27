package org.informatics.contracts.storemanagement.contracts;

import org.informatics.models.StaffPersonnel;

import java.util.UUID;

public interface RemovePersonnelFromStore {
    boolean removePersonnelFromStore(StaffPersonnel personnel);
}
