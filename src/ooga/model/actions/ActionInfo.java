package ooga.model.actions;

import java.util.Map;
import java.util.Set;

public class ActionInfo {
  private static final String ANY_ITEM = "any";
  private static final String ANY_SIDE = "any";
  private Set<String> with = Set.of(ANY_ITEM);
  private Set<String> positions = Set.of(ANY_SIDE);
  private Map<String, String> actions;

  public ActionInfo(){};

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


    return ret && (positions.contains(ANY_SIDE) || positions.contains(info.position()));
  }

  public Map<String, String> getActions() {
    return this.actions;
  }

//  public String getPayload() {
//    return payload;
//  }
}
