package ooga.model.systems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ooga.model.objects.GameObject;
import ooga.model.components.Component;
import ooga.model.components.PlayerComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestComponent extends Component {

  public TestComponent(int id, GameObject owner) {
    super(id, owner);
  }
}

public class ComponentManagerTest {

  ComponentManager componentManager;
  GameObject go;

  ComponentManagerTest() {
    componentManager = new ComponentManager();
  }

  @BeforeEach
  void setup() {
    go = new GameObject(0, "test game object");
  }

  @Test
  void testCreateComponent() {
    TestComponent component = componentManager.createComponent(go, TestComponent.class);
    assertNotNull(component);
  }

  @Test
  void testSystemGetComponent() {
    PlayerSystem playerSystem = new PlayerSystem(componentManager);
    componentManager.createComponent(go, PlayerComponent.class);
    var comps = playerSystem.getComponents();
    assertEquals(1, comps.size());
  }

  @Test
  void testRemoveComponent() {
    componentManager.createComponent(go, PlayerComponent.class);
    componentManager.createComponent(go, PlayerComponent.class);
    componentManager.createComponent(go, PlayerComponent.class);
    Component comp = componentManager.createComponent(go, PlayerComponent.class);

    componentManager.removeComponent(PlayerComponent.class, comp.getId());
    var comps = componentManager.getComponents(PlayerComponent.class);
    assertEquals(3, comps.size());
  }

  @Test
  void testComponentRegistering() {
    Component comp1 = new PlayerComponent(9999, null);
    Component comp2 = new PlayerComponent(9999, null);
    go.addComponent(comp1);
    go.addComponent(comp2);

    componentManager.registerExistingComponents(List.of(go));
    assertNotEquals(9999, comp1.getId());
    assertNotEquals(9999, comp2.getId());
    assertEquals(go, comp1.getOwner());
    assertEquals(go, comp2.getOwner());
  }

  @Test
  void testCreateComponentUniqueIds() {
    // TODO:
  }

}
