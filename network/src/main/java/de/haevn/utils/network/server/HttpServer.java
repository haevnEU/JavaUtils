package de.haevn.utils.network.server;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.util.Arrays;

final class HttpServer {
    private final Builder config;
    com.sun.net.httpserver.HttpServer server;

    private HttpServer(final Builder config) throws IOException {
        this.config = config;

        server = com.sun.net.httpserver.HttpServer.create(new InetSocketAddress(config.port), 0);
        server.createContext(config.path, exchange -> {
            final String path = exchange.getRequestURI().getPath();
            String response;
            if (new File(config.rootPath + path).isDirectory()) {
                exchange.getResponseHeaders().add("Content-Type", "text/html");
                response = String.join("\n", Arrays.stream(new File(config.rootPath + path).listFiles()).map(File::getName).map(e -> "<a href=\"" + e + "\">" + e + "</a>").toList());
            } else {
                response = readFile(config.rootPath + path);
            }
            exchange.sendResponseHeaders(200, response.length());
            exchange.getResponseBody().write(response.getBytes());
            exchange.getResponseBody().close();
        });
    }

    private String readFile(final String path) throws IOException {
        return Files.readString(new File(path).toPath());
    }

    public void start() {
        server.start();
    }

    public void stop() {
        server.stop(0);
    }


    private static class Builder {
        private int port = 80;
        private int backlog = 0;
        private String path = "/";
        private String rootPath = "./";

        public Builder(final String path, final String rootPath) {
            this.path = path;
            this.rootPath = rootPath;
        }

        public Builder setPort(int port) {
            this.port = port;
            return this;
        }

        public Builder setBacklog(int backlog) {
            this.backlog = backlog;
            return this;
        }

        public HttpServer build() throws IOException {
            return new HttpServer(this);
        }
    }
}
