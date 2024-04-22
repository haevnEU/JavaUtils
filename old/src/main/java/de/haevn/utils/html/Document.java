package de.haevn.utils.html;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @version 1.0
 * @since 1.1
 * @author haevn
 */
public class Document {
    private final String lang;
    private final String title;
    private final Header header = new Header();

    private final Footer footer = new Footer();

    private final Body body = new Body();

    public Document(final String title) {
        this(title, "en");
    }

    public Document(final String title, final String lang) {
        this.lang = lang;
        this.title = title;
    }

    public Header getHeader() {
        return header;
    }

    public Footer getFooter() {
        return footer;
    }


    public Body getBody() {
        return body;
    }



    public Path export(final String path, final String name) throws IOException {
        return export(path, name, false);
    }

    public Path export(final String path, final String name, final boolean minified) throws IOException {
        return export(Path.of(path, name), minified);
    }

    public Path export(final Path path) throws IOException {
        return export(path, false);
    }

    public Path export(final Path path, final boolean minified) throws IOException {
        if(!Files.exists(path)){
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }
        return Files.write(path, compile(minified).getBytes());
    }


    public String get(){
        return compile();
    }

    public String get(final boolean minified){
        return compile(minified);
    }

    private String compile(){
        return compile(false);
    }
    private String compile(final boolean minified) {
        String document = "<!DOCTYPE html>\n<html title=\"" + title + "\" lang=\""+lang+"\">\n" + header.build() + "\n" + body.build() + "\n" + footer.build() + "\n</html>";
        return minified ? document.replace("\n", "").replace("\t", "") : document;
    }
}
