package de.haevn.utils;

import java.util.Optional;

/**
 * This class provides some useful methods for lists.
 *
 * @author haevn
 * @version 1.0
 * @since 1.0
 */
public class MetaMethodAccessor {
    private final String className;
    private final String methodName;
    private final int lineNumber;
    private final String fileName;

    public MetaMethodAccessor(StackWalker.StackFrame frame) {
        this.className = frame.getClassName();
        this.methodName = frame.getMethodName();
        this.lineNumber = frame.getLineNumber();
        this.fileName = frame.getFileName();
    }

    /**
     * Returns the method that called this method.
     *
     * @return The method that called this method.
     */
    public static Optional<MetaMethodAccessor> getMethod() {
        return getMethod(1);
    }

    /**
     * Returns the method that is skip methods above this method.
     *
     * @param skip The amount of methods to skip.
     * @return The method that is skip methods above this method.
     */
    public static Optional<MetaMethodAccessor> getMethod(int skip) {
        StackWalker walker = StackWalker.getInstance();

        return walker.walk(stackFrameStream -> stackFrameStream
                .skip(skip)
                .findFirst()).map(MetaMethodAccessor::new);
    }

    /**
     * Returns the name of the file.
     *
     * @return The name of the file.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Returns the name of the class.
     *
     * @return The name of the class.
     */
    public String getClassName() {
        return className;
    }

    /**
     * Returns the line number of the method.
     *
     * @return The line number of the method.
     */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * Returns the name of the method.
     *
     * @return The name of the method.
     */
    public String getMethodName() {
        return methodName;
    }

    @Override
    public String toString() {
        return "\"" + className + "#" + methodName + "\" in \"" + fileName + "#" + lineNumber + "\"";
    }
}
