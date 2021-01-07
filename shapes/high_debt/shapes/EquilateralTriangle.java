package shapes;

import java.util.Objects;

public class EquilateralTriangle implements Shape2D {
    private final double sideLength;

    public EquilateralTriangle(double sideLength) {
        this.sideLength = sideLength;
    }

    public double sideLength() {
        return this.sideLength;
    }

    public double height() {
        // Given by pythagoras
        // sideLength^2 = height^2 + (sideLength/2)^2
        // height^2 = sideLength^2 - (sideLength/2)^2
        // height = sqrt(sideLength^2 - (sideLength/2)^2)
        return Math.sqrt(Math.pow(this.sideLength(), 2) - Math.pow(this.sideLength() / 2, 2));
    }

    @Override
    public double perimeter() {
        return this.sideLength() * 3;
    }

    @Override
    public double area() {
        return this.sideLength() * this.height() / 2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        EquilateralTriangle triangle = (EquilateralTriangle) o;
        return Double.compare(triangle.sideLength(), this.sideLength()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.sideLength());
    }

    @Override
    public String toString() {
        return String.format("Equilateral triangle with side length (%,.02f)", this.sideLength());
    }
}
