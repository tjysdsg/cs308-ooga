package ooga.model.components.equipment;

import ooga.model.components.Component;
import ooga.model.components.HealthComponent;
import ooga.model.objects.GameObject;

/**
 * The weapon needs AP(attack power), and the health of the weapon, When health==0, the weapon will
 * be destroyed
 */
public class EquipmentComponent extends Component {

  private double payLoad = 0;

  public EquipmentComponent(int id, GameObject owner) {
    super(id, owner);
  }

  @Override
  public String typeUnerasure() {
    return EquipmentComponent.class.getName();
  }

  public double getPayLoad() {
    return payLoad;
  }

  public void setPayLoad(double payLoad) {
    this.payLoad = payLoad;
  }

}
