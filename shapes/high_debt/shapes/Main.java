package shapes;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Shape3D> shapes = new LinkedList<>();

        shapes.add(new HexagonalPyramid(10, 40));
        shapes.add(new HexagonalPyramid(20, 80));

        shapes.add(new TriangularPyramid(10, 40));
        shapes.add(new TriangularPyramid(20, 80));

        /*
        shapes.add(new SquarePyramid(10, 40));
        shapes.add(new SquarePyramid(20, 80));
        */

        for (Shape3D shape : shapes) {
            System.out.println(shape.toString());
            System.out.println("Surface area: " + shape.surfaceArea());
            System.out.println("Volume: " + shape.volume());
            // System.out.println("TotalEdgeLength; " + shape.totalEdgeLength());
            System.out.println();
        }
    }
}
