package de.haevn.utils.concurrency;

import de.haevn.utils.logging.Logger;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <h1>BackgroundWorkerThreadService</h1>
 * <br>This class extends the {@link ScheduledThreadPoolExecutor} to provide custom behaviour.
 * <br>Only the {@link BackgroundWorker} should uses this class to execute tasks in the background
 * <br>
 * <h3>Example</h3>
 * <pre>
 *     {@code
 *     BackgroundWorkerThreadService service = new BackgroundWorkerThreadService();
 *     service.submit(() -> System.out.println("Hello World"), "HelloWorld", 1, TimeUnit.SECONDS);
 *     }
 * </pre>
 * <br>
 * <h3>Custom extension</h3>
 * <ul>
 *     <li>Adds logging to start and finishing of tasks</li>
 *     <li>Overrides the default shutdown method to wait for all tasks to finish</li>
 *     <li>Use virtual threads instead of platform ones</li>
 *     <li>Adds exception handling for threads</li>
 * </ul>
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
     * <h2>BackgroundWorkerThreadService(double)</h2>
     * <p>Creates a new BackgroundWorkerThreadService with the given amount of threads</p>
     * <p>It will set the innerhited poolsize to the given amount</p>
     * <p>Overrides the thread creation to use a virtual thread with {@link BackgroundWorkerThreadService#exceptionHandler(Thread, Throwable)}</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     BackgroundWorkerThreadService worker = new BackgroundWorkerThreadService(5);
     *     }
     * </pre>
     *
     * @param corePoolSize the amount of threads
     */
    public BackgroundWorkerThreadService(final int corePoolSize) {
        super(corePoolSize);
        setThreadFactory(runnable -> Thread.ofVirtual().uncaughtExceptionHandler(this::exceptionHandler).unstarted(runnable));
        LOGGER.atInfo().withMessage("Background worker started with %s threads", corePoolSize).log();
    }


    /**
     * <h2>BackgroundWorkerThreadService()</h2>
     * <p>Creates a new BackgroundWorkerThreadService with 70% of available cores</p>
     * <p>internally the {@link BackgroundWorkerThreadService#BackgroundWorkerThreadService(int)} constructor is called</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     BackgroundWorkerThreadService worker = new BackgroundWorkerThreadService();
     *     }
     * </pre>
     */
    public BackgroundWorkerThreadService() {
        this((int) (Runtime.getRuntime().availableProcessors() * 0.7));
    }


    /**
     * <h2>submit(Runnable, String, int, TimeUnit)</h2>
     * <p>This method logs the submission of a task to the background worker and returns a {@link ScheduledFuture} as result</p>
     * <p>After the logging the task is scheduled to be executed in the given interval</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     worker.submit(() -> System.out.println("Hello World"), "HelloWorld", 1, TimeUnit.SECONDS);
     *     }
     * </pre>
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
     * <h2>submit(Runnable, String, int, TimeUnit)</h2>
     * <p>This method logs the submission of a task to the background worker and returns a {@link ScheduledFuture} as result</p>
     * <p>After both logging and a delay the task is scheduled to be executed in the given interval</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     worker.submit(() -> System.out.println("Hello World"), "HelloWorld", 1, TimeUnit.SECONDS);
     *     }
     * </pre>
     *
     * @param runnable the task
     * @param name     the name of the task
     * @param interval the interval in which the task should be executed
     * @param unit     the unit of the interval
     * @return a {@link ScheduledFuture} representing pending completion of the task
     */
    public ScheduledFuture<?> submit(final Runnable runnable, final String name, final int delay, final int interval, final TimeUnit unit) {
        LOGGER.atInfo().withMessage("Submitting %s to background worker", name).log();
        return super.scheduleAtFixedRate(runnable, delay, interval, unit);
    }

    /**
     * <h2>submitOnce(Runnable, String, long)</h2>
     * <p>Submits a {@link Runnable} to the service, it will be executed as soon as possible</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     worker.submitOnce(() -> System.out.println("Hello World"), "HelloWorld", 1);
     *     }
     * </pre>
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
     * <h2>shutdown()</h2>
     * <p>Shuts down the service and waits for all tasks to finish</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     worker.shutdown();
     *     }
     * </pre>
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
     * <h2>join()</h2>
     * <p>Request a shutdown and waits for all tasks to finish</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     worker.join();
     *     }
     * </pre>
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
     * <h2>exceptionHandler(Thread, Throwable)</h2>
     * <p>Logs an uncaught exception in a thread</p>
     * <h3>Example:</h3>
     * <b>This is an internal method and should not be called directly</b>
     *
     * @param thread    the thread
     * @param throwable the throwable
     */
    private void exceptionHandler(final Thread thread, final Throwable throwable) {
        LOGGER.atError().withException(throwable).withMessage("Uncaught exception in %s", thread.getName()).log();
    }

    /**
     * <h2>beforeExecute(Thread, Runnable)</h2>
     * <p>Overrides the default beforeExecute method to log the start of a task</p>
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
     * <h2>afterExecute(Runnable, Throwable)</h2>
     * <p>Overrides the default afterExecute method to log the end of a task</p>
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
