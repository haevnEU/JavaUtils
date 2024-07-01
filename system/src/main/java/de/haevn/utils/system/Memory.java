package de.haevn.utils.system;

import com.sun.management.OperatingSystemMXBean;
import de.haevn.utils.enumeration.BinarySize;

import java.io.File;
import java.lang.management.ManagementFactory;

/**
 * <h1>Memory</h1>
 * <p>This class provides information about the memory.</p>
 * <p>It provides information about the RAM, hard drive and virtual machine memory.</p>
 *
 * @author haevn
 * @version 1.1
 * @since 1.1
 */
public final class Memory {
    private Memory() {
    }

    /**
     * <h1>RAM</h1>
     * A simple class for RAM information.
     *
     * @author haevn
     * @version 1.1
     * @since 1.1
     */
    public static final class RAM {
        private RAM() {
        }

        /**
         * This attribute is used to get the operating system memory information.
         */
        private static final OperatingSystemMXBean OS_BEAN = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        /**
         * <h2>getMemoryTotal()</h2>
         * This method is used to get the total memory of the operating system.
         *
         * @return The total memory of the operating system.
         */
        public static long getMemoryTotal() {
            return OS_BEAN.getTotalMemorySize();
        }

        /**
         * <h2>getMemoryTotal({@link BinarySize})</h2>
         * This method is used to get the total memory of the operating system.
         *
         * @param unit The unit of the memory.
         * @return The total memory of the operating system.
         */
        public static long getMemoryTotal(BinarySize unit) {
            return getMemoryTotal() / unit.getValue();
        }

        /**
         * <h2>getMemoryFree()</h2>
         * This method is used to get the free memory of the operating system.
         *
         * @return The free memory of the operating system.
         */
        public static long getMemoryFree() {
            return OS_BEAN.getFreeMemorySize();
        }

        /**
         * <h2>getMemoryFree({@link BinarySize})</h2>
         * This method is used to get the free memory of the operating system.
         *
         * @param unit The unit of the memory.
         * @return The free memory of the operating system.
         */
        public static long getMemoryFree(BinarySize unit) {
            return getMemoryFree() / unit.getValue();
        }

        /**
         * <h2>getMemoryUsed()</h2>
         * This method is used to get the used memory of the operating system.
         *
         * @return The used memory of the operating system.
         */
        public static long getMemoryUsed() {
            return getMemoryTotal() - getMemoryFree();
        }

        /**
         * <h2>getMemoryUsed({@link BinarySize})</h2>
         * This method is used to get the used memory of the operating system.
         *
         * @param unit The unit of the memory.
         * @return The used memory of the operating system.
         */
        public static long getMemoryUsed(BinarySize unit) {
            return getMemoryUsed() / unit.getValue();
        }

        /**
         * <h2>getMemoryUsedPercentage()</h2>
         * This method is used to get the used memory percentage of the operating system.
         *
         * @return The used memory percentage of the operating system.
         */
        public static long getMemoryUsedPercentage() {
            return (getMemoryUsed() * 100) / getMemoryTotal();
        }

        /**
         * <h2>getMemoryFreePercentage()</h2>
         * This method is used to get the free memory percentage of the operating system.
         *
         * @return The free memory percentage of the operating system.
         */
        public static long getMemoryFreePercentage() {
            return (getMemoryFree() * 100) / getMemoryTotal();
        }


    }

    /**
     * <h1>HardDrive</h1>
     * <p>A simple class for hard drive information.</p>
     * <p>It provides information about the hard drive space.</p>
     * <p>It provides information about the space, free space, used space and used space percentage of the hard drive.</p>
     *
     * @author haevn
     * @version 1.1
     * @since 1.1
     */
    public static final class HardDrive {
        private HardDrive() {
        }

        /**
         * <h2>getSpaceForDrive(String)</h2>
         * <p>This method is used to get the total space of the drive, if the the size is not available it returns -1.</p>
         *
         * <h3>Example:</h3>
         * <pre>{@code
         * long space = HardDrive.getSpaceForDrive("C:\\"); // for windows
         * long space = HardDrive.getSpaceForDrive("/"); // for unix based
         * }</pre>
         *
         * @param drive The drive to get the space from.
         * @return The space of the drive.
         */
        public static long getSpaceForDrive(final String drive) {
            if (!new File(drive).exists()) {
                return -1;
            }
            return new File(drive).getTotalSpace();
        }

        /**
         * <h2>getSpaceForDrive(String, {@link BinarySize})</h2>
         * <p>This method is used to get the total space of the drive, if the the size is not available it returns -1.</p>
         *
         * <h3>Example:</h3>
         * <pre>{@code
         * long space = HardDrive.getSpaceForDrive("C:\\", BinarySize.GB); // for windows
         * long space = HardDrive.getSpaceForDrive("/", BinarySize.GB); // for unix based
         * }</pre>
         *
         * @param drive The drive to get the space from.
         * @param unit  The unit of the space.
         * @return The space of the drive.
         */
        public static long getSpaceForDrive(final String drive, final BinarySize unit) {
            return getSpaceForDrive(drive) / unit.getValue();
        }

        /**
         * <h2>getFreeSpaceForDrive(String)</h2>
         * <p>This method is used to get the free space of the drive, if the the size is not available it returns -1.</p>
         *
         * <h3>Example:</h3>
         * <pre>{@code
         * long space = HardDrive.getFreeSpaceForDrive("C:\\"); // for windows
         * long space = HardDrive.getFreeSpaceForDrive("/"); // for unix based
         * }</pre>
         *
         * @param drive The drive to get the free space from.
         * @return The free space of the drive.
         */
        public static long getFreeSpaceForDrive(final String drive) {
            if (!new File(drive).exists()) {
                return -1;
            }
            return new File(drive).getFreeSpace();
        }

        /**
         * <h2>getFreeSpaceForDrive(String, {@link BinarySize})</h2>
         * <p>This method is used to get the free space of the drive, if the the size is not available it returns -1.</p>
         *
         * <h3>Example:</h3>
         * <pre>{@code
         * long space = HardDrive.getFreeSpaceForDrive("C:\\", BinarySize.GB); // for windows
         * long space = HardDrive.getFreeSpaceForDrive("/", BinarySize.GB); // for unix based
         * }</pre>
         */
        public static long getFreeSpaceForDrive(final String drive, final BinarySize unit) {
            return getFreeSpaceForDrive(drive) / unit.getValue();
        }

        /**
         * <h2>getUsedSpaceForDrive(String)</h2>
         * <p>This method is used to get the used space of the drive, if the the size is not available it returns -1.</p>
         * <h3>Example:</h3>
         * <pre>{@code
         * long space = HardDrive.getUsedSpaceForDrive("C:\\"); // for windows
         * long space = HardDrive.getUsedSpaceForDrive("/"); // for unix based
         * }</pre>
         *
         * @param drive The drive to get the used space from.
         * @return The used space of the drive.
         */
        public static long getUsedSpaceForDrive(final String drive) {
            if (!new File(drive).exists()) {
                return -1;
            }
            return getSpaceForDrive(drive) - getFreeSpaceForDrive(drive);
        }

        /**
         * <h2>getUsedSpaceForDrive(String, {@link BinarySize})</h2>
         * <p>This method is used to get the used space of the drive, if the the size is not available it returns -1.</p>
         * <h3>Example:</h3>
         * <pre>{@code
         * long space = HardDrive.getUsedSpaceForDrive("C:\\", BinarySize.GB); // for windows
         * long space = HardDrive.getUsedSpaceForDrive("/", BinarySize.GB); // for unix based
         * }</pre>
         *
         * @param drive The drive to get the used space from.
         * @return The used space of the drive.
         */
        public static long getUsedSpaceForDrive(final String drive, final BinarySize unit) {
            return getUsedSpaceForDrive(drive) / unit.getValue();
        }

        /**
         * <h2>getUsedSpacePercentageForDrive(String)</h2>
         * <p>This method is used to get the used space percentage of the drive, if the the size is not available
         * it returns -1.</p>
         * <h3>Example:</h3>
         * <pre>{@code
         * long space = HardDrive.getUsedSpacePercentageForDrive("C:\\"); // for windows
         * long space = HardDrive.getUsedSpacePercentageForDrive("/"); // for unix based
         * }</pre>
         *
         * @param drive The drive to get the used space percentage from.
         * @return The used space percentage of the drive.
         */
        public static long getUsedSpacePercentageForDrive(final String drive) {
            if (!new File(drive).exists()) {
                return -1;
            }
            return (getUsedSpaceForDrive(drive) * 100) / getSpaceForDrive(drive);
        }

        /**
         * <h2>getUsedSpacePercentageForDrive(String, {@link BinarySize})</h2>
         * <p>This method is used to get the used space percentage of the drive, if the the size is not available
         * it returns -1.</p>
         * <h3>Example:</h3>
         * <pre>{@code
         * long space = HardDrive.getUsedSpacePercentageForDrive("C:\\", BinarySize.GB); // for windows
         * long space = HardDrive.getUsedSpacePercentageForDrive("/", BinarySize.GB); // for unix based
         * }</pre>
         *
         * @param drive The drive to get the used space percentage from.
         * @return The used space percentage of the drive.
         */
        public static long getUsedSpacePercentageForDrive(final String drive, final BinarySize unit) {
            return getUsedSpacePercentageForDrive(drive) / unit.getValue();
        }
    }

    /**
     * <h1>VirtualMachine</h1>
     * <p>A simple class for virtual machine memory information.</p>
     * <p>It provides information about the total memory, free memory, used memory, max memory,
     * used memory percentage and free memory percentage of the virtual machine.</p>
     * <p>It provides information about the memory of the virtual machine.</p>
     *
     * @author haevn
     * @version 1.1
     * @since 1.1
     */
    public static final class VirtualMachine {
        private VirtualMachine() {
        }

        /**
         * <h2>getMemoryTotal()</h2>
         * <p>This method is used to get the total memory of the virtual machine.</p>
         *
         * @return The total memory of the virtual machine.
         */
        public static long getMemoryTotal() {
            return Runtime.getRuntime().totalMemory();
        }

        /**
         * <h2>getMemoryTotal({@link BinarySize})</h2>
         * <p>This method is used to get the total memory of the virtual machine.</p>
         *
         * @param unit The unit of the memory.
         * @return The total memory of the virtual machine.
         */
        public static long getMemoryTotal(BinarySize unit) {
            return getMemoryTotal() / unit.getValue();
        }

        /**
         * <h2>getMemoryFree()</h2>
         * <p>This method is used to get the free memory of the virtual machine.</p>
         *
         * @return The free memory of the virtual machine.
         */
        public static long getMemoryFree() {
            return Runtime.getRuntime().freeMemory();
        }

        /**
         * <h2>getMemoryFree({@link BinarySize})</h2>
         * <p>This method is used to get the free memory of the virtual machine.</p>
         *
         * @param unit The unit of the memory.
         * @return The free memory of the virtual machine.
         */
        public static long getMemoryFree(BinarySize unit) {
            return getMemoryFree() / unit.getValue();
        }

        /**
         * <h2>getMemoryUsed()</h2>
         * <p>This method is used to get the used memory of the virtual machine.</p>
         *
         * @return The used memory of the virtual machine.
         */
        public static long getMemoryUsed() {
            return getMemoryTotal() - getMemoryFree();
        }

        /**
         * <h2>getMemoryUsed({@link BinarySize})</h2>
         * <p>This method is used to get the used memory of the virtual machine.</p>
         *
         * @param unit The unit of the memory.
         * @return The used memory of the virtual machine.
         */
        public static long getMemoryUsed(BinarySize unit) {
            return getMemoryUsed() / unit.getValue();
        }

        /**
         * <h2>getMemoryMax()</h2>
         * <p>This method is used to get the max memory of the virtual machine.</p>
         *
         * @return The max memory of the virtual machine.
         */
        public static long getMemoryMax() {
            return Runtime.getRuntime().maxMemory();
        }

        /**
         * <h2>getMemoryMax({@link BinarySize})</h2>
         * <p>This method is used to get the max memory of the virtual machine.</p>
         *
         * @param unit The unit of the memory.
         * @return The max memory of the virtual machine.
         */
        public static long getMemoryMax(BinarySize unit) {
            return getMemoryMax() / unit.getValue();
        }

        /**
         * <h2>getMemoryUsedPercentage()</h2>
         * <p>This method is used to get the used memory percentage of the virtual machine.</p>
         *
         * @return The used memory percentage of the virtual machine.
         */
        public static long getMemoryUsedPercentage() {
            return (getMemoryUsed() * 100) / getMemoryTotal();
        }

        /**
         * <h2>getMemoryFreePercentage()</h2>
         * <p>This method is used to get the free memory percentage of the virtual machine.</p>
         *
         * @return The free memory percentage of the virtual machine.
         */
        public static long getMemoryFreePercentage() {
            return (getMemoryFree() * 100) / getMemoryTotal();
        }
    }

}
