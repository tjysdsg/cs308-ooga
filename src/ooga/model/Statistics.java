package ooga.model;

import java.util.List;
import java.util.function.Consumer;
import ooga.model.observables.ObservableStatistics;

public class Statistics implements ObservableStatistics {

  @Override
  public void setOnStatisticUpdate(String statistic, Consumer<String> consumer) {

  }

  @Override
  public List<String> getTrackableStatistics() {
    return null;
  }
}
