package de.haevn.utils.concurrency;


import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * This class provides a simple way to execute tasks in the background.<br>
 * It uses a {@link BackgroundWorkerThreadService} to execute the tasks.<br>
 * Accessing via {@link BackgroundWorker#getInstance()} will return a singleton instance with 70% of the available processors.<br>
 * Accessing via {@link BackgroundWorker#getInstance(int)} will return a singleton instance with the given amount of threads.
 * <br>
 * <b>Example</b>
 * <pre>
 * {@code
 * BackgroundWorker.getInstance().submit(() -> System.out.println("Hello World"), "HelloWorld", 1, TimeUnit.SECONDS);
 * }
 * @author haevn
 * @version 1.1
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


    private final BackgroundWorkerThreadService executor;

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
        return submit(runnable, name, 0, interval, unit);
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
        return executor.submit(runnable, name, delay, interval, unit);
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
        return executor.submitOnce(runnable, name, delay);
    }

    /**
     * Shuts down the background worker.
     */
    public void shutdown() {
        executor.shutdown();
    }

    /**
     * Waits for all tasks to finish.
     */
    public void join() {
        executor.join();
    }
}
