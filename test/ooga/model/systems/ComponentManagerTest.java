package ooga.model.systems;

import ooga.model.GameObject;
import ooga.model.components.Component;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestComponent extends Component {

  public TestComponent(int id, GameObject owner) {
    super(id, owner);
  }
}

public class ComponentManagerTest {

  ComponentManager componentManager;

  ComponentManagerTest() {
    componentManager = new ComponentManager();
  }

  @BeforeEach
  void setup() {
  }

  @Test
  void testCreateComponent() {
    GameObject go = new GameObject(0, "test game object");
    TestComponent component = componentManager.createComponent(go, TestComponent.class);
    assertNotNull(component);
  }

  @Test
  void testCreateComponentUniqueIds() {
    // TODO:
  }

}
