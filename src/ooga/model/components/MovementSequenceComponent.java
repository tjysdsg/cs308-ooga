package ooga.model.components;

import java.util.ArrayList;
import java.util.List;
import ooga.model.actions.NPCAction;
import ooga.model.objects.GameObject;

public class MovementSequenceComponent extends MovementComponent {

  List<NPCAction> actionSequence;
  List<Double> actualActionTime;
  List<Double> actionTime;
  private double cumTime = 0;
  private int actionIndex = 0;

  public MovementSequenceComponent(int id, GameObject owner) {
    super(id, owner);
    actionSequence = new ArrayList<>();
    actionTime = new ArrayList<>();
    actualActionTime=new ArrayList<>();
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
    if(actionIndex==0&&cumTime==0){
      if(actualActionTime==null){
        actualActionTime=new ArrayList<>();
      }
      else{
        actualActionTime.clear();
      }
      for(double t: actionTime){
        if(t==-1){
          actualActionTime.add(Math.random());
        }
        else{
          actualActionTime.add(t);
        }
      }
    }
    return actualActionTime;
  }

  public void setCumTime(double cumTime) {
    this.cumTime = cumTime;
  }

  public void setActionIndex(int actionIndex) {
    this.actionIndex = actionIndex;
  }
}
