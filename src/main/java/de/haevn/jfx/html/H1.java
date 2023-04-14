package de.haevn.jfx.html;

import javafx.scene.control.Label;

public class H1 extends Label {
    public H1() {
        this("");
    }

    public H1(String text) {
        getStyleClass().add("html-h1");
        setText(text);
        setStyle("-fx-font-size: 24;-fx-font-weight: bolder");
    }
}
