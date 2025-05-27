package org.informatics.contracts.storemanagement.serialization;

import org.informatics.contracts.storemanagement.contracts.DeserializeStore;
import org.informatics.models.RetailStore;

import java.io.*;

public class DeserializeStoreImpl implements DeserializeStore {
    @Override
    public RetailStore deserializeStore(File storeFile) {
        if (storeFile == null)
            throw new NullPointerException("Store file cannot be null");
        if (!storeFile.exists())
            return null;

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(storeFile))) {
            Object object = ois.readObject();
            if(object instanceof RetailStore)
                return (RetailStore) object;
            else
                throw new InvalidObjectException("Object is not of type RetailStore.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
