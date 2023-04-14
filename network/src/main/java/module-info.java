module de.haevn.utils.network {
    requires java.desktop;
    requires java.datatransfer;
    requires java.net.http;
    requires jdk.httpserver;

    requires com.fasterxml.jackson.annotation;
    requires static lombok;

    requires de.haevn.utils.exceptions;
    requires de.haevn.utils.logger;
    requires de.haevn.utils.utils;

    exports de.haevn.utils.network;
    exports de.haevn.utils.network.webhook;
    exports de.haevn.utils.network.webhook.discord;
}