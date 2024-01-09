package de.haevn.utils;

public class AppDefinition {
    private static String appName = "";
    public static void initialize(final String appName){
        AppDefinition.appName = appName;
    }

    public static String getAppName() {
        return appName;
    }

    private AppDefinition(){}
}
