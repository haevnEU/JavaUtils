package de.haevn.utils.html;

import java.util.ArrayList;
import java.util.List;

public class Footer {
    private final List<AbstractElement> elements = new ArrayList<>();


    public Footer addElement(final AbstractElement element) {
        elements.add(element);
        return this;
    }

    public String build() {

        return "<footer>\n\t"
                + String.join("\n", elements.stream().map(AbstractElement::code).toList())
                + "\n</footer>";
    }

}
