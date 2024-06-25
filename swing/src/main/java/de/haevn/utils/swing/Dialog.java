package de.haevn.utils.swing;

import de.haevn.utils.TimeUtils;
import de.haevn.utils.debug.ThreadTools;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicBoolean;

public final class Dialog extends JFrame {
    private final AtomicBoolean visibility = new AtomicBoolean(false);
    private DialogResult result = DialogResult.NONE;

    public Dialog(final String title, final String message, DialogResult ... result){
        setTitle(title);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        final JPanel bottomPanel = new JPanel();
        for (DialogResult r : result){
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

    public DialogResult getResult(){
        return result;
    }

    public DialogResult showAndWait(){
        setVisible(true);
        ThreadTools.waitFor(visibility);
        return getResult();
    }

    @Override
    public void setVisible(boolean visible) {
        visibility.set(visible);
        super.setVisible(visible);
    }

    public static Dialog creeate(final String title, final String message, DialogResult ... result){
        return new Dialog(title, message, result);
    }

    public static Dialog warningDialog(final String message){
        return creeate("Warning", message, DialogResult.OK, DialogResult.CANCEL);
    }

    public static Dialog errorDialog(final String message){
        return creeate("Error", message, DialogResult.CLOSE);
    }

    public static Dialog infoDialog(final String message){
        return creeate("Information", message, DialogResult.OK);
    }

    public static Dialog confirmDialog(final String message){
        return creeate("Confirmation", message, DialogResult.YES, DialogResult.NO);
    }




    public enum DialogResult {
        OK,
        CANCEL,
        YES,
        NO,
        CLOSE,
        NONE
    }
}
