package org.informatics.services.receipts.contracts;

import org.informatics.models.Receipt;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public interface ReceiptDeserialization {
    Set<Receipt> deserializeReceiptFile(File file) throws IOException;
}
