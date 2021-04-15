package ooga.view.components.game;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import javafx.scene.image.Image;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ImageConfiguration {
  private Map<String, String> codeToPath;
  private Map<String, Image> cachedImages;
  private String directory;
  private static final Logger logger = LogManager.getLogger(ImageConfiguration.class);

  public ImageConfiguration(String directory) {
    this.directory = directory;
    Moshi moshi = new Moshi.Builder().build();
    var type = Types.newParameterizedType(Map.class, String.class, String.class);
    JsonAdapter<Map<String, String>> mapAdapter = moshi.adapter(type);
    try {
      String json = Files.readString(Paths.get(directory, "images.json"));
      this.codeToPath = mapAdapter.fromJson(json);
      logger.info("Created image config {}", codeToPath);
    } catch (IOException e) {
      logger.warn("Couldn't create image config for path {}", directory);
    }
  }

  public Image getImage(String mode) {
    Image image;
    if (cachedImages.containsKey(mode)) {
      image = cachedImages.get(mode);
    } else {
      image = getImageFile(mode);
    }
    return image;
  }

  private Image getImageFile(String mode) {
    String fileName = codeToPath.get(mode);
    Image image = new Image(Paths.get(directory, fileName).toString());
    cachedImages.put(mode, image);
    return image;
  }
}
