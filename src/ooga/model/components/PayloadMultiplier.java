package ooga.model.components;

import ooga.model.objects.GameObject;

public class PayloadMultiplier extends Component{
    private double multiplier=0;
    public PayloadMultiplier(int id, GameObject owner) {
        super(id, owner);
    }

    @Override
    public String typeUnerasure() {
        return PayloadMultiplier.class.getName();
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    public double getMultiplier() {
        return multiplier;
    }
}