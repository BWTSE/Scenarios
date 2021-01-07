package shapes;

import java.util.Objects;

public class Hexagon implements RegularShape2D {
    private final double sideLength;

    public Hexagon(double sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public int sides() {
        return 6;
    }

    @Override
    public double sideLength() {
        return this.sideLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Hexagon hexagon = (Hexagon) o;
        return Double.compare(hexagon.sideLength, sideLength) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sideLength);
    }

    @Override
    public String toString() {
        return String.format("Regular hexagon with a side length of (%,.02f)", this.sideLength());
    }
}
