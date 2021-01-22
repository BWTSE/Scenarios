package shapes;

public class TriangularPyramid extends AbstractRegularPyramid {
    public TriangularPyramid (double baseSideLength, double height) {
        super(height, new EquilateralTriangle(baseSideLength));
    }
}
