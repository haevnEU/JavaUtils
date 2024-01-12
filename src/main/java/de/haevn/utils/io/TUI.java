package de.haevn.utils.io;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public final class TUI implements AutoCloseable {
    private final Scanner in;
    private final PrintStream out;
    private final List<TuiEntry> entries = new ArrayList<>();
    private final String title;
    private final String exitWord;
    public TUI(final String title, final String exitWord, final TuiEntry ... entries){
        this.title = title;
        this.exitWord = exitWord;
        Collections.addAll(this.entries, entries);

        in = new Scanner(System.in);
        out = System.out;
    }

    public void show() {
        printHeader(title);
        do {
            final String input = getInput(in);
            if (input.isBlank()) {
                continue;
            }
            if (input.equalsIgnoreCase(exitWord)) {
                out.print("Hit any key to continue");
                in.nextLine();
                return;
            } else if (input.equalsIgnoreCase("help") || input.equalsIgnoreCase("?")) {
                out.println("Available commands:");
                out.println("help / ? - Show this help");
                out.println(exitWord + " - Exit");
                entries.forEach(entry -> out.println(entry.name + ": " + entry.command));
            } else if (input.equalsIgnoreCase("clear")) {
                clear();

            } else {
                getAction(input).run();
            }

        } while (true);
    }

    private String getInput(final Scanner in){
        try {
            out.print("> ");
            return in.nextLine();
        }catch (final Exception e){
            return "";
        }
    }

    private void clear(){
        out.print("\033[H\033[2J");
        out.flush();
    }

    private void printHeader(final String header){

        String headerOut = header.length() > 58 ? header.substring(0, 58) : header;

        final int half = (int)(headerOut.length() * 0.5);
        final int firstHalf = half;
        final int secondHalf = half % 2 == 0 ? (half + 1) : half;
        out.println("+" + "-".repeat(60) + "+");
        out.println("|" + " ".repeat(30 - firstHalf) + headerOut + " ".repeat(30 - secondHalf) + "|");
        out.println("+" + "-".repeat(60) + "+");
    }

    private Runnable getAction(final String command){
        return entries.stream().filter(entry -> entry.command.equalsIgnoreCase(command)).findFirst().orElse(TuiEntry.EMPTY_ENTRY).action;
    }

    @Override
    public void close() throws Exception {
        in.close();
        out.println("Thanks for using " + title);
        out.flush();
        out.close();

    }


    public record TuiEntry(String name, String command, Runnable action) {
        public static final TuiEntry EMPTY_ENTRY = new TuiEntry("", "", () -> {});
    }
}
