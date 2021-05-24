//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//



import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Absolute path to the input file: ");
        String file = scanner.nextLine();
        File inFile = new File(file);
        File outFile = new File("C:\\Users\\blazb\\Desktop\\out.txt");
        boolean var4 = true;

        try {
            scanner = new Scanner(inFile);

            try {
                int n = Integer.parseInt(scanner.nextLine());
                generate(n, scanner, outFile);
            } catch (Throwable var9) {
                try {
                    scanner.close();
                } catch (Throwable var8) {
                    var9.addSuppressed(var8);
                }

                throw var9;
            }

            scanner.close();
        } catch (FileNotFoundException var10) {
            System.out.println("Error! FileNotFound!");
        }

    }

    public static void generate(int n, Scanner scanner, File outFile) {
        double[][] matrix = new double[n][n + 1];

        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n + 1; ++j) {
                matrix[i][j] = scanner.nextDouble();
            }
        }

        solveSystem(n, matrix, outFile);
    }

    public static void solveSystem(int n, double[][] matrix, File outFile) {
        row(n, matrix);
        printSolutionsInFile(n, matrix, outFile);
        printSolutionsOnTerminal(n, matrix);
    }

    public static void row(int n, double[][] matrix) {
        for(int i = 0; i < n; ++i) {
            double mainNum = matrix[i][i];
            if (mainNum != 0.0D) {
                for(int j = 0; j < n + 1; ++j) {
                    matrix[i][j] /= mainNum;
                }
            }

            column(n, matrix);
        }

    }

    public static void column(int n, double[][] matrix) {
        for(int i = 0; i < n; ++i) {
            for(int j = 0; j < n; ++j) {
                if (j != i) {
                    double mainNum = matrix[j][i];

                    for(int k = 0; k < n + 1; ++k) {
                        matrix[j][k] -= mainNum * matrix[i][k];
                    }
                }
            }
        }

    }

    public static void printSolutionsOnTerminal(int n, double[][] matrix) {
        for(int i = 0; i < n; ++i) {
            System.out.printf(Locale.US, "%.2f\n", matrix[i][n]);
        }

    }

    public static void printSolutionsInFile(int n, double[][] matrix, File outFile) {
        try {
            PrintWriter fileWriter = new PrintWriter(outFile);

            try {
                for(int i = 0; i < n; ++i) {
                    fileWriter.printf(Locale.US, "%.2f\n", matrix[i][n]);
                }
            } catch (Throwable var7) {
                try {
                    fileWriter.close();
                } catch (Throwable var6) {
                    var7.addSuppressed(var6);
                }

                throw var7;
            }

            fileWriter.close();
        } catch (FileNotFoundException var8) {
            var8.printStackTrace();
        }

    }
}
