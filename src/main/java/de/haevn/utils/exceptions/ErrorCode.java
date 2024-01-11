package de.haevn.utils.exceptions;

/**
 * This class contains codes and method for every used error code
 * <br>
 * <b>Structure</b>
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
 *  }catch(Excecption ex){
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

    private ErrorCode(){}



    /**
     * Contains all projects.
     * Add new projects here, not that the value 0 to 9 are reserved for the framework.
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
        Project(final int value){
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static boolean isProject(final long errorCode, final Project project){
        return getProject(errorCode) == project.value;
    }





    private static final long PROJECT = 0xFF00_0000L;
    private static final long MODULE = 0x00FF_0000L;
    private static final long ERROR = 0x0000_FFFFL;


    /**
     * Check if the given error code is an unknown error.
     * @param errorCode The error code
     * @return True if the given error code is an unknown error
     */
    public static boolean isUnknownError(final long errorCode){
        return getProject(errorCode) == Project.UNKNOWN.value;
    }

    /**
     * Check if the given error code is a JavaUtils error.
     * @param errorCode The error code
     * @return True if the given error code is a JavaUtils error
     */
    public static boolean isJavaUtilsModuleError(final long errorCode){
        return getProject(errorCode) == Project.UTILS.value;
    }

    /**
     * Check if the given error code is a JavaFXUtils error.
     * @param errorCode The error code
     * @return True if the given error code is a JavaFXUtils error
     */
    public static boolean isJavaFXUtilsError(final long errorCode){
        return getProject(errorCode) == Project.JFX_UTILS.value;
    }

    /** Get the project from the given error code.
     * @param errorCode The error code
     * @return The project
     */
    public static int getProject(final long errorCode){
        return (int) ((errorCode & PROJECT) >> 24);
    }

    /** Get the module from the given error code.
     * @param errorCode The error code
     * @return The module
     */
    public static int getModule(final long errorCode){
        return (int) ((errorCode & MODULE) >> 16);
    }

    /** Get the error code from the given error code.
     * @param errorCode The error code
     * @return The error code
     */
    public static int getError(final long errorCode){
        return (int) (errorCode & ERROR);
    }

    /** Create an error code from the given parts.
     * @param project The project
     * @param module The module
     * @param error The error
     * @return The error code
     */
    public static long getErrorCode(final long project, final long module, final long error){
        return (project << 24) | (module << 16) | error;
    }

    /**
     * Extract the parts of an error code.
     * @param errorCode The error code
     * @return The parts of the error code
     */
    public static ErrorTypes extract(final long errorCode){
        return new ErrorTypes(getProject(errorCode), getModule(errorCode), getError(errorCode));
    }


    /**
     * A record for the parts of an error code.
     * @param project The project
     * @param module The module
     * @param errorCode The error code
     */
    public record ErrorTypes(long project, long module, long errorCode){ }

}
