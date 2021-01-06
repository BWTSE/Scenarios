public class TriangularPyramid extends RegularPyramid {
    public TriangularPyramid (double baseSideLength, double height) {
        super(height, new EquilateralTriangle(baseSideLength));
    }

    @Override
    protected double slantHeight() {
        return Math.sqrt( // Pythagoras
                Math.pow(this.height(), 2) + Math.pow(this.base.sideLength() / 2.0, 2)
        );
    }
}
