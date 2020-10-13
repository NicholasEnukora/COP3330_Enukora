//Name and stat retrieval for Pyramids
public class Pyramid extends Shape {
    private String name;
    private double length;
    private double width;
    private double height;

    public String getName() {
        this.name = "pyramid";
        return name;
    }

    public double getArea() {
        this.length = length;
        this.width = width;
        this.height = height;

        double pyramidArea = (length * width) + (length * Math.sqrt((width / 2) * (width / 2)) +
                (height * height)) + (width * Math.sqrt((length / 2) * (length / 2)) + (height * height));

        return pyramidArea;

    }

    public double getVolume() {
        this.length = length;
        this.width = width;
        this.height = height;

        double volume = length * width * height / 3;
        return volume;
    }

}