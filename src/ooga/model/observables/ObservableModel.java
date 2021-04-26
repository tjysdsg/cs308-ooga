package ooga.model.observables;

import java.util.function.Consumer;

public interface ObservableModel {

  void setOnLevelChange(Consumer<ObservableLevel> callback);

  void setOnNewObject(Consumer<ObservableObject> callback);

  void setOnTextUpdate(Consumer<String> text);

  void setOnObjectDestroy(Consumer<ObservableObject> callback);
}
