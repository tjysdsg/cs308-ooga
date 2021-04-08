package ooga.view.gameselection;

import static ooga.view.TestUtils.isPresent;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
class TestGameSelectionScene {

  private Stage stage;

  @Start
  private void start(Stage stage) {
    this.stage = stage;
  }

  @Test
  void splashScreenButtonsVisible(FxRobot robot) {
    FxAssert.verifyThat("#load-game-splash", isPresent);
  }

  @Test
  void gameSelectAppears(FxRobot robot) {
  }

  @Test
  void exitApplicationWorks(FxRobot robot) {
  }
}
