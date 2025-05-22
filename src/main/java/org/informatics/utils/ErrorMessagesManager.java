package org.informatics.utils;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class ErrorMessagesManager {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("error_messages");

    public static String getErrorMessage(String key, Object... args) {
        String pattern = bundle.getString(key);
        return MessageFormat.format(pattern, args);
    }
}
