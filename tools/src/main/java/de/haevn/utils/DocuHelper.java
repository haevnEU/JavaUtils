package de.haevn.utils;

import java.util.ArrayList;
import java.util.List;

public class DocuHelper {

    public static void main(String[] args) {
        System.out.println("DocuHelper will generate a documentation for the given class.");
        System.out.println("Enter the header (typical name of the class/method/value");
        String header = System.console().readLine();
        System.out.println("Enter the description (!q to finish)");
        List<String> lines = new ArrayList<>();
        String line;
        while (!(line = System.console().readLine()).equals("!q")) {
            lines.add(line);
        }
        System.out.println("Enter an example: ");
        String example = System.console().readLine();
        System.out.println("Enter the author: ");
        String author = System.console().readLine();
        System.out.println("Enter the version: ");
        String version = System.console().readLine();
        System.out.println("Enter the since: ");
        String since = System.console().readLine();
        System.out.println("Enter the author");
        Documentation documentation = new Documentation(header, lines, example, author, version, since);
        System.out.println("Documentation generated: " + documentation);

    }

    private record Documentation(String header, List<String> lines,
                                 String example, String author, String version, String since) {
        @Override
        public String toString() {
            return """
                    \n/**
                     * <h1>%s</h1>
                     * <br>%s
                     * <br>
                     * <h3>Example</h3>
                     * <pre>
                     * {@code
                     * %s
                     * }
                     * </pre>
                     * @author %s
                     * @version %s
                     * @since %s
                     */
                    """.formatted(header, String.join("\n* <br>", lines), example, author, version, since);
        }
    }
}
