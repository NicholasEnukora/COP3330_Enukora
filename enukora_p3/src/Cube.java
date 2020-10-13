//Name and stat retrieval for cubes
public class Cube extends Shape {
    private String name;
    private double side;

    public String getName() {
        this.name = "cube";
        return name;
    }

    double getArea() {
        this.side = side;
        double area = (side * side) * 6;
        return area;
    }

    public double getVolume() {
        double volume = side * side * side;
        return volume;
    }
}