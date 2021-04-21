package ooga.model.components;


import ooga.model.objects.GameObject;

public class AttackComponent extends Component {
    protected int frequency=0;// attack every n frames
    protected int counter=0;

    public AttackComponent(int id, GameObject owner) {
        super(id, owner);
    }

    @Override
    public String typeUnerasure() {
        return AttackComponent.class.getName();
    }

    public void setFrequency(int frequency){
        this.frequency=frequency;
    }

    public boolean attack(){
        return counter==0;
    }

    public void update(){
        counter+=1;
        counter=counter%frequency;
    }

    public void reset(){
        counter=0;
    }

}
