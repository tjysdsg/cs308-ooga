package ooga.model.systems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import ooga.model.objects.GameObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EntityManagerTest {

  EntityManager entityManager;
  ComponentManager componentManager;
  GameObject go;

  EntityManagerTest() {
    componentManager = new ComponentManager();
    entityManager = new EntityManager(componentManager);
  }

  @BeforeEach
  void setup() {
  }

  @Test
  void testCreateEntity() {
    go = entityManager.createEntity("Test game object");
    assertNotNull(go);
  }

  @Test
  void testGetAllEntities() {
    int numGOs = 10;
    for (int i = 0; i < numGOs; ++i) {
      go = entityManager.createEntity(Integer.toString(i));
      assertNotNull(go);
    }

    List<GameObject> objects = entityManager.getEntities();
    assertEquals(numGOs, objects.size());
  }

}
