package ooga.model;

import ooga.view.Controller;
import ooga.view.ModelController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ControllerTest {

    Model model;
    ModelController controller;
    private int levelChanges;
    private int numObjects;


    @BeforeEach
    void setup() {
        levelChanges = 0;
        numObjects = 0;

        model = new Model();
        model.setOnLevelChange((level) -> {
                levelChanges++;
        });

        model.setOnNewObject((thing) -> {
            numObjects++;
        });

        controller = new Controller(model);
    }

    @Test
    void creatingGameTest() {
        assertDoesNotThrow(() -> controller.setGame(new File("data/example")));
    }
}
