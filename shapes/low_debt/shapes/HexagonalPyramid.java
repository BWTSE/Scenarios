package shapes;

public class HexagonalPyramid extends AbstractRegularPyramid {
    public HexagonalPyramid(double baseSideLength, double height) {
        super(height, new Hexagon(baseSideLength));
    }
}
