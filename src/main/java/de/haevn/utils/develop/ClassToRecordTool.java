package de.haevn.utils.develop;

import de.haevn.utils.io.TUI;

import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class ClassToRecordTool {

    private static Scanner is;
    private static PrintStream out;
    private static PrintStream err;

    public static void main(String[] args) {
        TUI.TuiEntry[] entries = {
                new TUI.TuiEntry("Class to Record", "record", "Maps the class to a record", ClassToRecordTool::toRecord),
                new TUI.TuiEntry("Record to Class", "class", "Maps the record to a class", ClassToRecordTool::toClass)
        };

        try (TUI tui = new TUI("Class to Record Tool", "exit", entries)) {
            is = tui.getIn();
            out = tui.getOut();
            err = tui.getErr();
            err.println("This tool is not yet implemented, therefore it will do nothing");
            tui.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String readFile(final String path) {
        final Path p = Path.of(path);
        if (!Files.exists(p)) {
            err.println("File " + path + " does not exist");
        }
        try {
            return Files.readString(p);
        } catch (Exception e) {
            err.println("Error reading file " + path + "\n" + e.getMessage());
        }
        return "";
    }

    private static void store(final String path, final String content) {
        final Path p = Path.of(path);

        try {
            if (Files.exists(p)) {
                Files.delete(p);
            }

            Files.writeString(p, content);
        } catch (Exception e) {
            err.println("Error writing file " + path + "\n" + e.getMessage());
        }
    }

    private static void toClass() {
        out.println("Record to Class");
        out.print("This operation will override the record\nAre you sure you want to continue? (y/n) ");
        final String response = is.nextLine();
        if (response.equalsIgnoreCase("n")) return;

        out.print("Enter the path to the record: ");
        final String path = is.nextLine();
        final String content = readFile(path);

    }

    private static void toRecord() {
        out.println("Class to Record");
        out.print("This operation will override the class\nAre you sure you want to continue? (y/n) ");
        final String response = is.nextLine();
        if (response.equalsIgnoreCase("n")) return;

        out.print("Enter the path to the class: ");
        final String path = is.nextLine();
        final String content = readFile(path);


    }
}
