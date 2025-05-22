package org.informatics.services.storemanagement;

import lombok.NoArgsConstructor;
import org.informatics.exceptions.NoCashoutOccupantException;
import org.informatics.exceptions.NoSuchCashoutException;
import org.informatics.models.RetailStore;
import org.informatics.services.storemanagement.interfaces.RemoveCheckoutOccupantInterface;
import org.informatics.utils.ErrorMessagesManager;

@NoArgsConstructor
public class RemoveCheckoutOccupantService implements RemoveCheckoutOccupantInterface {
    private RetailStore store;
    @Override
    public void removeCheckoutOccupant(final int checkoutId) {
        try {
            if (store.getCheckouts().length <= checkoutId || checkoutId < 0)
                throw new NoSuchCashoutException(
                        ErrorMessagesManager.getErrorMessage("error.checkout_not_found", checkoutId)
                );
            if (store.getCheckouts()[checkoutId].getOccupant() == null)
                throw new NoCashoutOccupantException(
                        ErrorMessagesManager.getErrorMessage("error.no_checkout_occupant", checkoutId)
                );

            store.getCheckouts()[checkoutId].setOccupant(null);
        } catch (NoCashoutOccupantException | NoSuchCashoutException exception) {
            exception.printStackTrace();
        }
    }
}
