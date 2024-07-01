package de.haevn.utils.system;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

/**
 * <h1>CPU</h1>
 * <p>The CPU class provides information about the CPU of the system.</p>
 * <p>It provides information about the number of cores, the load of the CPU and the load of the current process.</p>
 * <p>It uses the OperatingSystemMXBean to get the information.</p>
 *
 * @author haevn
 * @version 1.0
 * @see OperatingSystemMXBean
 * @see ManagementFactory
 * @see Runtime
 * @see CPU
 * @since 1.0
 */
public final class CPU {
    /**
     * The OperatingSystemMXBean to get the information.
     */
    private static final OperatingSystemMXBean OS_BEAN = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    private CPU() {
    }

    /**
     * <h2>getCoreCount()</h2>
     * <p>Gets the number of cores of the CPU using the {@link Runtime#availableProcessors()}</p>
     *
     * @return Number of cores of the CPU.
     */
    public static int getCoreCount() {
        return Runtime.getRuntime().availableProcessors();
    }

    /**
     * <h2>getCoreCountPhysical()</h2>
     * <p>Gets the number of physical cores of the CPU using the {@link Runtime#availableProcessors()}</p>
     *
     * @return Number of physical cores of the CPU.
     */
    public static int getCoreCountPhysical() {
        return Runtime.getRuntime().availableProcessors();
    }

    /**
     * <h2>getLoad()</h2>
     * <p>Gets the load of the CPU using the {@link OperatingSystemMXBean#getCpuLoad()}</p>
     *
     * @return Load of the CPU.
     */
    public static double getLoad() {
        return OS_BEAN.getCpuLoad();
    }

    /**
     * <h2>getProcessCpuLoad()</h2>
     * <p>Gets the load of the current process using the {@link OperatingSystemMXBean#getProcessCpuLoad()}</p>
     *
     * @return Load of the current process.
     */
    public static double getProcessCpuLoad() {
        return OS_BEAN.getProcessCpuLoad();
    }

}
