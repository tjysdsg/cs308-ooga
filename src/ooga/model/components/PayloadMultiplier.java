package ooga.model.components;

import ooga.model.objects.GameObject;

public class PayloadMultiplier extends Component{
    private double multiplier=0;
    public PayloadMultiplier(int id, GameObject owner) {
        super(id, owner);
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    public double getMultiplier() {
        return multiplier;
    }
}
