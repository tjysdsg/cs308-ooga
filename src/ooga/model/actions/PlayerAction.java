package ooga.model.actions;

import ooga.model.components.MovementComponent;
import ooga.model.components.PlayerComponent;
import ooga.model.managers.ECManager;

public abstract class PlayerAction {

  private transient ECManager ecManager;
  private String input;
  private MovementComponent owner;

  public void setOwner(MovementComponent owner) {
    this.owner = owner;
  }

  public void setManager(ECManager ecManager) {
    this.ecManager = ecManager;
  }

  protected ECManager getManager() {
    return ecManager;
  }

  public abstract void handleAction(boolean on);

  public String getInput() {
    return input;
  }

  public MovementComponent getOwner() {
    return owner;
  }
}
