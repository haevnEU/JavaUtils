package de.haevn.utils.debug;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;

/**
 * <h1>ThreadTools</h1>
 * <br>
 * <p>This class provides some useful methods for threads.</p>
 * <p>It can be used to get information about the threads that are currently running.</p>
 * <h3>Example</h3>
 * <pre>
 * {@code
 *     final List<Thread> threads = ThreadTools.getThreads();
 *     final List<Thread> runningThreads = ThreadTools.getRunningThreads();
 *     final List<Thread> waitingThreads = ThreadTools.getThreads(Thread.State.WAITING);
 *     final List<String> runningThreadNames = ThreadTools.getRunningThreadNames();
 * }
 * </pre>
 * <i>More features will be added in the future.</i>
 * @author haevn
 * @version 1.0
 * @since 1.0
 */
@DebugTool(name = "ThreadTools", description = "Provides some useful methods for threads.")
public class ThreadTools {
    private ThreadTools() {}

    /**
     * <h2>getThreads()</h2>
     * <p>Returns a list of all threads that are currently running.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final List<Thread> threads = ThreadTools.getThreads();
     * }
     * </pre>
     * @return A list of all threads that are currently running.
     */
    public static List<Thread> getThreads() {
        final var threads = Thread.getAllStackTraces().keySet();
        return new ArrayList<>(threads);
    }

    /**
     * <h2>getThreadsBy({@link Predicate Predicate<Thread>})</h2>
     * <p>Returns a list of all threads that match the given predicate.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final List<Thread> threads = ThreadTools.getThreadsBy(thread -> thread.getName().startsWith("Thread"));
     * }
     * </pre>
     * @param predicate The predicate to filter the threads.
     * @return A list of all threads that match the given predicate.
     */
    public static List<Thread> getThreadsBy(final Predicate<Thread> predicate) {
        return getThreads().stream().filter(predicate).toList();
    }

    /**
     * <h2>getRunningThreads()</h2>
     * <p>Returns a list of all threads that are currently running.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final List<Thread> runningThreads = ThreadTools.getRunningThreads();
     * }
     * </pre>
     * @return A list of all threads that are currently running.
     */
    public static List<Thread> getRunningThreads() {
        return getThreadsBy(Thread::isAlive);
    }

    /**
     * <h2>getThreads({@link Thread.State})</h2>
     * <p>Returns a list of all threads that are in the given state.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final List<Thread> waitingThreads = ThreadTools.getThreads(Thread.State.WAITING);
     * }
     * </pre>
     * @param state The state of the threads.
     * @return A list of all threads that are in the given state.
     */
    public static List<Thread> getThreads(final Thread.State state){
        return getThreadsBy(thread -> thread.getState() == state);
    }

    /**
     * <h2>getRunningThreadNames()</h2>
     * <p>Returns a list of the names of all threads that are currently running.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final List<String> runningThreadNames = ThreadTools.getRunningThreadNames();
     * }
     * </pre>
     * @return A list of the names of all threads that are currently running.
     */
    public static List<String> getRunningThreadNames() {
        return getThreads().stream().map(Thread::getName).toList();
    }

    /**
     * <h2>waitFor({@link  AtomicBoolean})</h2>
     * <p>Waits until the given condition is true.</p>
     * <p>A new virtual thread is started that waits until the condition is true.</p>
     * <p>It wraps the {@link InterruptedException} to avoid unnecessary try-catch blocks.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final AtomicBoolean condition = new AtomicBoolean(false);
     *     ThreadTools.waitFor(condition);
     * }
     * </pre>
     * @param condition The condition to wait for.
     */
    public static void waitFor(final AtomicBoolean condition) {
        try {
            Thread.ofVirtual().start(() -> {
                while(!condition.get()) {
                    Thread.onSpinWait();
                }
            }).join();
        } catch (InterruptedException ignored) {}
    }
}
