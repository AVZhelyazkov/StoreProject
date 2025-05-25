package org.informatics.contracts.storemanagement;

import lombok.RequiredArgsConstructor;
import org.informatics.exceptions.CashoutOccupiedException;
import org.informatics.exceptions.NoSuchCashoutException;
import org.informatics.models.RetailStore;
import org.informatics.models.StaffPersonnel;
import org.informatics.contracts.storemanagement.contracts.AddCheckoutOccupant;

@RequiredArgsConstructor
public class AddCheckoutOccupantImpl implements AddCheckoutOccupant {
    final private RetailStore store;
    @Override
    public void addCheckoutOccupant(StaffPersonnel personnel,final int checkoutId) {
        try {
            if (store.getCheckouts().size() <= checkoutId || checkoutId < 0)
                throw new NoSuchCashoutException("Checkout with id " + checkoutId + " does not exist");
            if (store.getCheckouts().get(checkoutId).getOccupant() == null)
                throw new CashoutOccupiedException("Checkout with id " + checkoutId + " is already occupied");

            store.getCheckouts().get(checkoutId).setOccupant(personnel);
        } catch (NoSuchCashoutException | CashoutOccupiedException exception) {
            exception.printStackTrace();
        }
    }
}
