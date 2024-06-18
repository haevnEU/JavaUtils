package de.haevn.utils.concurrency;

import de.haevn.utils.logging.Logger;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * This class provides a simple way to execute tasks in the background.
 * It uses a {@link ScheduledExecutorService} to execute the tasks.
 * Accessing via BackgroundWorker.getInstance() will return a singleton instance with 70% of the available processors.
 * Accessing via BackgroundWorker.getInstance(int) will return a singleton instance with the given amount of threads.
 * <br>
 * <b>Example</b>
 * <pre>
 * {@code
 * BackgroundWorker.getInstance().submit(() -> System.out.println("Hello World"), "HelloWorld", 1, TimeUnit.SECONDS);
 * }
 * </pre>
 *
 * @author haevn
 * @version 1.0
 * @since 1.0
 */
public class BackgroundWorker {

    private static BackgroundWorker instance;

    /**
     * Initialize the singleton instance with the given amount of threads, e.g. 70% of the available processors
     *
     * @param amountThreads the amount of threads
     * @return the singleton instance
     */
    public static BackgroundWorker initialize(final int amountThreads) {
        instance = new BackgroundWorker(amountThreads);
        return instance;
    }

    /**
     * Get the singleton instance with the given amount of threads, e.g. 70% of the available processors
     *
     * @param amountThreads the amount of threads
     * @return the singleton instance
     */
    public static synchronized BackgroundWorker getInstance(final int amountThreads) {
        if (null == instance) {
            instance = new BackgroundWorker(amountThreads);
        }
        return instance;
    }

    /**
     * Get the singleton instance with 70% of the available processors
     *
     * @return the singleton instance
     */
    public static synchronized BackgroundWorker getInstance() {
        return getInstance((int) (Runtime.getRuntime().availableProcessors() * 0.7));
    }


    private final ScheduledExecutorService executor;

    /**
     * Creates a new BackgroundWorker with 70% of the available processors
     */
    public BackgroundWorker() {
        this((int) (Runtime.getRuntime().availableProcessors() * 0.7));
    }

    /**
     * Creates a new BackgroundWorker with the given amount of threads
     *
     * @param amountThreads the amount of threads
     */
    public BackgroundWorker(final int amountThreads) {
        executor = new BackgroundWorkerThreadService(amountThreads);
    }

    private static final Logger LOGGER = new Logger(BackgroundWorker.class);

    /**
     * Submits a task to the background worker.
     *
     * @param runnable the task
     * @param name     the name of the task
     * @param interval the interval in which the task should be executed
     * @param unit     the unit of the interval
     * @return a {@link ScheduledFuture} representing pending completion of the task
     */
    public ScheduledFuture<?> submit(final Runnable runnable, final String name, final int interval, final TimeUnit unit) {
        LOGGER.atInfo().withMessage("Submitting %s to background worker, interval is %s %s", name, interval, unit.toString()).log();
        return executor.scheduleAtFixedRate(runnable, 0, interval, unit);
    }

    /**
     * Submits a task to the background worker.
     *
     * @param runnable the task
     * @param name     the name of the task
     * @param delay    the delay before the task should be executed
     * @param interval the interval in which the task should be executed
     * @param unit     the unit of the interval
     * @return a {@link ScheduledFuture} representing pending completion of the task
     */
    public ScheduledFuture<?> submit(final Runnable runnable, final String name, final int delay, final int interval, final TimeUnit unit) {
        LOGGER.atInfo().withMessage("Submitting %s to background worker", name).log();
        return executor.scheduleAtFixedRate(runnable, delay, interval, unit);
    }

    /**
     * Submits a task to the background worker.
     *
     * @param runnable the task
     * @param name     the name of the task
     * @return a {@link ScheduledFuture} representing pending completion of the task
     */
    public ScheduledFuture<?> submitOnce(final Runnable runnable, final String name) {
        return submitOnce(runnable, name, 0);
    }

    /**
     * Submits a task to the background worker.
     *
     * @param runnable the task
     * @param name     the name of the task
     * @param delay    the delay before the task should be executed
     * @return a {@link ScheduledFuture} representing pending completion of the task
     */
    public ScheduledFuture<?> submitOnce(final Runnable runnable, final String name, final long delay) {
        LOGGER.atInfo().withMessage("Submitting %s to background worker", name).log();
        return executor.schedule(runnable, delay, TimeUnit.SECONDS);
    }

    /**
     * Shuts down the background worker.
     */
    public void shutdown() {
        LOGGER.atInfo().withMessage("Shutting down background worker").log();
        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                LOGGER.atError().withMessage("Background worker did not shut down in time. Forcing shutdown").log();
                executor.shutdownNow();
            }
        } catch (InterruptedException ex) {
            LOGGER.atError().withException(ex).withMessage("Background worker was interrupted while shutting down").log();
        }
    }

    private static final class BackgroundWorkerThreadService extends ScheduledThreadPoolExecutor {
        public BackgroundWorkerThreadService(int corePoolSize) {
            super(corePoolSize);
            setThreadFactory(runnable -> Thread.ofVirtual().uncaughtExceptionHandler(this::exceptionHandler).unstarted(runnable));
        }

        private void exceptionHandler(Thread thread, Throwable throwable) {
            LOGGER.atError().withException(throwable).withMessage("Uncaught exception in %s", thread.getName()).log();
        }

        @Override
        protected void beforeExecute(Thread thread, Runnable runnable) {
            super.beforeExecute(thread, runnable);
            LOGGER.atInfo().withMessage("Executing %s", thread.getName()).withObject(thread).log();
        }

        @Override
        protected void afterExecute(Runnable runnable, Throwable throwable) {
            super.afterExecute(runnable, throwable);
            LOGGER.atInfo().withMessage("Finished").withException(throwable).log();
        }

    }
}
