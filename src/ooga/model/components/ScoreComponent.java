package ooga.model.components;

import ooga.model.objects.GameObject;

// FIXME: remove score in PlayComponent after MoveComponent is completed
public class ScoreComponent extends Component {

  private double score = 0;

  public double getScore() {
    return score;
  }

  public void changeScore(double score, boolean allowNegative) {
    double tmpScore = score + this.score;
    if (tmpScore > 0) {
      this.score = tmpScore;
    } else {
      if (!allowNegative) {
        this.score = 0;
      }
    }
  }

  protected ScoreComponent() {
    super();
  }

  public ScoreComponent(int id, GameObject owner) {
    super(id, owner);
  }

  public String typeUnerasure() {
    return ScoreComponent.class.getName();
  }
}
