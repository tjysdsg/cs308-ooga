package ooga.model.systems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import ooga.model.components.HealthComponent;
import ooga.model.objects.GameObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HealthSystemTest {

  EntityManager entityManager;
  ComponentManager componentManager;
  GameObject go;
  HealthSystem healthSystem;
  HealthComponent healthComponent;

  @BeforeEach
  void setup() {
    componentManager = new ComponentManager();
    entityManager = new EntityManager(componentManager);
    healthSystem = new HealthSystem(entityManager, componentManager);

    go = entityManager.createEntity("test entity");
    healthComponent = componentManager.createComponent(go, HealthComponent.class);
  }

//  @Test
//  void testChangeHealth() {
//    healthComponent.setHealth(100);
//    healthSystem.changeHealth(go.getId(), -50);
//    assertEquals(50, healthComponent.getHealth());
//  }
//
//  @Test
//  void testDeathHealth() {
//    healthComponent.setHealth(100);
//    healthSystem.changeHealth(go.getId(), -100);
//    healthSystem.update(0.1);
//    assertEquals(0, healthComponent.getHealth());
//    assertEquals(0, entityManager.getEntities().size());
//  }
}
