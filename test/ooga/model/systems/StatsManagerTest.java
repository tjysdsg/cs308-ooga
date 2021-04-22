package ooga.model.systems;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import ooga.model.StatsInfo;
import ooga.model.Vector;
import ooga.model.objects.GameObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StatsManagerTest {

  ECManager ecManager = new ECManager(null, null, null);
  StatsManager statsManager = new StatsManager();
  List<BaseSystem> systems = new ArrayList<>();
  List<StatsInfo> stats;

  @BeforeEach
  void setup() {
    ecManager.createEntity("go1");
    ecManager.createEntity("go2");
    ecManager.createEntity("go3");

    systems.add(new TransformSystem(ecManager));

    for (var s : systems) {
      s.registerAllStats(statsManager);
    }
  }

  @Test
  void testPositionStats() {
    statsManager.setOnStatisticUpdate("position", info -> stats = info);

    List<GameObject> objects = ecManager.getEntities();
    for (GameObject o : objects) {
      o.setVelocity(new Vector(10, 10));
    }

    assertTrue(statsManager.getTrackableStatistics().contains("position"));
    for (var s : systems) {
      s.update(1.0);
    }

    stats.sort(Comparator.comparingInt(StatsInfo::entityID));
    int id = 0;
    for (StatsInfo s : stats) {
      assertEquals("(10.0, 10.0)", s.value());
      assertEquals(id, s.entityID());
      ++id;
    }
  }

}
