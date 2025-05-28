package org.informatics.services.storemanagement.remvimpl;

import lombok.RequiredArgsConstructor;
import org.informatics.exceptions.NoCashoutOccupantException;
import org.informatics.exceptions.NoSuchCashoutException;
import org.informatics.models.RetailStore;
import org.informatics.models.StoreCheckoutMachine;
import org.informatics.services.storemanagement.contracts.RemoveCheckoutOccupant;
import org.informatics.utils.ErrorMessagesManager;

@RequiredArgsConstructor
public class RemoveCheckoutOccupantImpl implements RemoveCheckoutOccupant {
    private final RetailStore store;
    @Override
    public void removeCheckoutOccupant(final int checkoutId) {
        StoreCheckoutMachine checkout = store.getCheckouts().get(checkoutId);

        try {
            if (store.getCheckouts().size() <= checkoutId || checkoutId < 0)
                throw new NoSuchCashoutException(
                        ErrorMessagesManager.getErrorMessage("error.checkout_not_found", checkoutId)
                );
            if (checkout.getOccupant() == null)
                throw new NoCashoutOccupantException(
                        ErrorMessagesManager.getErrorMessage("error.no_checkout_occupant", checkoutId)
                );

            checkout.setOccupant(null);
        } catch (NoCashoutOccupantException | NoSuchCashoutException exception) {
            exception.printStackTrace();
        }
    }
}
