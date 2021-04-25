package ooga.model.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import ooga.model.objects.GameObject;
import ooga.model.systems.WinCondition;

public class WinComponent extends Component {

  private List<WinCondition> winConds = new ArrayList();

  public WinComponent(){

  }

  public WinComponent(int id, GameObject owner){
    super(id,owner);
  }



  @Override
  public String typeUnerasure() {
    return null;
  }
}
