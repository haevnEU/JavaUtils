package de.haevn.utils.swing;

import de.haevn.utils.debug.ThreadTools;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <h1>Dialog</h1>
 * <br>
 * <p>This is a simple Dialog class that can be used to inform the user about something or to ask for confirmation.</p>
 * <h2>Example (warning):</h2>
 * <pre>{@code
 *     Dialog.warningDialog("This is a warning message").showAndWait();
 * }</pre>
 * <h2>Example (error):</h2>
 * <pre>{@code
 *    Dialog.errorDialog("This is an error message").showAndWait();
 * }</pre>
 * <h2>Example (information):</h2>
 * <pre>{@code
 *    Dialog.infoDialog("This is an information message").showAndWait();
 * }</pre>
 * <h2>Example (confirmation):</h2>
 * <pre>{@code
 *    Dialog.confirmDialog("Do you want to continue?").showAndWait();
 * }</pre>
 * <h2>Example (custom):</h2>
 * <pre>{@code
 *    var dialog = new Dialog("Title", "Message", DialogResult.OK, DialogResult.CANCEL);
 *    var result = dialog.showAndWait();
 *    System.out.println(result);
 * }</pre>
 */
public final class Dialog extends JFrame {
    private final AtomicBoolean visibility = new AtomicBoolean(false);
    private DialogResult result = DialogResult.NONE;

    /**
     * <h2>Dialog(String, String, {@link DialogResult}</h2>
     * <p>Creates a new dialog with the given title, message and possible results.</p>
     * <h3>Example:</h3>
     * <pre>
     *     {@code
     *     var dialog = new Dialog("Title", "Message", DialogResult.OK, DialogResult.CANCEL);
     *     var result = dialog.showAndWait();
     *     System.out.println(result);
     * }
     * </pre>
     * @param title The title of the dialog
     * @param message The message of the dialog
     * @param result The possible results of the dialog
     */
    public Dialog(final String title, final String message, DialogResult... result) {
        setTitle(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        final JPanel bottomPanel = new JPanel();
        for (DialogResult r : result) {
            final JButton btn = new JButton(r.name());
            btn.addActionListener(_ -> {
                this.result = r;
                setVisible(false);
                dispose();
            });
            bottomPanel.add(btn);
        }

        add(new JLabel(message), "Center");
        add(bottomPanel, "South");
    }

    /**
     * <h2>getResult()</h2>
     * <p>Returns the result of the dialog.</p>
     * @return The result of the dialog
     */
    public DialogResult getResult() {
        return result;
    }

    /**
     * <h2>showAndWait()</h2>
     * <p>Shows the dialog and waits for the user to close it.</p>
     * @return The result of the dialog
     */
    public DialogResult showAndWait() {
        setVisible(true);
        ThreadTools.waitFor(visibility);
        return getResult();
    }

    /**
     * <h2>setVisible(boolean)</h2>
     * <p>Sets the visibility of the dialog and updates the visibility flag.</p>
     * @param visible The visibility of the dialog
     */
    @Override
    public void setVisible(boolean visible) {
        visibility.set(visible);
        super.setVisible(visible);
    }

    /**
     * <h2>create(String, String, {@link DialogResult}...)</h2>
     * <p>Creates a new dialog with the given title, message and possible results.</p>
     * @param title The title of the dialog
     * @param message The message of the dialog
     * @param result The possible results of the dialog
     * @return The created dialog
     */
    public static Dialog creeate(final String title, final String message, DialogResult... result) {
        return new Dialog(title, message, result);
    }

    /**
     * <h2>warningDialog(String)</h2>
     * <p>Creates a new warning dialog with the given message.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     * var result = Dialog.warningDialog("This is a warning message").showAndWait();
     * System.out.println(result);
     * }
     * @param message The message of the dialog
     * @return The created warning dialog
     */
    public static Dialog warningDialog(final String message) {
        return creeate("Warning", message, DialogResult.OK, DialogResult.CANCEL);
    }

    /**
     * <h2>errorDialog(String)</h2>
     * <p>Creates a new error dialog with the given message.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     * var result = Dialog.errorDialog("This is an error message").showAndWait();
     * System.out.println(result);
     * }
     * @param message The message of the dialog
     * @return The created error dialog
     */
    public static Dialog errorDialog(final String message) {
        return creeate("Error", message, DialogResult.CLOSE);
    }

    /**
     * <h2>infoDialog(String)</h2>
     * <p>Creates a new information dialog with the given message.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     * var result = Dialog.infoDialog("This is an information message").showAndWait();
     * System.out.println(result);
     * }
     * @param message The message of the dialog
     * @return The created information dialog
     */
    public static Dialog infoDialog(final String message) {
        return creeate("Information", message, DialogResult.OK);
    }

    /**
     * <h2>confirmDialog(String)</h2>
     * <p>Creates a new confirmation dialog with the given message.</p>
     * <h3>Example:</h3>
     * <pre>
     * {@code
     * var result = Dialog.confirmDialog("Do you want to continue?").showAndWait();
     * System.out.println(result);
     * }
     * @param message The message of the dialog
     * @return The created confirmation dialog
     */
    public static Dialog confirmDialog(final String message) {
        return creeate("Confirmation", message, DialogResult.YES, DialogResult.NO);
    }

    /**
     * <h2>DialogResult</h2>
     * <p>An enumeration of possible dialog results.</p>
     */
    public enum DialogResult {
        OK,
        CANCEL,
        YES,
        NO,
        CLOSE,
        NONE;

        @Override
        public String toString() {
            return name();
        }
    }
}
