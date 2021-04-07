package ooga.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class LevelFactoryTest {

    LevelFactory factory;
    String exampleDir = "example/";
    String objectDirectory = exampleDir + "objects";
    String basicLevel = exampleDir + "levels/level1.json";

    @BeforeEach
    void setup() throws IOException, URISyntaxException {
       factory = new LevelFactory(getFile(objectDirectory));
    }

    @Test
    void buildBasicLevelTest() throws URISyntaxException, IOException {
        factory.buildLevel(getFile(basicLevel));
    }


    File getFile(String fileName) throws URISyntaxException {
        return new File(getClass().getClassLoader().getResource(fileName).toURI());
    }
}
