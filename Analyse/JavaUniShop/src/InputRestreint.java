import java.io.IOException;
import java.util.List;
//auteur: wanting
public class InputRestreint {


    public InputRestreint() {
    }

    static boolean isValidInput(String input) {
        return input.matches("^[a-zA-Z]{2,}$");
    }

    static boolean isValidAddress(String address) {
        return address.length() <= 20;
    }

    static boolean isValidTelephone(String telephone) {
        return telephone.matches("^\\d{10}$");
    }

    static boolean isValidCourriel(String courriel) {
        return courriel.endsWith("@gmail.com") || courriel.endsWith("@umontreal.ca");
    }

    static boolean isValidUniqueRow(String filePath, String unique, int column) throws IOException {
        List<String[]> data = CSVHandler.readCSV(filePath,9999);

        if (data != null) {
            for (String[] row : data) {
                // Check if the "unique" value matches any value in the third column
                if (row.length > 2 && unique.equals(row[column])) {
                    return false;
                }
            }
        }
        return true;
    }
}



