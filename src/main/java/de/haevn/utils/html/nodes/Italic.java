package de.haevn.utils.html.nodes;

import de.haevn.utils.html.AbstractElement;

public class Italic extends AbstractElement {
    private String text;

    public static Italic of(String text) {
        return new Italic(text);
    }



    public Italic(String text) {
        super("i");
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
