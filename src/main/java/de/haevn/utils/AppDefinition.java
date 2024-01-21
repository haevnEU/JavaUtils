package de.haevn.utils;

import de.haevn.utils.exceptions.ErrorCode;

import java.util.Arrays;

public class AppDefinition {
    private static String appName = "";
    private static ErrorCode.Project project = ErrorCode.Project.UNKNOWN;
    private static boolean debugMode = false;
    

    /**
     * Initializes the app definition.
     * <p>
     * Important information about the project argument, the project is a unique identifier please contact the developer to request one. If you don't want a unique identifier you can use the {@link ErrorCode.Project#UNKNOWN} as identfier.
     *
     * @param appName Name of the app. This name will be used for the underlying file operation, everything will be stored under ~/.{appName} %USER%/{appName}
     * @param project Project code. The code will be used inside the error handling.
     */
    public static void initialize(final String appName, final ErrorCode.Project project) {
        AppDefinition.appName = appName;
        AppDefinition.project = project;


    }

    /**
     * Initializes the app definition.
     * <p>
     * Important information about the project argument, the project is a unique identifier please contact the developer to request one. If you don't want a unique identifier you can use the {@link ErrorCode.Project#UNKNOWN} as identfier.
     *
     * @param appName Name of the app. This name will be used for the underlying file operation, everything will be stored under ~/.{appName} %USER%/{appName}
     * @param project Project code. The code will be used inside the error handling.
     * @param args    Additional arguments.
     */
    public static void initialize(final String appName, final ErrorCode.Project project, String... args) {
        AppDefinition.appName = appName;
        AppDefinition.project = project;

        // Filter debug argument
        Arrays.stream(args).filter(arg -> arg.equalsIgnoreCase("--debug")).findFirst().ifPresent(arg -> AppDefinition.enableDebugMode());
    }

    public static void enableDebugMode() {
        AppDefinition.debugMode = true;
    }

    public static boolean isDebugMode() {
        return debugMode;
    }

    public static String getAppName() {
        return appName;
    }


    public static ErrorCode.Project getProjectIdentifier() {
        return project;
    }

    public static int getAppID() {
        return project.getValue();
    }


    private AppDefinition() {
    }
}
