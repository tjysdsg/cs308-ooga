package ooga.model.actions;

import ooga.model.managers.ECManager;
import ooga.model.objects.GameObject;
import ooga.model.objects.ObjectInstance;

public class ObjectSpawner {
  private String payload;
  private ECManager ecManager;

  public ObjectSpawner(String payload, ECManager ecManager) {
    this.payload = payload;
    this.ecManager = ecManager;
  }

  public void handleSpawn(Integer id, boolean on) {
    if (on) {
      ObjectInstance newGuy = parsePayload(payload, ecManager.getEntity(id));
      ecManager.addEntity(newGuy);
    }
  }

  private ObjectInstance parsePayload(String payload, GameObject original) {
    String[] params = payload.split(",");
    String which = params[0];
    int offsetx = Integer.parseInt(params[1]);
    int offsety = Integer.parseInt(params[2]);
    
    return new ObjectInstance(which, original.getX() + offsetx, original.getY() + offsety);
  }
}
