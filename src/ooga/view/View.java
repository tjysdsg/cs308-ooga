package ooga.view;

import javafx.stage.Stage;
import ooga.model.Model;
import ooga.model.ModelFactory;
import ooga.model.observables.ObservableModel;

public class View {
  Model model;
  ModelController modelController;

  public View(Stage stage) {
    ObservableModel model = ModelFactory.createObservableModel();
    //this.modelController = model.getController();
  }
}
