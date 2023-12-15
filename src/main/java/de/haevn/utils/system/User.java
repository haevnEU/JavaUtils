package de.haevn.utils.system;

/**
 * A simple class for OS information.
 * @version 1.1
 * @since 1.1
 * @author haevn
 */
public class User {
    private User(){}


    public static String getUserName(){
        return System.getProperty("user.name");
    }

    public static String getUserHome(){
        return System.getProperty("user.home");
    }

    public static String getUserDir(){
        return System.getProperty("user.dir");
    }


}
