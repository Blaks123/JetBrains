package encryptdecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//      Scanner sc = new Scanner(System.in);
//
        String choice = "enc";
        String alg = "shift";
        String message = "";
        File inFile = null;
        File outFile = null;
        int number = 0;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-mode")) {
                choice = args[i + 1];
                ++i;
            } else if (args[i].equals("-key")) {
                number = Integer.parseInt(args[i + 1]);
                ++i;
            } else if (args[i].equals("-data")) {
                message = args[i + 1];
                ++i;
            } else if (args[i].equals("-in")) {
                inFile = new File(args[i + 1]);
                ++i;
            } else if (args[i].equals("-out")) {
                outFile = new File(args[i + 1]);
                ++i;
            } else if (args[i].equals("-alg")) {
                alg = args[i + 1];
                ++i;
            }
        }

        if (message.equals("")) {
            try(Scanner sc = new Scanner(inFile)) {
                while(sc.hasNextLine()) {
                    message = sc.nextLine();
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error! FileNotFound!");
            }
        }

        char[] chars = null;
        switch (choice) {
            case "enc" :
                switch (alg) {
                    case "shift":
                        chars = encryptShift(message, number);
                        break;
                    case "unicode":
                        chars = encryptUnicode(message, number);
                        break;
                }
                break;
            case "dec" :
                switch (alg) {
                    case "shift":
                        chars = decryptShift(message, number);
                        break;
                    case "unicode":
                        chars = decryptUnicode(message, number);
                        break;
                }
                break;
        }
        if (outFile == null) {
            System.out.println(chars);
        } else {
            try (PrintWriter writer = new PrintWriter(outFile)) {
                writer.println(chars);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    public static char[] decryptUnicode(String message, int num) {
        char[] chars = message.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] - num);
        }
        return chars;
    }

    public static char[] decryptShift(String message, int num) {
        char[] chars = message.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 97 && chars[i] <= 122) {
                chars[i] = (char) (chars[i] - num);
                if (chars[i] < 97) {
                    int over = 97 - chars[i];
                    chars[i] = (char) (122 - over + 1);
                }
            }
        }
        return chars;
    }

    public static char[] encryptUnicode(String message, int num) {
        char[] chars = message.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] + num);
        }
        return chars;
    }


    public static char[] encryptShift(String message, int num) {
        char[] chars = message.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 97 && chars[i] <= 122) {
                chars[i] = (char) (chars[i] + num);
                if (chars[i] > 122) {
                    int over = chars[i] - 123;
                    chars[i] = (char) (97 + over);
                }
            }

        }
        return chars;
    }

}
