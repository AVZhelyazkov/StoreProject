package org.informatics.services.receipts.impl;

import lombok.RequiredArgsConstructor;
import org.informatics.exceptions.NoCashoutOccupantException;
import org.informatics.models.Receipt;
import org.informatics.models.RetailStore;
import org.informatics.models.StoreCheckoutMachine;
import org.informatics.services.products.ProductsTotalCalcImpl;
import org.informatics.services.receipts.contracts.ReceiptGeneration;
import org.informatics.utils.ErrorMessagesManager;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
public class ReceiptGenerationImpl implements ReceiptGeneration {
    final private RetailStore store;
    final private ProductsTotalCalcImpl productsTotalCalc;

    @Override
    public void processReceipt(int checkoutId) throws NoCashoutOccupantException {
        StoreCheckoutMachine checkout = store.getCheckouts().get(checkoutId);

        if (checkout == null)
            throw new NoSuchElementException(
                    ErrorMessagesManager.getErrorMessage("error.checkout_not_found", checkoutId));
        else if (checkout.getOccupant() == null)
            throw new NoCashoutOccupantException(
                    ErrorMessagesManager.getErrorMessage("error.no_checkout_occupant"));

        Receipt receipt = new Receipt(
                checkout.getOccupant().getStaffId(),
                checkout.getScannedProducts().keySet().stream().toList(),
                productsTotalCalc.calculateTotalWithDiscount(checkoutId));

        checkout.addReceipt(receipt);
    }
}
