package de.haevn.utils.network;

public record PingResult(String host, String hostAddress, long ttl, boolean reachable) {
    public String printf(){
        return String.format("PingResult{host=%s, hostAddress=%s, ttl=%d, reachable=%b}", host, hostAddress, ttl, reachable);
    }
}
