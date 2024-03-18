package de.haevn.utils.network;

import java.util.HashMap;
import java.util.Map;

public class NetworkInteractionV2 {


    public Map<String, String> headers = new HashMap<>();

    public static Builder open() {
        return new Builder(new NetworkInteractionV2());
    }

    public static class Builder {
        private final NetworkInteractionV2 networkInteraction;
        private final Map<String, String> headers = new HashMap<>();

        public Builder(NetworkInteractionV2 networkInteraction) {
            this.networkInteraction = networkInteraction;
        }

        public Builder withHeader(final String key, final String value) {
            headers.put(key, value);
            return this;
        }

        public Builder withPassword(final String password) {
            headers.put("password", password);
            return this;
        }

        public Builder withUser(final String user) {
            headers.put("user", user);
            return this;
        }

        public Builder withToken(final String token) {
            headers.put("token", token);
            return this;
        }

        public Builder withHeaders(final Map<String, String> headers) {
            this.headers.putAll(headers);
            return this;
        }


        public NetworkInteractionV2 build() {
            networkInteraction.headers = headers;
            return networkInteraction;
        }

    }
}
