import java.util.Scanner;

public class myScanner {

    private static Scanner scanner;

    public myScanner() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads an integer from the user.
     *
     * @return The integer entered by the user.
     */
    public static int getIntInput() {
        System.out.print("Entrer un integer: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. veuillez entrer un integer.");
            scanner.next(); // Consume the invalid input
        }
        int result = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        return result;
    }

    /**
     * Reads a double from the user.
     *
     * @return The double entered by the user.
     */
    public static double getDoubleInput() {
        System.out.print("Entrer un double: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. veuillez entrer un double.");
            scanner.next(); // Consume the invalid input
        }
        double result = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character
        return result;
    }

    /**
     * Reads a string from the user.
     *
     * @return The string entered by the user.
     */
    public static String getStringInput() {
        System.out.print("Entrer un string: ");
        String result = scanner.nextLine();
        return result;
    }

    /**
     * Closes the scanner.
     */
    public void closeScanner() {
        scanner.close();
    }


}
