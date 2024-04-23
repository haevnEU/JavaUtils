package de.haevn.utils.io;

import de.haevn.utils.concurrency.BackgroundWorker;
import de.haevn.utils.logging.Logger;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;


public class FileWatcher implements AutoCloseable {
    private static final Logger LOGGER = new Logger(FileWatcher.class);

    private final AtomicLong watchingFiles = new AtomicLong(0);
    private final AtomicLong maxFiles = new AtomicLong(5);
    private static final FileWatcher INSTANCE = new FileWatcher();
    private final BackgroundWorker backgroundWorker = BackgroundWorker.getInstance();

    public static synchronized FileWatcher getInstance() {
        return INSTANCE;
    }

    public static synchronized void setLimit(final int limit) {
        INSTANCE.maxFiles.set(limit);
    }

    public void stop() {
        backgroundWorker.shutdown();
    }

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


    @Override
    public void close() throws Exception {
        stop();
    }
}