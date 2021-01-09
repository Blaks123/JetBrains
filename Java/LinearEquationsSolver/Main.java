package solver;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        scanAndSolveSystem();
    }



    public static void scanAndSolveSystem() {
        Scanner sc = new Scanner(System.in);

        double a = sc.nextDouble();
        double b = sc.nextDouble();
        double c = sc.nextDouble();

        double d = sc.nextDouble();
        double e = sc.nextDouble();
        double f = sc.nextDouble();

        solveSystem(a, b, c, d, e , f);
    }

    public static void solveSystem(double a, double b, double c,
           double d, double e, double f) {
        double y = (f - c * (d / a)) / (e - b * (d / a));
        b = b * y - c;
        simpleSolve(a, b);
        System.out.println(y);
    }

    public static void simpleScan() {
        Scanner sc = new Scanner(System.in);
        double a = sc.nextDouble();
        double b = sc.nextDouble();
        simpleSolve(a, b);
    }

    public static void simpleSolve(double a, double b) {
        double x;
        x = b / a;
        System.out.print(-x + " ");
    }

}
