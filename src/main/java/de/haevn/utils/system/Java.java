package de.haevn.utils.system;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Java {
    private Java(){}


    public static String getJavaVersion(){
        return System.getProperty("java.version");
    }

    public static String getJavaVersionDate(){
        return System.getProperty("java.version.date");
    }

    public static String getJavaHome(){
        return System.getProperty("java.home");
    }

    public static String getJavaVendor(){
        return System.getProperty("java.vendor");
    }

    public static String getJavaVendorVersion(){
        return System.getProperty("java.vendor.version");
    }

    public static String getJavaVendorUrl(){
        return System.getProperty("java.vendor.url");
    }

    public static String getJavaSpecificationName(){
        return System.getProperty("java.specification.name");
    }

    public static String getJavaSpecificationVendor(){
        return System.getProperty("java.specification.vendor");
    }

    public static String getJavaSpecificationVersion(){
        return System.getProperty("java.specification.version");
    }

    public static String getJavaVmName(){
        return System.getProperty("java.vm.name");
    }

    public static String getJavaVmVendor(){
        return System.getProperty("java.vm.vendor");
    }

    public static String getJavaVmVersion(){
        return System.getProperty("java.vm.version");
    }

    public static String getJavaVmInfo(){
        return System.getProperty("java.vm.info");
    }

    public static String getJavaVmSpecificationName(){
        return System.getProperty("java.vm.specification.name");
    }

    public static String getJavaVmSpecificationVendor(){
        return System.getProperty("java.vm.specification.vendor");
    }

    public static String getJavaVmSpecificationVersion(){
        return System.getProperty("java.vm.specification.version");
    }

public static String getJavaRuntimeName(){
        return System.getProperty("java.runtime.name");
    }

    public static String getJavaRuntimeVersion(){
        return System.getProperty("java.runtime.version");
    }

    public static String getJavaClassVersion(){
        return System.getProperty("java.class.version");
    }

    public static String getJdkDebug(){
        return System.getProperty("jdk.debug");
    }

    public static String getSunJavaLauncher(){
        return System.getProperty("sun.java.launcher");
    }

    public static String getSunManagementCompiler(){
        return System.getProperty("sun.management.compiler");
    }

    public static String getJavaIoTmpdir(){
        return System.getProperty("java.io.tmpdir");
    }

    public static String getJavaLibraryPath(){
        return System.getProperty("java.library.path");
    }

    public static String getJavaExtDirs(){
        return System.getProperty("java.ext.dirs");
    }

    public static String getJavaEndorsedDirs(){
        return System.getProperty("java.endorsed.dirs");
    }
}
