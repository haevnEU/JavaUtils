package de.haevn.utils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;

public class Core {
    private static String APP_NAME = "";
    private static String VERSION = "";
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

    public static <R, T> R map(T value, Function<? super T, ? extends R> mapper){
        return mapper.apply(value);
    }

    public static <T> T mapRecord(T value, Function<? super T, ? extends T> mapper){
        return mapper.apply(value);
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

    public static void executeSecure(Runnable run){
        try {
            run.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setVersion(final String version){
        VERSION = version;
    }

    public static String getAppVersion() {
        return VERSION;
    }



    public static String getCurrentTime(){
        return getCurrentDateWithFormat("HH:mm:ss");
    }

    public static String getCurrentDate(){
        return getCurrentDateWithFormat("dd/MM/yyyy");
    }

    public static String getCurrentDateAndTime(){
        return getCurrentDateWithFormat("dd/MM/yyyy HH:mm:ss");
    }

    public static String getCurrentDateWithFormat(String format){
        return new SimpleDateFormat(format).format(new Date());
    }

    public static String[] getKeyValue(String key, String value){
        return new String[]{key, value};
    }



}
