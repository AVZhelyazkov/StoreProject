package org.informatics.contracts.storemanagement.serialization;

import org.informatics.contracts.storemanagement.contracts.SerializeStore;
import org.informatics.models.RetailStore;
import org.informatics.utils.FileStoragePaths;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.FileAlreadyExistsException;

public class SerializeStoreImpl implements SerializeStore {

    @Override
    public void serializeStore(final RetailStore store,final boolean shouldOverwrite) throws FileAlreadyExistsException {
        File file = new File(
                String.format(FileStoragePaths.getStoresPath(), store.getStoreName().toLowerCase())
        );
        file.getParentFile().mkdirs();

        if (!shouldOverwrite && file.exists())
            throw new FileAlreadyExistsException("File already exists. Overwriting is denied.");

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(store);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
