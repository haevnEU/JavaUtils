package de.haevn.mvc;

public interface IWidget {
    String getName();
    IController getController();
    IModel getModel();
    IView getView();

}
