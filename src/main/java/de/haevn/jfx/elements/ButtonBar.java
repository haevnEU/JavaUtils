package de.haevn.jfx.elements;

import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ButtonBar {
    private final Pane root;

    public ButtonBar() {
        this(Orientation.VERTICAL);
    }

    public ButtonBar(Orientation orientation, Button... buttons) {
        root = orientation == Orientation.VERTICAL ? new VBox() : new HBox();

        if (orientation == Orientation.VERTICAL) {
            ((VBox) root).setSpacing(10);
        } else {
            ((HBox) root).setSpacing(10);
        }
        add(buttons);
    }

    public static ButtonBar createVertical(Button... buttons) {
        return new ButtonBar(Orientation.VERTICAL, buttons);
    }

    public static ButtonBar createHorizontal(Button... buttons) {
        return new ButtonBar(Orientation.HORIZONTAL, buttons);
    }

    public static ButtonBar create(Orientation orientation, Button... buttons) {
        return new ButtonBar(orientation, buttons);
    }

    public void add(Node... buttons) {
        for (Node node : buttons) {
            node.resize(Double.MAX_VALUE, 50);
            root.getChildren().add(node);
        }
    }

    public Pane getPane() {
        return root;
    }
}
