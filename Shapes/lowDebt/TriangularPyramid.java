public class TriangularPyramid extends RegularPyramid {
    public TriangularPyramid (double baseSideLength, double height) {
        super(height, new EquilateralTriangle(baseSideLength));
    }

    @Override
    protected double slantHeight() {
        // Given by pythagoras:
        // slantHeight^2 = height^2 + (baseWidth/2)^2
        // baseWidth (height of base triangle) is give by pythagoras
        // baseWidth^2 + (baseSide/2)^2 = baseSide^2
        // baseWidth^2 = baseSide^2 - (baseSide/2)^2
        // baseWidth = sqrt(baseSide^2 - (baseSide/2)^2)
        // Insertion of baseWidth gives:
        // slantHeight^2 = height^2 + (sqrt(baseSide^2 - (baseSide/2)^2)/2)^2
        // slantHeight = sqrt(height^2 + (sqrt(baseSide^2 - (baseSide/2)^2)/2)^2)
        return Math.sqrt( // height^2 + (sqrt(baseSide^2 - (baseSide/2)^2)/2)^2
                Math.pow(this.height(), 2)
                + Math.pow( // sqrt(baseSide^2 - (baseSide/2)^2)/2
                    Math.sqrt( // baseSide^2 - (baseSide/2)^2
                        Math.pow(this.base().sideLength(), 2)
                        - Math.pow(this.base().sideLength()/2, 2)
                    )
                    / 2
                , 2)
        );
    }
}
