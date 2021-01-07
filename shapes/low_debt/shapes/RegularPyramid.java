package shapes;

import java.util.Objects;

public abstract class RegularPyramid implements Shape3D {
    protected final double height;
    protected final RegularShape2D base;

    protected RegularPyramid(double height, RegularShape2D base) {
        this.height = height;
        this.base = base;
    }

    public double height() {
        return this.height;
    }

    public RegularShape2D base() {
        return this.base;
    }

    protected abstract double slantHeight();

    @Override
    public double volume() {
        return this.height() * this.base().area() / 3.0;
    }

    @Override
    public double surfaceArea() {
        return this.base().area() + this.sideShape().area() * this.base().sides();
    }

    public Shape2D sideShape() {
        return new IsoscelesTriangle(
                this.base().sideLength(),
                this.slantHeight()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof RegularPyramid))
            return false;
        RegularPyramid that = (RegularPyramid) o;
        return Double.compare(that.height(), this.height()) == 0 &&
                this.base().equals(that.base());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.height(), this.base());
    }

    @Override
    public String toString() {
        return String.format("Regular pyramid with height (%,.02f) and base (%s)", this.height(), this.base().toString());
    }
}
