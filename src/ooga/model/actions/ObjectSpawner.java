package ooga.model.actions;

import java.util.Timer;
import ooga.model.managers.ECManager;
import ooga.model.objects.GameObject;
import ooga.model.objects.ObjectInstance;

public class ObjectSpawner {
  private String payload;
  private ECManager ecManager;
  private double cooldown;
  private long start = System.nanoTime();

  public ObjectSpawner(String payload, ECManager ecManager) {
    this.payload = payload;
    this.ecManager = ecManager;
  }

  public void handleSpawn(Integer id, boolean on) {
    if (on) {
      double elapsedTime = (Long.valueOf(System.nanoTime() - start)).doubleValue()/1000000000;
      System.out.println(elapsedTime);
      if (elapsedTime > cooldown) {
        ObjectInstance newGuy = parsePayload(payload, ecManager.getEntity(id));
        ecManager.addEntity(newGuy);
        start = System.nanoTime();
      }
    }
  }

  private ObjectInstance parsePayload(String payload, GameObject original) {
    String[] params = payload.split(",");
    String which = params[0];

    int offsetx = Integer.parseInt(params[1]);
    int offsety = Integer.parseInt(params[2]);
    cooldown = Double.parseDouble(params[3]);
    
    return new ObjectInstance(which, original.getX() + offsetx, original.getY() + offsety);
  }
}
