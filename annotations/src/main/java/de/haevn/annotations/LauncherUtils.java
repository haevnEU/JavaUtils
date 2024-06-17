package de.haevn.annotations;

import java.io.File;

public class LauncherUtils {
    private final Launcher launcher;

    private LauncherUtils(final Launcher launcher) {
        this.launcher = launcher;
    }

    public static LauncherUtils getLauncher() {
        return new LauncherUtils(AnnotationUtils.findLauncher("de.haevn").stream().findFirst().orElseThrow());
    }

    public String getRootPath() {
        return System.getProperty("user.home") + File.separator + (launcher.root().isEmpty() ? launcher.name() : launcher.root()) + File.separator;
    }

    public String getIcon() {
        return launcher.icon();
    }

    public String getDescription() {
        return launcher.description();
    }

    public String getLicense() {
        return launcher.license();
    }

    public String getWebsite() {
        return launcher.website();
    }

    public String getAuthor() {
        return launcher.author();
    }

    public String getVersion() {
        return launcher.version();
    }

    public String getName() {
        return launcher.name();
    }

}
