package ooga.model;

import java.util.HashMap;
import java.util.Map;
import ooga.model.components.Component;
import ooga.model.managers.ECManager;
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
  Level basicLevel;

  @BeforeEach
  void setup() throws IOException, URISyntaxException {
    objectsCount = new HashMap<>();
    factory = new LevelFactory(getFile(objectDirectory));
    basicLevel = factory.buildLevel(getFile(basicLevelFile));
  }

  @Test
  void buildBasicLevelTest() throws URISyntaxException, IOException {
    assertEquals(basicLevelObjectsCount, basicLevel.generateObjects().size());
  }

  @Test
  void assertCorrectObjects() {
    int goombaCount = 1;
    String goombaName = "goomba";
    int marioCount = 1;
    String marioName = "mario";
    int qBlockCount = 3;
    String qBlockName = "qblock";
    int brickCount = 3;
    String brickName = "brick";
    int blockCount = 34;
    String blockName = "block";

    ECManager ecManager = basicLevel.getECManager();

    assertEquals(goombaCount, ecManager.getEntities(goombaName).size());
    assertEquals(marioCount, ecManager.getEntities(marioName).size());
    assertEquals(qBlockCount, ecManager.getEntities(qBlockName).size());
    assertEquals(brickCount, ecManager.getEntities(brickName).size());
    assertEquals(blockCount, ecManager.getEntities(blockName).size());
  }

  File getFile(String fileName) throws URISyntaxException {
    return new File(getClass().getClassLoader().getResource(fileName).toURI());
  }
}
