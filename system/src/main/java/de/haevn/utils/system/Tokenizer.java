package de.haevn.utils.system;

import de.haevn.utils.datastructure.Tuple;

import java.util.List;

public final class Tokenizer {
    private final String splitter;

    public Tokenizer() {
        this("=");
    }

    public Tokenizer(String splitter) {
        this.splitter = splitter;
    }

    public Tuple<String, String> tokenize(final String input) {
        final var result = input.split(splitter);
        if (result.length == 0) {
            return new Tuple<>("", "");
        } else if (result.length == 1) {
            return new Tuple<>(result[0], "");
        } else if (result.length == 2) {
            return new Tuple<>(result[0], result[1]);
        } else {
            return new Tuple<>(result[0], result[1]);
        }
    }

    public List<Tuple<String, String>> tokenize(final List<String> input) {
        return input.stream().map(this::tokenize).toList();
    }
}
