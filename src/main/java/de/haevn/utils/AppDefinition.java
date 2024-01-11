package de.haevn.utils;

public class AppDefinition {
    private static String appName = "";
    private static long appID = 0;

    @Deprecated(since = "1.1", forRemoval = true)
    public static void initialize(final String appName){
        initialize(appName, 0);
    }
    public static void initialize(final String appName, final long appID){
        AppDefinition.appName = appName;
        if(appID > 0xFFL) throw new IllegalArgumentException("Invalid AppID. Please contact the developer for a unique AppID, for testing use value 0");
        AppDefinition.appID = appID;
    }

    public static String getAppName() {
        return appName;
    }

    public static long getAppID() {
        return appID;
    }

    private AppDefinition(){}
}
