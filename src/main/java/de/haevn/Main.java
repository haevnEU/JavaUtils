package de.haevn;

import de.haevn.exceptions.NotYetImplementedException;
import de.haevn.jfx.widgets.ProgressWidget;
import de.haevn.logging.Level;
import de.haevn.logging.Logger;
import de.haevn.utils.ReadonlyTuple;
import de.haevn.utils.Tuple;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static final String APP_NAME = "WarcraftAddonManager";


    public static void main(String[] args) throws NotYetImplementedException {
        Tuple<String, String> s = new Tuple<>("a", "b");
        s.setFirst("c");
        s.setSecond("d");

        Tuple<String, String> s2 = new ReadonlyTuple<>("a", "b");
        s2.setFirst("c");
        s2.setSecond("d");

        System.out.println(s.getFirst());
        System.out.println(s.getSecond());

        System.out.println(s2.getFirst());
        System.out.println(s2.getSecond());

        Logger logger = new Logger();
        Main main = new Main();
        logger.at(Level.INFO).forEnclosingMethod().withMessage("Starting %s %s", APP_NAME, main).log();
        logger.flush();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Hello World!");

        final GridPane pane = new GridPane();
        final Scene scene = new Scene(pane, 750, 500);

        stage.setScene(scene);

        var pw = ProgressWidget.create();
        pw.addCloseAction(() -> System.out.println("Closed")).addCloseAction(() -> System.out.println("Closed2")).show();
        var bt = new Button("Close");
        bt.setOnAction(e -> pw.stop());

        pane.add(bt, 0, 0);
        stage.show();


    }

}
