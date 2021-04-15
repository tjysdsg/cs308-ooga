package ooga.model.systems;

import ooga.model.components.EquipmentComponent;

public class ArmorSystem extends ComponentBasedSystem{
    ComponentMapper<EquipmentComponent> componentMapper;
    public ArmorSystem(ECManager ecManager) {
        super(ecManager);
    }

    @Override
    public void update(double deltaTime) {
    }

}
