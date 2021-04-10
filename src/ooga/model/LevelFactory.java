package ooga.model;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.File;
import java.io.FileNotFoundException;
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
import ooga.model.exceptions.InvalidDataFileException;
import ooga.model.objects.GameObject;
import ooga.model.objects.GameObjectAdapter;
import ooga.model.objects.ObjectFactory;

public class LevelFactory {

  private JsonAdapter<GameLevel> levelAdapter;

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

    ObjectFactory objectFactory = new ObjectFactory(presetMap);

    GameObjectAdapter objectAdapter = new GameObjectAdapter(objectFactory);
    Moshi objectMoshi = new Moshi.Builder().add(objectAdapter).build();
    levelAdapter = objectMoshi.adapter(GameLevel.class);
  }

  private void addObjects(File objectsFile, JsonAdapter<List<GameObject>> adapter,
      Map<String, GameObject> presetMap) throws IOException {
    String objectsText = fileToString(objectsFile);

    List<GameObject> objectPresets = adapter.fromJson(objectsText);

    for (GameObject object : objectPresets) {
      presetMap.put(object.getName(), object);
    }
  }

  private String fileToString(File toConvert) throws FileNotFoundException {
    Path filePath = toConvert.toPath();

    try {
      return Files.readString(filePath);
    } catch (IOException e) {
      throw new FileNotFoundException(toConvert.getName());
    }
  }

  Level buildLevel(File levelFile) throws FileNotFoundException, InvalidDataFileException {

    String levelText;
    try {
      levelText = fileToString(levelFile);
    } catch (IOException e) {
      throw new FileNotFoundException(levelFile.getName());
    }

    GameLevel newLevel;
    try {
      newLevel = levelAdapter.fromJson(levelText);
    } catch (IOException e) {
      throw new InvalidDataFileException(levelFile.getName());
    }

    newLevel.init();
    return newLevel;
  }
}
