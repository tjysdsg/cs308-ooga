package ooga.model.actions;

import ooga.model.Vector;
import ooga.model.objects.GameObject;
import ooga.model.objects.ObjectInstance;

public class Spawn extends PlayerAction {

  private String what;
  private Vector offset = new Vector();
  private Vector speed = new Vector();

  @Override
  public void handleAction(boolean on) {
    GameObject owner = getOwner().getOwner();
    double x = owner.getX() + offset.getX();
    double y = owner.getY() + offset.getY();
    getManager().addEntity(new ObjectInstance(what, x, y, speed));
  }
}
