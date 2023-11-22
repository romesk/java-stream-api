package org.lab.utils;

import java.util.Scanner;

public class InputReader {
    static Scanner scanner = new Scanner(System.in);


    private InputReader() {
        throw new IllegalStateException("Utility class");
    }

    public static int readInteger() {
        try {
            var result = scanner.nextInt();
            scanner.nextLine();
            return result;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
            return -1;
        }
    }

    public static String readString() {
        try {
            return scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
            return "";
        }
    }

    public static void close() {
        scanner.close();
    }
}
