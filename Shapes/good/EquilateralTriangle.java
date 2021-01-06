public class EquilateralTriangle extends IsoscelesTriangle implements RegularShape2D {
    public EquilateralTriangle(double sideLength) {
        super(sideLength, EquilateralTriangle.height(sideLength));
    }

    private static double height (double sideLength) {
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
