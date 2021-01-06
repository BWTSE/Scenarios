import java.util.Objects;

public abstract class RegularPyramid implements Shape3D {
    protected final double height;
    protected final RegularShape2D base;

    protected RegularPyramid(double height, RegularShape2D base) {
        this.height = height;
        this.base = base;
    }

    protected abstract double slantHeight();

    @Override
    public double volume() {
        return this.height() * this.base.area() / 3.0;
    }

    @Override
    public double surfaceArea() {
        return this.base.area() + sideShape().area() * 4.0;
    }

    public double height() {
        return this.height;
    }

    public Shape2D sideShape() {
        return new IsoscelesTriangle(
                this.base.sideLength(),
                this.slantHeight()
        );
    }

    public RegularShape2D base() {
        return this.base;
    }

    public int sides() {
        return this.base.sides() + 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof RegularPyramid))
            return false;
        RegularPyramid that = (RegularPyramid) o;
        return Double.compare(that.height, height) == 0 &&
                base.equals(that.base);
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, base);
    }

    @Override
    public String toString() {
        return "Regular pyramid with height " + this.height() + " and base: " + this.base();
    }
}
