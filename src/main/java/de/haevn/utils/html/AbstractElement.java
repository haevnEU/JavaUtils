package de.haevn.utils.html;

public abstract class AbstractElement {

    private final String tag;

    protected AbstractElement(String tag) {
        this.tag = tag;
    }

    public String getTagFormat() {
        return "<" + tag + ">%s</" + tag + ">";
    }
    public abstract String code();

}
