package de.haevn.utils.html.nodes;

import de.haevn.utils.html.AbstractElement;

public class Deleted extends AbstractElement {
    private String text;

    public static Deleted of(String text) {
        return new Deleted(text);
    }



    public Deleted(String text) {
        super("del");
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
