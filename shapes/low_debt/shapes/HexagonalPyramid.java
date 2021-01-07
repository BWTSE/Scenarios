package shapes;

public class HexagonalPyramid extends RegularPyramid {
    public HexagonalPyramid(double baseSideLength, double height) {
        super(height, new Hexagon(baseSideLength));
    }

    @Override
    protected double slantHeight() {
        // Given by pythagoras:
        // slantHeight^2 = height^2 + (baseWidth / 2)^2
        // baseWidth (width of a regular hexagon) is given by:
        // baseWidth = sideLength * sqrt(3)
        // Insertion gives:
        // slantHeight^2 = height^2 + (sideLength * sqrt(3) / 2)^2
        // slantHeight = sqrt(height^2 + (sideLength * sqrt(3) / 2)^2)
        return Math.sqrt(
                Math.pow(this.height(), 2) + Math.pow(this.base.sideLength() * Math.sqrt(3) / 2, 2)
        );
    }
}
