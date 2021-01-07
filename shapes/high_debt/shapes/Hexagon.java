package shapes;

import java.util.Objects;

public class Hexagon {
    private final double sideLength;

    public Hexagon(double sideLength) {
        this.sideLength = sideLength;
    }

    public double sideLength() {
        return this.sideLength;
    }

    public double area() {
        // Area of hexagon
        // area = sideLength^2 * sqrt(3) * 3 / 2
        return 3 * Math.sqrt(3) * Math.pow(this.sideLength(), 2) / 2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Hexagon hexagon = (Hexagon) o;
        return Double.compare(hexagon.sideLength(), this.sideLength()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.sideLength());
    }

    @Override
    public String toString() {
        return String.format("Regular hexagon with a side length of (%,.02f)", this.sideLength());
    }
}
