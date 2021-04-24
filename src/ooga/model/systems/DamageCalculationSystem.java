package ooga.model.systems;

import ooga.model.annotations.Track;
import ooga.model.components.equipment.EquipmentComponent;
import ooga.model.components.PayloadMultiplier;
import ooga.model.managers.ECManager;

@Track({EquipmentComponent.class,PayloadMultiplier.class})
public class DamageCalculationSystem extends ComponentBasedSystem{
    //TODO: discuss whether a new class is needed for the weapon/armor
    ComponentMapper<EquipmentComponent> attackPowerMapper;
    ComponentMapper<PayloadMultiplier> multiplierMapper;
    ComponentMapper<EquipmentComponent> armorMapper;
    public DamageCalculationSystem(ECManager ecManager) {
        super(ecManager);
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
