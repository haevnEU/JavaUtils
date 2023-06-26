package de.haevn.jfx.mvc;

import de.haevn.exceptions.InvalidCastException;
import de.haevn.mvc.IWidget;
import javafx.scene.layout.Pane;

public interface IJfxWidget extends IWidget {
    default Pane getPane() throws InvalidCastException {
        if(! (getView() instanceof Pane)){
            throw new InvalidCastException(getView().getClass(), Pane.class);
        }
        return (Pane) getView();
    }
}
