package de.haevn.utils.system;

import de.haevn.utils.datastructure.Tuple;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.List;

public class Tokenizer {
    private static final Tokenizer INSTANCE = new Tokenizer();

    public static synchronized Tokenizer getInstance() {
        return getInstance("=");
    }
    public static synchronized Tokenizer getInstance(String splitter) {
        return INSTANCE.setSplitter(splitter);
    }

    private Tokenizer() {
    }

    private String splitter = "";

    private Tokenizer setSplitter(String splitter){
        this.splitter = splitter;
        return this;
    }

    public Tuple<String, String> tokenize(final String input) {
        final var result = input.split(splitter);
        if(result.length == 0) {
            return new Tuple<>("", "");
        }else if(result.length == 1){
            return new Tuple<>(result[0], "");
        }else if(result.length == 2) {
            return new Tuple<>(result[0], result[1]);
        }else {
            return new Tuple<>(result[0], result[1]);
        }
    }

    public List<Tuple<String, String>> tokenize(final List<String> input) {
        return input.stream().map(this::tokenize).toList();
    }
}
