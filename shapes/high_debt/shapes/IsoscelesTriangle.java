package shapes;

import java.util.Locale;
import java.util.Objects;

public class IsoscelesTriangle implements Shape2D {

    private final double baseLength;
    private final double height;

    public IsoscelesTriangle(double baseLength, double height) {
        this.baseLength = baseLength;
        this.height = height;
    }

    public double getBaseLength() {
        return baseLength;
    }

    public double getHeight() {
        return height;
    }

    public double legLength() {
        // Given by pythagoras:
        // legLength^2 = height^2 + (baseLength/2)^2
        // legLength = sqrt(height^2 + (baseLength/2)^2)
        return Math.sqrt(Math.pow(this.getHeight(), 2) + Math.pow(this.getBaseLength()/2, 2));
    }

    @Override
    public double perimeter() {
        return this.legLength() * 2 + this.getBaseLength();
    }

    @Override
    public double area() {
        return this.getBaseLength() * this.getHeight() / 2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        IsoscelesTriangle triangle = (IsoscelesTriangle) o;
        return Double.compare(triangle.getHeight(), this.getHeight()) == 0 &&
                Double.compare(triangle.getBaseLength(), this.getBaseLength()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getBaseLength(), this.getHeight());
    }

    @Override
    public String toString() {
        return String.format(
                Locale.ENGLISH,
                "Isosceles triangle with height (%,.02f) and base (%,.02f)",
                this.getHeight(),
                this.getBaseLength()
        );
    }
}
