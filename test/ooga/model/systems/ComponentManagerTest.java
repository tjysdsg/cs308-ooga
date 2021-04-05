package ooga.model.systems;

import ooga.model.GameObject;
import ooga.model.components.Component;
import ooga.model.components.PlayerComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestComponent extends Component {

  public TestComponent(int id, GameObject owner) {
    super(id, owner);
  }
}

public class ComponentManagerTest {

  ComponentManager componentManager;
  GameObject go = new GameObject(0, "test game object");

  ComponentManagerTest() {
    componentManager = new ComponentManager();
  }

  @BeforeEach
  void setup() {
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
  void testCreateComponentUniqueIds() {
    // TODO:
  }

}
