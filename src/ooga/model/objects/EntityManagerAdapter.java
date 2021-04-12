package ooga.model.objects;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import ooga.model.systems.EntityManager;

import java.util.ArrayList;
import java.util.List;

public class EntityManagerAdapter {

    private ObjectFactory factory;

    public EntityManagerAdapter(ObjectFactory factory) {
        this.factory = factory;
    }

    @FromJson
    EntityManager listToManager(List<ObjectInstance> instanceList) {
        EntityManager manager = new EntityManager(factory);
        for (ObjectInstance instance : instanceList) {
            manager.addEntity(instance);
        }
        return manager;
    }

    @ToJson
    List<ObjectInstance> managerToList(EntityManager manager) {
        List<ObjectInstance> instanceList = new ArrayList<>();
        for (GameObject object : manager.getEntities()) {
            instanceList.add(new ObjectInstance(object));
        }
        return instanceList;
    }
}
