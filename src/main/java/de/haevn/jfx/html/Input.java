package de.haevn.jfx.html;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;


public class Input extends HBox {

    private final TextField textField = new TextField();


    public Input() {
        this("");
    }

    public Input(String text) {
        this(text, false);
    }

    public Input(String text, boolean readonly) {
        final Button btClear = new Button("Clear");
        final Button btCopy = new Button("Copy");

        getStyleClass().add("html-input-clear");
        setPrompt(text);

        textField.setEditable(!readonly);
        textField.setOnMouseClicked(event1 -> textField.selectAll());

        btClear.setOnAction(event -> textField.setText(""));
        btCopy.setOnAction(event -> {
            textField.selectAll();
            textField.copy();
        });

        btClear.setMinWidth(50);
        btCopy.setMinWidth(50);

        getChildren().addAll(textField, btClear, btCopy);
        setSpacing(10);
        HBox.setHgrow(textField, Priority.ALWAYS);
    }

    public static Input create(String prompt) {
        return new Input(prompt);
    }

    public void setOnTextChangeAction(ChangeListener<String> event) {
        textField.textProperty().addListener(event);
    }

    public String getText() {
        return textField.getText();
    }

    public void setText(String text) {
        textField.setText(text);
    }

    public StringProperty getTextProperty() {
        return textField.textProperty();
    }

    public void bindTextProperty(StringProperty property) {
        textField.textProperty().bindBidirectional(property);
    }

    public void setEditable(boolean editable) {
        textField.setEditable(editable);
    }


    public void setPrompt(String prompt) {
        textField.setPromptText(prompt);
    }

    protected <V> void setValidator(TextFormatter<V> formatter) {
        textField.setTextFormatter(formatter);
    }

}
