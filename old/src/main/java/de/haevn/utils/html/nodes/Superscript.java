package de.haevn.utils.html.nodes;

import de.haevn.utils.html.AbstractElement;

public class Superscript extends AbstractElement {
    private String text;

    public static Superscript of(String text) {
        return new Superscript(text);
    }



    public Superscript(String text) {
        super("sup");
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
