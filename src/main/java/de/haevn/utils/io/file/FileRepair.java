package de.haevn.utils.io.file;

import de.haevn.utils.concurency.BackgroundWorker;
import de.haevn.utils.datastructure.Observable;
import de.haevn.utils.logging.Logger;
import de.haevn.utils.network.NetworkInteraction;
import de.haevn.utils.network.NetworkUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class FileRepair {
    private static final Logger LOGGER = new Logger(FileRepair.class);

    final Observable<Status> status = new Observable<>(Status.NOT_STARTED);

    public static RepairPipeline openPipeline() {
        return new RepairPipeline();
    }

    private final RepairPipeline config;

    private FileRepair(final RepairPipeline config) {
        this.config = config;
        status.addObserver(this::stateChanged);
    }

    private void stateChanged(Status status) {
        config.onStatusChange.accept(status);
    }


    public void startAsync() {
        BackgroundWorker.getInstance().submitOnce(this::start, "FileRepair");
    }


    private List<File> scanDirectories() {
        stateChanged(Status.SCANNING);
        final List<File> missingDirectories = new ArrayList<>();
        for (File directory : config.directories) {
            if (!directory.exists()) {
                missingDirectories.add(directory);
                LOGGER.atInfo().withMessage("Directory %s does not exist", directory.getAbsolutePath()).log();
            }
        }
        return missingDirectories;
    }

    private void repairDirectories(final List<File> missingDirectories) {
        stateChanged(Status.REPAIRING);
        for (File directory : missingDirectories) {
            if (directory.mkdirs()) {
                LOGGER.atInfo().withMessage("Created directory: %s", directory.getAbsolutePath()).log();
            } else {
                return;
            }
        }
    }

    private List<File> scanFiles() {
        stateChanged(Status.SCANNING);
        final List<File> missingFiles = new ArrayList<>();
        for (File file : config.files) {
            if (!file.exists()) {
                missingFiles.add(file);
                LOGGER.atInfo().withMessage("File %s does not exist", file.getAbsolutePath()).log();
            }
        }
        return missingFiles;
    }

    private void repairFiles(final List<File> missingFiles) {
        stateChanged(Status.REPAIRING);
        final String downloadUrl = config.downloadUrl;

        missingFiles.forEach(file -> {
            String fileUrl = downloadUrl + "/" + file.getName();
            NetworkInteraction.sendGetRequest(fileUrl).ifPresent(response -> {
                if (NetworkUtils.is2xx(response.statusCode())) {
                    FileIO.store(file, response.body());
                }
            });
        });
    }

    public void start() {
        config.onStart.run();
        stateChanged(Status.STARTED);
        stateChanged(Status.WAITING);
        final List<File> missingDirectories = scanDirectories();
        stateChanged(Status.WAITING);
        repairDirectories(missingDirectories);
        stateChanged(Status.WAITING);
        final List<File> missingFiles = scanFiles();
        stateChanged(Status.WAITING);
        repairFiles(missingFiles);
        stateChanged(Status.WAITING);
        stateChanged(Status.COMPLETE);
        config.onComplete.run();
    }

    public static class RepairPipeline {

        private String downloadUrl = "";


        private final List<File> directories = new ArrayList<>();
        private final List<File> files = new ArrayList<>();
        private Runnable onComplete = () -> {
        };
        private Consumer<Status> onStatusChange = status -> {
        };
        private Runnable onStart = () -> {
        };


        public RepairPipeline setOnStart(final Runnable onStart) {
            if (onStart == null) return this;

            this.onStart = onStart;
            return this;
        }


        public RepairPipeline setOnComplete(final Runnable onComplete) {
            if (onComplete == null) return this;

            this.onComplete = onComplete;
            return this;
        }

        public RepairPipeline addRequiredDirectory(final File directory) {
            directories.add(directory);
            return this;
        }

        public RepairPipeline addRequiredFile(final File file) {
            files.add(file);
            return this;
        }

        public RepairPipeline setOnStatusChange(final Consumer<Status> onStatusChange) {
            if (onStatusChange == null) return this;

            this.onStatusChange = onStatusChange;
            return this;
        }

        public RepairPipeline setOnDownloadUrl(final String downloadUrl) {
            if (downloadUrl.isEmpty()) return this;

            this.downloadUrl = downloadUrl;
            return this;
        }

        public FileRepair build() {
            return new FileRepair(this);
        }
    }


    public enum Status {
        NOT_STARTED,
        WAITING,
        STARTED, SCANNING, REPAIRING, COMPLETE
    }
}
