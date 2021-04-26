package ooga.model.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import ooga.model.actions.NPCAction;
import ooga.model.objects.GameObject;

public class MovementSequenceComponent extends MovementComponent {
  private transient Random RNJesus = new Random();
  List<NPCAction> actionSequence;
  List<Double> actionTime;
  private double cumTime = 0;
  private int actionIndex = 0;

  public MovementSequenceComponent(){};

  public MovementSequenceComponent(int id, GameObject owner) {
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

  public List<NPCAction> getActionSequence() {
    return actionSequence;
  }

  public List<Double> getActionTime() {
    List<Double> ret = new ArrayList<>();
    for (Double time : actionTime) {
      if (time < 0) {
        ret.add(RNJesus.nextDouble() * Math.abs(time) + 1);
      } else {
        ret.add(time);
      }
    }
    return actionTime;
  }

  public void setCumTime(double cumTime) {
    this.cumTime = cumTime;
  }

  public void setActionIndex(int actionIndex) {
    this.actionIndex = actionIndex;
  }
}
