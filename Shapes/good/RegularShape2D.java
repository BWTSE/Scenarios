public interface RegularShape2D extends Shape2D {
    int sides ();
    double sideLength();

    @Override
    default double perimeter() {
        return this.sideLength() * this.sides();
    }

    @Override
    default double area() {
        return Math.pow(this.sideLength(), 2) * this.sides() / (4.0 * Math.tan(Math.PI/this.sides()));
    }
}
