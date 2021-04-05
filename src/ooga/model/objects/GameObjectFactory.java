package ooga.model.objects;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;
import ooga.model.components.Component;

public class GameObjectFactory implements ObjectFactory {

  private String type;
  private int height, width;
  private String imageID;

  private List<Component> components = new ArrayList<>();

  public GameObjectFactory(String type,
      int height, int width,
      List<Component> components,
      String imageID) {

    this.type = type;
    this.height = height;
    this.width = width;
    this.components = components;
    this.imageID = imageID;
  }

  public GameObjectFactory(String type,
      int height, int width,
      String imageID) {

    this.type = type;
    this.height = height;
    this.width = width;
    this.imageID = imageID;
  }

  @Override
  public GameObject build(int x, int y) {
    return new ActiveAgent(type, height, width, components, imageID, x, y);
  }

  @Override
  public String getType() {
    return type;
  }
}
