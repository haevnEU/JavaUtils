package de.haevn.utils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class Core {
    private static String APP_NAME = "";
    private Core() {}

    public static void setAppName(String appName) {
        if(APP_NAME.isBlank()) {
            APP_NAME = appName;
        }
    }

    public static String getAppName() {
        return APP_NAME;
    }

    public static void copyText(String text) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new StringSelection(text), null);
    }

    public static void sleepSecond(long seconds) {
        sleepMillis(seconds * 1000);
    }

    public static void sleepMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
        }
    }

}
