package org.informatics.services;

public class ProductIdentificationService {
    private static long lastGeneratedId = 0;

    public static long generateProductId() {
        return ++lastGeneratedId;
    }
}
