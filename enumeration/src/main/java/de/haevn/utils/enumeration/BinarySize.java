package de.haevn.utils.enumeration;

/**
 * <h1>BinarySize</h1>
 * <br>
 * <br> A simple enum for binary sizes, the base size is one Byte.
 * <br> The enum provides the following sizes:
 * <ul>
 *     <li>BYTE</li>
 *     <li>KILOBYTE</li>
 *     <li>MEGABYTE</li>
 *     <li>GIGABYTE</li>
 *     <li>TERABYTE</li>
 *     <li>PETABYTE</li>
 *     <li>EXABYTE</li>
 *     <li>ZETTABYTE</li>
 *     <li>YOTTABYTE</li>
 * </ul>
 *
 * @author haevn
 * @version 1.0
 * @since 1.0
 */
public enum BinarySize {
    BYTE(1),
    KILOBYTE(1024),
    MEGABYTE(KILOBYTE.getValue() * 1024),
    GIGABYTE(MEGABYTE.getValue() * 1024),
    TERABYTE(GIGABYTE.getValue() * 1024),
    PETABYTE(TERABYTE.getValue() * 1024),
    EXABYTE(PETABYTE.getValue() * 1024),
    ZETTABYTE(EXABYTE.getValue() * 1024),
    YOTTABYTE(ZETTABYTE.getValue() * 1024);

    private final long value;

    BinarySize(final long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }
}
