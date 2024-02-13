package de.haevn.utils.html;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Document {
    private final String lang;
    private final Header header = new Header();

    private final Footer footer = new Footer();

    private final Body body = new Body();

    public Document() {
        this("en");
    }

    public Document(String lang) {
        this.lang = lang;
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


    public static void main(String[] args) throws IOException {
        Document document = new Document();
        document.getHeader().setTitle("Test");
        document.getHeader().setCharset("UTF-8");
        document.getHeader().setViewport("width=device-width, initial-scale=1.0");
        document.getHeader().addStyle("style.css");
        document.getHeader().addScript("script.js");
        document.getFooter().addElement("Test");
        document.getFooter().addElement("Test2");
        document.getFooter().addElement("Test3");
        document.getBody().addElement("<h1>Test</h1>");
        document.getBody().addElement("<p>Test</p>");

        document.export("D:\\repos\\JavaUtils\\target", "test.html");
        System.out.println(document.export("D:\\repos\\JavaUtils\\target\\sub", "test.html", true));
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
        String document = "<!DOCTYPE html>\n<html lang=\""+lang+"\">\n" + header.build() + "\n" + body.build() + "\n" + footer.build() + "\n</html>";
        return minified ? document.replace("\n", "").replace("\t", "") : document;
    }
}
