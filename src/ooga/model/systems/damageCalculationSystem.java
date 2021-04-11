package ooga.model.systems;

import ooga.model.components.EquipmentComponent;
import ooga.model.components.PayloadMultiplier;

public class damageCalculationSystem extends ComponentBasedSystem{
    //TODO: discuss whether a new class is needed for the weapon/armor
    ComponentMapper<EquipmentComponent> attackPowerMapper;
    ComponentMapper<PayloadMultiplier> multiplierMapper;
    ComponentMapper<EquipmentComponent> armorMapper;
    public damageCalculationSystem(EntityManager entityManager, ComponentManager componentManager) {
        super(entityManager, componentManager);
        attackPowerMapper=getComponentMapper(EquipmentComponent.class);
        multiplierMapper=getComponentMapper(PayloadMultiplier.class);
        //TODO: unfinished
        armorMapper = getComponentMapper(EquipmentComponent.class);
    }

    /**
     * Calculate the AD
     * @param entityId  ID of the entity who's executing the damage
     */
    public double calculateAttackDamage(int entityId){
        return multiplierMapper.get(entityId).getMultiplier()*attackPowerMapper.get(entityId).getPayLoad();
    }

    public double calculateHealthDamage(int entityId, boolean attack){
        if(attack){
            //TODO: add Indestructible components exception: initial weapon, etc.
            armorMapper.get(entityId).healthIncrement(-1, false);
            attackPowerMapper.get(entityId).healthIncrement(-1, false);
            return calculateAttackDamage(entityId)-armorMapper.get(entityId).getPayLoad();
        }
        return 0;
    }
    //TODO: How to trigger the attack
    @Override
    public void update(double deltaTime) {

    }
}
