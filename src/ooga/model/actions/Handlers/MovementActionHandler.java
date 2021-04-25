package ooga.model.actions.Handlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ooga.model.components.MovementComponent;

public abstract class MovementActionHandler {

  private List<MovementComponent> listeners = new ArrayList<>();

  public void addListener(MovementComponent listener) {
    listeners.add(listener);
  }

  protected List<MovementComponent> getListeners() {
    return Collections.unmodifiableList(listeners);
  }

  public abstract void handleAction(boolean on);
}
