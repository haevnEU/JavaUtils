package de.haevn.mvc;

public interface IController {

    void link(IView view);
    default void link(IView view, IModel model){}
}
