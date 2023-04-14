package de.haevn.jfx.html;

import de.haevn.network.NetworkInteraction;
import javafx.event.ActionEvent;
import javafx.scene.control.Hyperlink;

public class AH3 extends Hyperlink {
    private String link;

    public AH3() {
        this("", "");
    }

    public AH3(String text, String link) {
        getStyleClass().add("html-ah3");
        setText(text);
        setLink(link);
        setStyle("-fx-font-size: 18;-fx-font-weight: bolder");
        setOnAction(this::openLink);
    }

    public void setLink(String link) {
        this.link = link;
    }

    private void openLink(ActionEvent event) {
        if (!link.isEmpty()) {
            NetworkInteraction.openWebsite(link);
        }
    }

}
