package shapes;

public class EquilateralTriangle extends IsoscelesTriangle implements RegularShape2D {
    public EquilateralTriangle(double sideLength) {
        super(sideLength, EquilateralTriangle.height(sideLength));
    }

    private static double height (double sideLength) {
        // Given by pythagoras:
        // height^2 + (sideLength/2)^2 = sideLength^2
        // height^2 = sideLength^2 - (sideLength/2)^2
        // height = sqrt(sideLength^2 - (sideLength/2)^2)
        return Math.sqrt(Math.pow(sideLength, 2) - Math.pow(sideLength / 2.0, 2));
    }

    @Override
    public int sides() {
        return 3;
    }

    @Override
    public double sideLength() {
        return this.AB;
    }
}
