package shapes;

public interface RegularShape2D extends Shape2D {
    int sides ();
    double sideLength();

    @Override
    default double perimeter() {
        return this.sideLength() * this.sides();
    }

    @Override
    default double area() {
        // Formula for the area of any regular shape
        // area = sideLength^2 * #sides / (4 * tan(pi / #sides))
        // https://www.wikihow.com/Find-the-Area-of-Regular-Polygons
        return Math.pow(this.sideLength(), 2) * this.sides() / (4.0 * Math.tan(Math.PI/this.sides()));
    }
}
