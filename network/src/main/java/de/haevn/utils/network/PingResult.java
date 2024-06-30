package de.haevn.utils.network;

/**
 * <h1>PingResult</h1>
 * <br>
 * <p>The result of a ping request.</p>
 * <p>Contains the host, the host address, the time to live and if the host is reachable.</p>
 *
 * @author haevn
 * @version 1.0
 * @since 1.0
 */
public record PingResult(String host, String hostAddress, double ttl, boolean reachable) {

    /**
     * <h1>printf</h1>
     * Returns a formatted string of the ping result.
     *
     * @return the formatted string
     */
    public String printf() {
        return String.format("PingResult{host=%s, hostAddress=%s, ttl=%.0f, reachable=%b}", host, hostAddress, ttl, reachable);
    }
}
