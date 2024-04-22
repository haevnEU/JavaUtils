import de.haevn.utils.html.Body;
import de.haevn.utils.html.Document;
import de.haevn.utils.html.nodes.*;

import java.io.IOException;

public class Playground {


    public static void main(String[] args) throws IOException {
        Document document = new Document("Test");
        document.getHeader().setCharset("UTF-8");
        document.getHeader().setViewport("width=device-width, initial-scale=1.0");
        document.getHeader().addStyle("style.css");
        document.getHeader().addScript("script.js");
        document.getFooter().addElement("Test");
        document.getFooter().addElement("Test2");
        document.getFooter().addElement("Test3");


        document.getBody().addElement(Header.ofH1("Test"));
        document.getBody().addElement(Header.ofH2("Test"));
        document.getBody().addElement(Header.ofH3("Test"));
        document.getBody().addElement(Header.ofH4("Test"));
        document.getBody().addElement(Header.ofH5("Test"));
        document.getBody().addElement(Header.ofH6("Test"));
        document.getBody().addElement(new Paragraph("Test"));
        document.getBody().addElement(new Paragraph("Test2"));
        document.getBody().addElement(Italic.of("Test"));
        document.getBody().addElement(Bold.of("Test"));
        document.getBody().addElement(new Code("Test3"));
        document.getBody().addElement(new Link("Test4"));
        document.getBody().addElement(new Inserted("Test5"));
        document.getBody().addElement(new Deleted("Test6"));
        document.getBody().addElement(new Marked("Test7"));
        document.getBody().addElement(new Small("Test8"));
        document.getBody().addElement(new Subscript("Test10"));
        document.getBody().addElement(new Superscript("Test11"));


        document.export("D:\\repos\\JavaUtils\\target", "test.html");
        System.out.println(document.export("D:\\repos\\JavaUtils\\target\\sub", "test.html", true));
    }
}
