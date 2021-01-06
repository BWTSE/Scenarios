public class HexagonalPyramid extends RegularPyramid {
    public HexagonalPyramid(double baseSideLength, double height) {
        super(height, new Hexagon(baseSideLength));
    }

    @Override
    protected double slantHeight() {
        return Math.sqrt( // Pythagoras
                Math.pow(this.height(), 2) + Math.pow(this.base.sideLength() * Math.sqrt(3) / 2, 2)
        );
    }
}
