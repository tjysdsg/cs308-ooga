package ooga.view.components.game;

import java.util.function.Consumer;

import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {
  private Consumer<Double> callback;
  private long prevTime;
  private double delta;

  @Override
  public void handle(long currentNanoTime) {
    delta = currentNanoTime - prevTime;
    delta /= 1e9;
    if (callback != null) {
      callback.accept(delta);
    }
  }

  public void setUpdateTime(long timeInNano) {}

  public void setOnUpdate(Consumer<Double> run) {
    this.callback = run;
  }
}
