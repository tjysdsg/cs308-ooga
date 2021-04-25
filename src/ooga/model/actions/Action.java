package ooga.model.actions;

import com.squareup.moshi.Json;
import java.util.List;

public class Action {
  private String payload;

  @Json(name="action")
  private String name;

  public String getPayload() {
    return payload;
  }

  public String getName() {
    return name;
  }
}
