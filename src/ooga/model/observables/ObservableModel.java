package ooga.model.observables;

import java.util.function.Consumer;
import ooga.model.Level;
import ooga.model.ModelController;

public interface ObservableModel {

  void setOnLevelChange(Consumer<Level> callback);
  void setOnNewObject(Consumer<ObservableObject> callback);
  ModelController getController();
  void setOnTextUpdate(Consumer<String> text);

}
