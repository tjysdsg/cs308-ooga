package ooga.model.systems.Equipment;

import ooga.model.components.EquipmentComponent;
import ooga.model.systems.ComponentBasedSystem;
import ooga.model.systems.ComponentMapper;
import ooga.model.systems.ECManager;

public class ArmorSystem extends ComponentBasedSystem {
    ComponentMapper<EquipmentComponent> componentMapper;
    public ArmorSystem(ECManager ecManager) {
        super(ecManager);
    }

    @Override
    public void update(double deltaTime) {
    }

}
