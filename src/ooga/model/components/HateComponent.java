package ooga.model.components;

import ooga.model.objects.GameObject;

public class HateComponent extends Component{
    private double range;
    private boolean hate;
    private int party; // different parties will have different hate relationship
    private int frequency;
    public HateComponent(int id, GameObject owner) {
        super(id, owner);
    }

    public void setRange(double range){
        this.range=range;
    }
    public boolean detectHate(double distance){
        return distance<this.range;
    }
    public void setFrequency(int frequency){
        this.frequency=frequency;
    }

    public int getFrequency() {
        return frequency;
    }

    public boolean getHate(){
        return hate;
    }
}
