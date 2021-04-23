package ooga.view.util;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import java.util.HashMap;
import java.util.Map;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.ObservableMap;
import javafx.scene.input.KeyCode;

public class ConfigurationAdapter {

  @FromJson
  ObservableMap<KeyCode, String> objectFromJson(Map<String, String> keyMaps) {
    SimpleMapProperty<KeyCode, String> convertedMap = new SimpleMapProperty<>();
    keyMaps.forEach((key, val) -> convertedMap.put(KeyCode.getKeyCode(key), val));
    return convertedMap;
  }

  @ToJson
  Map<String, String> objectToJson(ObservableMap<KeyCode, String> keyMaps) {
    Map<String, String> newMap = new HashMap<>();
    keyMaps.forEach((key, value) -> newMap.put(key.getName(), value));
    return newMap;
  }
}
