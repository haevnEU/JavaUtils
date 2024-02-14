package de.haevn.utils.html.nodes;

import de.haevn.utils.html.AbstractElement;

public class Marked extends AbstractElement {
    private String text;

    public static Marked of(String text) {
        return new Marked(text);
    }



    public Marked(String text) {
        super("mark");
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
