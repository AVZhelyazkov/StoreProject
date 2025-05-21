package org.informatics.services.storemanagement;

import lombok.NoArgsConstructor;
import org.informatics.models.RetailStore;
import org.informatics.models.StaffPersonnel;
import org.informatics.services.storemanagement.interfaces.AddCheckoutOccupantInterface;

@NoArgsConstructor
public class AddCheckoutOccupantService implements AddCheckoutOccupantInterface {
    private RetailStore store;
    @Override
    public void addCheckoutOccupant(StaffPersonnel personnel,final int checkoutId) {

    }
}
