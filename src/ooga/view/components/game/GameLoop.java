package ooga.view.components.game;

import java.util.function.Consumer;
import javafx.animation.AnimationTimer;

/** Manage time and allow games to pause and play. */
public class GameLoop extends AnimationTimer {

  private Consumer<Double> callback;
  private long prevTime = System.nanoTime();
  private double delta;
  private boolean isPaused = false;

  @Override
  public void handle(long currentNanoTime) {
    delta = currentNanoTime - prevTime;
    delta /= 1e9;
    if (callback != null && !isPaused) {
      callback.accept(delta);
    }
    prevTime = currentNanoTime;
  }

  /** Stop the gameloop. */
  public void pause() {
    this.isPaused = true;
  }

  /** Start the gameloop/ */
  public void play() {
    this.isPaused = false;
  }

  /**
   * Notify the specified caller when time has changed.
   *
   * @param run - The callback that will be called every time change.
   */
  public void setOnUpdate(Consumer<Double> run) {
    this.callback = run;
  }
}
