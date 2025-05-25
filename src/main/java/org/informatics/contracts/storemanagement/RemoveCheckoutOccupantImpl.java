package org.informatics.contracts.storemanagement;

import lombok.NoArgsConstructor;
import org.informatics.exceptions.NoCashoutOccupantException;
import org.informatics.exceptions.NoSuchCashoutException;
import org.informatics.models.RetailStore;
import org.informatics.contracts.storemanagement.contracts.RemoveCheckoutOccupant;
import org.informatics.utils.ErrorMessagesManager;

@NoArgsConstructor
public class RemoveCheckoutOccupantImpl implements RemoveCheckoutOccupant {
    private RetailStore store;
    @Override
    public void removeCheckoutOccupant(final int checkoutId) {
        try {
            if (store.getCheckouts().size() <= checkoutId || checkoutId < 0)
                throw new NoSuchCashoutException(
                        ErrorMessagesManager.getErrorMessage("error.checkout_not_found", checkoutId)
                );
            if (store.getCheckouts().get(checkoutId).getOccupant() == null)
                throw new NoCashoutOccupantException(
                        ErrorMessagesManager.getErrorMessage("error.no_checkout_occupant", checkoutId)
                );

            store.getCheckouts().get(checkoutId).setOccupant(null);
        } catch (NoCashoutOccupantException | NoSuchCashoutException exception) {
            exception.printStackTrace();
        }
    }
}
