package ooga.model.objects;

public interface ObjectFactory {
  GameObject build(int x, int y);

  String getType();
}
