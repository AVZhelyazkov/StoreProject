package org.informatics.services.receipts.contracts;

import org.informatics.exceptions.NoCashoutOccupantException;

public interface ReceiptGeneration {
    void processReceipt(int checkoutId) throws NoCashoutOccupantException;
}
