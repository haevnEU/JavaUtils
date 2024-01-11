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
 * @version 1.0
 * @since 1.0
 */
public class ErrorType {
    private ErrorType(){}


    /**
     * Contains all projects.
     */
    public static final class Projects{
        private Projects(){}
        public static final int UNKNOWN = 0b0000_0000;
        public static final int UTILS = 0b0000_0001;
        public static final int JFX_UTILS = 0b0000_0010;

        // Projects
        public static final int PROJECT_LUNAR = 0b0000_0011;
    }

    public static boolean isProject(final long errorCode, final int project){
        return getProject(errorCode) == project;
    }





    private static final long PROJECT = 0xFF00_0000L;
    private static final long MODULE = 0x00FF_0000L;
    private static final long ERROR = 0x0000_FFFFL;


    public static boolean isProjectError(final long errorCode, final int project){
        return getProject(errorCode) == project;
    }

    /**
     * Check if the given error code is an unknown error.
     * @param errorCode The error code
     * @return True if the given error code is an unknown error
     */
    public static boolean isUnknownError(final long errorCode){
        return getProject(errorCode) == Projects.UNKNOWN;
    }

    /**
     * Check if the given error code is a JavaUtils error.
     * @param errorCode The error code
     * @return True if the given error code is a JavaUtils error
     */
    public static boolean isJavaUtilsModuleError(final long errorCode){
        return getProject(errorCode) == Projects.UTILS;
    }

    /**
     * Check if the given error code is a JavaFXUtils error.
     * @param errorCode The error code
     * @return True if the given error code is a JavaFXUtils error
     */
    public static boolean isJavaFXUtilsError(final long errorCode){
        return getProject(errorCode) == Projects.JFX_UTILS;
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
    public static long getErrorCode(final int project, final int module, final int error){
        return (project << 24) | (module << 16) | error;
    }

    /**
     * Extract the parts of an error code.
     * @param errorCode The error code
     * @return The parts of the error code
     */
    public static Parts extract(final long errorCode){
        return new Parts(getProject(errorCode), getModule(errorCode), getError(errorCode));
    }


    /**
     * A record for the parts of an error code.
     * @param project The project
     * @param module The module
     * @param errorCode The error code
     */
    public record Parts(long project, long module, long errorCode){ }

}
