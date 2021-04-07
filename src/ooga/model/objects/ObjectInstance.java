package ooga.model.objects;

public class ObjectInstance {
    private String name;
    private double x, y;

    public ObjectInstance(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getName() {
        return name;
    }
}
