package de.haevn.utils.debug;


import java.util.Optional;

/**
 * This class provides some useful methods for lists.
 *
 * @author haevn
 * @version 1.0
 * @since 1.0
 */
@DebugTool(name = "MethodTools", description = "Provides some useful methods for methods.")
public class MethodTools {
    private final MethodDetails methodDetails;

    private MethodTools(final StackWalker.StackFrame frame) {
        this.methodDetails = new MethodDetails(frame.getClassName(), frame.getMethodName(), frame.getLineNumber(), frame.getFileName());
    }

    /**
     * Returns the method that called this method.
     *
     * @return The method that called this method.
     */
    public static Optional<MethodTools> getMethod() {
        return getMethod(2);
    }

    /**
     * Returns the method that is skip methods above this method.
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
     * Returns the name of the file.
     *
     * @return The name of the file.
     */
    public String getFileName() {
        return methodDetails.fileName;
    }

    /**
     * Returns the name of the class.
     *
     * @return The name of the class.
     */
    public String getClassName() {
        return methodDetails.className;
    }

    /**
     * Returns the line number of the method.
     *
     * @return The line number of the method.
     */
    public int getLineNumber() {
        return methodDetails.lineNumber;
    }

    /**
     * Returns the name of the method.
     *
     * @return The name of the method.
     */
    public String getMethodName() {
        return methodDetails.methodName;
    }

    @Override
    public String toString() {
        return "\"" + methodDetails.className + "#" + methodDetails.methodName + ":" + methodDetails.lineNumber + "\" in \"" + methodDetails.fileName + "\"";
    }

    private record MethodDetails(String className, String methodName, int lineNumber, String fileName) {
    }
}
