package de.haevn.jfx.html;

import javafx.scene.control.TextFormatter;
import javafx.util.converter.DoubleStringConverter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class InputNumber extends Input {

    public InputNumber(String prompt) {
        this(prompt, 0.0);
    }

    public InputNumber(String prompt, double defaultValue) {
        super(prompt);
        super.setValidator(new TextFormatter<>(new DoubleStringConverter(), defaultValue, this::validation));
    }

    public static InputNumber create(String prompt) {
        return new InputNumber(prompt);
    }

    private TextFormatter.Change validation(TextFormatter.Change change) {
        if (change.getText().isEmpty()) return change;
        final String text = change.getControlNewText();
        final Pattern pattern = Pattern.compile("^-?(0|[1-9]\\d*)(\\.\\d+)?$");
        final Matcher matcher = pattern.matcher(text);
        final boolean matches = (matcher.matches() || matcher.hitEnd());
        return matches ? change : null;
    }
}
