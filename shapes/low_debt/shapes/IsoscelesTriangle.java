package shapes;

public class IsoscelesTriangle extends Triangle {
    public IsoscelesTriangle(double baseLength, double height) {
        super(baseLength, legLength(baseLength, height), legLength(baseLength, height));
    }

    private static double legLength(double baseLength, double height) {
        // Given by pythagoras:
        // legLength^2 = height^2 + (baseLength/2)^2
        // legLength = sqrt(height^2 + (baseLength/2)^2)
        return Math.sqrt(Math.pow(baseLength / 2.0, 2) + Math.pow(height, 2));
    }
}
