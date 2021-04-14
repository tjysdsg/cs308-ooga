package ooga.model.actions;

import java.util.Objects;

public class ActionInfo {

  private String with;
  private String position;
  private String action;
  private String payload;

  public ActionInfo(String with, String position, String action, String payload) {
    this.with = with;
    this.position = position;
    this.action = action;
    this.payload = payload;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ActionInfo)) {
      return false;
    }
    ActionInfo that = (ActionInfo) o;
    return Objects.equals(with, that.with) && Objects.equals(position, that.position);
  }

  public String getAction() {
    return this.action;
  }

  public String getPayload() {
    return payload;
  }
}
