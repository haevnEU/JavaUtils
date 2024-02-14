package de.haevn.utils.system;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

/**
 * A simple class for CPU information.
 * @version 1.0
 * @since 1.0
 * @see OperatingSystemMXBean
 * @see ManagementFactory
 * @see Runtime
 * @see CPU
 * @author haevn
 */
public class CPU {
    private static final OperatingSystemMXBean OS_BEAN = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    private CPU(){}

    public static int getCoreCount() {
        return Runtime.getRuntime().availableProcessors();
    }

    public static int getCoreCountPhysical() {
        return Runtime.getRuntime().availableProcessors();
    }

    public static double getLoad() {
        return OS_BEAN.getCpuLoad();
    }

    public static double getProcessCpuLoad() {
        return OS_BEAN.getProcessCpuLoad();
    }

}
