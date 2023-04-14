package de.haevn.jfx.html;

import de.haevn.network.NetworkInteraction;
import javafx.event.ActionEvent;
import javafx.scene.control.Hyperlink;

public class A extends Hyperlink {
    private String link;

    public A() {
        this("", "");
    }

    public A(String text, String link) {
        getStyleClass().add("html-text-link");
        setText(text);
        setLink(link);
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

    public void setFontSize(int size) {
        setStyle("-fx-font-size: " + size + ";-fx-font-weight: italic;");
    }

}
