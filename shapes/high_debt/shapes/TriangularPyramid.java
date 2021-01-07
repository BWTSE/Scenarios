package shapes;

import java.util.Objects;

public class TriangularPyramid implements Shape3D {

    private final double height;

    private final EquilateralTriangle base;

    public TriangularPyramid (double baseSideLength, double height) {
        this.height = height;
        this.base = new EquilateralTriangle(baseSideLength);
    }

    private double slantHeight() {
        // Given by pythagoras:
        // slantHeight^2 = height^2 + (baseWidth/2)^2
        // baseWidth (height of base triangle) is give by pythagoras
        // baseWidth^2 + (baseSide/2)^2 = baseSide^2
        // baseWidth^2 = baseSide^2 - (baseSide/2)^2
        // baseWidth = sqrt(baseSide^2 - (baseSide/2)^2)
        // Insertion of baseWidth gives:
        // slantHeight^2 = height^2 + (sqrt(baseSide^2 - (baseSide/2)^2)/2)^2
        // slantHeight = sqrt(height^2 + (sqrt(baseSide^2 - (baseSide/2)^2)/2)^2)
        return Math.sqrt( // height^2 + (sqrt(baseSide^2 - (baseSide/2)^2)/2)^2
                Math.pow(this.height(), 2)
                + Math.pow( // sqrt(baseSide^2 - (baseSide/2)^2)/2
                    Math.sqrt( // baseSide^2 - (baseSide/2)^2
                        Math.pow(this.base().sideLength(), 2)
                        - Math.pow(this.base().sideLength()/2, 2)
                    )
                    / 2
                , 2)
        );
    }

    public double height() {
        return this.height;
    }

    public EquilateralTriangle base() {
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
        return this.base().area() + this.sideShape().area() * 3.0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.height(), this.base());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof TriangularPyramid))
            return false;
        TriangularPyramid that = (TriangularPyramid) o;
        return Double.compare(that.height(), this.height()) == 0 &&
                this.base().equals(that.base());
    }

    @Override
    public String toString() {
        return String.format("Triangular pyramid with height (%,.02f) and base (%s)", this.height(), this.base().toString());
    }
}
