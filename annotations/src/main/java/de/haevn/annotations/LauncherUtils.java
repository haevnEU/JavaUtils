package de.haevn.annotations;

import java.io.File;

/**
 * <h1>LauncherUtils</h1>
 * <p>This class provides utilities to handle the {@link Launcher} annotation</p>
 * <p>It operates on the <b>first</b> found {@link Launcher}</p>
 * @version 1.0
 * @since 2.1
 * @author haevn
 * @see Launcher
 */
public class LauncherUtils {
    private final Launcher launcher;

    private LauncherUtils(final Launcher launcher) {
        this.launcher = launcher;
    }

    /**
     * <h2>getLauncher</h2>
     * <p>Get the <b>first</b> {@link Launcher} annotation of in the package "de.haevn"</p>
     * @return The <b>first</b> {@link Launcher} annotation
     */
    public static LauncherUtils getLauncher() {
        return getLauncher("de.haevn");
    }

    /**
     * <h2>getLauncher</h2>
     * <p>Get the <b>first</b> {@link Launcher} annotation of in the specified package</p>
     * @param packageName The package to search in
     * @return The <b>first</b> {@link Launcher} annotation
     */
    public static LauncherUtils getLauncher(final String packageName) {
        return new LauncherUtils(AnnotationUtils.findLauncher(packageName).stream().findFirst().orElseThrow());
    }

    /**
     * <h2>getRootPath</h2>
     * <p>The root path is inside the use.home directory, iff the {@link Launcher#root()} is not definied the {
     * @link Launcher#name()} is used as root directory</p>
     * @return The root path of the launcher
     */
    public String getRootPath() {
        return System.getProperty("user.home") + File.separator + (launcher.root().isEmpty() ? launcher.name() : launcher.root()) + File.separator;
    }

    /**
     * <h2>getIcon</h2>
     * <p>Get the icon of the launcher</p>
     * @return The icon of the launcher
     */
    public String getIcon() {
        return launcher.icon();
    }

    /**
     * <h2>getDescription</h2>
     * <p>Get the description of the launcher</p>
     * @return The description of the launcher
     */
    public String getDescription() {
        return launcher.description();
    }

    /**
     * <h2>getLicense</h2>
     * <p>Get the license of the launcher</p>
     * @return The license of the launcher
     */
    public String getLicense() {
        return launcher.license();
    }

    /**
     * <h2>getWebsite</h2>
     * <p>Get the website of the launcher</p>
     * @return The website of the launcher
     */
    public String getWebsite() {
        return launcher.website();
    }

    /**
     * <h2>getAuthor</h2>
     * <p>Get the author of the launcher</p>
     * @return The author of the launcher
     */
    public String getAuthor() {
        return launcher.author();
    }

    /**
     * <h2>getVersion</h2>
     * <p>Get the version of the launcher</p>
     * @return The version of the launcher
     */
    public String getVersion() {
        return launcher.version();
    }

    /**
     * <h2>getName</h2>
     * <p>Get the name of the launcher</p>
     * @return The name of the launcher
     */
    public String getName() {
        return launcher.name();
    }

}
