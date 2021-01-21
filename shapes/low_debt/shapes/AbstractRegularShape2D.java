package shapes;

import java.util.Locale;
import java.util.Objects;

public abstract class AbstractRegularShape2D implements Shape2D {

    private final int sides;
    private final double sideLength;

    protected AbstractRegularShape2D(int sides, double sideLength) {
        this.sides = sides;
        this.sideLength = sideLength;
    }

    // The shortest cross section of the shape
    public abstract double width();

    public int getSides() {
        return sides;
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
        // Formula for the area of any regular shape
        // area = sideLength^2 * #sides / (4 * tan(pi / #sides))
        // https://www.wikihow.com/Find-the-Area-of-Regular-Polygons
        return Math.pow(this.getSideLength(), 2)
                * this.getSides()
                / (4.0 * Math.tan(Math.PI/this.getSides()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        AbstractRegularShape2D regularShape2D = (AbstractRegularShape2D) o;
        return Double.compare(regularShape2D.getSideLength(), this.getSideLength()) == 0 &&
                regularShape2D.getSides() == this.getSides();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getSideLength(), this.getSides());
    }

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH,
                "Regular 2d shape with a side length of (%,.02f) and (%d) sides.",
                this.getSideLength(),
                this.getSides()
        );
    }
}
