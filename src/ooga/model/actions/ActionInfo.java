package ooga.model.actions;

import com.google.common.base.MoreObjects;

public class ActionInfo {
  private String with = "Enemy";
  private String position = "Left";
  private String action = "jump_self";
  private String payload = "50";

  @Override
  public boolean equals(Object obj) {
    return false;
  }

  public String getAction() {
    return this.action;
  }

  public String getPayload() {
    return payload;
  }
}
