package de.haevn.utils.debug;

import de.haevn.utils.datastructure.ReadonlyTuple;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * <h1>TimeMeasurementTools</h1>
 * <br>
 * <p>This class can be used to measure the time of a code.</p>
 * <br>
 * <b>Example 1</b>
 * <pre>
 * {@code
 *  try(TimeMeasurement timeMeasurement = new TimeMeasurement()){
 *     timeMeasurement.time(() -> {
 *     // Code to measure
 *     }, this.getClass(), "methodName", "description");
 * }catch(Exception ex){}
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
 * @author haevn
 * @version 1.0
 * @since 1.0
 */
@DebugTool(name = "TimeMeasurementTools", description = "This class can be used to measure the time of a code.")
public class TimeMeasurementTools implements AutoCloseable {
    private static final List<String> RUN_TIME_STACK = new ArrayList<>();
    private final PrintStream out;


    /**
     * <h2>TimeMeasurementTools()</h2>
     * <p>Create a new TimeMeasurement with the default output stream ({@link System#out}).</p>
     */
    public TimeMeasurementTools() {
        this(System.out);
    }

    /**
     * <h2>TimeMeasurementTools({@link PrintStream})</h2>
     * <p>Create a new TimeMeasurement with the given output stream.</p>
     * @param out The {@link PrintStream} to print the results.
     */
    public TimeMeasurementTools(final PrintStream out) {
        this.out = out;
    }

    /**
     * <h2>TimeMeasurementTools({@link Runnable}, {@link Class<?>}, {@link String}, {@link String}})</h2>
     * <p>Create a new TimeMeasurement with the given code, class, method name and description.</p>
     * <p>It will measure the time of the provided {@link Runnable}.</p>
     * <h3>Example 1:</h3>
     * <pre>
     * {@code
     * new TimeMeasurement(() -> {
     *     // Code to measure
     *     }, this.getClass(), "methodName", "description");
     * }
     * </pre>
     * <h3>Example 2:</h3>
     * <pre>
     *     {@code
     *     new TimeMeasurement(this::methodToMeasure, this.getClass(), "methodName", "description");
     *     }
     * </pre>
     * @param code        The code to measure.
     * @param cl          The class of the code.
     * @param methodName  The method name of the code.
     * @param description The description of the code.
     */
    public void time(final Runnable code, final Class<?> cl, final String methodName, final String description) {
        time(() -> {
            code.run();
            return null;
        }, cl, methodName, description);
    }

    /**
     * <h2>TimeMeasurementTools({@link Callable}, {@link Class<?>}, {@link String}, {@link String})</h2>
     * <p>Create a new TimeMeasurement with the given code, class, method name and description.</p>
     * <p>It will measure the time of the provided {@link Callable} and returns the result of the code.</p>
     * <h3>Example 1:</h3>
     * <pre>
     * {@code
     *     var result = new TimeMeasurement(() -> {
     *     // Code to measure
     *     }, this.getClass(), "methodName", "description");
     * }
     * </pre>
     * <h3>Example 2:</h3>
     * <pre>
     * {@code
     *     var result = new TimeMeasurement(this::methodToMeasure, this.getClass(), "methodName", "description");
     * }
     * </pre>
     *
     * @param code        The code to measure.
     * @param cl          The class of the code.
     * @param methodName  The method name of the code.
     * @param description The description of the code.
     * @param <T>         The type of the result.
     * @return The result of the code.
     */
    public <T> T time(final Callable<T> code, final Class<?> cl, final String methodName, final String description) {
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
     * <h2>stop()</h2>
     * <p>Stop the time measurement and print the results to the specified {@link java.io.PrintWriter} .</p>
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

    /**
     * <h2>close()</h2>
     * <p>This method invokes {@link TimeMeasurementTools#stop()}</p>
     * {@see TimeMeasurementTools#stop()}
     * {@see AutoCloseable#close()}
     *
     * @throws Exception If an error occurs.
     */
    @Override
    public void close() throws Exception {
        stop();
    }


    //----------------------------------------------------------------------------------------------------------------------
    //  Static methods
    //----------------------------------------------------------------------------------------------------------------------


    /**
     * <h2>measure({@link Runnable})</h2>
     * <p>This method can be used to measure the time of a code.</p>
     * <p>This is a static version of the {@link TimeMeasurementTools#time(Runnable, Class, String, String)} method.
     * and does not require an instance of the {@link TimeMeasurementTools} class.</p>
     * <p>It will return the execution time in milliseconds.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     long time = TimeMeasurementTools.measure(() -> {...});
     * }
     * </pre>
     *
     * @param code The code to measure.
     * @return The execution time in milliseconds.
     */
    public static long measure(final Runnable code) {
        final long startTime = System.currentTimeMillis();
        code.run();
        return System.currentTimeMillis() - startTime;
    }

    /**
     * <h2>measure({@link Callable})</h2>
     * <p>This method can be used to measure the time of a code.</p>
     * <p>This is a static version of the {@link TimeMeasurementTools#time(Callable, Class, String, String)} method.
     * and does not require an instance of the {@link TimeMeasurementTools} class.</p>
     * <p>It will return a {@link de.haevn.utils.datastructure.Tuple} containing the result and the
     * execution time in milliseconds.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     var result = TimeMeasurementTools.measure(() -> {...});
     *     System.out.println("Result: " + result.getFirst());
     *     System.out.println("Execution time: " + result.getSecond());
     * }
     * </pre>
     *
     * @param code The code to measure.
     * @param <T>  The type of the result.
     * @return The result and the execution time in milliseconds.
     */
    public static <T> ReadonlyTuple<T, Long> measure(final Callable<T> code) {
        final long startTime = System.currentTimeMillis();
        try {
            final T result = code.call();
            return new ReadonlyTuple<>(result, System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            return new ReadonlyTuple<>(null, System.currentTimeMillis() - startTime);
        }
    }
}
