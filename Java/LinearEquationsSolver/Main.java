import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Absolute path to the input file: ");
        String file = scanner.nextLine();

        File inFile = new File(file);
        File outFile = new File("C:\\Users\\blazb\\Desktop\\out.txt");

        scanner = new Scanner(inFile);
        int n = Integer.parseInt(scanner.nextLine());
        generate(n, scanner, outFile);

        scanner.close();

    }


    public static void generate(int n, Scanner scanner, File outFile) throws FileNotFoundException {
        double[][] matrix = new double[n][n + 1];

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n + 1; ++j) {
                matrix[i][j] = scanner.nextDouble();
            }
        }

        solveSystem(n, matrix, outFile);
    }

    public static void solveSystem(int n, double[][] matrix, File outFile) throws FileNotFoundException {
        row(n, matrix);
        printSolutionsInFile(n, matrix, outFile);
        printSolutionsOnTerminal(n, matrix);
    }

    public static void row(int n, double[][] matrix) {
        for (int i = 0; i < n; ++i) {
            double mainNum = matrix[i][i];
            if (mainNum != 0.0D) {
                for (int j = 0; j < n + 1; ++j) {
                    matrix[i][j] /= mainNum;
                }
            }

            column(n, matrix);
        }

    }

    public static void column(int n, double[][] matrix) {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (j != i) {
                    double mainNum = matrix[j][i];

                    for (int k = 0; k < n + 1; ++k) {
                        matrix[j][k] -= mainNum * matrix[i][k];
                    }
                }
            }
        }

    }

    public static void printSolutionsOnTerminal(int n, double[][] matrix) {
        for (int i = 0; i < n; ++i) {
            System.out.printf(Locale.US, "%.2f\n", matrix[i][n]);
        }

    }

    public static void printSolutionsInFile(int n, double[][] matrix, File outFile) throws FileNotFoundException {
        PrintWriter fileWriter = new PrintWriter(outFile);
        for (int i = 0; i < n; ++i) {
            fileWriter.printf(Locale.US, "%.2f\n", matrix[i][n]);
        }
        fileWriter.close();
    }
}
