package de.haevn.utils.io;

import de.haevn.utils.concurrency.BackgroundWorker;
import de.haevn.utils.logging.Logger;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;


/**
 * <h1>FileWatcher</h1>
 * <p>The FileWatcher class provides methods to watch files for changes</p>
 * <p>It provides methods to watch files for changes and execute a callback when a file has changed</p>
 * <p>The FileWatcher can be accessed via static methods</p>
 *
 * <h2>Example:</h2>
 * <pre>{@code
 * FileWatcher watcher = FileWatcher.getInstance();
 * watcher.watch("path/to/file.txt", 1, TimeUnit.SECONDS, () -> System.out.println("File has changed"));
 * }</pre>
 *
 * @author Haevn
 * @version 1.0
 * @since 2.1
 */
public class FileWatcher implements AutoCloseable {
    private static final Logger LOGGER = new Logger(FileWatcher.class);

    private static final FileWatcher INSTANCE = new FileWatcher();
    private final BackgroundWorker backgroundWorker = BackgroundWorker.getInstance();

    /**
     * This attribute stores the number of files that are currently being watched
     */
    private final AtomicLong watchingFiles = new AtomicLong(0);

    /**
     * This attribute stores the maximum number of files that can be watched
     */
    private final AtomicLong maxFiles = new AtomicLong(5);

    /**
     * <h2>getInstance()</h2>
     * <p>Returns the FileWatcher instance</p>
     *
     * @return the FileWatcher instance
     */
    public static synchronized FileWatcher getInstance() {
        return INSTANCE;
    }

    /**
     * <h2>setLimit(int)</h2>
     * <p>Sets the maximum number of files that can be watched</p>
     *
     * @param limit the maximum number of files
     */
    public static synchronized void setLimit(final int limit) {
        INSTANCE.maxFiles.set(limit);
    }

    /**
     * <h2>stop()</h2>
     * <p>Stops the FileWatcher</p>
     */
    public void stop() {
        backgroundWorker.shutdown();
    }

    /**
     * <h2>watch(String, int, {@link TimeUnit}, {@link Runnable})</h2>
     * <p>The submitted file will be checked every interval for changes. If the file has changed, the callback will be executed</p>
     * <p>When the maximum number of files is reached, the method will return without watching the file</p>
     * <p>The change of the file is determined by the last modified date of the file</p>     *
     * <h3>Example:</h3>
     * <pre>{@code
     * FileWatcher watcher = FileWatcher.getInstance();
     * watcher.watch("path/to/file.txt", 1, TimeUnit.SECONDS, () -> System.out.println("File has changed"));
     * }</pre>
     *
     * @param path     the path to the file
     * @param interval the interval to check for changes
     * @param unit     the time unit of the interval
     * @param callback the callback to execute when the file has changed
     */
    public void watch(final String path, final int interval, TimeUnit unit, final Runnable callback) {
        if (maxFiles.get() < watchingFiles.get()) {
            LOGGER.atError().forEnclosingMethod().withMessage("Max files reached, current limit is %s/%s", watchingFiles.get(), maxFiles).log();
            return;
        }

        LOGGER.atInfo().forEnclosingMethod().withMessage("Start watching, Currently %s", watchingFiles.getAndIncrement()).log();
        final File file = new File(path);
        final AtomicLong lastModified = new AtomicLong(file.lastModified());

        backgroundWorker.submit(() -> {
            if (file.lastModified() != lastModified.get()) {
                LOGGER.atInfo().forEnclosingMethod().withMessage("File %s has changed", file.getName()).log();
                lastModified.set(file.lastModified());
                callback.run();
            }
        }, "FileWatcher", interval, unit);

        LOGGER.atInfo().forEnclosingMethod().withMessage("Finished watching").log();
    }

    /**
     * <h2>close()</h2>
     * <p>Invokes the {@link #stop()} method</p>
     */
    @Override
    public void close() throws Exception {
        stop();
    }
}