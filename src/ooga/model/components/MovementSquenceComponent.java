package ooga.model.components;

import ooga.model.objects.GameObject;

import java.util.ConcurrentModificationException;
import java.util.List;

public class MovementSquenceComponent extends Component {
    List<Integer> actionSequence;
    List<Double> actionTime;
    private double cumTime=0;
    private double cycleTime=0;
    public MovementSquenceComponent(int id, GameObject owner) {
        super(id, owner);
    }

    public void initCycleTime(){
        for (Double aDouble : actionTime) {
            cycleTime += aDouble;
        }
    }
    public void cumTimeUpdate(){
        if(cycleTime!=0){
            cumTime=cumTime%cycleTime;
        }
    }
}
