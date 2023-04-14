package de.haevn.jfx.html;

import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class InputInteger extends Input {


    public InputInteger(String prompt) {
        this(prompt, 0);
    }

    public InputInteger(String prompt, int defaultValue) {
        super(prompt);
        super.setValidator(new TextFormatter<>(new IntegerStringConverter(), defaultValue, this::validation));
    }

    public static InputInteger create(String prompt) {
        return new InputInteger(prompt);
    }

    private TextFormatter.Change validation(TextFormatter.Change change) {
        if (change.getText().isEmpty()) return change;
        final String text = change.getControlNewText();
        final Pattern pattern = Pattern.compile("^-?(0|[1-9]\\d*)$");
        final Matcher matcher = pattern.matcher(text);
        final boolean matches = (matcher.matches() || matcher.hitEnd());
        return matches ? change : null;
    }
}
