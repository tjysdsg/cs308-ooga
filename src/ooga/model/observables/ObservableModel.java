package ooga.model.observables;

import java.util.function.Consumer;
import ooga.model.Level;

public interface ObservableModel {

  void setOnLevelChange(Consumer<String> callback);
  void setOnNewObject(Consumer<ObservableObject> callback);
  void setOnTextUpdate(Consumer<String> text);
}
