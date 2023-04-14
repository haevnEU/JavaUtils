package de.haevn.utils.html.nodes;

import de.haevn.utils.html.AbstractElement;

public class Code extends AbstractElement {
    private String text;

    public static Code of(String text) {
        return new Code(text);
    }



    public Code(String text) {
        super("code");
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
