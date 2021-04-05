package ooga.model.systems;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.Multimaps;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import ooga.model.GameObject;
import ooga.model.components.Component;

/**
 * System for creating, accessing, updating, and deleting components
 */
public class ComponentManager {

  private IDManager idManager;
  private Multimap<Class, Component> existingComponents;

  public ComponentManager() {
    idManager = new IDManager();
    existingComponents = MultimapBuilder.hashKeys().arrayListValues().build();
  }

  public <T> List<T> getComponents(Class<T> componentClass) {
    return (List<T>) existingComponents.get(componentClass);
  }

  public <T extends Component> T createComponent(
      GameObject owner, Class<T> componentClass
  ) {
    // TODO: log errors
    T ret = null;
    try {
      var constructor = componentClass.getConstructor(int.class, GameObject.class);
      ret = constructor.newInstance(idManager.getNewId(), owner);
      owner.addComponent(ret);
      existingComponents.put(componentClass, ret);
    } catch (NoSuchMethodException e) {
      System.out.println(
          "Cannot find a valid constructor in component class: " + componentClass.getName()
      );
    } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
      System.out.println(
          "Failed to instantiate component class: " + componentClass.getName()
      );
    }
    return ret;
  }
}
