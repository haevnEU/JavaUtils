package de.haevn.utils.crypto;

import de.haevn.annotations.Draft;

@Draft
public final class Base32Util {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567";
    private static final String PADDING = "=";
    private static final int MASK = 0x1F;

    private Base32Util() {}

    public static String encode(final String data) {
        return encode(data.getBytes());
    }

    public static String encode(final byte[] data) {
        final var length = (data.length * 8 + 4) * 0.2;
        final StringBuilder result = new StringBuilder((int)length);
        int buffer = data[0];
        int next = 1;
        int bitsLeft = 8;
        while (bitsLeft > 0 || next < data.length) {
            if (bitsLeft < 5) {
                if (next < data.length) {
                    buffer <<= 8;
                    buffer |= data[next++] & 0xFF;
                    bitsLeft += 8;
                } else {
                    final int pad = 5 - bitsLeft;
                    buffer <<= pad;
                    bitsLeft += pad;
                }
            }
            final int index = MASK & (buffer >> (bitsLeft - 5));
            bitsLeft -= 5;
            result.append(ALPHABET.charAt(index));
        }

        final int padding = result.length() % 8;
        if (padding > 0) {
            result.append(PADDING.repeat(8 - padding));
        }

        return result.toString();
    }

    public static byte[] decode(final String encodedString) {
        final StringBuilder sb = new StringBuilder();
        for (char c : encodedString.toCharArray()) {
            final int index = ALPHABET.indexOf(c);
            sb.append(String.format("%5s", Integer.toBinaryString(index)).replace(' ', '0'));
        }

        final String binaryString = sb.toString();
        final byte[] bytes = new byte[binaryString.length() / 8];
        for (int i = 0; i < bytes.length; i++) {
            final String byteString = binaryString.substring(i * 8, (i + 1) * 8);
            bytes[i] = (byte) Integer.parseInt(byteString, 2);
        }

        return bytes;
    }
}
