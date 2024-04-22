package de.haevn.utils.html.nodes;

import de.haevn.utils.html.AbstractElement;

public class Small extends AbstractElement {
    private String text;

    public static Small of(String text) {
        return new Small(text);
    }



    public Small(String text) {
        super("small");
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Override
    public String code() {
        return String.format(getTagFormat(), text);
    }
}
