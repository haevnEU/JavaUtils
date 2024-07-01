package de.haevn.utils.network;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Ping</h1>
 * <br>
 * <p>This is just a simple ping utility to check if a host is reachable.</p>
 *
 * @author haevn
 * @version 1.0
 * @since 1.0
 */
public class Ping {

    private final PingBuilder builder;

    /**
     * <h1>open</h1>
     * Opens a new {@link PingBuilder} with the given host.
     *
     * @param host the host to ping
     * @return the new {@link PingBuilder}
     */
    public static PingBuilder open(final String host) {
        return new PingBuilder(host);
    }

    /**
     * <h1>Ping</h1>
     * Creates a new {@link Ping} with the given {@link PingBuilder}.
     *
     * @param builder the {@link PingBuilder} to use
     */
    private Ping(final PingBuilder builder) {
        this.builder = builder;
    }

    /**
     * <h1>execute</h1>
     * Executes the ping request.
     *
     * @return the list of {@link PingResult}s
     */
    public List<PingResult> execute() {
        List<PingResult> results = new ArrayList<>();
        for (int i = 0; i < builder.count; i++) {
            results.add(ping(builder.host, builder.port, builder.timeout));
        }

        var average = results.stream().mapToDouble(PingResult::ttl).average().orElse(0);
        results.add(new PingResult("", "", average, true));
        return results;
    }

    /**
     * <h1>ping</h1>
     * Pings the given host.
     *
     * @param uri     the host to ping
     * @param port    the port to ping
     * @param timeout the timeout in milliseconds
     * @return the {@link PingResult}
     */
    private PingResult ping(final String uri, final int port, final int timeout) {
        final InetAddress address = new InetSocketAddress(uri, port).getAddress();
        boolean reachable = false;
        long ttl = Long.MAX_VALUE;
        try {
            final long start = System.currentTimeMillis();
            reachable = address.isReachable(timeout);
            ttl = System.currentTimeMillis() - start;
            return new PingResult(uri, address.getHostAddress(), ttl, reachable);
        } catch (Exception ignored) {
            return new PingResult(uri, "", ttl, reachable);
        }
    }

    /**
     * <h1>PingBuilder</h1>
     * The builder for the {@link Ping}.
     */
    public static final class PingBuilder {
        private final String host;
        private int port = 7;
        private int timeout = 1000;
        private int count = 3;


        /**
         * <h1>PingBuilder</h1>
         * Creates a new {@link PingBuilder} with the given host.
         *
         * @param host the host to ping
         */
        public PingBuilder(final String host) {
            this.host = host;
        }

        /**
         * <h1>port</h1>
         * Sets the port to ping.
         *
         * @param port the port to ping
         * @return the {@link PingBuilder}
         */
        public PingBuilder port(final int port) {
            this.port = port;
            return this;
        }

        /**
         * <h1>timeout</h1>
         * Sets the timeout in milliseconds.
         *
         * @param timeout the timeout in milliseconds
         * @return the {@link PingBuilder}
         */
        public PingBuilder timeout(final int timeout) {
            this.timeout = timeout;
            return this;
        }

        /**
         * <h1>count</h1>
         * Sets the count of pings.
         *
         * @param count the count of pings
         * @return the {@link PingBuilder}
         */
        public PingBuilder count(final int count) {
            this.count = count;
            return this;
        }

        /**
         * <h1>build</h1>
         * Builds the {@link Ping}.
         *
         * @return the {@link Ping}
         */
        public Ping build() {
            return new Ping(this);
        }
    }
}


// Dont forget 0xFF is the same as 255 in decimal and 11111111 in binary


// Here is a special cookie for you somewhere                                                                                                                                                                                                                                                               YOU FOUND IT, hope you enjoyed it, btw your cookie is here -> 🍪


// Just a bit more of scrolling


// Almost there


// Just a bit more


// You're almost there don't give up


// Just a bit more

// Let us count down from 10 to 1


// 10


// 9


// 8


// 7


// 6


// 5


// 4

// 3

// 2
// WAIT FOR IT


// 5 wait wasn't it 2? Nevermind let us restart
// 10
// 9
// 8
// 7
// 6
// 5


// DONT GIVE UP YOU'RE A SMART PERSON YOU CAN DO IT, just scroll back up heres nothing more


// :)
// you are such a good person, you deserve a cookie for scrolling this far down here is a cookie... wait where is it?!
// I guess I left it in the code, you can find it if you look closely