import java.util.Arrays;
import java.util.List;

public class testCSV {
    public static void main(String[] args) {
        // Exemple de chemin de fichier
        String filePath = "src/test/fichier.csv";

        // Exemple de données CSV
        List<String[]> data = Arrays.asList(
                new String[]{"Nom", "Prenom", "Email"},
                new String[]{"Doe", "John", "john.doe@example.com"},
                new String[]{"Smith", "Alice", "alice.smith@example.com"}
        );

        // Exemple pour lire CSV avec un nombre spécifié de lignes
        List<String[]> readData = CSVHandler.readCSV(filePath, 2);
        CSVHandler.printCSV(readData);

        // Exemple pour imprimer des données CSV
        CSVHandler.printCSV(data);

        // Exemple pour couvrir (remplacer) un fichier CSV
        CSVHandler.coverCSV(filePath, data);

        // Exemple pour ajouter des données à un fichier CSV
        CSVHandler.appendCSV(filePath, data);

        // Exemple pour rechercher un mot-clé dans un fichier CSV
        List<String> matchingLines = CSVHandler.searchKeywordInCSV(filePath, "john");
        System.out.println("Lignes correspondantes : " + matchingLines);

        // Exemple pour ajuster le CSV selon un index de ligne
        CSVHandler.ajusterCSVSelonIndex(filePath, 2, new String[]{"Doe", "Jane", "jane.doe@example.com"});

        // Exemple pour vérifier si une liste existe dans un fichier CSV
        List<String> searchTerms = Arrays.asList("Doe", "Jane", "jane.doe@example.com");
        boolean existe = CSVHandler.verifieSiExiste(filePath, searchTerms);
        System.out.println("La liste existe : " + existe);

        // Exemple pour trouver l'index d'un terme dans une colonne
        int occurrenceIndex = CSVHandler.findOccurrenceIndex(filePath, "jane.doe@example.com", 2);
        System.out.println("Index de l'occurrence : " + occurrenceIndex);

        // Exemple pour vérifier si une valeur est à l'index et à la colonne spécifiés
        boolean isValue = CSVHandler.isValueAtIndexAndColumn(filePath, 2, 2, "jane.doe@example.com");
        System.out.println("La valeur est à l'index et à la colonne spécifiés : " + isValue);

        // Exemple pour lire une ligne par index
        String lineByIndex = CSVHandler.readLineByIndex(filePath, 2);
        System.out.println("Ligne à l'index spécifié : " + lineByIndex);

        // Exemple pour obtenir la valeur d'une colonne dans une ligne CSV
        String columnValue = CSVHandler.getColumnValue(lineByIndex, 1);
        System.out.println("Valeur de la colonne spécifiée : " + columnValue);

        // Exemple pour mettre à jour une ligne dans un fichier CSV
        CSVHandler.uploadCSVLine(filePath, 2, Arrays.asList("Doe", "Jane", "jane.doe@example.com"));

        // Exemple pour trier une liste de tableaux de chaînes par colonne
        CSVHandler.sortByColumn(data, 1, CSVHandler.SortOrder.ASCENDING);
        System.out.println("Données triées par ordre ascendant selon la deuxième colonne :");
        CSVHandler.printCSV(data);

        // Exemple pour transformer une liste de chaînes en une liste de tableaux de chaînes
        List<String> stringList = Arrays.asList("1,Alice,25", "2,Bob,30", "3,Charlie,22");
        List<String[]> resultList = FormatAdjust.transformList(stringList);
        System.out.println("Résultat de la transformation :");
        CSVHandler.printCSV(resultList);
    }
}
