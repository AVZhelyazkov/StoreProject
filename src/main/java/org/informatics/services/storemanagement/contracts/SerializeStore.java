package org.informatics.services.storemanagement.contracts;

import org.informatics.models.RetailStore;

import java.nio.file.FileAlreadyExistsException;

public interface SerializeStore {
    void serializeStore(final RetailStore store, boolean shouldOverwrite) throws FileAlreadyExistsException;
}
