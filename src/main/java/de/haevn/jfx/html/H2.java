package de.haevn.jfx.html;

import javafx.scene.control.Label;

public class H2 extends Label {
    public H2() {
        this("");
    }

    public H2(String text) {
        getStyleClass().add("html-h2");
        setText(text);
        setStyle("-fx-font-size: 20;-fx-font-weight: bolder");
    }
}
