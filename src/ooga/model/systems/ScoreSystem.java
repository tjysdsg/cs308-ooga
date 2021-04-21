package ooga.model.systems;

public class ScoreSystem extends ComponentBasedSystem{

  public ScoreSystem(ECManager ecManager) {
    super(ecManager);
    addCollisionMapping("increment_score",);
  }

  @Override
  public void update(double deltaTime) {

  }
}
