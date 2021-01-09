package solver;

import java.nio.file.SimpleFileVisitor;

public class SimpleEquation {
    private double a, b, x;

    public SimpleEquation (double a, double b) {
        this.a = a;
        this.b = b;
    }

    public void setA(double a) {
        this.a = a;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getX() {
        return b / a;
    }

}
