package ooga.model.objects;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import ooga.model.observables.ObservableObject;
import ooga.model.managers.ECManager;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EntityManagerAdapter {

    private ObjectFactory factory;

    public EntityManagerAdapter(ObjectFactory factory) {
        this.factory = factory;
    }

    @FromJson
    ECManager listToManager(List<ObjectInstance> instanceList) {
        ECManager manager = new ECManager(factory);
        for (ObjectInstance instance : instanceList) {
            manager.addEntity(instance);
        }
        return manager;
    }

    @ToJson
    List<ObjectInstance> managerToList(ECManager manager) {
        List<ObjectInstance> instanceList = new ArrayList<>();
        for (GameObject object : manager.getEntities()) {
            instanceList.add(new ObjectInstance(object));
        }
        return instanceList;
    }
}
