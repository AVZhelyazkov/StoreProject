package org.informatics.utils;

public class ReceiptsStoragePath {
    private static final String RECEIPTS_STORAGE_PATH = "receipts/%s.ser";

    public static String getReceiptsStoragePath() {
        return RECEIPTS_STORAGE_PATH;
    }
}
