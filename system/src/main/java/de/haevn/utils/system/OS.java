package de.haevn.utils.system;

/**
 * A simple class for OS information.
 * @version 1.1
 * @since 1.1
 * @author haevn
 */
public class OS {
    private OS(){}

    public static String getName() {
        return System.getProperty("os.name");
    }

    public static String getVersion() {
        return System.getProperty("os.version");
    }

    public static String getArch() {
        return System.getProperty("os.arch");
    }

    public static String getVendor() {
        return System.getProperty("os.vendor");
    }



    public static boolean isWindows(){
        return getName().startsWith("Windows");
    }

    public static boolean isMac(){
        return getName().startsWith("Mac");
    }

    public static boolean isLinux(){
        return getName().startsWith("Linux");
    }

    public static boolean isUnix(){
        return getName().startsWith("Unix");
    }
}
