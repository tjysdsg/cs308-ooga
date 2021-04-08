package ooga.model.systems;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import ooga.model.actions.ActionInfo;
import ooga.model.objects.GameObject;
import ooga.model.util.CollisionAction;
import org.checkerframework.checker.units.qual.C;

public class ActionManager {
  ListMultimap<String, Consumer<CollisionAction>> actions;

  public ActionManager() {
    actions = MultimapBuilder.hashKeys().arrayListValues().build();
  }
  public void registerAction(String actionName, Consumer<CollisionAction> action) {
    actions.put(actionName, action);
  }

  public void handleAction(GameObject self, GameObject other, ActionInfo a) {
    List<ActionInfo> actionInfos = self.getActions();
    for(var ai : actionInfos) {
      if (ai.equals(a)) {
        doAction(ai, self, other);
      }
    }
  }

  private void doAction(ActionInfo action, GameObject self, GameObject other) {
    List<Consumer<CollisionAction>> doActions = actions.get(action.getAction());
    doActions.forEach(e -> e.accept(new CollisionAction(self, other, action.getPayload())));
  }

}