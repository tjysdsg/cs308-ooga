package ooga.model.observables;

import java.util.List;
import java.util.function.Consumer;

interface ObservableStatistics {
    public void setOnStatisticUpdate(String statistic, Consumer<String> consumer);
    public List<String> getTrackableStatistics();
}
