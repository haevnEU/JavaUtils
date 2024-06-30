package de.haevn.utils.system;

/**
 * <h1>Java</h1>
 * <p>This class provides information about the Java environment.</p>
 * <p>It provides information about the Java version, the Java home, the Java vendor and the Java VM.</p>
 *
 * @author haevn
 * @version 1.0
 * @since 1.0
 */
public final class Java {
    private Java() {
    }

    /**
     * <h2>getJavaVersion()</h2>
     * <p>Gets the Java version using the {@link System#getProperty(String)} with java.version</p>
     *
     * @return Java version.
     */
    public static String getJavaVersion() {
        return System.getProperty("java.version");
    }

    /**
     * <h2>getJavaVersionDate()</h2>
     * <p>Gets the Java version date using the {@link System#getProperty(String)} with java.version.date</p>
     *
     * @return Java version date.
     */
    public static String getJavaVersionDate() {
        return System.getProperty("java.version.date");
    }

    /**
     * <h2>getJavaHome()</h2>
     * <p>Gets the Java home using the {@link System#getProperty(String)} with java.home</p>
     *
     * @return Java home.
     */
    public static String getJavaHome() {
        return System.getProperty("java.home");
    }

    /**
     * <h2>getJavaVendor()</h2>
     * <p>Gets the Java vendor using the {@link System#getProperty(String)} with java.vendor</p>
     *
     * @return Java vendor.
     */
    public static String getJavaVendor() {
        return System.getProperty("java.vendor");
    }

    /**
     * <h2>getJavaVendorUrl()</h2>
     * <p>Gets the Java vendor URL using the {@link System#getProperty(String)} with java.vendor.url</p>
     *
     * @return Java vendor URL.
     */
    public static String getJavaVendorVersion() {
        return System.getProperty("java.vendor.version");
    }

    /**
     * <h2>getJavaSpecificationName()</h2>
     * <p>Gets the Java specification name using the {@link System#getProperty(String)} with java.specification.name</p>
     *
     * @return Java specification name.
     */
    public static String getJavaVendorUrl() {
        return System.getProperty("java.vendor.url");
    }

    /**
     * <h2>getJavaSpecificationVendor()</h2>
     * <p>Gets the Java specification vendor using the {@link System#getProperty(String)} with java.specification.vendor</p>
     *
     * @return Java specification vendor.
     */
    public static String getJavaSpecificationName() {
        return System.getProperty("java.specification.name");
    }

    /**
     * <h2>getJavaSpecificationVersion()</h2>
     * <p>Gets the Java specification version using the {@link System#getProperty(String)} with java.specification.version</p>
     *
     * @return Java specification version.
     */
    public static String getJavaSpecificationVendor() {
        return System.getProperty("java.specification.vendor");
    }

    /**
     * <h2>getJavaSpecificationVersion()</h2>
     * <p>Gets the Java specification version using the {@link System#getProperty(String)} with java.specification.version</p>
     *
     * @return Java specification version.
     */
    public static String getJavaSpecificationVersion() {
        return System.getProperty("java.specification.version");
    }

    /**
     * <h2>getJavaVmName()</h2>
     * <p>Gets the Java VM name using the {@link System#getProperty(String)} with java.vm.name</p>
     *
     * @return Java VM name.
     */
    public static String getJavaVmName() {
        return System.getProperty("java.vm.name");
    }

    /**
     * <h2>getJavaVmVendor()</h2>
     * <p>Gets the Java VM vendor using the {@link System#getProperty(String)} with java.vm.vendor</p>
     *
     * @return Java VM vendor.
     */
    public static String getJavaVmVendor() {
        return System.getProperty("java.vm.vendor");
    }

    /**
     * <h2>getJavaVmVersion()</h2>
     * <p>Gets the Java VM version using the {@link System#getProperty(String)} with java.vm.version</p>
     *
     * @return Java VM version.
     */
    public static String getJavaVmVersion() {
        return System.getProperty("java.vm.version");
    }

    /**
     * <h2>getJavaVmInfo()</h2>
     * <p>Gets the Java VM info using the {@link System#getProperty(String)} with java.vm.info</p>
     *
     * @return Java VM info.
     */
    public static String getJavaVmInfo() {
        return System.getProperty("java.vm.info");
    }

    /**
     * <h2>getJavaVmSpecificationName()</h2>
     * <p>Gets the Java VM specification name using the {@link System#getProperty(String)} with java.vm.specification.name</p>
     *
     * @return Java VM specification name.
     */
    public static String getJavaVmSpecificationName() {
        return System.getProperty("java.vm.specification.name");
    }

    /**
     * <h2>getJavaVmSpecificationVendor()</h2>
     * <p>Gets the Java VM specification vendor using the {@link System#getProperty(String)} with java.vm.specification.vendor</p>
     *
     * @return Java VM specification vendor.
     */
    public static String getJavaVmSpecificationVendor() {
        return System.getProperty("java.vm.specification.vendor");
    }

    /**
     * <h2>getJavaVmSpecificationVersion()</h2>
     * <p>Gets the Java VM specification version using the {@link System#getProperty(String)} with java.vm.specification.version</p>
     *
     * @return Java VM specification version.
     */
    public static String getJavaVmSpecificationVersion() {
        return System.getProperty("java.vm.specification.version");
    }

    /**
     * <h2>getJavaRuntimeName()</h2>
     * <p>Gets the Java runtime name using the {@link System#getProperty(String)} with java.runtime.name</p>
     *
     * @return Java runtime name.
     */
    public static String getJavaRuntimeName() {
        return System.getProperty("java.runtime.name");
    }

    /**
     * <h2>getJavaRuntimeVersion()</h2>
     * <p>Gets the Java runtime version using the {@link System#getProperty(String)} with java.runtime.version</p>
     *
     * @return Java runtime version.
     */
    public static String getJavaRuntimeVersion() {
        return System.getProperty("java.runtime.version");
    }

    /**
     * <h2>getJavaClassVersion()</h2>
     * <p>Gets the Java class version using the {@link System#getProperty(String)} with java.class.version</p>
     *
     * @return Java class version.
     */
    public static String getJavaClassVersion() {
        return System.getProperty("java.class.version");
    }

    /**
     * <h2>getJdkDebug()</h2>
     *
     * @return Java class path.
     */
    public static String getJdkDebug() {
        return System.getProperty("jdk.debug");
    }

    /**
     * <h2>getSunJavaLauncher()</h2>
     * <p>Gets the sun Java launcher using the {@link System#getProperty(String)} with sun.java.launcher</p>
     *
     * @return Sun Java launcher.
     */
    public static String getSunJavaLauncher() {
        return System.getProperty("sun.java.launcher");
    }

    /**
     * <h2>getSunManagementCompiler()</h2>
     * <p>Gets the sun management compiler using the {@link System#getProperty(String)} with sun.management.compiler</p>
     *
     * @return Sun management compiler.
     */
    public static String getSunManagementCompiler() {
        return System.getProperty("sun.management.compiler");
    }

    /**
     * <h2>getJavaIoTmpdir()</h2>
     * <p>Gets the Java IO tmpdir using the {@link System#getProperty(String)} with java.io.tmpdir</p>
     *
     * @return Sun OS patch level.
     */
    public static String getJavaIoTmpdir() {
        return System.getProperty("java.io.tmpdir");
    }

    /**
     * <h2>getJavaLibraryPath()</h2>
     * <p>Gets the Java library path using the {@link System#getProperty(String)} with java.library.path</p>
     *
     * @return Java library path.
     */
    public static String getJavaLibraryPath() {
        return System.getProperty("java.library.path");
    }

    /**
     * <h2>getJavaExtDirs()</h2>
     * <p>Gets the Java class path using the {@link System#getProperty(String)} with java.ext.dirs</p>
     *
     * @return Java class path.
     */
    public static String getJavaExtDirs() {
        return System.getProperty("java.ext.dirs");
    }

    /**
     * <h2>getJavaEndorsedDirs()</h2>
     * <p>Gets the Java class path using the {@link System#getProperty(String)} with java.endorsed.dirs</p>
     *
     * @return Java class path.
     */
    public static String getJavaEndorsedDirs() {
        return System.getProperty("java.endorsed.dirs");
    }
}
