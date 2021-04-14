package ooga.model.components;


import ooga.model.objects.GameObject;

public class AttackComponent extends Component {
    protected int frequency;// attack every n frames

    public AttackComponent(int id, GameObject owner) {
        super(id, owner);
    }

}
