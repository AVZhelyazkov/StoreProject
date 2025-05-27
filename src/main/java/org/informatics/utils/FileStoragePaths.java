package org.informatics.utils;

public class FileStoragePaths {
    private static final String RECEIPTS_STORAGE_PATH = "receipts/%s.ser";
    private static final String STORES_STORAGE_PATH = "stores/%s.ser";

    public static String getReceiptsStoragePath() {
        return RECEIPTS_STORAGE_PATH;
    }

    public static String getStoresPath() {
        return STORES_STORAGE_PATH;
    }
}
