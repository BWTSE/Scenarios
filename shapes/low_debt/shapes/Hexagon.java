package shapes;

public class Hexagon extends AbstractRegularShape2D {

    private static final int SIDES = 6;

    public Hexagon(double sideLength) {
        super(SIDES, sideLength);
    }

    @Override
    public double width() {
        // width (width of a regular hexagon) is given by:
        // baseWidth = sideLength * sqrt(3)
        return this.getSideLength() * Math.sqrt(3);
    }
}
