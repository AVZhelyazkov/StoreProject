package org.informatics.services.storemanagement.contracts;

import org.informatics.models.RetailStore;

import java.io.File;

public interface DeserializeStore {
    RetailStore deserializeStore(File storeFile);
}
