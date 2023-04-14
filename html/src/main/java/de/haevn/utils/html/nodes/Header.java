package de.haevn.utils.html.nodes;

import de.haevn.utils.html.AbstractElement;

public class Header extends AbstractElement {
    private String text;
    public static Header ofH1(String text) {
        return new Header(text, 1);
    }

    public static Header ofH2(String text) {
        return new Header(text, 2);
    }

    public static Header ofH3(String text) {
        return new Header(text, 3);
    }

    public static Header ofH4(String text) {
        return new Header(text, 4);
    }

    public static Header ofH5(String text) {
        return new Header(text, 5);
    }

    public static Header ofH6(String text) {
        return new Header(text, 6);
    }



    public Header(String text, int level) {
        super("h" + level);
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
