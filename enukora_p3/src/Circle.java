//Name and stat retrieval for circles
public class Circle extends Shape {
    private String name;
    private double radius;
    double pi = 3.14;

    public String getName() {
        this.name = "circle";
        return name;
    }

    public double getArea() {
        this.radius = radius;
        double area = pi * radius * radius;
        return area;

    }
}