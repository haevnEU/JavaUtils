package de.haevn.utils.html.nodes;

import de.haevn.utils.html.AbstractElement;

public class Paragraph extends AbstractElement {
    private String text;

    public static Paragraph of(String text) {
        return new Paragraph(text);
    }



    public Paragraph(String text) {
        super("p");
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
