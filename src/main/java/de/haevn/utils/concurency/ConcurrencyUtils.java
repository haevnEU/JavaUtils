package de.haevn.utils.concurency;

import de.haevn.utils.logging.Logger;

import java.util.concurrent.Callable;

public final class ConcurrencyUtils {
    private static final Logger LOGGER = new Logger(ConcurrencyUtils.class);

    private ConcurrencyUtils() {
    }

    private static final Object LOCK = new Object();


    public static void runConcurrent(final Runnable runnable, final String name) {
        synchronized (LOCK) {
            LOGGER.atInfo().withThreadName().withMessage("Running concurrent task %s", name).log();
            runnable.run();
            LOGGER.atInfo().withThreadName().withMessage("Finished concurrent task %s", name).log();
        }
    }

    public static <T> T runConcurrent(final Callable<T> callable, final String name) {
        T result = null;
        synchronized (LOCK) {
            LOGGER.atInfo().withThreadName().withMessage("Running concurrent task %s", name).log();
            try {
                result = callable.call();
                LOGGER.atInfo().withThreadName().withMessage("Finished concurrent task %s", name).log();
            } catch (Exception e) {
                LOGGER.atError().withException(e).withMessage("Error while running concurrent task %s", name).log();
            }
        }
        return result;
    }
}
