package ooga.model;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
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

  String presetTest = """
       [
          {
            "name": "goomba",
            "height": 50,
            "width": 50,
            "imageID": "marioimage",
            "components" : [
              {
                "type" : "PlayerComponent"
              }
            ]
          },
          {
            "name": "advance_level2",
            "height": 50,
            "width": 50,
            "imageID": "marioimage"
          }
       ]
      """;

  String levelTest = """
      {
        "name" : "Sunny Level",
        "id" :  "sunny_day",
        "background" : "sunny_day",
        "height" : 50,
        "width" : 500,
        "gameObjects" : [
          {
            "type" : "goomba",
            "x" : 234,
            "y" : 50
          },
          {
            "type" : "goomba",
            "x" : 200,
            "y" : 50
          }
        ]
      }
      """;

  //String RESOURCES = "/Users/Student/Desktop/OOGA/data/";

  //  Map<String, GameObject> presetMap = new HashMap<>();
  ObjectFactory objectFactory;

  public LevelFactory(File objectsDir) throws IOException {
    // Load the presets for the levels
    Moshi moshi = new Moshi.Builder().add(
        PolymorphicJsonAdapterFactory
            .of(Component.class, "type")
            .withSubtype(PlayerComponent.class, "PlayerComponent")).build();

    Type type = Types.newParameterizedType(List.class, GameObject.class);
    JsonAdapter<List<GameObject>> adapter = moshi.adapter(type);

    //File file = new File(RESOURCES + ObjectsFile);
    List<GameObject> objectPresets = adapter.fromJson(presetTest);

    Map<String, GameObject> presetMap = new HashMap<>();
    for (GameObject object : objectPresets) {
      presetMap.put(object.getType(), object);
    }

    objectFactory = new ObjectFactory(presetMap);
  }

  Level buildLevel(String levelFile) throws IOException {
    // Use the object Factory to create the game objects
//    JSObject

    GameObjectAdapter adapter = new GameObjectAdapter(objectFactory);
    Moshi moshi = new Moshi.Builder().add(adapter).build();
    JsonAdapter<GameLevel> levelAdapter = moshi.adapter(GameLevel.class);

    GameLevel newLevel = levelAdapter.fromJson(levelTest);
    newLevel.init(); // MUST CALL level.init()

    return newLevel;
  }

  public static void main(String[] args) throws IOException {
    LevelFactory fact = new LevelFactory(new File("thing"));
    fact.buildLevel(null);
  }
}
