package org.informatics.services.storemanagement;

import lombok.RequiredArgsConstructor;
import org.informatics.exceptions.CashoutOccupiedException;
import org.informatics.exceptions.NoSuchCashoutException;
import org.informatics.models.RetailStore;
import org.informatics.models.StaffPersonnel;
import org.informatics.services.storemanagement.interfaces.AddCheckoutOccupantInterface;

@RequiredArgsConstructor
public class AddCheckoutOccupantService implements AddCheckoutOccupantInterface {
    final private RetailStore store;
    @Override
    public void addCheckoutOccupant(StaffPersonnel personnel,final int checkoutId) {
        try {
            if (store.getCheckouts().length <= checkoutId || checkoutId < 0)
                throw new NoSuchCashoutException("Checkout with id " + checkoutId + " does not exist");
            if (store.getCheckouts()[checkoutId].getOccupant() == null)
                throw new CashoutOccupiedException("Checkout with id " + checkoutId + " is already occupied");

            store.getCheckouts()[checkoutId].setOccupant(personnel);
        } catch (NoSuchCashoutException | CashoutOccupiedException exception) {
            exception.printStackTrace();
        }
    }
}
