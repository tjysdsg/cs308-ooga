package ooga.model;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import ooga.model.actions.PlayerAction;
import ooga.model.components.Component;
import ooga.model.exceptions.InvalidDataFileException;
import ooga.model.exceptions.NotADirectoryException;
import ooga.model.objects.EntityManagerAdapter;
import ooga.model.objects.GameObject;
import ooga.model.objects.ObjectFactory;
import ooga.model.observables.ObservableObject;
import ooga.model.util.FileReader;
import org.reflections.Reflections;

/**
 * The level factory creates a level out of a json object. It must be given a directory and it will read every object in the directory defined in a data file
 *
 * @author Oliver Rodas
 */
public class LevelFactory {

  private JsonAdapter<GameLevel> levelAdapter;
  private ObjectFactory objectFactory;

  /**
   * Creates a new level factory with the defined items
   * @param objectsDir the directory containing the objects
   * @throws FileNotFoundException IF the file is not found
   */
  public LevelFactory(File objectsDir) throws FileNotFoundException {
    if (!objectsDir.isDirectory()) {
      throw new NotADirectoryException(objectsDir.getName());
    }

    Map<String, GameObject> presetMap = new HashMap<>();

    PolymorphicJsonAdapterFactory<Component> componentAdapter = createComponentAdapter();
    PolymorphicJsonAdapterFactory playerAdapter = createAdapter(PlayerAction.class, "action");
    Moshi moshi = new Moshi.Builder().add(componentAdapter).add(playerAdapter).build();

    Type type = Types.newParameterizedType(List.class, GameObject.class);
    JsonAdapter<List<GameObject>> adapter = moshi.adapter(type);

    File[] objectFiles = objectsDir.listFiles((dir, name) -> name.contains(".json"));

    for (File objectFile : objectFiles) {
      addObjects(objectFile, adapter, presetMap);
    }

    objectFactory = new ObjectFactory(presetMap);
  }

  /**
   * Create a component adapter
   * Note: this method did not exist when I refactored the code, but my team deleted those commits so it is back here
   * @return A new component adapter
   */
  public static PolymorphicJsonAdapterFactory<Component> createComponentAdapter() {
    PolymorphicJsonAdapterFactory<Component> adapter = PolymorphicJsonAdapterFactory
        .of(Component.class, "type");
    Reflections reflections = new Reflections(Component.class.getPackageName());
    for (Class subclass : reflections.getSubTypesOf(Component.class)) {
      adapter = adapter.withSubtype(subclass, subclass.getSimpleName());
    }
    return adapter;
  }

  public static PolymorphicJsonAdapterFactory<Component> createAdapter(Class superClass, String label) {
    PolymorphicJsonAdapterFactory adapter = PolymorphicJsonAdapterFactory.of(superClass, label);
    Reflections reflections = new Reflections(superClass.getPackageName());
    for (Object subclass : reflections.getSubTypesOf(superClass)) {
      Class subClazz = (Class) subclass;
      if (!Modifier.isAbstract(subClazz.getModifiers()))
        adapter = adapter.withSubtype(subClazz, subClazz.getSimpleName());
    }
    return adapter;
  }

  private void addObjects(File objectsFile, JsonAdapter<List<GameObject>> adapter,
      Map<String, GameObject> presetMap) throws FileNotFoundException, InvalidDataFileException {
    String objectsText = FileReader.readFile(objectsFile);

    List<GameObject> objectPresets;
    try {
      objectPresets = adapter.fromJson(objectsText);
    } catch (IOException e) {
      throw new InvalidDataFileException(objectsFile.getName());
    }

    for (GameObject object : objectPresets) {
      presetMap.put(object.getName(), object);
    }
  }

  /**
   * Create a level from a level file
   * @param levelFile the file to use for the level
   * @return a new level
   * @throws FileNotFoundException if the file is not found
   * @throws InvalidDataFileException If the file is invalid
   */
  public Level buildLevel(File levelFile) throws FileNotFoundException, InvalidDataFileException {
    EntityManagerAdapter entityManagerAdapter = new EntityManagerAdapter(objectFactory);
    Moshi objectMoshi = new Moshi.Builder().add(entityManagerAdapter).build();
    JsonAdapter<GameLevel> levelAdapter = objectMoshi.adapter(GameLevel.class);

    String levelText;
    try {
      levelText = FileReader.readFile(levelFile);
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
