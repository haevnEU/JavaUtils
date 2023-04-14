package de.haevn.jfx.html;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class DIV extends Pane {

    public DIV() {
    }

    public DIV(Node... elements) {
        setContent(elements);
    }

    public void setContent(Node... elements) {
        getStyleClass().add("html-div");
        getChildren().clear();
        getChildren().addAll(elements);
    }
}
