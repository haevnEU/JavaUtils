package de.haevn.utils.debug;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * This class provides some useful methods for threads.
 *
 * @author haevn
 * @version 1.0
 * @since 1.0
 */
@DebugTool(name = "ThreadTools", description = "Provides some useful methods for threads.")
public class ThreadTools {
    private ThreadTools() {}

    public static List<Thread> getThreads() {
        final var threads = Thread.getAllStackTraces().keySet();
        return new ArrayList<>(threads);
    }

    public static List<Thread> getThreadsBy(final Predicate<Thread> predicate) {
        return getThreads().stream().filter(predicate).toList();
    }

    public static List<Thread> getRunningThreads() {
        return getThreadsBy(Thread::isAlive);
    }

    public static List<Thread> getThreads(final Thread.State state){
        return getThreadsBy(thread -> thread.getState() == state);
    }

    public static List<String> getRunningThreadNames() {
        return getThreads().stream().map(Thread::getName).toList();
    }
}
