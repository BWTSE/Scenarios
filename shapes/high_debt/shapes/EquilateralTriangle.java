package shapes;

import java.util.Locale;
import java.util.Objects;

public class EquilateralTriangle implements Shape2D {

    private static final int SIDES = 3;

    private final double sideLength;

    public EquilateralTriangle(double sideLength) {
        this.sideLength = sideLength;
    }

    // The shortest cross section of the shape
    public double width () {
        // width (height of triangle) is give by pythagoras
        // width^2 + (side/2)^2 = side^2
        // width^2 = side^2 - (baseSide/2)^2
        // width = sqrt(side^2 - (side/2)^2)
        double side = this.getSideLength();
        return Math.sqrt(Math.pow(side, 2) - Math.pow(side/2, 2));
    }

    public double height() {
        // Given by pythagoras
        // sideLength^2 = height^2 + (sideLength/2)^2
        // height^2 = sideLength^2 - (sideLength/2)^2
        // height = sqrt(sideLength^2 - (sideLength/2)^2)
        return Math.sqrt(Math.pow(this.getSideLength(), 2) - Math.pow(this.getSideLength() / 2, 2));
    }

    public int getSides() {
        return EquilateralTriangle.SIDES;
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
        return this.getSideLength() * this.height() / 2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        EquilateralTriangle equilateralTriangle = (EquilateralTriangle) o;
        return Double.compare(equilateralTriangle.getSideLength(), this.getSideLength()) == 0 &&
                equilateralTriangle.getSides() == this.getSides();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getSideLength(), this.getSides());
    }

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH,
                "Equilateral triangle with a side length of (%,.02f) and (%d) sides.",
                this.getSideLength(),
                this.getSides()
        );
    }
}
