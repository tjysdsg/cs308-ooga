package ooga.model.observables;

import java.util.function.Consumer;

public interface ObservableModel {

  void setOnLevelChange(Consumer<ObservableLevel> callback);

  void setOnTextUpdate(Consumer<String> text);
}
