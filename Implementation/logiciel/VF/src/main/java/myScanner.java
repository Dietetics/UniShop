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
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. veuillez entrer un integer.");
            scanner.next(); // Consume the invalid input
        }
        int result = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        return result;
    }



    public static double getDoubleInput() {
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
