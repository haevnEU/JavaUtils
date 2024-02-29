package de.haevn.utils.io;

import de.haevn.utils.concurency.BackgroundWorker;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static de.haevn.utils.AppLauncher.LOGGER;

public class FileWatcher implements AutoCloseable {
    private static final FileWatcher INSTANCE = new FileWatcher();
    private final BackgroundWorker backgroundWorker = BackgroundWorker.getInstance();

    public static synchronized FileWatcher getInstance() {
        return INSTANCE;
    }

    private FileWatcher() {
    }

    public void stop() {
        backgroundWorker.shutdown();
    }

    public void watch(final String path, final int interval, TimeUnit unit, final Runnable callback) {
        LOGGER.atInfo().forEnclosingMethod().withMessage("Start watching").log();
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
