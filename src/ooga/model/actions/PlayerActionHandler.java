package ooga.model.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ooga.model.components.PlayerComponent;

public abstract class PlayerActionHandler {

  private List<PlayerComponent> listeners = new ArrayList<>();

  public void addListener(PlayerComponent listener) {
    listeners.add(listener);
  }

  protected List<PlayerComponent> getListeners() {
    return Collections.unmodifiableList(listeners);
  }

  public abstract void handleAction(boolean on);
}
