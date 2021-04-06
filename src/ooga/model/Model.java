package ooga.model;

import com.google.common.base.Preconditions;
import com.google.common.collect.Multimap;
import java.util.function.Consumer;
import ooga.model.observables.ObservableModel;
import ooga.model.observables.ObservableObject;

public class Model implements ObservableModel {

  GameLevel level;

  public Model() {
    String name = Preconditions.checkNotNull("osjfa");
  }

  public void handleCode(String k, boolean on) {
    level.handleCode(k, on);
  }

  public void step() {

  }

  public void advanceLevel() {

  }

  @Override
  public void setOnLevelChange(Consumer<String> callback) {

  }

  @Override
  public void setOnNewObject(Consumer<ObservableObject> callback) {

  }

  @Override
  public void setOnTextUpdate(Consumer<String> text) {

  }

  public void play() {

  }

  private void checkCollisions() {

  }
}
