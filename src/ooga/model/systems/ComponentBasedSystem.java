package ooga.model.systems;

import java.util.HashMap;
import java.util.Map;
import ooga.model.annotations.Track;
import ooga.model.components.Component;

public abstract class ComponentBasedSystem extends BaseSystem {

  private ECManager ecManager;
  private Map<Class<? extends Component>, ComponentMapper<? extends Component>> componentMappers;

  public ComponentBasedSystem(ECManager ecManager) {
    this.ecManager = ecManager;

    /* build componentMappers,
     * so that subclasses can use getComponentMapper() to get ComponentMapper of a specific
     * component type
     */
    componentMappers = new HashMap<>();
    Class<? extends ComponentBasedSystem> thisClass = this.getClass();
    Class<? extends Component>[] trackedComponents;
    Track tracked = thisClass.getAnnotation(Track.class);
    if (tracked != null) {
      trackedComponents = tracked.value();
      for (Class<? extends Component> compType : trackedComponents) {
        componentMappers.put(
            compType, new ComponentMapper<>(ecManager, compType)
        );
      }
    }
  }

  public <T extends Component> ComponentMapper<T> getComponentMapper(Class<T> componentClass) {
    ComponentMapper<T> ret = (ComponentMapper<T>) componentMappers.get(componentClass);
    if (ret == null) {
      throw new RuntimeException(
          "Cannot find ComponentMapper for " + componentClass.getName()
              + ". Please use @Track() to specify it."
      );
    }

    return ret;
  }

  protected ECManager getECManager() {
    return ecManager;
  }

}
