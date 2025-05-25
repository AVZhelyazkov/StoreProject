package org.informatics.contracts.receipts.impl;

import lombok.RequiredArgsConstructor;
import org.informatics.models.RetailStore;
import org.informatics.contracts.receipts.contracts.ReceiptGeneration;

@RequiredArgsConstructor
public class ReceiptGenerationImpl implements ReceiptGeneration {
    final private RetailStore store;

    @Override
    public void processReceipt() {

    }
}
