package shapes;

import java.util.Locale;
import java.util.Objects;

public class Hexagon implements Shape2D {

    private static final int SIDES = 6;

    private final double sideLength;

    public Hexagon(double sideLength) {
        this.sideLength = sideLength;
    }

    // The shortest cross section of the shape
    public double width() {
        // width (width of a regular hexagon) is given by:
        // baseWidth = sideLength * sqrt(3)
        return this.getSideLength() * Math.sqrt(3);
    }

    public int getSides() {
        return Hexagon.SIDES;
    }

    public double getSideLength() {
        return sideLength;
    }

    @Override
    public double perimeter() {
        return this.getSideLength() * this.getSides();
    }

    @Override
    public double area() {
        // Area of hexagon
        // area = sideLength^2 * sqrt(3) * 3 / 2
        return Math.pow(this.getSideLength(), 2) * Math.sqrt(3) * 3 / 2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Hexagon hexagon = (Hexagon) o;
        return Double.compare(hexagon.getSideLength(), this.getSideLength()) == 0 &&
                hexagon.getSides() == this.getSides();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getSideLength(), this.getSides());
    }

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH,
                "Hexagon with a side length of (%,.02f) and (%d) sides.",
                this.getSideLength(),
                this.getSides()
        );
    }
}
