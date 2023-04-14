package de.haevn.jfx.elements;

import de.haevn.jfx.html.H1;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

public class ScrollableContainer extends Pane {
    private final ScrollPane root = new ScrollPane();

    public ScrollableContainer() {
        this("");
    }

    public ScrollableContainer(String text) {
        this(text, new Pane());
    }

    public ScrollableContainer(String text, Pane content) {

        getStyleClass().addAll("jfx-scrollable-container");

        getChildren().addAll(new H1(text), root);

        root.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        root.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        root.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        setContent(content);
    }

    public void setContent(Node content) {
        root.setContent(content);
    }
}
