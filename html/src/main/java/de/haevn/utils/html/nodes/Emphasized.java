package de.haevn.utils.html.nodes;

import de.haevn.utils.html.AbstractElement;

public class Emphasized extends AbstractElement {
    private String text;

    public static Emphasized of(String text) {
        return new Emphasized(text);
    }



    public Emphasized(String text) {
        super("em");
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
