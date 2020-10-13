//Name and stat retrieval for squares
public class Square extends Shape {
        private String name;
        private double side;

        public String getName() {
            this.name = "square";
            return name;
        }

        public double getArea() {
            this.side = side;
            double squareArea = side * side;
            return squareArea;
        }
}