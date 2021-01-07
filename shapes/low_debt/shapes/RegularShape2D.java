package shapes;

import java.util.Objects;

public abstract class RegularShape2D implements Shape2D {

    private int sides;
    private double sideLength;

    protected RegularShape2D(int sides, double sideLength) {
        this.sides = sides;
        this.sideLength = sideLength;
    }

    public int sides () {
        return sides;
    }
    public double sideLength() {
        return sideLength;
    }

    @Override
    public double perimeter() {
        return this.sideLength() * this.sides();
    }

    @Override
    public double area() {
        // Formula for the area of any regular shape
        // area = sideLength^2 * #sides / (4 * tan(pi / #sides))
        // https://www.wikihow.com/Find-the-Area-of-Regular-Polygons
        return Math.pow(this.sideLength(), 2) * this.sides() / (4.0 * Math.tan(Math.PI/this.sides()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        RegularShape2D regularShape2D = (RegularShape2D) o;
        return Double.compare(regularShape2D.sideLength(), sideLength()) == 0 &&
                regularShape2D.sides() == this.sides();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.sideLength(), this.sides());
    }

    @Override
    public String toString() {
        return String.format("Regular 2d shape with a side length of (%,.02f) and (%d) sides.", this.sideLength(), this.sides());
    }
}
