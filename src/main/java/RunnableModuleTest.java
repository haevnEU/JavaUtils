import de.haevn.utils.io.TUI;

import java.util.Scanner;

public class RunnableModuleTest {
    public static void main(String[] args) {
        TUI tui = new TUI(args[0], "exit", new TUI.TuiEntry("Test", "test", () -> System.out.println("Test")),
                new TUI.TuiEntry("Dummy", "dummy", RunnableModuleTest::dummy));
        tui.show();
    }


    private static void dummy(){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = in.nextLine();
        System.out.println("You are: " + name);

    }
}
