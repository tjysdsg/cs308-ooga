package ooga.view.gameselection;

import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.stage.Stage;
import ooga.view.components.gameselection.GSelectionScene;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.util.WaitForAsyncUtils;

@ExtendWith(ApplicationExtension.class)
class TestGameSelectionScene {

  private Stage stage;
  private static final Predicate<Node> verifyFade = node -> node.getOpacity() < 1;
  private static final Predicate<Node> isPresent = Node::isVisible;

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
