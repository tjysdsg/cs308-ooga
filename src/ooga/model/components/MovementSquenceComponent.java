package ooga.model.components;

import java.util.ArrayList;
import java.util.List;
import ooga.model.objects.GameObject;

public class MovementSquenceComponent extends MovementComponent {

  List<String> actionSequence;
  List<Double> actionTime;
  private double cumTime = 0;
  private int actionIndex = 0;

  public MovementSquenceComponent(int id, GameObject owner) {
    super(id, owner);
    actionSequence = new ArrayList<>();
    actionTime = new ArrayList<>();
  }

  public int getActionIndex() {
    return actionIndex;
  }

  public double getCumTime() {
    return cumTime;
  }

  public List<String> getActionSequence() {
    return actionSequence;
  }

  public List<Double> getActionTime() {
    return actionTime;
  }

  public void setCumTime(double cumTime) {
    this.cumTime = cumTime;
  }

  public void setActionIndex(int actionIndex) {
    this.actionIndex = actionIndex;
  }
}
