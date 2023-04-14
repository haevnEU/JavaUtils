package de.haevn.jfx.html;

import javafx.scene.control.Label;

public class H3 extends Label {
    public H3() {
        this("");
    }

    public H3(String text) {
        getStyleClass().add("html-h3");
        setText(text);
        setStyle("-fx-font-size: 18;-fx-font-weight: bolder");
    }
}
