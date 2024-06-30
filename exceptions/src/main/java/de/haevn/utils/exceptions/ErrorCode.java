package de.haevn.utils.exceptions;

/**
 * <h1>ErrorCode</h1>
 * <br>
 * <p>This class provides an error code system that can be used to identify errors in the application.</p>
 * <p>It provides a way to create error codes based on the project, module and error.</p>
 * <p>It also provides a way to check if an error code is an unknown error or an error from a specific project or module.</p>
 * <h3>Structure</h3>
 * <table>
 *     <tr>
 *         <th></th>
 *         <th>Project</th>
 *         <th>Module</th>
 *         <th>Error</th>
 *     </tr>
 *     <tr>
 *         <td>Hex</td>
 *         <td>FF</td>
 *         <td>FF</td>
 *         <td>FFFF</td>
 *     </tr>
 *     <tr>
 *         <td>Binary</td>
 *         <td>1111</td>
 *         <td>1111</td>
 *         <td>11111111</td>
 *     </tr>
 * </table>
 * <br>
 * <b>Example</b>
 * <pre>
 * {@code
 *  try{
 *     // Code to measure
 *  }catch(Exception ex){
 *      throw new RuntimeException("An error occurred", ex, ErrorType.getErrorCode(ErrorType.Projects.UTILS, ErrorType.Modules.IO, ErrorType.IO.FILE_NOT_FOUND));
 *  }
 * }
 * </pre>
 *
 * @version 1.1
 * @since 1.1
 */
public class ErrorCode {
    public static final long UNKNOWN = getErrorCode(Project.UNKNOWN.value, 0L, 0L);

    private ErrorCode() {
    }


    /**
     * <h1>Project</h1>
     * <br>
     * <p>This enum provides the projects that can be used to create error codes.</p>
     * <p>It provides a way to identify the project of an error code.</p>
     * <p><b>Adding new projects requires a new entry in this enum therefore a new issue should be created.</b></p>
     *
     * @version 1.1
     * @since 1.1
     */
    public enum Project {
        UNKNOWN(0),
        UTILS(1),
        JFX_UTILS(2),

        // Projects add new here
        PROJECT_LUNAR(10);


        final int value;

        Project(final int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * <h2>isProject(long, {@link Project})</h2>
     * <p>Check if the given error code is from the given project.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     if(ErrorCode.isProject(errorCode, Project.UTILS)){...}
     * }
     * </pre>
     *
     * @param errorCode The error code
     * @param project   The project to check
     * @return True iff the given error code is from the given project
     */
    public static boolean isProject(final long errorCode, final Project project) {
        return getProject(errorCode) == project.value;
    }

    /**
     * Prject mask
     */
    private static final long PROJECT = 0xFF00_0000L;

    /**
     * Module mask
     */
    private static final long MODULE = 0x00FF_0000L;

    /**
     * Actual Error mask
     */
    private static final long ERROR = 0x0000_FFFFL;


    /**
     * <h2>isUnknownError(long)</h2>
     * <p>Check if the given error code is an unknown error.</p>
     *
     * @param errorCode The error code
     * @return True iff the given error code is an unknown error
     */
    public static boolean isUnknownError(final long errorCode) {
        return getProject(errorCode) == Project.UNKNOWN.value;
    }

    /**
     * <h2>isJavaUtilsModuleError(long)</h2>
     * <p>Check if the given error code is associated with the JavaUtils module.</p>
     *
     * @param errorCode The error code
     * @return True iff the given error code is a JavaUtils error
     */
    public static boolean isJavaUtilsModuleError(final long errorCode) {
        return getProject(errorCode) == Project.UTILS.value;
    }

    /**
     * <h2>isJavaFXUtilsError(long)</h2>
     * <p>Check if the given error code is associated with the JavaFXUtils module.</p>
     *
     * @param errorCode The error code
     * @return True iff the given error code is a JavaFXUtils error
     */
    public static boolean isJavaFXUtilsError(final long errorCode) {
        return getProject(errorCode) == Project.JFX_UTILS.value;
    }

    /**
     * <h2>getProject(long)</h2>
     * <p>Get the project from the given error code.</p>
     *
     * @param errorCode The error code
     * @return The project
     */
    public static int getProject(final long errorCode) {
        return (int) ((errorCode & PROJECT) >> 24);
    }

    /**
     * <h2>getModule(long)</h2>
     * <p>Get the module from the given error code.</p>
     *
     * @param errorCode The error code
     * @return The module
     */
    public static int getModule(final long errorCode) {
        return (int) ((errorCode & MODULE) >> 16);
    }

    /**
     * <h2>getError(long)</h2>
     * <p>Get the error from the given error code.</p>
     *
     * @param errorCode The error code
     * @return The error code
     */
    public static int getError(final long errorCode) {
        return (int) (errorCode & ERROR);
    }

    /**
     * <h2>getErrorCode(long, long, long)</h2>
     * <p>Construct an error code from the given project, module and error.</p>
     *
     * @param project The project
     * @param module  The module
     * @param error   The error
     * @return The error code
     */
    public static long getErrorCode(final long project, final long module, final long error) {
        return (project << 24) | (module << 16) | error;
    }

    /**
     * <h2>extract(long)</h2>
     * <p>Extract the parts of the given error code.</p>
     * <p>It returns a record with the project, module and error.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     *     final long errorCode = ErrorCode.getErrorCode(ErrorCode.Projects.UTILS, ErrorCode.Modules.IO, ErrorCode.IO.FILE_NOT_FOUND);
     *     final ErrorTypes error = ErrorCode.extract(errorCode);
     *     System.out.println("Project: " + error.project());
     *     System.out.println("Module: " + error.module());
     *     System.out.println("Error: " + error.errorCode());
     * }
     * </pre>
     *
     * @param errorCode The error code
     * @return The parts of the error code
     */
    public static ErrorTypes extract(final long errorCode) {
        return new ErrorTypes(getProject(errorCode), getModule(errorCode), getError(errorCode));
    }


    /**
     * <h1>ErrorTypes</h1>
     * <br>
     * <p>This record represents the parts of an error code.</p>
     *
     * @param project   The project
     * @param module    The module
     * @param errorCode The error code
     */
    public record ErrorTypes(long project, long module, long errorCode) {
    }

}
