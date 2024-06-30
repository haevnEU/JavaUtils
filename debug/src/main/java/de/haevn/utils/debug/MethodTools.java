package de.haevn.utils.debug;


import java.util.Optional;

/**
 * <h1>MethodTools</h1>
 * <br>
 * <p>This class provides some useful methods for methods.</p>
 * <p>It can be used to get information about the method that called this method.</p>
 * <h3>Example</h3>
 * <pre>
 * {@code
 *     final Optional<MethodTools> method = MethodTools.getMethod();
 *     if (method.isPresent()) {
 *        System.out.println(method.get().getClassName());
 *        System.out.println(method.get().getMethodName());
 *        System.out.println(method.get().getLineNumber());
 *        System.out.println(method.get().getFileName());
 *     }
 * }
 * </pre>
 *
 * @author haevn
 * @version 1.0
 * @since 1.0
 */
@DebugTool(name = "MethodTools", description = "Provides some useful methods for methods.")
public class MethodTools {
    private final MethodDetails methodDetails;

    /**
     * Creates a new MethodTools object with the given frame.
     * @param frame The frame of the method.
     */
    private MethodTools(final StackWalker.StackFrame frame) {
        this.methodDetails = new MethodDetails(frame.getClassName(), frame.getMethodName(), frame.getLineNumber(), frame.getFileName());
    }

    /**
     * <h2>getMethod()</h2>
     * <p>Returns the last called method.</p>
     * <p>The stackframe contains this call and the original call, therefore it skips 2 methods.</p>
     * <pre>
     *     +---------------------+
     *     | getMethod()         | <-- Top of the stackframe (skip = 0)
     *     +---------------------+
     *     | getMethod(int skip) | <-- Internal call (skip = 1)
     *     +---------------------+
     *     | methodOne()         | <-- Second call and targeted method  (skip = 2)
     *     +---------------------+
     *     | methodTwo()         | <-- First method call (skip = 3)
     *     +---------------------+
     *     | main()              | <-- Bottom of the stackframe (skip = 4)
     *     +---------------------+
     * </pre>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final Optional<MethodTools> method = MethodTools.getMethod();
     * }
     * </pre>
     * @return The method that called this method.
     */
    public static Optional<MethodTools> getMethod() {
        return getMethod(2);
    }

    /**
     * <h2>getMethod(int skip)</h2>
     * <p>Returns the method that is skip methods above this method.</p>
     * <pre>
     *     +---------------------+
     *     | getMethod(int skip) | <-- getMethod(2) (skip = 0)
     *     +---------------------+
     *     | methodOne()         | <-- previous call (skip = 1)
     *     +---------------------+
     *     | main()              | <-- main call (skip = 2)
     *     +---------------------+
     * </pre>
     *
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final Optional<MethodTools> method = MethodTools.getMethod(2);
     * }
     * </pre>
     *
     * @param skip The amount of methods to skip.
     * @return The method that is skip methods above this method.
     */
    public static Optional<MethodTools> getMethod(final int skip) {
        StackWalker walker = StackWalker.getInstance();

        return walker.walk(stackFrameStream -> stackFrameStream
                .skip(skip)
                .findFirst()).map(MethodTools::new);
    }

    /**
     * <h2>getFileName()</h2>
     * <p>Returns the filename where the method is located.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final Optional<MethodTools> method = MethodTools.getMethod();
     *     if (method.isPresent()) {
     *         System.out.println(method.get().getFileName());
     *     }
     * }
     * </pre>
     * @return The name of the file.
     */
    public String getFileName() {
        return methodDetails.fileName;
    }

    /**
     * <h2>getClassName()</h2>
     * <p>Returns the name of the class.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final Optional<MethodTools> method = MethodTools.getMethod();
     *     if (method.isPresent()) {
     *         System.out.println(method.get().getClassName());
     *     }
     * }
     * </pre>
     *
     * @return The name of the class.
     */
    public String getClassName() {
        return methodDetails.className;
    }

    /**
     * <h2>getLineNumber()</h2>
     * <p>Returns the line number of the method.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final Optional<MethodTools> method = MethodTools.getMethod();
     *     if (method.isPresent()) {
     *         System.out.println(method.get().getLineNumber());
     *     }
     * }
     * </pre>
     *
     * @return The line number of the method.
     */
    public int getLineNumber() {
        return methodDetails.lineNumber;
    }

    /**
     * <h2>getMethodName()</h2>
     * <p>Returns the name of the method.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final Optional<MethodTools> method = MethodTools.getMethod();
     *     if (method.isPresent()) {
     *         System.out.println(method.get().getName());
     *     }
     * }
     * </pre>
     *
     * @return The name of the method.
     */
    public String getMethodName() {
        return methodDetails.methodName;
    }

    /**
     * <h2>toString()</h2>
     * <p>Returns a string representation of the method.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final Optional<MethodTools> method = MethodTools.getMethod();
     *     if (method.isPresent()) {
     *         System.out.println(method.get());
     *     }
     * }
     * </pre>
     *
     * @return A string representation of the method.
     */
    @Override
    public String toString() {
        return "\"" + methodDetails.className + "#" + methodDetails.methodName + ":" + methodDetails.lineNumber + "\" in \"" + methodDetails.fileName + "\"";
    }

    /**
     * <h1>MethodDetails</h1>
     * <p>This record represents the details of a method.</p>
     * <p>It contains the class name, the method name, the line number and the file name.</p>
     */
    private record MethodDetails(String className, String methodName, int lineNumber, String fileName) {
    }
}
