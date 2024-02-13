package de.haevn.utils.html;

import java.util.ArrayList;
import java.util.List;

public class Body {

    private String style;
    private final List<String> script = new ArrayList<>();
    private final List<String> styleClass = new ArrayList<>();
    private final List<String> elements = new ArrayList<>();


    public Body addElement(String element) {
        elements.add(element);
        return this;
    }

    public Body addScript(String script) {
        this.script.add(script);
        return this;
    }

    public Body setStyle(String style) {
        this.style = style;
        return this;
    }

    public Body addStyleClass(String styleClass) {
        this.styleClass.add(styleClass);
        return this;
    }

    public String build(){
        return "<body"
                + (!styleClass.isEmpty() ? " class=\"" + String.join(" ", styleClass) + "\" " : "")
                + (style != null ? " style=\"" + style + "\" " : "") + ">\n"
                + String.join("\n", elements)
                + (!script.isEmpty() ? String.join("\n", "<script>\n" +  script + "\n<script>") : "")
                + "\n</body>";
    }
}
