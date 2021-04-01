package ooga.view;

import ooga.model.Model;
import ooga.model.ModelController;
import ooga.model.ObservableModel;
import ooga.model.ModelFactory;

public class View {
    Model model;
    ModelController modelController;
    public View() {
        ObservableModel model = ModelFactory.createObservableModel();
        this.modelController = model.getController();
    }
}
