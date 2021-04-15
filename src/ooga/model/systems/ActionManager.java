package ooga.model.systems;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;
import java.util.List;
import java.util.function.Consumer;
import ooga.model.actions.ActionInfo;
import ooga.model.actions.CollisionInfo;
import ooga.model.objects.GameObject;
import ooga.model.actions.CollisionAction;

public class ActionManager {

  ListMultimap<String, Consumer<CollisionAction>> actions;

  public ActionManager() {
    actions = MultimapBuilder.hashKeys().arrayListValues().build();
  }

  public void registerAction(String actionName, Consumer<CollisionAction> action) {
    actions.put(actionName, action);
  }

  public void handleAction(GameObject self, GameObject other, CollisionInfo a) {
    List<ActionInfo> actionInfos = self.getActions();
    for (var ai : actionInfos) {
      // FIXME: add this back when collision direction is calculated correctly
      //  if (ai.equals(a)) {
      doAction(ai, self, other);
      // }
    }
  }

  private void doAction(ActionInfo action, GameObject self, GameObject other) {
    List<Consumer<CollisionAction>> doActions = actions.get(action.getAction());
    doActions.forEach(e -> e.accept(new CollisionAction(self, other, action.getPayload())));
  }

}
