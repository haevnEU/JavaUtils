package de.haevn.utils.system;

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
