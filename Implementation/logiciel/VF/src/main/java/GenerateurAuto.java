import java.util.Set;
import java.util.UUID;

public class GenerateurAuto {

    public static String uniqueUUID(String csvFilePath, int columnIndex) {
        Set<String> existingValues = CSVHandler.readColumnValuesFromCSV(csvFilePath, columnIndex);

        while (true) {
            String newUUID = UUID.randomUUID().toString();
            if (!existingValues.contains(newUUID)) {

                return newUUID;
            }
        }
    }

}
