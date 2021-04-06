package ooga.model;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory;
import ooga.model.components.Component;
import ooga.model.objects.GameObjectFactory;
import ooga.model.objects.ObjectFactory;

public class LevelFactory {

  Map<String, ObjectFactory> typeMap = new HashMap<>();

  public LevelFactory(String ObjectsFile) throws IOException {
    // Load the presets for the levels
    Moshi moshi = new Moshi.Builder().add(PolymorphicJsonAdapterFactory.of(Component.class, "type")).build();

    Type type = Types.newParameterizedType(List.class, GameObjectFactory.class);
    JsonAdapter<List<GameObjectFactory>> adapter = moshi.adapter(type);

    List<GameObjectFactory> factories = adapter.fromJson(ObjectsFile);

    for (ObjectFactory factory : factories) {
      typeMap.put(factory.getType(), factory);
    }
  }

  Level buildLevel(String levelFile) {
    // Use the object Factory to create the game objects
//    JSObject
    return null;
    // TODO: FIXME: OLIVER: REMEMBER TO CALL level.init()
    //level.init();
    return null;
  }

  public static void main(String[] args) throws IOException {
    LevelFactory fact = new LevelFactory("gameobjects.json");
  }
}
