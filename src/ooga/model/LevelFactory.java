package ooga.model;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory;
import ooga.model.components.Component;
import ooga.model.components.PlayerComponent;
import ooga.model.objects.GameObject;
import ooga.model.objects.GameObjectAdapter;
import ooga.model.objects.ObjectFactory;

public class LevelFactory {

  ObjectFactory objectFactory;

  public LevelFactory(File objectsDir) throws IOException {
    if (!objectsDir.isDirectory()) {
      throw new RuntimeException("Objects Path must be a Directory");
    }

    Map<String, GameObject> presetMap = new HashMap<>();

    Moshi moshi = new Moshi.Builder().add(
            PolymorphicJsonAdapterFactory
                    .of(Component.class, "type")
                    .withSubtype(PlayerComponent.class, "PlayerComponent")).build();

    Type type = Types.newParameterizedType(List.class, GameObject.class);
    JsonAdapter<List<GameObject>> adapter = moshi.adapter(type);

    File[] objectFiles = objectsDir.listFiles((dir, name) -> name.contains(".json"));

    for (File objectFile : objectFiles) {
      addObjects(objectFile, adapter, presetMap);
    }

    objectFactory = new ObjectFactory(presetMap);
  }

  private void addObjects(File objectsFile, JsonAdapter<List<GameObject>> adapter, Map<String, GameObject> presetMap) throws IOException {
    String objectsText = fileToString(objectsFile);

    List<GameObject> objectPresets = adapter.fromJson(objectsText);

    for (GameObject object : objectPresets) {
      presetMap.put(object.getName(), object);
    }
  }

  private String fileToString(File toConvert) throws IOException {
    Path filePath = toConvert.toPath();
    String fileText = Files.readString(filePath);
    return fileText;
  }

  Level buildLevel(File levelFile) throws IOException {
    GameObjectAdapter adapter = new GameObjectAdapter(objectFactory);
    Moshi moshi = new Moshi.Builder().add(adapter).build();
    JsonAdapter<GameLevel> levelAdapter = moshi.adapter(GameLevel.class);

    String levelText = fileToString(levelFile);

    GameLevel newLevel = levelAdapter.fromJson(levelText);
    newLevel.init();
    return newLevel;
  }
}
