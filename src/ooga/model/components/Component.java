package ooga.model.components;

import com.google.common.collect.Multimap;
import ooga.model.GameObject;

//P LAYER WHAKADING COMPONETN
public abstract class Component {
  private Multimap<String, Runnable> functionMaps;
  private GameObject obj;

  public Component(GameObject obj) {
    this.obj = obj;
  }
}
