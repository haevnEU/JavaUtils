package de.haevn.utils.html.nodes;

import de.haevn.utils.html.AbstractElement;

public class Inserted extends AbstractElement {
    private String text;

    public static Inserted of(String text) {
        return new Inserted(text);
    }



    public Inserted(String text) {
        super("ins");
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
