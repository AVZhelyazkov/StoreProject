package org.informatics.services.clientcheckout;

import lombok.RequiredArgsConstructor;
import org.informatics.models.RetailStore;
import org.informatics.services.clientcheckout.interfaces.ReceiptGenerationInterface;

@RequiredArgsConstructor
public class ReceiptGenerationService implements ReceiptGenerationInterface {
    final private RetailStore store;

    @Override
    public void processReceipt() {

    }
}
