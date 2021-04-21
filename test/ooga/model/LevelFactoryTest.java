package ooga.model;

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

    String basicLevelFile = exampleDir + "levels/level1.json";
    int basicLevelObjectsCount = 42;

    @BeforeEach
    void setup() throws IOException, URISyntaxException {
       factory = new LevelFactory(getFile(objectDirectory), null);
    }

    @Test
    void buildBasicLevelTest() throws URISyntaxException, IOException {
        Level basicLevel = factory.buildLevel(getFile(basicLevelFile));

        // FIXME: Use a dedicated game config to test this, otherwise number of game objects keeps
        //  changing during development
        //  assertEquals(basicLevelObjectsCount, basicLevel.generateObjects().size());
    }


    File getFile(String fileName) throws URISyntaxException {
        return new File(getClass().getClassLoader().getResource(fileName).toURI());
    }
}
