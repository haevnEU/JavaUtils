package de.haevn.utils.html.nodes;

import de.haevn.utils.html.AbstractElement;

public class Bold extends AbstractElement {
    private String text;

    public static Bold of(String text) {
        return new Bold(text);
    }



    public Bold(String text) {
        super("b");
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
