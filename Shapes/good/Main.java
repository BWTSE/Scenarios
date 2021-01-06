public class Main {
    public static void main(String[] args) {
        HexagonalPyramid hexagonalPyramid = new HexagonalPyramid(20, 80);
        System.out.println(hexagonalPyramid);
        System.out.println("Surface area: " + hexagonalPyramid.surfaceArea());
        System.out.println("Volume: " + hexagonalPyramid.volume());

        TriangularPyramid triangularPyramid = new TriangularPyramid(20, 80);
        System.out.println(triangularPyramid);
        System.out.println("Surface area: " + triangularPyramid.surfaceArea());
        System.out.println("Volume: " + triangularPyramid.volume());
    }
}
