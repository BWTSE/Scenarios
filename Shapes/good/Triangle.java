import java.util.Objects;

public class Triangle implements Shape2D {
    protected final double AB, BC, CA;
    public Triangle (double AB, double BC,double CA) {
        this.AB = AB;
        this.BC = BC;
        this.CA = CA;
    }

    @Override
    public double perimeter() {
        return this.AB + this.BC + this.CA;
    }

    @Override
    public double area() {
        double s = this.perimeter() / 2.0;
        return Math.sqrt(s * (s - this.AB) * (s - this.BC) * (s - this.CA));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Triangle triangle = (Triangle) o;
        return Double.compare(triangle.AB, AB) == 0 &&
                Double.compare(triangle.BC, BC) == 0 &&
                Double.compare(triangle.CA, CA) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(AB, BC, CA);
    }
}
