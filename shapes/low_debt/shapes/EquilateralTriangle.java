package shapes;

public class EquilateralTriangle extends AbstractRegularShape2D {

    private static final int SIDES = 3;

    public EquilateralTriangle(double sideLength) {
        super(SIDES, sideLength);
    }

    @Override
    public double width () {
        // width (height of triangle) is give by pythagoras
        // width^2 + (side/2)^2 = side^2
        // width^2 = side^2 - (baseSide/2)^2
        // width = sqrt(side^2 - (side/2)^2)
        double side = this.getSideLength();
        return Math.sqrt(Math.pow(side, 2) - Math.pow(side/2, 2));
    }
}
