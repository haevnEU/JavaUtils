package de.haevn.utils;

import de.haevn.utils.exceptions.ErrorCode;
import de.haevn.utils.logging.Logger;

import java.util.Arrays;

/**
 * The AppLauncher class is used to launch an application.
 * The class is abstract and needs to be extended.
 *
 * @author haevn
 * @version 1.0
 * @since 1.1
 */
public abstract class AppLauncher {
    protected static final Logger LOGGER = new Logger(AppLauncher.class);
    private boolean launched = false;

    protected AppLauncher(final String name, final String... args) {
        this(name, ErrorCode.Project.UNKNOWN, args);
    }

    protected AppLauncher(final String name, final ErrorCode.Project project, final String... args) {
        AppDefinition.initialize(name, project, args);
        LOGGER.atInfo().withMessage("Start application").log();
        setup();
        launched = true;
        Runtime.getRuntime().addShutdownHook(new Thread(this::onShutdown));

        start(args);
    }

    /**
     * Starts the application.
     *
     * @param args The arguments.
     */
    private void start(final String... args) {
        Arrays.stream(args).filter(arg -> arg.equalsIgnoreCase("--repair")).findFirst().ifPresent(arg -> repair());

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
        LOGGER.atDebug().withMessage("Not implemented integrity check is called").log();
        return true;
    }

    /**
     * Repairs the application.
     * default implementation does nothing.
     */
    public void repair() {
        LOGGER.atDebug().withMessage("Not implemented repair is called").log();
    }

    /**
     * Returns the title of the application.
     *
     * @return the title of the application.
     */
    public String getTitle() {
        return AppDefinition.getAppName();
    }
}
