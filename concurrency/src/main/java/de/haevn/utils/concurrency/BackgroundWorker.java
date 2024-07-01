package de.haevn.utils.concurrency;


import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * <h1>BackgroundWorker</h1>
 * <br>This class provides a simple way to execute tasks in the background
 * <br>It uses the {@link BackgroundWorkerThreadService} to execute the tasks
 * <br>When accessed via the {@link BackgroundWorker#getInstance() getInstance()} method the maximum capacity for threads are 70%
 * <br>This behaviour can be overridden using the {@link BackgroundWorker#getInstance(int) getInstance(int)} method
 * <br>
 * <h2>Example</h2>
 * <pre>
 * {@code
 * BackgroundWorker.getInstance().submit(() -> System.out.println("Hello World"), "HelloWorld", 1, TimeUnit.SECONDS);
 * }
 * </pre>
 *
 * @author haevn
 * @version 1.1
 * @since 1.0
 */
public class BackgroundWorker {

    private static BackgroundWorker instance;

    /**
     * <h2>initialize(int)</h2>
     * <p>Initializes the singleton instance with a given capacity</p>
     *
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     BackgroundWorker.initialize(60);
     *     }
     *     </pre>
     *
     * @param amountThreads The amount of usable threads
     * @return the singleton instance
     */
    public static BackgroundWorker initialize(final int amountThreads) {
        instance = new BackgroundWorker(amountThreads);
        return instance;
    }

    /**
     * <h2>initialize()</h2>
     * <p>Initializes the singleton instance with a given amount Threads</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     BackgroundWorker.initialize(5);
     *     }
     * </pre>
     *
     * @param amountThreads amount of usable threads
     * @return the singleton instance
     */
    public static synchronized BackgroundWorker getInstance(final int amountThreads) {
        if (null == instance) {
            instance = new BackgroundWorker(amountThreads);
        }
        return instance;
    }

    /**
     * <h2>getInstance()</h2>
     * <p>Initializes the singleton instance with 70% of the available processors</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     BackgroundWorker.initialize();
     *     }
     * </pre>
     *
     * @return the singleton instance
     */
    public static synchronized BackgroundWorker getInstance() {
        return getInstance((int) (Runtime.getRuntime().availableProcessors() * 0.7));
    }


    private final BackgroundWorkerThreadService executor;


    /**
     * <h2>BackgroundWorker()</h2>
     * <p>Creates a new BackgroundWorker with 70% of the available processors</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     BackgroundWorker worker = new BackgroundWorker();
     *     }
     * </pre>
     */
    public BackgroundWorker() {
        this(Runtime.getRuntime().availableProcessors() * 0.7);
    }

    /**
     * <h2>BackgroundWorker(double)</h2>
     * <p>Creates a new BackgroundWorker with the given amount of threads</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     BackgroundWorker worker = new BackgroundWorker(5);
     *     }
     * </pre>
     *
     * @param amountThreads the amount of threads
     */
    public BackgroundWorker(final double amountThreads) {
        executor = new BackgroundWorkerThreadService((int) amountThreads);
    }

    /**
     * <h2>submit(Runnable, String, int, TimeUnit)</h2>
     * <p>Submits a repeatable task to the background worker and return a {@link ScheduledFuture} as result</p>
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
    public ScheduledFuture<?> submit(final Runnable runnable, final String name, final int interval, final TimeUnit unit) {
        return submit(runnable, name, 0, interval, unit);
    }

    /**
     * <h2>submit(Runnable, String, int, int, TimeUnit)</h2>
     * <p>Submits a repeatable delayed task to the background worker and return a {@link ScheduledFuture} as result</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     worker.submit(() -> System.out.println("Hello World"), "HelloWorld", 1, 1, TimeUnit.SECONDS);
     *     }
     * </pre>
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
     * <h2>submitOnce(Runnable, String)</h2>
     * <p>Submits a task to the background worker and return a {@link ScheduledFuture} as result</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     worker.submitOnce(() -> System.out.println("Hello World"), "HelloWorld");
     *     }
     * </pre>
     *
     * @param runnable the task
     * @param name     the name of the task
     * @return a {@link ScheduledFuture} representing pending completion of the task
     */
    public ScheduledFuture<?> submitOnce(final Runnable runnable, final String name) {
        return submitOnce(runnable, name, 0);
    }

    /**
     * <h2>submitOnce(Runnable, String, long)</h2>
     * <p>Submits a delayed task to the background worker and return a {@link ScheduledFuture} as result</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     worker.submitOnce(() -> System.out.println("Hello World"), "HelloWorld", 1);
     *     }
     * </pre>
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
     * <h2>shutdown()</h2>
     * <p>Request a shutdown of the {@link BackgroundWorker}</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *      worker.shutdown();
     *     }
     * </pre>
     */
    public void shutdown() {
        executor.shutdown();
    }

    /**
     * <h2>join()</h2>
     * <p>Waits for all tasks to finish</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     worker.join();
     *     }
     * </pre>
     */
    public void join() {
        executor.join();
    }
}
