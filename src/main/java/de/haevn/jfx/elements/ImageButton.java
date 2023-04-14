package de.haevn.jfx.elements;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageButton extends Label {

    public ImageButton(Image img) {
        this(img, null);
    }

    public ImageButton(Image img, Runnable action) {
        getStyleClass().addAll("jfx-button-image");

        if (null != action) {
            setOnAction(action);
        }

        if (null != img) {
            setGraphic(new ImageView(img));
        } else {
            setText("No Image");
        }
    }

    public void setOnAction(Runnable action) {
        setOnMouseClicked(e -> action.run());
    }

}