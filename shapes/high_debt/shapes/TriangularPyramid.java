package shapes;

import java.util.Locale;
import java.util.Objects;

public class TriangularPyramid implements Shape3D {
    private final double height;
    private final EquilateralTriangle base;

    public TriangularPyramid(double height, double base) {
        this.height = height;
        this.base = new EquilateralTriangle(base);
    }

    public double getHeight() {
        return this.height;
    }

    public EquilateralTriangle getBase() {
        return this.base;
    }

    @Override
    public double volume() {
        return this.getHeight() * this.getBase().area() / 3.0;
    }

    @Override
    public double surfaceArea() {
        return this.getBase().area() + this.sideShape().area() * this.getBase().getSides();
    }

    public IsoscelesTriangle sideShape() {
        return new IsoscelesTriangle(
                this.getBase().getSideLength(),
                this.slantHeight()
        );
    }

    private double slantHeight() {
        // Given by pythagoras:
        // slantHeight^2 = height^2 + (baseWidth/2)^2
        // slantHeight = sqrt(height^2 + (baseWidth/2)^2)
        double baseWidth = this.getBase().width();
        return Math.sqrt(Math.pow(this.getHeight(), 2) + Math.pow(baseWidth/2, 2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        TriangularPyramid triangularPyramid = (TriangularPyramid) o;
        return Double.compare(triangularPyramid.getHeight(), this.getHeight()) == 0 &&
                this.getBase().equals(triangularPyramid.getBase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getHeight(), this.getBase());
    }

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH,
                "Triangular pyramid with height (%,.02f) and base (%s)",
                this.getHeight(),
                this.getBase().toString()
        );
    }
}
