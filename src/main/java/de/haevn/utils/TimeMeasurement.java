package de.haevn.utils;

import lombok.NonNull;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * This class can be used to measure the time of a code.
 * <br>
 * <b>Example 1</b>
 * <pre>
 * {@code
 *  try(TimeMeasurement timeMeasurement = new TimeMeasurement()){
 *     timeMeasurement.time(() -> {
 *     // Code to measure
 *     }, this.getClass(), "methodName", "description");
 * }catch(Excecption ex){}
 * }
 * </pre>
 * <br>
 * <b>Example 2</b>
 * <pre>
 * {@code
 *  TimeMeasurement timeMeasurement = new TimeMeasurement();
 *  timeMeasurement.time(() -> {
 *    // Code to measure
 * }, this.getClass(), "methodName", "description");
 * timeMeasurement.close();
 * }
 * </pre>
 *
 * @version 1.0
 * @since 1.0
 * @author haevn
 */
public class TimeMeasurement implements AutoCloseable {
    private static final List<String> RUN_TIME_STACK = new ArrayList<>();
    private final PrintStream out;

    /**
     * Create a new TimeMeasurement with System.out as default output stream.
     */
    public TimeMeasurement() {
        this(System.out);
    }

    /**
     * Create a new TimeMeasurement with the given output stream.
     * @param out The output stream where the result should be printed.
     */
    public TimeMeasurement(@NonNull final PrintStream out) {
        this.out = out;
    }

    /**
     * Measure the time of the given code.
     * @param code The code to measure.
     * @param cl The class of the code.
     * @param methodName The method name of the code.
     * @param description The description of the code.
     */
    public void time(final Runnable code, final Class<?> cl, final String methodName, final String description) {
        time(() -> {
            code.run();
            return null;
        }, cl, methodName, description);
    }

    /**
     * Measure the time of the given code.
     * @param code The code to measure.
     * @param cl The class of the code.
     * @param methodName The method name of the code.
     * @param description The description of the code.
     * @return The result of the code.
     * @param <T> The type of the result.
     */
    public <T> T time(final Callable<T> code, final Class<?> cl, final String methodName, final String  description) {
        final long startTime = System.currentTimeMillis();
        try {
            final T result = code.call();
            final long endTime = System.currentTimeMillis();
            final long runTime = endTime - startTime;

            final String runTimeString = String.format("%d min, %d sec",
                    (runTime / 1000) / 60, (runTime / 1000) % 60);

            final String runTimeStackString = String.format("%s.%s•%s•%s", cl.getSimpleName(), methodName, runTimeString, description);
            RUN_TIME_STACK.add(runTimeStackString);

            return result;
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * Stop the time measurement and print the result.
     * <ul>
     *     <li><b>result[0]</b>: Class.Method</li>
     *     <li><b>result[1]</b>: Execution time</li>
     *     <li><b>result[2]</b>: Description</li>
     *     <li><b>result[3]</b>: Other</li>
     * </ul>
     *
     * @return The result as a list of string arrays.
     */
    public List<String[]> stop() {
        final List<String[]> result = new ArrayList<>();
        RUN_TIME_STACK.stream().map(s -> {
            final String[] split = s.split("•");
            result.add(split);
            final StringBuilder builder = new StringBuilder();
            for (String string : split) {
                builder.append("[").append(string).append("]");
            }

            return builder.toString();
        }).forEach(out::println);
        return result;
    }

    @Override
    public void close() throws Exception {
        stop();
    }
}
