package ooga.model.actions;

import ooga.model.objects.GameObject;

public record CollisionInfo(GameObject self, GameObject other, String position) {

}
