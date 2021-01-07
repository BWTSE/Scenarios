package shapes;

import java.util.Objects;

public class IsoscelesTriangle implements Shape2D {

    private final double baseLength;
    private final double height;

    public IsoscelesTriangle(double baseLength, double height) {
        this.baseLength = baseLength;
        this.height = height;
    }

    public double baseLength() {
        return baseLength;
    }

    public double height() {
        return height;
    }

    public double legLength() {
        // Given by pythagoras:
        // legLength^2 = height^2 + (baseLength/2)^2
        // legLength = sqrt(height^2 + (baseLength/2)^2)
        return Math.sqrt(Math.pow(this.baseLength() / 2.0, 2) + Math.pow(this.height(), 2));
    }

    @Override
    public double perimeter() {
        return this.legLength() * 2 + this.baseLength();
    }

    @Override
    public double area() {
        return this.baseLength() * this.height() / 2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        IsoscelesTriangle triangle = (IsoscelesTriangle) o;
        return Double.compare(triangle.height(), this.height()) == 0 &&
                Double.compare(triangle.baseLength(), this.baseLength()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.baseLength(), this.height());
    }

    @Override
    public String toString() {
        return String.format("Isosceles triangle with height (%,.02f) and base (%,.02f)", this.height(), this.baseLength());
    }
}
