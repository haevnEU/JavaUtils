package de.haevn.utils.logging;

import de.haevn.annotations.Launcher;
import de.haevn.utils.SerializationUtilsV2;
import de.haevn.utils.debug.MethodTools;
import de.haevn.utils.exceptions.ApplicationException;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static de.haevn.annotations.AnnotationUtils.findLauncher;

/**
 * <h1>Logger</h1>
 * <br>
 * <p>This class is a custom logger that can be used to log messages to the console and a file.</p>
 * <p>It uses the pipeline pattern to create log entries.</p>
 *
 * <p>A result of the design decision is that maintability and readability is increased.
 * Another advantage is that an entry is logged iff the {@link EntryBuilder#log()} method is invoked .</p>
 * <p>The logger can be configured to log only messages with a certain log level or to flush the log entries automatically.</p>
 * <p>The logger is thread-safe and can be used in a multi-threaded environment.</p> *
 *
 * @author Haevn
 * @version 1.1
 * @since 1.0
 */
public final class Logger {

    public static void createValidJsonDoc(final Logger logger) throws IOException {
        LogConversion.
        final File logFile = logger.config.getLogFile();
        final List<String> logEntries = Files.readAllLines(logFile.toPath());
        final String data = String.join(",\n\t", logEntries);
        String name = logFile.getName().substring(0, logFile.getName().lastIndexOf("."));
        var file = new File(logFile.getParent(), name + ".json");
        file.getParentFile().mkdirs();
        if (file.exists()) {
            file.delete();
        }
        Files.write(file.toPath(), ("[\n\t" + data + "\n]").getBytes());
    }

    private static final LoggerHandler HANDLER = LoggerHandler.getInstance();
    private final LoggerConfig config;
    private final List<LogEntry> logEntries = new ArrayList<>();
    private final Thread shutdownHook = new Thread(this::flush);

    /**
     * <h2>Logger()</h2>
     * Creates a new anonymous Logger with the default configuration
     *
     * @param <T> The type of the logger
     */
    public <T> Logger() {
        this(null, new LoggerConfig());
    }

    /**
     * <h2>Logger({@link Class})</h2>
     * <p>Create a new Logger associated with the given class and the default configuration</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *   class Dummy{
     *       private static final Logger LOGGER = new Logger(Dummy.class);
     *       public void doSomething(){
     *          LOGGER.atInfo().withMessage("Doing something").log();
     *       }
     *   }
     * }
     * </pre>
     *
     * @param cl  The class to use
     * @param <T> The type of the logger
     */
    public <T> Logger(Class<?> cl) {
        this(cl, new LoggerConfig());
    }

    /**
     * <h2>Logger({@link Class}, {@link LoggerConfig})</h2>
     * <br>
     * <p>Create a new Logger associated with the given class and configuration</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     class Dummy{
     *        private static final Logger LOGGER = new Logger(Dummy.class, new LoggerConfig().setLevel(Level.INFO));
     *        public void doSomething(){
     *          LOGGER.atInfo().withMessage("Doing something").log();
     *        }
     *     }
     * }
     * </pre>
     * <ul>
     *   <li>The logger will use the class name as the logger name, if the class is null the logger name will be "Logger"</li>
     *   <li>The logger will store the logs under the user home directory in a folder called "haevn"
     *   and a subfolder with the name of the application</li>
     *   <li>When the directories do not exist, they will be created</li>
     * </ul>
     *
     * @param cl     The class to use
     * @param config The configuration to use
     */
    public <T> Logger(final Class<?> cl, final LoggerConfig config) {

        String name = (null == cl) ? "Logger" : cl.getSimpleName();

        final String appName = findLauncher("de.haevn")
                .stream()
                .findFirst()
                .map(Launcher::name).orElse("/UNKNOWN");
        String rootPath = System.getProperty("user.home") + File.separator + "haevn" + File.separator + appName;
        final File root = new File(rootPath, "logs");
        this.config = config;
        if (null == this.config.getFileOutput()) {
            try {
                final var logFile = new File(root, File.separatorChar + name + ".log");
                if (!logFile.exists()) {
                    logFile.getParentFile().mkdirs();
                    logFile.createNewFile();
                }
                this.config.setOutput(logFile);
            } catch (final Exception ex) {
                throw new ApplicationException(ex);
            }
        }
        HANDLER.addLogger(this);
        activateShutdownHook();
    }

    /**
     * <h2>at({@link Level})</h2>
     * <p>Creates a new {@link EntryBuilder} for the given log level</p>
     *
     * @param level The log level to use
     * @return The EntryBuilder
     */
    public EntryBuilder at(Level level) {
        return new EntryBuilder(level).forEnclosingMethod(3);
    }

    /**
     * <h2>atInternal({@link Level})</h2>
     * <p>Creates a new {@link EntryBuilder} for the given log level</p>
     * <p>This is an internal method and should only be used by the logger</p>
     *
     * @param level The log level to use
     * @return The EntryBuilder
     */
    private EntryBuilder atInternal(Level level) {
        return new EntryBuilder(level).forEnclosingMethod(4);
    }

    /**
     * <h2>atDebug()</h2>
     * <p>Creates a new {@link EntryBuilder} for the DEBUG log level</p>
     * <p>Example:</p>
     * <pre>
     * {@code
     *     LOGGER.atDebug().withMessage("Debug message").log();
     * }
     * </pre>
     * <p>Internally {@link #atInternal(Level)} is called with {@link Level#DEBUG}</p>
     *
     * @return The EntryBuilder
     */
    public EntryBuilder atDebug() {
        return atInternal(Level.DEBUG);
    }

    /**
     * <h2>atInfo()</h2>
     * <p>Creates a new {@link EntryBuilder} for the INFO log level</p>
     * <p>Example:</p>
     * <pre>
     * {@code
     *     LOGGER.atInfo().withMessage("Info message").log();
     * }
     * </pre>
     * <p>Internally {@link #atInternal(Level)} is called with {@link Level#INFO}</p>
     *
     * @return The EntryBuilder
     */
    public EntryBuilder atInfo() {
        return atInternal(Level.INFO);
    }

    /**
     * <h2>atWarning()</h2>
     * <p>Creates a new {@link EntryBuilder} for the WARNING log level</p>
     * <p>Example:</p>
     * <pre>
     * {@code
     *     LOGGER.atWarning().withMessage("Warning message").log();
     * }
     * </pre>
     * <p>Internally {@link #atInternal(Level)} is called with {@link Level#WARNING}</p>
     *
     * @return The EntryBuilder
     */
    public EntryBuilder atWarning() {
        return atInternal(Level.WARNING);
    }

    /**
     * <h2>atError()</h2>
     * <p>Creates a new {@link EntryBuilder} for the ERROR log level</p>
     * <p>Example:</p>
     * <pre>
     * {@code
     *     LOGGER.atError().withMessage("Error message").log();
     * }
     * </pre>
     * <p>Internally {@link #atInternal(Level)} is called with {@link Level#ERROR}</p>
     *
     * @return The EntryBuilder
     */
    public EntryBuilder atError() {
        return atInternal(Level.ERROR);
    }

    /**
     * <h2>atFatal()</h2>
     * <p>Creates a new {@link EntryBuilder} for the FATAL log level</p>
     * <p>Example:</p>
     * <pre>
     * {@code
     *     LOGGER.atFatal().withMessage("Fatal message").log();
     * }
     * </pre>
     * <p>Internally {@link #atInternal(Level)} is called with {@link Level#FATAL}</p>
     *
     * @return The EntryBuilder
     */
    public EntryBuilder atFatal() {
        return atInternal(Level.FATAL);
    }

    /**
     * <h2>atUnknown()</h2>
     * <p>Creates a new {@link EntryBuilder} for the UNKNOWN log level</p>
     * <p>Example:</p>
     * <pre>
     * {@code
     *     LOGGER.atUnknown().withMessage("Unknown message").log();
     * }
     * </pre>
     * <p>Internally {@link #atInternal(Level)} is called with {@link Level#UNKNOWN}</p>
     *
     * @return The EntryBuilder
     */
    public EntryBuilder atUnknown() {
        return atInternal(Level.UNKNOWN);
    }

    /**
     * <h2>getLogEntries()</h2>
     * <p>Gets the list of log entries</p>
     *
     * @return The list of log entries
     */
    public List<LogEntry> getLogEntries() {
        return logEntries;
    }

    /**
     * <h2>getLogEntries(int)</h2>
     * <p>Gets the list of log entries with the given log level</p>
     *
     * @param level The log level to use
     * @return The list of log entries
     */
    public List<LogEntry> getLogEntries(int level) {
        return logEntries.stream().filter(entry -> (entry.getLevel().value & level) == level).toList();
    }

    /**
     * <h2>getLogEntries({@link Level})</h2>
     * <p>Gets the list of log entries with the given log level</p>
     *
     * @param level The log level to use
     * @return The list of log entries
     */
    public List<LogEntry> getLogEntries(final Level level) {
        return getLogEntries(level.value);
    }

    /**
     * <h2>clearLogEntries()</h2>
     * <p>Clears the list of log entries</p>
     */
    public void clearLogEntries() {
        logEntries.clear();
    }

    /**
     * <h2>flush()</h2>
     * <p>Flushes the log entries to the console and the file</p>
     * <p>The method is synchronized to ensure that the log entries are not modified while flushing</p>
     * <p>The entries are printed to the console</p>
     * <p>The entries are also appended as a json entry to the log file</p>
     */
    public void flush() {
        synchronized (logEntries) {

            logEntries.forEach(entry -> {
                final PrintStream consoleOutput = config.getConsoleOutput();
                final PrintStream fileOutput = config.getFileOutput();

                final Consumer<PrintStream> consumer = stream -> {
                    if (null == stream) {
                        return;
                    }
                    SerializationUtilsV2.json().disablePretty().useSafeModule()
                            .export(SanitizedLogEntry.getFromLogEntry(entry))
                            .ifPresent(stream::println);
                };

                consumer.accept(consoleOutput);
                consumer.accept(fileOutput);
            });
            logEntries.clear();
        }
    }

    /**
     * <h2>activateShutdownHook()</h2>
     * <p>Activates the shutdown hook</p>
     * <p>The shutdown hook will flush the log entries when the application is terminated</p>
     *
     * @return The Logger
     * @hidden This method is preview method and should not be used in production
     */
    public Logger activateShutdownHook() {
        Runtime.getRuntime().addShutdownHook(shutdownHook);
        return this;
    }

    /**
     * <h2>deactivateShutdownHook()</h2>
     * <p>Deactivates the shutdown hook</p>
     *
     * @return The Logger
     * @hidden This method is preview method and should not be used in production
     */
    public Logger deactivateShutdownHook() {
        Runtime.getRuntime().removeShutdownHook(shutdownHook);
        return this;
    }


    /**
     * <h1>EntryBuilder</h1>
     * <p>This class is a builder for log entries, that can be used to create log entries with a fluent API</p>
     * <p>It is used in the pipeline pattern to create log entries</p>
     *
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     LOGGER.atInfo().withMessage("Info message").log();
     * }
     * </pre>
     *
     * @author Haevn
     * @version 1.0
     * @since 1.0
     */
    public final class EntryBuilder {
        private final LogEntry entry = new LogEntry();

        /**
         * <h2>EntryBuilder({@link Level})</h2>
         * <p>Creates a new EntryBuilder with the given log level</p>
         * <p>The EntryBuilder is also used as a pipeline</p>
         *
         * @param level The log level to use
         */
        EntryBuilder(Level level) {
            entry.setLevel(level);
        }

        /**
         * <h2>forEnclosingMethod()</h2>
         * <p>Sets the helper to the current method</p>
         *
         * @return The used pipeline
         */
        public EntryBuilder forEnclosingMethod() {
            MethodTools.getMethod(2).ifPresent(entry::setHelper);
            return this;
        }

        /**
         * <h2>forEnclosingMethod(int)</h2>
         * <p>Sets the helper to the method that is {@code skip} levels above the current method</p>
         *
         * @return The used pipeline
         */
        private EntryBuilder forEnclosingMethod(final int skip) {
            MethodTools.getMethod(skip).ifPresent(entry::setHelper);
            return this;
        }

        /**
         * <h2>withException({@link Throwable})</h2>
         * <p>Adds a {@link Throwable} to the log entry</p>
         *
         * @param throwable The exception to set
         * @return The used pipeline
         */
        public EntryBuilder withException(final Throwable throwable) {
            entry.setThrowable(throwable);
            return this;
        }

        /**
         * <h2>withThreadName()</h2>
         * <p>Adds the thread name of the log entry</p>
         *
         * @return The used pipeline
         */
        public EntryBuilder withThreadName() {
            entry.setThreadName(Thread.currentThread().getName());
            return this;
        }

        /**
         * <h2>withMessage()</h2>
         * <p>Adds a message of the log entry</p>
         *
         * @param message The message to log
         * @return The used pipeline
         */
        public EntryBuilder withMessage(final String message) {
            entry.setMessage(message);
            return this;
        }

        /**
         * <h2>withMessage(String, {@link Object}...)</h2>
         * <p>Adds a message of the log entry with arguments</p>
         *
         * @param message The message to set
         * @param args    The arguments to use
         * @return The used pipeline
         */
        public EntryBuilder withMessage(final String message, final Object... args) {
            entry.setMessage(String.format(message, args));
            return this;
        }

        /**
         * <h2>withObject({@link Object})</h2>
         * <p>Adds an object to the log entry</p>
         *
         * @param obj The object to set
         * @return The used pipeline
         */
        public EntryBuilder withObject(final Object obj) {
            entry.setObj(obj);
            return this;
        }

        /**
         * <h2>log()</h2>
         * <p>Logs the log entry</p>
         * <p>The log entry is only logged if the log level of the entry is greater or equal to the log level of the logger</p>
         * <p>The log entry is also added to the list of log entries</p>
         * <p>If the auto flush is enabled or the log size is reached, the log entries are flushed</p>
         */
        public synchronized void log() {
            synchronized (logEntries) {
                if (config.getLevel().ordinal() >= entry.getLevel().ordinal()) {
                    entry.setTimestamp(System.currentTimeMillis());
                    logEntries.add(entry);
                }

                if (config.isAutoFlush() || config.getLogSize() <= logEntries.size()) {
                    flush();
                }
            }
        }

        /**
         * <h2>noop()</h2>
         * <p>This is the no-operation method</p>
         * <p>It will discard the log entry</p>
         */
        public void noop() {
            // Do nothing
        }
    }
}
