package ooga.model.observables;

import java.util.function.Consumer;
import ooga.model.Level;

public interface ObservableModel {

  void setOnLevelChange(Consumer<ObservableLevel> callback);
  void setOnTextUpdate(Consumer<String> text);
}
