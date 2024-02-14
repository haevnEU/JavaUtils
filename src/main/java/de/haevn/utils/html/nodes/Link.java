package de.haevn.utils.html.nodes;

import de.haevn.utils.html.AbstractElement;

public class Link extends AbstractElement {
    private String text;

    public static Link of(String text) {
        return new Link(text);
    }



    public Link(String text) {
        super("a");
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
