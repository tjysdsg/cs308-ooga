package ooga.model.systems.creature;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import ooga.model.components.HealthComponent;
import ooga.model.components.MovementComponent;
import ooga.model.components.PlayerComponent;
import ooga.model.components.enemy.HateComponent;
import ooga.model.managers.ECManager;
import ooga.model.objects.GameObject;
import ooga.model.systems.HealthSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SampleEnemySystemTest {
  ECManager ecManager;
  GameObject player;
  HealthComponent healthComponentP;
  HealthComponent healthComponentE;

  GameObject enemy;
  HateComponent hateComponent;
  PlayerComponent playerComponent;
  MovementComponent movementComponentP;
  MovementComponent movementComponentE;

  PlayerSystem playerSystem;
  SampleEnemySystem sampleEnemySystem;
  HealthSystem healthSystem;

  @BeforeEach
  void setup(){
    ecManager = new ECManager(null,null, null);

    enemy=ecManager.createEntity("enemy");
    player=ecManager.createEntity("player");

    hateComponent = ecManager.createComponent(enemy,HateComponent.class);
    playerComponent =ecManager.createComponent(player,PlayerComponent.class);
    healthComponentE=ecManager.createComponent(enemy,HealthComponent.class);
    healthComponentP=ecManager.createComponent(player,HealthComponent.class);
    movementComponentE=ecManager.createComponent(enemy,MovementComponent.class);
    movementComponentP=ecManager.createComponent(player,MovementComponent.class);

    enemy.setX(0);
    enemy.setY(0);
    player.setY(0);
    player.setX(5);
    hateComponent.setFrequency(10);
    hateComponent.setRange(10);
    hateComponent.setDamage(-10);
    healthComponentP.setHealth(100);
    healthComponentE.setHealth(100);
    healthSystem= new HealthSystem(ecManager);
    playerSystem=new PlayerSystem(ecManager);

    sampleEnemySystem = new SampleEnemySystem(ecManager);

  }

  @Test
  void update(){
    sampleEnemySystem.update(0.1);
    assertEquals(90,healthComponentP.getHealth());

    sampleEnemySystem.update(0.1);
    assertEquals(90,healthComponentP.getHealth());
    for(int i=0;i<9;i++){
      sampleEnemySystem.update(0.1);
    }
    assertEquals(80,healthComponentP.getHealth());
    hateComponent.setRange(4);
    for(int i=0;i<10;i++){
      sampleEnemySystem.update(0.1);
    }
    assertEquals(80,healthComponentP.getHealth());
    hateComponent.setRange(10);
    for(int i=0;i<100;i++){
      sampleEnemySystem.update(0.1);
    }
    healthSystem.update(0.1);
    List<PlayerComponent> tmp=ecManager.getComponents(PlayerComponent.class);
    assertEquals(0,ecManager.getComponents(PlayerComponent.class).size());
  }



}