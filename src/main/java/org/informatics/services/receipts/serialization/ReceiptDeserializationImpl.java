package org.informatics.services.receipts.serialization;

import org.informatics.models.Receipt;
import org.informatics.services.receipts.contracts.ReceiptDeserialization;

import java.io.*;
import java.util.Set;

@SuppressWarnings("all")
public class ReceiptDeserializationImpl implements ReceiptDeserialization {

    @Override
    public Set<Receipt> deserializeReceiptFile(File file) throws IOException {
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
