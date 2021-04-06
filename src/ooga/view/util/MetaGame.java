package ooga.view.util;
import com.google.common.base.MoreObjects;
import java.util.ArrayList;
import java.util.List;

public class MetaGame {

  private String author = "Unknown";
  private String dateCreated = "Unknown";
  private List<String> levels = new ArrayList<>();
  private List<String> tags = new ArrayList<>();

  // Do not remove! Moshi needs this :).
  public MetaGame() {}

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("Meta Data")
        .add("Author", author)
        .add("Date Ceated", dateCreated)
        .add("Levels", levels)
        .add("Tags", tags)
        .toString();
  }
}
