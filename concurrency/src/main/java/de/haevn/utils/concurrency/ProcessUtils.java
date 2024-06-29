package de.haevn.utils.concurrency;

import de.haevn.annotations.Draft;
import de.haevn.utils.datastructure.Tuple;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@Draft(description = "This class should be used to start processes in a more convenient way",
        todo = {"Add javadoc", "Refactor pipe"})
public class ProcessUtils {


    public Builder builder(final String name) {
        return new Builder(name);
    }

    private record Procc(long pid, String name, String directory, ProcessHandle.Info info) {
    }

    private static final class Builder {
        private final String name;
        private String directory = "";
        private boolean inheritIO = true;

        final List<String> args = new ArrayList<>();

        Builder(final String name) {
            this.name = name;
            args.add(name);
        }

        public Builder addArg(final String arg) {
            args.add(arg);
            return this;
        }

        public Builder directory(final String directory) {
            this.directory = directory;
            return this;
        }

        public Builder inheritIO(final boolean inheritIO) {
            this.inheritIO = inheritIO;
            return this;
        }


        public Tuple<Integer, Procc> start() throws IOException {
            final var result = startAsync();
            return new Tuple<>(result.getFirst().join(), result.getSecond());
        }

        public Tuple<CompletableFuture<Integer>, Procc> startAsync() throws IOException {

            final ProcessBuilder processBuilder = new ProcessBuilder(args);
            if (!directory.isBlank()) processBuilder.directory(Path.of(directory).toFile());
            if (inheritIO) processBuilder.inheritIO();


            final Process process = processBuilder.start();

            final Procc procc = new Procc(process.pid(), name, directory, process.info());
            final CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return process.waitFor();
                } catch (InterruptedException e) {
                    return -1;
                }
            });

            return new Tuple<>(future, procc);
        }

    }
}
