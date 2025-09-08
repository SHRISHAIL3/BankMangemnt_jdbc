package util;

import java.util.Scanner;

public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("❌ Invalid input. Please enter a number.");
            scanner.next(); // clear invalid input
            System.out.print(prompt);
        }
        return scanner.nextInt();
    }

    public static double getDouble(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("❌ Invalid input. Please enter a number.");
            scanner.next(); // clear invalid input
            System.out.print(prompt);
        }
        return scanner.nextDouble();
    }

    public static String getString(String prompt) {
        System.out.print(prompt);
        return scanner.next();
    }
}
