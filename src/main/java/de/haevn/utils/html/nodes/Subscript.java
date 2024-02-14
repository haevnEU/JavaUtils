package de.haevn.utils.html.nodes;

import de.haevn.utils.html.AbstractElement;

public class Subscript extends AbstractElement {
    private String text;

    public static Subscript of(String text) {
        return new Subscript(text);
    }



    public Subscript(String text) {
        super("sub");
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
