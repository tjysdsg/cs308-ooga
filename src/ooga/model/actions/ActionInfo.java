package ooga.model.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ActionInfo {
  private static final String ANY_ITEM = "any";
  private static final String ANY_SIDE = "any";
  private Set<String> with = Set.of(ANY_ITEM);
  private Set<String> positions = Set.of(ANY_SIDE);
  private Set<Action> actions;

  public ActionInfo(){};

//  public ActionInfo(String with, String position, String action, String payload) {
//    this.with = with;
//    this.position = position;
//    this.action = action;
//    this.payload = payload;
//  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CollisionInfo)) {
      return false;
    }
    CollisionInfo info = (CollisionInfo) o;

    boolean ret = with.contains(ANY_ITEM);
    for (String item : with) {
      ret = ret || info.other().isA(item);
    }

     return ret && positions.contains(ANY_SIDE) && positions.contains(info.position());
  }

  public Set<Action> getActions() {
    return this.actions;
  }

//  public String getPayload() {
//    return payload;
//  }
}
