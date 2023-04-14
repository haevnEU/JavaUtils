package de.haevn.jfx.widgets;

import de.haevn.jfx.html.H4;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;

public class ProgressWidget {
    private final List<Runnable> closeActions = new ArrayList<>();
    private final Stage stage = new Stage();
    private boolean isRunning = false;

    private ProgressWidget(String title, String text) {
        final GridPane root = new GridPane();
        final Button btCancel = new Button("Cancel");
        final ProgressBar pb = new ProgressBar(ProgressBar.INDETERMINATE_PROGRESS);
        pb.setMaxWidth(400);
        pb.setPrefWidth(400);

        root.add(new H4(title), 0, 0, 2, 1);
        root.add(new Label(text), 0, 1, 2, 1);
        root.add(pb, 0, 2, 2, 1);
        root.add(btCancel, 1, 3, 1, 1);

        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);

        GridPane.setHalignment(btCancel, HPos.RIGHT);

        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setAlwaysOnTop(true);
        stage.setScene(new Scene(root));

        btCancel.setOnAction(e -> stop());
    }

    public static ProgressWidget create() {
        return create("An operation is currently ongoing", "This may take a few seconds.");
    }

    public static ProgressWidget create(String title, String text) {
        return new ProgressWidget(title, text);
    }

    public ProgressWidget addCloseAction(Runnable action) {
        closeActions.add(action);
        return this;
    }

    public ProgressWidget removeCloseAction(Runnable action) {
        closeActions.remove(action);
        return this;
    }

    public ProgressWidget show() {
        stage.show();
        isRunning = true;
        return this;
    }

    public void stop() {
        if (isRunning) {
            closeActions.forEach(Runnable::run);
            stage.close();
        }
        isRunning = false;
    }
}
