package de.haevn.utils;


import java.awt.*;

/**
 * <h1>ColorUtils</h1>
 * <p>ColorUtils provides utility methods for colors.</p>
 * <p>It provides methods to convert colors to different formats.</p>
 * <p>Example:</p>
 * <pre>{@code
 * final var color = new ColorUtils(255, 255, 255);
 * final var hex = color.getColorAsHex();
 * }</pre>
 * <p>Output: #FFFFFF</p>
 * <p>Example:</p>
 * <pre>{@code
 * final var color = new ColorUtils(255, 255, 255);
 * final var hex = color.getColorAsHex("#");
 * }</pre>
 *
 * @author haevn
 * @version 1.1
 * @since 1.0
 */
public final class ColorUtils {
    private final Color color;

    /**
     * <h2>ColorUtils({@link Color})</h2>
     * <p>Constructor to create a new instance of ColorUtils with a given color.</p>
     *
     * @param color The color to use.
     */
    private ColorUtils(final Color color) {
        this.color = color;
    }

    /**
     * <h2>ColorUtils(String)</h2>
     * <p>Constructor to create a new instance of ColorUtils with a given hex string.</p>
     *
     * @param hex The hex value.
     */
    public ColorUtils(final String hex) {
        this(new Color(Integer.parseInt(hex, 16)));
    }

    /**
     * <h2>ColorUtils(int)</h2>
     * <p>Constructor to create a new instance of ColorUtils with a given hex value.</p>
     *
     * @param hex The hex value.
     */
    public ColorUtils(final int hex) {
        this(new Color(hex));
    }

    /**
     * <h2>ColorUtils(int, int, int)</h2>
     * <p>Constructor to create a new instance of ColorUtils with a given RGB value.</p>
     *
     * @param r The red value.
     * @param g The green value.
     * @param b The blue value.
     */
    public ColorUtils(final int r, final int g, final int b) {
        this(new Color(r, g, b));

    }

    /**
     * <h2>ColorUtils(long, long, long)</h2>
     * <p>Constructor to create a new instance of ColorUtils with a given RGB value.</p>
     *
     * @param r The red value.
     * @param g The green value.
     * @param b The blue value.
     */
    public ColorUtils(final long r, final long g, final long b) {
        this(new Color((int) r, (int) g, (int) b));
    }

    /**
     * <h2>ColorUtils(double, double, double)</h2>
     * <p>Constructor to create a new instance of ColorUtils with a given RGB value.</p>
     *
     * @param r The red value between 0 and 1.
     * @param g The green value between 0 and 1.
     * @param b The blue value between 0 and 1.
     */
    public ColorUtils(final double r, final double g, final double b) {
        this(new Color((int) (r * 255), (int) (g * 255), (int) (b * 255)));
    }

    /**
     * <h2>getColorAsLong</h2>
     * <p>Converts the color to a long value.</p>
     * <p>Example:</p>
     * <pre>{@code
     * final var color = new ColorUtils(255, 255, 255);
     * final var longValue = color.getColorAsLong();
     * }</pre>
     * <p>Output: 16777215</p>
     *
     * @return The color as long.
     */
    public long getColorAsLong() {
        long color2 = ((long) color.getRed() << 16) | ((long) color.getGreen() << 8) | color.getBlue();
        return color2 & 0xFFFFFFL;
    }

    /**
     * <h2>getColorAsHex</h2>
     * <p>Converts the color to a hex value.</p>
     * <p>Example:</p>
     * <pre>{@code
     * final var color = new ColorUtils(255, 255, 255);
     * final var hex = color.getColorAsHex(0x);
     * }</pre>
     * <p>Output: 0xFFFFFF</p>
     *
     * @param prefix The prefix to use.
     * @return The color as hex.
     */
    public String getColorAsHex(final String prefix) {
        return prefix + String.format("%06X", getColorAsLong() & 0xFFFFFFL);
    }

    /**
     * <h2>getColorAsHex</h2>
     * <p>Converts the color to a hex value.</p>
     * <p>Example:</p>
     * <pre>{@code
     * final var color = new ColorUtils(255, 255, 255);
     * final var hex = color.getColorAsHex();
     * }</pre>
     * <p>Output: #FFFFFF</p>
     *
     * @return The color as hex.
     */
    public String getColorAsHex() {
        return getColorAsHex("#");
    }
}