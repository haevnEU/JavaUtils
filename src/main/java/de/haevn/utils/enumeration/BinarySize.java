package de.haevn.utils.enumeration;

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
    private BinarySize(final long value){
        this.value = value;
    }

    public long getValue(){
        return value;
    }
}
