package de.haevn.utils.html;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Header {
    private final Map<String, String> elements = new HashMap<>();
    public Header setTitle(String title) {
        elements.put("title", "<title>" + title + "</title>");
        return this;
    }

    public Header addMeta(String name, String content) {
        elements.put(name, "<meta name=\"" + name + "\" content=\"" + content + "\">");
        return this;
    }

    public Header addLink(String rel, String href) {
        elements.put(rel, "<link rel=\"" + rel + "\" href=\"" + href + "\">");
        return this;
    }

    public Header addScript(String src) {
        elements.put(src, "<script src=\"" + src + "\"></script>");
        return this;
    }

    public Header addStyle(String src) {
        elements.put(src, "<link rel=\"stylesheet\" href=\"" + src + "\">");
        return this;
    }

    public Header setCharset(String charset) {
        elements.put("charset", "<meta charset=\"" + charset + "\">");
        return this;
    }

    public Header setViewport(String content) {
        elements.put("viewport", "<meta name=\"viewport\" content=\"" + content + "\">");
        return this;
    }

    public Header setAuthor(String author) {
        elements.put("author", "<meta name=\"author\" content=\"" + author + "\">");
        return this;
    }

    public Header setDescription(String description) {
        elements.put("description", "<meta name=\"description\" content=\"" + description + "\">");
        return this;
    }

    public Header addElement(String name, String element) {
        elements.put(name, element);
        return this;
    }



    public String build() {
        List<String> list = new ArrayList<>();
        elements.forEach((k, v) -> list.add(v));
        return "<header>\n" +  String.join("\n\t", list) + "\n</header>";
    }
}
