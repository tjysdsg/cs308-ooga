package ooga.model.components;

import ooga.model.objects.GameObject;

public class HateComponent extends Component{
    private double range;
    private boolean hate;
    private int party; // different parties will have different hate relationship
    public HateComponent(int id, GameObject owner) {
        super(id, owner);
    }

    public void setRange(double range){
        this.range=range;
    }
    public void detectHate(double distance){
        if (distance<this.range){
            hate=true;
        }
        hate=false;
    }
    public boolean getHate(){
        return hate;
    }
}
