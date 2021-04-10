package ooga.model.systems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import ooga.model.components.HealthComponent;
import ooga.model.components.PlayerComponent;
import ooga.model.objects.GameObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ComponentMapperTest {

  EntityManager entityManager;
  ComponentManager componentManager;
  GameObject go1;
  GameObject go2;
  PlayerComponent comp;
  ComponentMapper<PlayerComponent> componentMapper;

  @BeforeEach
  void setup() {
    componentManager = new ComponentManager();
    entityManager = new EntityManager(componentManager);

    go1 = entityManager.createEntity("test entity 1");
    go2 = entityManager.createEntity("test entity 2");
    comp = componentManager.createComponent(go1, PlayerComponent.class);
    componentManager.createComponent(go2, PlayerComponent.class);

    componentMapper = new ComponentMapper<>(entityManager, componentManager, PlayerComponent.class);
  }

  @Test
  void testGet() {
    // check returns correct comp
    PlayerComponent tmp = componentMapper.get(go1.getId());
    assertEquals(comp.getId(), tmp.getId());

    // make sure doesn't return component that belongs to other entities
    tmp = componentMapper.get(go2.getId());
    assertNotEquals(comp.getId(), tmp.getId());

    // returns null if not found
    ComponentMapper<HealthComponent> tmpMapper = new ComponentMapper<>(
        entityManager, componentManager, HealthComponent.class
    );
    assertNull(tmpMapper.get(go1.getId()));
  }

  @Test
  void testCreate() {
    PlayerComponent tmp = componentMapper.create(go1.getId());
    assertNotNull(tmp);
    assertNotEquals(comp.getId(), tmp.getId());
  }

  @Test
  void testRemove() {
    // check if removes
    componentMapper.remove(go1.getId());
    assertNull(componentMapper.get(go1.getId()));

    // make sure GameObject.components() is updated
    assertEquals(0, go1.getComponents().size());

    // make sure doesn't remove components in other entities
    assertNotNull(componentMapper.get(go2.getId()));
  }
}
