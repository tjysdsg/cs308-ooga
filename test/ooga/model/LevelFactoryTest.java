package ooga.model;

import java.util.HashMap;
import java.util.Map;
import ooga.model.objects.GameObject;
import ooga.model.observables.ObservableObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LevelFactoryTest {

  LevelFactory factory;
  String exampleDir = "example/";
  String objectDirectory = exampleDir + "objects";
  Map<String, Integer> objectsCount;

  String basicLevelFile = exampleDir + "levels/level1.json";
  int basicLevelObjectsCount = 42;
  int newObjectsCount;
  Level basicLevel;

  @BeforeEach
  void setup() throws IOException, URISyntaxException {
    objectsCount = new HashMap<>();
    newObjectsCount = 0;
    factory = new LevelFactory(getFile(objectDirectory), this::incrementObjectsCount, null);
    basicLevel = factory.buildLevel(getFile(basicLevelFile));
  }

  private void incrementObjectsCount(ObservableObject observableObject) {
    objectsCount.putIfAbsent(observableObject.getName(), 0);
    objectsCount.put(observableObject.getName(), objectsCount.get(observableObject.getName() + 1));
  }


  @Test
  void buildBasicLevelTest() throws URISyntaxException, IOException {
    // FIXME: Use a dedicated game config to test this, otherwise number of game objects keeps
    //  changing during development
    assertEquals(basicLevelObjectsCount, basicLevel.generateObjects().size());
    assertEquals(basicLevelObjectsCount, newObjectsCount);
  }

  @Test
  void assertCorrectObjects() {
    int goombaCount = 1;
    String goombaName = "goomba";
    int marioCount = 1;
    String marioName = "mario";
    int qBlockCount = 3;
    String qBlockName = "qblock";
    int brickCount = 37;
    String brickName = "brick";

    assertEquals(goombaCount, objectsCount.get("goomba"));
  }


  File getFile(String fileName) throws URISyntaxException {
    return new File(getClass().getClassLoader().getResource(fileName).toURI());
  }
}
