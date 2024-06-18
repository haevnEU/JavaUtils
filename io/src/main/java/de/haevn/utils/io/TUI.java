package de.haevn.utils.io;


import de.haevn.utils.StringUtils;

import java.io.PrintStream;
import java.util.*;

public final class TUI implements AutoCloseable {
    private final Scanner in;
    private final PrintStream out;
    private final List<TuiEntry> entries = new ArrayList<>();
    private final List<TuiEntry> shortCuts = new ArrayList<>();
    private final String title;
    private final String exitWord;

    public TUI(final String title, final String exitWord, final TuiEntry... entries) {
        this.title = title;
        this.exitWord = exitWord;
        Collections.addAll(this.entries, entries);

        in = new Scanner(System.in);
        out = System.out;
    }

    public Scanner getIn() {
        return in;
    }

    public PrintStream getOut() {
        return out;
    }

    /**
     * Sets the shortcuts for the TUI.<br>
     * <b>Note</b><br>
     * - Calling this method twice will override the previous shortcuts<br>
     * - The shortcuts are only available if the command starts with "!"<br>
     * @param entries List of all shortcuts
     * @return The TUI instance
     */
    public TUI setShortCuts(final TuiEntry... entries) {
        shortCuts.clear();
        shortCuts.addAll(Arrays.stream(entries).map(entry -> new TuiEntry(entry.name(), entry.command().replace("!", ""), entry.description, entry.action())).toList());
        return this;
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
                help();
            } else if (input.equalsIgnoreCase("clear")) {
                clear();

            } else {
                getAction(input).run();
            }

        } while (true);
    }

    private void help(){

        out.println(StringUtils.fitString("Command", 10) + " | " + StringUtils.fitString("Name", 10) + " | Description");
        out.println(StringUtils.fitString("Command", 10) + " | " + StringUtils.fitString("Name", 10) + " | Description");
        out.println("-".repeat(60));
        out.println(StringUtils.fitString(exitWord, 10) + " | " + StringUtils.fitString("Exit", 10)+ " | Exit the application");
        out.println(StringUtils.fitString("help", 10) + " | " + StringUtils.fitString("Help", 10)+ " | Show this help");
        out.println(StringUtils.fitString("?", 10) + " | " + StringUtils.fitString("Help", 10)+ " | Show this help");
        out.println(StringUtils.fitString("clear", 10) + " | " + StringUtils.fitString("clear", 10)+ " | Clear the screen");
        entries.forEach(entry -> out.println(StringUtils.fitString(entry.command, 10) + " | " + StringUtils.fitString(entry.name, 10) + " | " + entry.description));
    }

    private String getInput(final Scanner in) {
        try {
            out.print("> ");
            return in.nextLine();
        } catch (final Exception e) {
            return "";
        }
    }

    private void clear() {
        out.print("\033[H\033[2J");
        out.flush();
    }

    private void printHeader(final String header) {

        String headerOut = header.length() > 58 ? header.substring(0, 58) : header;

        final int half = (int) (headerOut.length() * 0.5);
        final int firstHalf = half;
        final int secondHalf = half % 2 == 0 ? (half + 1) : half;
        out.println("+" + "-".repeat(60) + "+");
        out.println("|" + " ".repeat(30 - firstHalf) + headerOut + " ".repeat(30 - secondHalf) + "|");
        out.println("+" + "-".repeat(60) + "+");
    }

    private Runnable getAction(final String command) {
        if (command.startsWith("!")) {
            return shortCuts.stream().filter(entry -> entry.command.equalsIgnoreCase(command.substring(1))).findFirst().orElse(TuiEntry.EMPTY_ENTRY).action;
        }
        return entries.stream().filter(entry -> entry.command.equalsIgnoreCase(command)).findFirst().orElse(TuiEntry.EMPTY_ENTRY).action;
    }

    @Override
    public void close() throws Exception {
        in.close();
        out.println("Thanks for using " + title);
        out.flush();
        out.close();

    }


    public record TuiEntry(String name, String command, String description, Runnable action) {
        public static final TuiEntry EMPTY_ENTRY = new TuiEntry("", "", "", () -> {
        });
    }
}
