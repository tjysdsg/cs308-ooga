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
    if (!(o instanceof CollisionInfo)) {
      return false;
    }
    CollisionInfo info = (CollisionInfo) o;
    boolean ret = info.other().isA(this.with);
    ret = ret && (position.equals(info.position()) || info.position().equals("any"));
    return ret;
  }

  public String getAction() {
    return this.action;
  }

  public String getPayload() {
    return payload;
  }
}
