package ooga.model.components;

import com.google.common.collect.Multimap;
import ooga.model.GameObject;

public abstract class Component {
  private Multimap<String, Runnable> functionMaps;
  private GameObject obj;
  private int id;

  public Component(GameObject obj) {
    this.obj = obj;
  }
}
