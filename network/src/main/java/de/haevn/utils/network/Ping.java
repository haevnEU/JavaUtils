package de.haevn.utils.network;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import java.util.ArrayList;
import java.util.List;

public class Ping {

    public static PingBuilder open(String host){
        return new PingBuilder(host);
    }

    private final PingBuilder builder;

    private Ping(final PingBuilder builder) {
        this.builder = builder;
    }

    public List<PingResult> execute(){
        List<PingResult> results = new ArrayList<>();
        for(int i = 0; i < builder.count; i++) {
            results.add(ping(builder.host, builder.port, builder.timeout));
        }

        var average = results.stream().mapToDouble(PingResult::ttl).average().orElse(0);
        results.add(new PingResult("", "", average, true));
        return results;
    }

    private PingResult ping(final String uri, final int port, final int timeout){
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

    public static final class PingBuilder{
        private String host;
        private int port = 7;
        private int timeout = 1000;
        private int count = 3;
        private int size;

        public PingBuilder(String host){
            this.host = host;
        }

        public PingBuilder port(int port){
            this.port = port;
            return this;
        }

        public PingBuilder timeout(int timeout){
            this.timeout = timeout;
            return this;
        }

        public PingBuilder count(int count){
            this.count = count;
            return this;
        }

        public PingBuilder size(int size){
            this.size = size;
            return this;
        }

        public Ping build(){
            return new Ping(this);
        }
    }
}
