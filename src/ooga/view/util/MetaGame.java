package ooga.view.util;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** Meta information about a game. */
public class MetaGame {

  private static final Logger logger = LogManager.getLogger(MetaGame.class);
  private static final JsonAdapter<MetaGame> adapter =
      new Moshi.Builder().build().adapter(MetaGame.class);
  private String author = "Unknown";
  private String dateCreated = "Unknown";
  private List<String> levels = new ArrayList<>();
  private List<String> tags = new ArrayList<>();

  // Do not remove! Moshi needs this :).
  public MetaGame() {}

  /**
   * Create meta information of a game off of a file.
   *
   * @param directory - The file containing the meta data in JSON format.
   * @return Meta information on the game.
   */
  public static MetaGame createMetaDataFromDirectory(File directory) {
    Preconditions.checkArgument(directory != null);
    try {
      String content = Files.readString(Path.of(directory.getPath() + "/meta.json"));
      MetaGame metadata = adapter.fromJson(content);
      logger.debug("MetaData created: {}", metadata);
      return metadata;
    } catch (IOException e) {
      logger.debug("Unable to create MetaData from directory {}", directory.getPath());
      return null;
    }
  }

  /** @return - The date the game was created. */
  public String getDateCreated() {
    return dateCreated;
  }

  /** @return - The levels of the game */
  public List<String> getLevels() {
    return levels;
  }

  /** @return - The tags of the game */
  public List<String> getTags() {
    return tags;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper("Meta Data")
        .add("Author", author)
        .add("Date Ceated", dateCreated)
        .add("Levels", levels)
        .add("Tags", tags)
        .toString();
  }

  /** @return - The author of the game */
  public String getAuthor() {
    return this.author;
  }
}
