package de.haevn.utils;

import java.text.NumberFormat;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * <h1>NumberUtils</hjson>
 * <p>This class provides utility methods for numbers.</p>
 *
 * <p>Note, new features will be added in the future.</p>
 *
 * @author haevn
 * @version 1.0
 * @since 1.0
 */
public final class NumberUtils {
    private NumberUtils() {
    }

    /**
     * <h2>getDecimalFormat</h2>
     * <p>Gets the default decimal format.</p>
     *
     * @return The default decimal format
     */
    public static NumberFormat getDecimalFormat() {
        return NumberFormat.getInstance();
    }


    /**
     * <h1>RandomNumber</h1>
     * <p>This class provides methods to generate random numbers.</p>
     *
     * @author haevn
     * @version 1.0
     * @since 1.0
     */
    public static class RandomNumber {
        private RandomNumber() {
        }

        /**
         * <h2>randomNumber(int)</h2>
         * <p>Generates a list of random numbers.</p>
         * <p>Example:</p>
         * <pre>{@code
         * final var numbers = NumberUtils.RandomNumber.randomNumber(10);
         * }</pre>
         *
         * @param amount The amount of numbers
         * @return The list of random numbers
         */
        public static List<Integer> randomNumber(final int amount) {
            List<Integer> numbers = new java.util.ArrayList<>();
            for (int i = 0; i < amount; i++) {
                Random random = new Random(java.lang.System.currentTimeMillis());
                numbers.add(random.nextInt());
            }

            return numbers;
        }

        /**
         * <h2>randomNumberStream(int)</h2>
         * <p>Generates a stream of random numbers.</p>
         * <p>Example:</p>
         * <pre>{@code
         * final var numbers = NumberUtils.RandomNumber.randomNumberStream(10);
         * }</pre>
         *
         * @param amount The amount of numbers
         * @return The stream of random numbers
         */
        public static Stream<Integer> randomNumberStream(final int amount) {
            return randomNumber(amount).stream();
        }

        /**
         * <h2>randomDouble(int)</h2>
         * <p>Generates a list of random double numbers.</p>
         * <p>Example:</p>
         * <pre>{@code
         * final var numbers = NumberUtils.RandomNumber.randomDouble(10);
         * }</pre>
         *
         * @param amount The amount of numbers
         * @return The list of random double numbers
         */
        public static List<Double> randomDouble(final int amount) {
            List<Double> numbers = new java.util.ArrayList<>();
            for (int i = 0; i < amount; i++) {
                Random random = new Random(java.lang.System.currentTimeMillis());
                numbers.add(random.nextDouble());
            }

            return numbers;
        }

        /**
         * <h2>randomDoubleStream(int)</h2>
         * <p>Generates a stream of random double numbers.</p>
         * <p>Example:</p>
         * <pre>{@code
         * final var numbers = NumberUtils.RandomNumber.randomDoubleStream(10);
         * }</pre>
         *
         * @param amount The amount of numbers
         * @return The stream of random double numbers
         */
        public static Stream<Double> randomDoubleStream(final int amount) {
            return randomDouble(amount).stream();
        }
    }

    /**
     * <h1>TimeConversion</h1>
     * <p>This class provides methods to convert time.</p>
     *
     * @author haevn
     * @version 1.0
     * @since 1.0
     */
    public static final class TimeConversion {
        private TimeConversion() {
        }

        /**
         * <h2>msToString(double)</h2>
         * <p>Converts milliseconds to a string with the format HH:mm:ss.</p>
         *
         * @param milliseconds The milliseconds
         * @return The string
         */
        public static String msToString(final double milliseconds) {
            return msToString(Math.round(milliseconds));
        }

        /**
         * <h2>msToString(long)</h2>
         * <p>Converts milliseconds to a string with the format HH:mm:ss.</p>
         *
         * @param milliseconds The milliseconds
         * @return The string
         */
        public static String msToString(final long milliseconds) {
            int seconds = (int) (milliseconds / 1000) % 60;
            int minutes = (int) ((milliseconds / (1000 * 60)) % 60);
            int hours = (int) ((milliseconds / (1000 * 60 * 60)) % 24);
            String negative = "";
            if (minutes < 0) {
                minutes *= -1;
                negative = "-";
            }
            if (seconds < 0) {
                seconds *= -1;
                negative = "-";
            }
            return negative + String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
        }
    }

    /**
     * <h1>StringToNumberConversion</h1>
     * <p>This class provides methods to convert a string to a number.</p>
     *
     * @author haevn
     * @version 1.0
     * @since 1.0
     */
    public static final class StringToNumberConversion {
        private StringToNumberConversion() {
        }

        /**
         * <h2>toInteger(String)</h2>
         * <p>Converts a string to an integer.</p>
         * <p>If the string is not a number it will return 0.</p>
         * <p>Example:</p>
         * <pre>{@code
         * final var number = NumberUtils.StringToNumberConversion.toInteger("10");
         * }</pre>
         *
         * @param str The string
         * @return The integer
         */
        public static Integer toInteger(final String str) {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException e) {
                return 0;
            }
        }

        /**
         * <h2>toLong(String)</h2>
         * <p>Converts a string to a long.</p>
         * <p>If the string is not a number it will return 0.</p>
         * <p>Example:</p>
         * <pre>{@code
         * final var number = NumberUtils.StringToNumberConversion.toLong("10");
         * }</pre>
         *
         * @param str The string
         * @return The long
         */
        public static Long toLong(final String str) {
            try {
                return Long.parseLong(str);
            } catch (NumberFormatException e) {
                return 0L;
            }
        }

        /**
         * <h2>toDouble(String)</h2>
         * <p>Converts a string to a double.</p>
         * <p>If the string is not a number it will return 0.0.</p>
         * <p>Example:</p>
         * <pre>{@code
         * final var number = NumberUtils.StringToNumberConversion.toDouble("10.5");
         * }</pre>
         *
         * @param str The string
         * @return The double
         */
        public static Double toDouble(final String str) {
            try {
                return Double.parseDouble(str);
            } catch (NumberFormatException e) {
                return 0.0;
            }
        }

        /**
         * <h2>numberToRoundText(double)</h2>
         * <p>Converts a number to a round text.</p>
         * <p>If the string is not a number it will return 0.0.</p>
         * <p>Example:</p>
         * <pre>{@code
         * final var number = NumberUtils.StringToNumberConversion.numberToRoundText(10.5);
         * }</pre>
         *
         * @param number The number
         * @return The round text
         */
        public static String numberToRoundText(final double number) {
            return numberToRoundText(number, false);
        }

        /**
         * <h2>numberToRoundText(double, boolean)</h2>
         * <p>Converts a number to a round text, when separator is true it will use the default decimal format.</p>
         * <p>Example:</p>
         * <pre>{@code
         * final var number = NumberUtils.StringToNumberConversion.numberToRoundText(10.5, true);
         * }</pre>
         *
         * @param number    The number
         * @param separator The separator
         * @return The round text
         */
        public static String numberToRoundText(final double number, final boolean separator) {
            try {
                if (separator) {
                    return getDecimalFormat().format(Math.round(number));
                }
                return Integer.toString((int) Math.round(number));
            } catch (NumberFormatException e) {
                return "0";
            }
        }
    }

    /**
     * <h2>Conversion</h2>
     * <p>This class provides method to convert a value between different number systems.</p>
     * <p>Currently supported</p>
     * <ul>
     *     <li>Hex</li>
     *     <li>Binary</li>
     *     <li>Octal</li>
     *     <li>Decimal</li>
     * </ul>
     *
     * @author haevn
     * @version 1.0
     * @since 2.1
     */
    public static final class Conversion {
        private Conversion() {
        }

        /**
         * <h2>integerToHex(int)</h2>
         * <p>Converts an integer to a hex string.</p>
         *
         * <p>Example:</p>
         * <pre>{@code
         * final var hex = NumberUtils.Conversion.integerToHex(255);
         * }</pre>
         *
         * @param number The integer
         * @return The hex string
         */
        public static String integerToHex(final int number) {
            return Integer.toHexString(number);
        }

        /**
         * <h2>integerToBinary(int)</h2>
         * <p>Converts an integer to a binary string.</p>
         *
         * <p>Example:</p>
         * <pre>{@code
         * final var binary = NumberUtils.Conversion.integerToBinary(255);
         * }</pre>
         *
         * @param number The integer
         * @return The binary string
         */
        public static String integerToBinary(final int number) {
            return Integer.toBinaryString(number);
        }

        /**
         * <h2>integerToOctal(int)</h2>
         * <p>Converts an integer to an octal string.</p>
         *
         * <p>Example:</p>
         * <pre>{@code
         * final var octal = NumberUtils.Conversion.integerToOctal(255);
         * }</pre>
         *
         * @param number The integer
         * @return The octal string
         */
        public static String integerToOctal(final int number) {
            return Integer.toOctalString(number);
        }

        /**
         * <h2>hexToInteger(String)</h2>
         * <p>Converts a hex string to an integer.</p>
         *
         * <p>Example:</p>
         * <pre>{@code
         * final var number = NumberUtils.Conversion.hexToInteger("FF");
         * }</pre>
         *
         * @param hex The hex string
         * @return The integer
         */
        public static int hexToInteger(final String hex) {
            return Integer.parseInt(hex, 16);
        }

        /**
         * <h2>hexToBinary(String)</h2>
         * <p>Converts a hex string to a binary string.</p>
         *
         * <p>Example:</p>
         * <pre>{@code
         * final var binary = NumberUtils.Conversion.hexToBinary("FF");
         * }</pre>
         *
         * @param hex The hex string
         * @return The binary string
         */
        public static String hexToBinary(final String hex) {
            return Integer.toBinaryString(hexToInteger(hex));
        }

        /**
         * <h2>hexToOctal(String)</h2>
         * <p>Converts a hex string to an octal string.</p>
         *
         * <p>Example:</p>
         * <pre>{@code
         * final var octal = NumberUtils.Conversion.hexToOctal("FF");
         * }</pre>
         *
         * @param hex The hex string
         * @return The octal string
         */
        public static String hexToOctal(final String hex) {
            return Integer.toOctalString(hexToInteger(hex));
        }

        /**
         * <h2>binaryToInteger(String)</h2>
         * <p>Converts a binary string to an integer.</p>
         *
         * <p>Example:</p>
         * <pre>{@code
         * final var number = NumberUtils.Conversion.binaryToInteger("1111");
         * }</pre>
         *
         * @param binary The binary string
         * @return The integer
         */
        public static int binaryToInteger(final String binary) {
            return Integer.parseInt(binary, 2);
        }

        /**
         * <h2>binaryToHex(String)</h2>
         * <p>Converts a binary string to a hex string.</p>
         *
         * <p>Example:</p>
         * <pre>{@code
         * final var hex = NumberUtils.Conversion.binaryToHex("1111");
         * }</pre>
         *
         * @param binary The binary string
         * @return The hex string
         */
        public static String binaryToHex(final String binary) {
            return Integer.toHexString(binaryToInteger(binary));
        }

        /**
         * <h2>binaryToOctal(String)</h2>
         * <p>Converts a binary string to an octal string.</p>
         *
         * <p>Example:</p>
         * <pre>{@code
         * final var octal = NumberUtils.Conversion.binaryToOctal("1111");
         * }</pre>
         *
         * @param binary The binary string
         * @return The octal string
         */
        public static String binaryToOctal(final String binary) {
            return Integer.toOctalString(binaryToInteger(binary));
        }

        /**
         * <h2>longToHex(long)</h2>
         * <p>Converts a long to a hex string.</p>
         *
         * <p>Example:</p>
         * <pre>{@code
         * final var hex = NumberUtils.Conversion.longToHex(255);
         * }</pre>
         *
         * @param errorCode The long
         * @return The hex string
         */
        public static String longToHex(long errorCode) {
            return Long.toHexString(errorCode);
        }

        /**
         * <h2>hexToLong(String)</h2>
         * <p>Converts a hex string to a long.</p>
         *
         * <p>Example:</p>
         * <pre>{@code
         * final var number = NumberUtils.Conversion.hexToLong("FF");
         * }</pre>
         *
         * @param hex The hex string
         * @return The long
         */
        public static long hexToLong(String hex) {
            return Long.parseLong(hex, 16);
        }

        /**
         * <h2>binaryToLong(String)</h2>
         * <p>Converts a binary string to a long.</p>
         * <p>Example:</p>
         * <pre>{@code
         * final var number = NumberUtils.Conversion.binaryToLong("1111");
         * }</pre>
         *
         * @param number The binary string
         * @return The long
         */
        public static String numberToShortValue(final int number) {
            if (number < 1000) return Integer.toString(number);
            if (number < 1000000) return getDecimalFormat().format(number / 1000.0) + "K";
            if (number < 1000000000) return getDecimalFormat().format(number / 1000000.0) + "M";
            return getDecimalFormat().format(number / 1000000000.0) + "B";
        }

        /**
         * <h2>numberToShortValue(double)</h2>
         * <p>Converts a number to a short value.</p>
         * <p>Example:</p>
         * <pre>{@code
         * final var number = NumberUtils.Conversion.numberToShortValue(1000);
         * }</pre>
         *
         * @param number The number
         * @return The short value
         */
        public static String numberToShortValue(final double number) {
            return numberToShortValue((int) number);
        }
    }
}
