package ooga.model.objects;

import java.util.ArrayList;
import java.util.List;
import ooga.model.Vector;
import ooga.model.actions.ActionInfo;
import ooga.model.components.Component;
import ooga.model.observables.ObservableObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameObject implements ObservableObject, Comparable<GameObject> {

  private List<Component> components = new ArrayList<>();
  private int id;
  private String name;
  private double x, y;
  private String imageID;
  private boolean isCollided;
  private List<String> isA;
  private transient Vector velocity = new Vector(0, 0);
  private boolean collidable = true;
  private double height, width;
  private List<ActionInfo> onCollide = new ArrayList<>();
  private transient Runnable positionCallback;
  private static final Logger logger = LogManager.getLogger(GameObject.class);

  public GameObject() {
  }

  public GameObject(int id, String name) {
    this.id = id;
    this.name = name;
    this.components = new ArrayList<>();
  }

  public List<ActionInfo> getActions() {
    return this.onCollide;
  }

  public boolean isA(String type) {
    return isA.contains(type);
  }

  public String getImageID() {
    return imageID;
  }

  @Override
  public void setOnPositionUpdate(Runnable callback) {
    positionCallback = callback;
  }

  public int getId() {
    return id;
  }

  public boolean isCollidable() {
    return collidable;
  }

  private void notifyPositionUpdate() {
    if (positionCallback != null) {
      positionCallback.run();
    }
  }

  public void setX(double x) {
    this.x = x;
    notifyPositionUpdate();
  }

  public void setY(double y) {
    this.y = y;
    notifyPositionUpdate();
  }

  @Override
  public double getX() {
    return x;
  }

  @Override
  public double getY() {
    return y;
  }

  @Override
  public double getHeight() {
    return this.height;
  }

  @Override
  public double getWidth() {
    return this.width;
  }

  @Override
  public int getMode() {
    return 0;
  }

  @Override
  public boolean isVisible() {
    return false;
  }

  @Override
  public int getID() {
    return this.id;
  }

  @Override
  public String getName() {
    return name;
  }

  public List<Component> getComponents() {
    return components;
  }

  public void addComponent(Component component) {
    this.components.add(component);
  }

  public void removeComponent(int id) {
    components.removeIf(comp -> comp.getId() == id);
  }

  public Vector getVelocity() {
    return velocity;
  }

  public Vector getLocation(){
    return new Vector(x,y);
  }

  public void setVelocity(Vector velocity) {
    this.velocity = velocity;
  }

  public void setVelocityX(double velocityX) {
    this.velocity.setX(velocityX);
  }

  public void setVelocityY(double velocityY) {
    this.velocity.setY(velocityY);
  }

  public boolean getCollided(){return isCollided;}

  public void setCollided(boolean collided){isCollided = collided;}

  @Override
  public int compareTo(GameObject o) {
    double diff = this.getX() - o.getX();
    return (int) Math.signum(diff);
  }
}
