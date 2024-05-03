package de.haevn.utils;


import java.util.Arrays;

/**
 * The AppLauncher class is used to launch an application.
 * The class is abstract and needs to be extended.
 *
 * @author haevn
 * @version 1.0
 * @since 1.1
 * @deprecated This class is deprecated and will be removed in a future release.
 */
@Deprecated(since = "1.1", forRemoval = true)
public abstract class AppLauncher {
    private static final String VERSION = "1.1";
    private static boolean debugMode = false;
    private static String appName = "";
    private boolean launched = false;

    protected AppLauncher(final String name, final String... args) {
        this(name, null, args);
    }

    protected AppLauncher(final String name, final Object project, final String... args) {
        AppLauncher.appName = name;

        setup();
        launched = true;
        Runtime.getRuntime().addShutdownHook(new Thread(this::onShutdown));

        start(args);
    }

    public static boolean isDebugMode() {
        return debugMode;
    }

    public static String getAppName() {
        return appName;
    }


    public static String getVersion() {
        return VERSION;
    }

    /**
     * Exit the application with code 0.
     *
     * @version 1.1
     * @see #exitApplication(int)
     * @since 1.1
     */
    public static void exitApplication() {
        exitApplication(0);
    }

    /**
     * Exit the application with the given code.
     *
     * @param code The exit code.
     * @version 1.1
     * @see #exitApplication()
     * @since 1.1
     */
    public static void exitApplication(final int code) {
        if (isDebugMode()) {
    //        MethodTools.getMethod(2).ifPresent(method -> java.lang.System.err.println("Exit with code " + code + " in " + method));
        }
        java.lang.System.exit(code);
    }

    /**
     * Starts the application.
     *
     * @param args The arguments.
     */
    private void start(final String... args) {
        Arrays.stream(args).filter(arg -> arg.equalsIgnoreCase("--repair")).findFirst().ifPresent(arg -> repair());
        Arrays.stream(args).filter(arg -> arg.equalsIgnoreCase("--debug")).findFirst().ifPresent(arg -> enableDebug());

        setup(args);
    }

    /**
     * Returns true if the application is launched.
     *
     * @return true if the application is launched.
     */
    public boolean isLaunched() {
        return launched;
    }

    /**
     * Called when the application is starting up.
     */
    public abstract void setup(final String... args);

    /**
     * Called when the application is shutting down.
     */
    public abstract void onShutdown();

    /**
     * Checks the integrity of the application.
     * default implementation returns true.
     *
     * @return true if the integrity check succeeded.
     */
    public boolean integrityCheck() {

        return true;
    }

    /**
     * Repairs the application.
     * default implementation does nothing.
     */
    public void repair() {

    }

    /**
     * Returns the title of the application.
     *
     * @return the title of the application.
     */
    public String getTitle() {
        return appName;
    }

    protected void enableDebug() {
        AppLauncher.debugMode = true;
    }


}
