package de.haevn.utils.system;

/**
 * <h1>User</h1>
 * <p>This class provides information about the user.</p>
 *
 * @author haevn
 * @version 1.1
 * @since 1.1
 */
public final class User {
    private User() {
    }

    /**
     * <h2>getUserName()</h2>
     * <p>Gets the user name using the {@link System#getProperty(String)} with user.name</p>
     *
     * @return Name of the user.
     */
    public static String getUserName() {
        return System.getProperty("user.name");
    }

    /**
     * <h2>getUserHome()</h2>
     * <p>Gets the user home directory using the {@link System#getProperty(String)} with user.home</p>
     *
     * @return Home directory of the user.
     */
    public static String getUserHome() {
        return System.getProperty("user.home");
    }

    /**
     * <h2>getUserDir()</h2>
     * <p>Gets the user directory using the {@link System#getProperty(String)} with user.dir</p>
     *
     * @return Directory of the user.
     */
    public static String getUserDir() {
        return System.getProperty("user.dir");
    }


}
