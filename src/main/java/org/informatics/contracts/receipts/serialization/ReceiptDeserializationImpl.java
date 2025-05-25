package org.informatics.contracts.receipts.serialization;

import org.informatics.contracts.receipts.contracts.ReceiptDeserialization;
import org.informatics.models.Receipt;
import org.informatics.utils.ReceiptsStoragePath;

import java.io.*;
import java.time.LocalDate;
import java.util.Set;

@SuppressWarnings("all")
public class ReceiptDeserializationImpl implements ReceiptDeserialization {

    @Override
    public Set<Receipt> deserializeReceiptFile(LocalDate dateOfFile) throws IOException {
        File file = new File(
                String.format(ReceiptsStoragePath.getReceiptsStoragePath(), dateOfFile)
        );
        if (!file.exists())
            throw new FileNotFoundException("File was not found.");

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();

            if (obj instanceof Set<?>)
                return (Set<Receipt>) obj;
            else
                throw new IOException("Invalid data format in file.");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Class not found during deserialization", e);
        }
    }
}
