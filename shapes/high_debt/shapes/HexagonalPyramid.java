package shapes;

import java.util.Objects;

public class HexagonalPyramid implements Shape3D {

    private final double height;

    private final Hexagon base;

    public HexagonalPyramid(double baseSideLength, double height) {
        this.height = height;
        this.base = new Hexagon(baseSideLength);
    }

    private double slantHeight() {
        // Given by pythagoras:
        // slantHeight^2 = height^2 + (baseWidth / 2)^2
        // baseWidth (width of a regular hexagon) is given by:
        // baseWidth = sideLength * sqrt(3)
        // Insertion gives:
        // slantHeight^2 = height^2 + (sideLength * sqrt(3) / 2)^2
        // slantHeight = sqrt(height^2 + (sideLength * sqrt(3) / 2)^2)
        return Math.sqrt(
                Math.pow(this.height(), 2) + Math.pow(this.base().sideLength() * Math.sqrt(3) / 2, 2)
        );
    }

    public double height() {
        return this.height;
    }

    public Hexagon base() {
        return this.base;
    }

    public IsoscelesTriangle sideShape() {
        return new IsoscelesTriangle(
                this.base().sideLength(),
                this.slantHeight()
        );
    }

    @Override
    public double volume() {
        return this.height() * this.base().area() / 3.0;
    }

    @Override
    public double surfaceArea() {
        return this.base().area() + this.sideShape().area() * 6;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.height(), this.base());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof HexagonalPyramid))
            return false;
        HexagonalPyramid that = (HexagonalPyramid) o;
        return Double.compare(that.height(), this.height()) == 0 &&
                this.base().equals(that.base());
    }

    @Override
    public String toString() {
        return String.format("Hexagonial pyramid with height (%,.02f) and base (%s)", this.height(), this.base().toString());
    }
}
