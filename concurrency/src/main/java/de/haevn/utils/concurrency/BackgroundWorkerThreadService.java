package de.haevn.utils.concurrency;

import de.haevn.utils.logging.Logger;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * This is an internal class that enhances the {@link ScheduledThreadPoolExecutor} with logging and exception handling.
 * It is used by the {@link BackgroundWorker} to execute tasks in the background.
 * <br>
 * <b>Example</b>
 * <pre>
 *     {@code
 *     BackgroundWorkerThreadService service = new BackgroundWorkerThreadService();
 *     service.submit(() -> System.out.println("Hello World"), "HelloWorld", 1, TimeUnit.SECONDS);
 *     }
 * </pre>
 *
 * @author haevn
 * @version 1.0
 * @see BackgroundWorker
 * @see BackgroundWorkerThreadService
 * @see ScheduledThreadPoolExecutor
 * @since 2.1
 */
final class BackgroundWorkerThreadService extends ScheduledThreadPoolExecutor {
    private static final Logger LOGGER = new Logger(BackgroundWorker.class);

    /**
     * Creates a new {@link BackgroundWorkerThreadService} with the given amount of threads
     *
     * @param corePoolSize the amount of threads
     */
    public BackgroundWorkerThreadService(final int corePoolSize) {
        super(corePoolSize);
        setThreadFactory(runnable -> Thread.ofVirtual().uncaughtExceptionHandler(this::exceptionHandler).unstarted(runnable));
        LOGGER.atInfo().withMessage("Background worker started with %s threads", corePoolSize).log();
    }


    /**
     * Creates a new {@link BackgroundWorkerThreadService} with 70% of the available processors
     */
    public BackgroundWorkerThreadService() {
        this((int) (Runtime.getRuntime().availableProcessors() * 0.7));
    }


    /**
     * Submits a {@link Runnable} to the service, it will be executed as soon as possible and then every {@code interval} time units
     *
     * @param runnable the task to be executed
     * @param name     the name of the task
     * @param interval the interval in which the task should be executed
     * @param unit     the unit of the interval
     * @return a ScheduledFuture representing pending completion of the task
     */
    public ScheduledFuture<?> submit(final Runnable runnable, final String name, final int interval, final TimeUnit unit) {
        LOGGER.atInfo().withMessage("Submitting %s to background worker, interval is %s %s", name, interval, unit.toString()).log();
        return super.scheduleAtFixedRate(runnable, 0, interval, unit);
    }

    /**
     * Submits a {@link Runnable} to the service, it will be executed after {@code delay} time units and then every {@code interval} time units
     *
     * @param runnable the task to be executed
     * @param name     the name of the task
     * @param delay    the delay before the task should be executed
     * @param interval the interval in which the task should be executed
     * @param unit     the unit of the interval
     * @return a ScheduledFuture representing pending completion of the task
     */
    public ScheduledFuture<?> submit(final Runnable runnable, final String name, final int delay, final int interval, final TimeUnit unit) {
        LOGGER.atInfo().withMessage("Submitting %s to background worker", name).log();
        return super.scheduleAtFixedRate(runnable, delay, interval, unit);
    }

    /**
     * Submits a {@link Runnable} to the service, it will be executed as soon as possible
     *
     * @param runnable the task to be executed
     * @param name     the name of the task
     * @return a ScheduledFuture representing pending completion of the task
     */
    public ScheduledFuture<?> submitOnce(final Runnable runnable, final String name, final long delay) {
        LOGGER.atInfo().withMessage("Submitting %s to background worker", name).log();
        return super.schedule(runnable, delay, TimeUnit.SECONDS);
    }

    /**
     * Stops the service and waits 5 seconds for all tasks to finish
     */
    @Override
    public void shutdown() {
        LOGGER.atInfo().withMessage("Shutting down background worker").log();
        super.shutdown();
        try {
            if (!super.awaitTermination(5, TimeUnit.SECONDS)) {
                LOGGER.atError().withMessage("Background worker did not shut down in time. Forcing shutdown").log();
                super.shutdownNow();
            }
        } catch (InterruptedException ex) {
            LOGGER.atError().withException(ex).withMessage("Background worker was interrupted while shutting down").log();
        }
    }

    /**
     * Stops the service and waits for all tasks to finish
     */
    public void join() {
        LOGGER.atInfo().withMessage("Waiting for all tasks to finish").log();
        super.shutdown();
        try {
            super.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        } catch (InterruptedException ex) {
            LOGGER.atError().withException(ex).withMessage("Background worker was interrupted while waiting for tasks to finish").log();
        }
    }




    /**
     * Logs an uncaught exception in a thread
     *
     * @param thread    the thread
     * @param throwable the throwable
     */
    private void exceptionHandler(final Thread thread, final Throwable throwable) {
        LOGGER.atError().withException(throwable).withMessage("Uncaught exception in %s", thread.getName()).log();
    }

    /**
     * Logs the start of a task
     *
     * @param thread   the thread that will run task {@code r}
     * @param runnable the task that will be executed
     */
    @Override
    protected void beforeExecute(final Thread thread, final Runnable runnable) {
        super.beforeExecute(thread, runnable);
        LOGGER.atInfo().withMessage("Executing %s", thread.getName()).withObject(thread).log();
    }

    /**
     * Logs the end of a task
     *
     * @param runnable  the task that has been executed
     * @param throwable the exception that has been thrown
     */
    @Override
    protected void afterExecute(final Runnable runnable, final Throwable throwable) {
        super.afterExecute(runnable, throwable);
        LOGGER.atInfo().withMessage("Finished").withException(throwable).log();
    }


}
