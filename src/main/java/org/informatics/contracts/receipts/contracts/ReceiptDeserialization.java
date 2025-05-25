package org.informatics.contracts.receipts.contracts;

import org.informatics.models.Receipt;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;

public interface ReceiptDeserialization {
    Set<Receipt> deserializeReceiptFile(LocalDate dateOfFile) throws IOException;
}
