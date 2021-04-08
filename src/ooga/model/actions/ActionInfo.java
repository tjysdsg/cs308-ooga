package ooga.model.actions;

import java.util.Objects;

public class ActionInfo {

  private String with = "Enemy";
  private String position = "Left";
  private String action = "jump_self";
  private String payload = "50";

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
    if (!(o instanceof ActionInfo that)) {
      return false;
    }
    return Objects.equals(with, that.with) && Objects.equals(position, that.position);
  }

  public String getAction() {
    return this.action;
  }

  public String getPayload() {
    return payload;
  }
}
