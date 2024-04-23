package de.haevn.utils.network;

public record PingResult(String host, String hostAddress, double ttl, boolean reachable) {
    public String printf(){
        return String.format("PingResult{host=%s, hostAddress=%s, ttl=%.0f, reachable=%b}", host, hostAddress, ttl, reachable);
    }
}
