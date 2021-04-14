package ooga.view.components.game;

import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {
  private Runnable callback;

  @Override
  public void handle(long currentNanoTime) {
    if (callback != null) {
      callback.run();
    }
  }

  public void setUpdateTime(long timeInNano) {}

  public void setOnUpdate(Runnable run) {
    this.callback = run;
  }
}
