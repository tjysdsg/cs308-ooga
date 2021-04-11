package ooga.model.systems;

import ooga.model.components.EquipmentComponent;

public class ArmorSystem extends ComponentBasedSystem{
    ComponentMapper<EquipmentComponent> componentMapper;
    public ArmorSystem(EntityManager entityManager, ComponentManager componentManager) {
        super(entityManager, componentManager);
    }

    @Override
    public void update(double deltaTime) {

    }

}
