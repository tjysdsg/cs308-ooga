package ooga.model.actions;

public class NPCAction {
  private String action;
  private String payload;

  public NPCAction(String payload) {
    this.payload = payload;
  }

  public String getAction() {
    return action;
  }

  public String getPayload() {
    return payload;
  }
}
