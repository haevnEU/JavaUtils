package de.haevn.jfx.html;

import javafx.scene.control.Label;

public class H4 extends Label {
    public H4() {
        this("");
    }

    public H4(String text) {
        getStyleClass().add("html-h4");
        setText(text);
        setStyle("-fx-font-size: 16;-fx-font-weight: bolder");
    }
}
