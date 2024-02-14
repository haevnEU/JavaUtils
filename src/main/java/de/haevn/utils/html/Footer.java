package de.haevn.utils.html;

import java.util.ArrayList;
import java.util.List;

public class Footer {
    private final List<String> elements = new ArrayList<>();


    public Footer addElement(String element) {
        elements.add(element);
        return this;
    }

    public String build() {
        return "<footer>\n\t" + String.join("\n\t", elements) + "\n</footer>";
    }

}
