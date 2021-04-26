package ooga.model.actions;

import com.squareup.moshi.Json;
import java.util.Map;

public class Action {

  private Map<String, String> payload;

  @Json(name = "action")
  private String name;

  public Map<String, String>  getPayload() {
    return payload;
  }

  public String getName() {
    return name;
  }
}
