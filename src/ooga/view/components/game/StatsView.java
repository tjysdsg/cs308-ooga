package ooga.view.components.game;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javafx.beans.binding.StringBinding;
import javafx.collections.ObservableList;
import javafx.scene.layout.HBox;
import ooga.view.util.LabelPair;
import ooga.view.util.ObservableResource;

/** Display stats within a game */
public class StatsView extends HBox {
  private ObservableResource resources;
  private Map<String, LabelPair> stats;

  public StatsView(ObservableResource resources) {
    this.resources = resources;
    getStyleClass().add("stats-view");
    this.stats = new HashMap<>();
  }

  /**
   * Add statics to be tracked
   *
   * @param newStats - The labels to entitle each stat
   */
  public void addStatistics(ObservableList<String> newStats) {
    for (String stat : newStats) {
      StringBinding binding = resources.getStringBinding(stat);
      LabelPair pair = new LabelPair(binding);
      stats.put(stat, pair);
      getChildren().add(pair);
    }
  }

  /**
   * Update the value of a labeled stat.
   *
   * @param stat - The stat label to update
   * @param value - The value to set it to
   */
  public void updateStat(String stat, String value) {
    if (stats.containsKey(stat)) {
      stats.get(stat).setValue(value);
    }
  }

  /** @return The stats currently being tracked. */
  public Collection<String> getTrackedStats() {
    return stats.keySet();
  }

  /** Remove the value field from all stats. */
  public void clear() {
    stats.values().forEach(o -> o.setValue(""));
  }
}
