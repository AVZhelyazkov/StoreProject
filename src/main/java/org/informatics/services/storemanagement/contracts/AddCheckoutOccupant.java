package org.informatics.services.storemanagement.contracts;

import org.informatics.models.StaffPersonnel;

public interface AddCheckoutOccupant {
    void addCheckoutOccupant(StaffPersonnel personnel, int storeId);
}
