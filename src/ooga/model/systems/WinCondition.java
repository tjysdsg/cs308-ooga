package ooga.model.systems;

public class WinCondition {

  private String condition;
  private double threshold;
  private boolean win;
  private String operator;

  public WinCondition(){

  }

  public WinCondition(String condition, double threshold, String operator, boolean win){
    this.condition = condition;
    this.threshold = threshold;
    this.operator = operator;
    this.win = win;
  }

  public boolean checkCondition(double input){
    if(operator.equals("equal")) return input == threshold;
    else if(operator.equals("lt")) return input < threshold;
    else if(operator.equals("gt")) return input > threshold;

    return false;
  }

  public String getCondition() {
    return condition;
  }

  public double getThreshold() {
    return threshold;
  }

  public boolean isWin() {
    return win;
  }

}
