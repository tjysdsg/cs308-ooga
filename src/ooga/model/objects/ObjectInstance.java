package ooga.model.objects;

public class ObjectInstance {
    private String type;
    private double x, y;

    public ObjectInstance(String type, double x, double y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getType() {
        return type;
    }
}
