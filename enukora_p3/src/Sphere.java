//Name and stat retrieval for Spheres
public class Sphere extends Shape {
    private String name;
    private double radius;

    String getName() {
        this.name = "square";
        return name;
    }

    public double getArea() {
        double area  = 4*3.14*radius*radius;
        return area;
    }


    public double getVolume() {
        double volume = (1.333333333333)*(3.14)*(radius*radius*radius);
        return volume;
    }
}