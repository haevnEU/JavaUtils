package de.haevn.utils.system;

import com.sun.management.OperatingSystemMXBean;
import de.haevn.utils.enumeration.BinarySize;

import java.io.File;
import java.lang.management.ManagementFactory;

/**
 * A simple class for memory information.
 * @version 1.1
 * @since 1.1
 * @author haevn
 */
public final class Memory {
    private Memory(){}

    public static final class RAM{
        private RAM(){}
        private static final OperatingSystemMXBean OS_BEAN = (OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
        public static long getMemoryTotal(){
            return OS_BEAN.getTotalMemorySize();
        }

        public static long getMemoryTotal(BinarySize unit){
            return getMemoryTotal() / unit.getValue();
        }

        public static long getMemoryFree(){
            return OS_BEAN.getFreeMemorySize();
        }

        public static long getMemoryFree(BinarySize unit){
            return getMemoryFree() / unit.getValue();
        }

        public static long getMemoryUsed(){
            return getMemoryTotal() - getMemoryFree();
        }

        public static long getMemoryUsed(BinarySize unit){
            return getMemoryUsed() / unit.getValue();
        }


        public static long getMemoryUsedPercentage(){
            return (getMemoryUsed() * 100) / getMemoryTotal();
        }

        public static long getMemoryFreePercentage(){
            return (getMemoryFree() * 100) / getMemoryTotal();
        }


    }

    /**
     * A simple class for hard drive information.
     * @version 1.1
     * @since 1.1
     * @author haevn
     */
    public static final class HardDrive {
        private HardDrive() {}

        public static long getSpaceForDrive(final String drive) {
            if (!new File(drive).exists()) {
                return -1;
            }
            return new File(drive).getTotalSpace();
        }

        public static long getSpaceForDrive(final String drive, final BinarySize unit) {
            return getSpaceForDrive(drive) / unit.getValue();
        }

        public static long getFreeSpaceForDrive(final String drive) {
            if (!new File(drive).exists()) {
                return -1;
            }
            return new File(drive).getFreeSpace();
        }

        public static long getFreeSpaceForDrive(final String drive, final BinarySize unit) {
            return getFreeSpaceForDrive(drive) / unit.getValue();
        }

        public static long getUsedSpaceForDrive(final String drive) {
            if (!new File(drive).exists()) {
                return -1;
            }
            return getSpaceForDrive(drive) - getFreeSpaceForDrive(drive);
        }

        public static long getUsedSpaceForDrive(final String drive, final BinarySize unit) {
            return getUsedSpaceForDrive(drive) / unit.getValue();
        }

        public static long getUsedSpacePercentageForDrive(final String drive) {
            if (!new File(drive).exists()) {
                return -1;
            }
            return (getUsedSpaceForDrive(drive) * 100) / getSpaceForDrive(drive);
        }

        public static long getUsedSpacePercentageForDrive(final String drive, final BinarySize unit) {
            return getUsedSpacePercentageForDrive(drive) / unit.getValue();
        }
    }

    /**
     * A simple class for virtual machine information.
     * @version 1.1
     * @since 1.1
     * @author haevn
     */
    public static final class VirtualMachine {
        private VirtualMachine() {
        }

        public static long getMemoryTotal() {
            return Runtime.getRuntime().totalMemory();
        }
        public static long getMemoryTotal(BinarySize unit) {
            return getMemoryTotal() / unit.getValue();
        }

        public static long getMemoryFree() {
            return Runtime.getRuntime().freeMemory();
        }

        public static long getMemoryFree(BinarySize unit) {
            return getMemoryFree() / unit.getValue();
        }

        public static long getMemoryUsed() {
            return getMemoryTotal() - getMemoryFree();
        }

        public static long getMemoryUsed(BinarySize unit) {
            return getMemoryUsed() / unit.getValue();
        }

        public static long getMemoryMax() {
            return Runtime.getRuntime().maxMemory();
        }

        public static long getMemoryMax(BinarySize unit) {
            return getMemoryMax() / unit.getValue();
        }

        public static long getMemoryUsedPercentage() {
            return (getMemoryUsed() * 100) / getMemoryTotal();
        }

        public static long getMemoryFreePercentage() {
            return (getMemoryFree() * 100) / getMemoryTotal();
        }
    }

}
