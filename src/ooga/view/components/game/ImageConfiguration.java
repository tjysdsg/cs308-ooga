package ooga.view.components.game;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class ImageConfiguration {
  private Map<String, String> codeToPath;
  private Map<String, Image> cachedImages;

  public ImageConfiguration(String directory) {
    Moshi moshi = new Moshi.Builder().build();
    var type = Types.newParameterizedType(Map.class, String.class, String.class);
    JsonAdapter<Map<String, String>> mapAdapter = moshi.adapter(type);
    File file = new File(directory, "images.json");
    try {
      String json = Files.readString(Paths.get(directory, "images.json"));
      this.codeToPath = mapAdapter.fromJson(json);
    } catch (IOException e) {

    }
  }

  public Image getImage(String mode) {
    return null;
  }
}
