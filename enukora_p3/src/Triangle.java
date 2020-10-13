//Name and stat retrieval for triangles
public class Triangle extends Shape {
    private String name;
    private double base, height;

    public String getName() {
        this.name = "triangle";
        return name;
    }

    public double getArea() {
        this.base = base;
        this.height = height;
        double area = (base * height) / 2;
        return area;
    }
} 