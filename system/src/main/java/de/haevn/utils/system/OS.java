package de.haevn.utils.system;

/**
 * <h1>OS</h1>
 * <p>This class provides information about the operating system.</p>
 *
 * @author haevn
 * @version 1.1
 * @since 1.1
 */
public final class OS {
    private OS() {
    }

    /**
     * <h2>getName()</h2>
     * <p>Gets the name of the operating system using the {@link System#getProperty(String)} with os.name</p>
     *
     * @return Name of the operating system.
     */
    public static String getName() {
        return System.getProperty("os.name");
    }

    /**
     * <h2>getVersion()</h2>
     * <p>Gets the version of the operating system using the {@link System#getProperty(String)} with os.version</p>
     *
     * @return Version of the operating system.
     */
    public static String getVersion() {
        return System.getProperty("os.version");
    }

    /**
     * <h2>getArch()</h2>
     * <p>Gets the architecture of the operating system using the {@link System#getProperty(String)} with os.arch</p>
     *
     * @return Architecture of the operating system.
     */
    public static String getArch() {
        return System.getProperty("os.arch");
    }

    /**
     * <h2>getVendor()</h2>
     * <p>Gets the vendor of the operating system using the {@link System#getProperty(String)} with os.vendor</p>
     *
     * @return Vendor of the operating system.
     */
    public static String getVendor() {
        return System.getProperty("os.vendor");
    }

    /**
     * <h2>isWindows()</h2>
     * <p>Checks if the operating system is Windows.</p>
     *
     * @return True iff the operating system is Windows.
     */
    public static boolean isWindows() {
        return getName().startsWith("Win");
    }

    /**
     * <h2>isMac()</h2>
     * <p>Checks if the operating system is Mac.</p>
     *
     * @return True iff the operating system is Mac.
     */
    public static boolean isMac() {
        return getName().startsWith("Mac");
    }

    /**
     * <h2>isLinux()</h2>
     * <p>Checks if the operating system is Linux.</p>
     *
     * @return True iff the operating system is Linux.
     */
    public static boolean isLinux() {
        return getName().startsWith("Linux");
    }

    /**
     * <h2>isUnix()</h2>
     * <p>Checks if the operating system is Unix.</p>
     *
     * @return True iff the operating system is Unix.
     */
    public static boolean isUnix() {
        return getName().startsWith("Unix");
    }

    /**
     * <h2>is(String)</h2>
     * <p>Checks if the operating system is the given one.</p>
     *
     * @param osName The name of the operating system.
     * @return True iff the operating system is the given one.
     */
    public static boolean is(final String osName) {
        return getName().startsWith(osName);
    }
}
