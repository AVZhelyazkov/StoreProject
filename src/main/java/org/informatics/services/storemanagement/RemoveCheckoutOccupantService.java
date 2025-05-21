package org.informatics.services.storemanagement;

import lombok.NoArgsConstructor;
import org.informatics.models.RetailStore;
import org.informatics.services.storemanagement.interfaces.RemoveCheckoutOccupantInterface;

@NoArgsConstructor
public class RemoveCheckoutOccupantService implements RemoveCheckoutOccupantInterface {
    private RetailStore store;
    @Override
    public void removeCheckoutOccupant(final int checkoutId) {

    }
}
