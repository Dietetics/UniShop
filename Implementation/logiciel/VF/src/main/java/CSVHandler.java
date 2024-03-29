import java.io.*;
import java.util.*;

public class CSVHandler {

    // pour Reset DataBase
    public static List<String> readLinesFromCSV(String filePath) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }





    /**
     * Lit un fichier CSV et retourne les données.
     *
     * @param filePath Le chemin du fichier CSV.
     * @param numLines Le nombre de lignes à lire.
     * @return Une liste de tableaux de chaînes représentant les données du CSV.
     */
    public static List<String[]> readCSV(String filePath, int numLines) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            List<String[]> data = new ArrayList<>();
            String line;

            int linesRead = 0;
            while ((line = reader.readLine()) != null && linesRead < numLines) {
                // Diviser la ligne en colonnes en utilisant la virgule comme délimiteur
                String[] columns = line.split(",");
                data.add(columns);
                linesRead++;
            }
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Imprime les données CSV dans le format CSV.
     *
     * @param data Les données à imprimer.
     */
    public static void printCSV(List<String[]> data) {
        int nb = 1;
        for (String[] row : data) {
            System.out.print(nb + ". ");
            System.out.println(String.join(",", row));
            nb++;
        }
    }



    /**
     * Imprime une colonne spécifique des données CSV dans le format CSV.
     *
     * @param data      Les données à imprimer.
     * @param columnIndex L'index de la colonne à imprimer.
     */
    public static void printCSV(List<String[]> data, int columnIndex) {
        int nb = 1;
        for (String[] row : data) {
            System.out.print(nb + ". ");

            // Vérifie si l'index de la colonne est valide
            if (columnIndex >= 0 && columnIndex < row.length) {
                System.out.println(row[columnIndex]);
            } else {
                System.out.println("Invalid column index");
            }

            nb++;
        }
    }




    /**
     * Remplace les données d'un fichier CSV.
     *
     * @param filePath Le chemin du fichier CSV.
     * @param data     Les nouvelles données à écrire.
     */
    public static void coverCSV(String filePath, List<String[]> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] row : data) {
                // Joindre les colonnes avec une virgule et écrire dans le fichier
                writer.write(String.join(",", row));
                writer.newLine(); // Ajouter un saut de ligne après chaque ligne
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void coverCSV(String filePath, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write the data to the file
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * Ajoute des données à un fichier CSV.
     *
     * @param filePath Le chemin du fichier CSV.
     * @param data     Les données à ajouter.
     */
    public static void appendCSV(String filePath, List<String[]> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (String[] row : data) {

                writer.write(String.join(",", row));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ajoute une ligne de données à un fichier CSV.
     *
     * @param filePath Le chemin du fichier CSV.
     * @param rowData   La ligne de données à ajouter.
     */
    public static void appendCSV(String filePath, String rowData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(rowData);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void transfereCSV(String sourceFilePath, String destinationFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFilePath));
             PrintWriter writer = new PrintWriter(new FileWriter(destinationFilePath, true))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Ajouter la ligne au fichier de destination
                writer.println(line);
            }

            System.out.println("Données ajoutées avec succès au fichier CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void transfereCSVEnproduction(String sourceFilePath, String destinationFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFilePath));
             PrintWriter writer = new PrintWriter(new FileWriter(destinationFilePath, true))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Ajouter "enProduction" à chaque ligne avant de l'écrire dans le fichier de destination
                String modifiedLine = line + ",enProduction";
                writer.println(modifiedLine);
            }

            System.out.println("Données ajoutées avec succès au fichier CSV avec la mention 'enProduction'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * Recherche un mot-clé dans un fichier CSV.
     *
     * @param filePath Le chemin du fichier CSV.
     * @param keyword  Le mot-clé à rechercher.
     * @return Une liste des lignes correspondantes.
     */
    public static List<String> searchKeywordInCSV(String filePath, String keyword) {
        List<String> matchingLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.toLowerCase().contains(keyword.toLowerCase())) {
                    matchingLines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matchingLines;
    }



    // méthode pour rechercher exactement le mot-clé et un argument qui indique les colonnes quon veut
    public static List<String> searchExaAndFilterColumnsInCSV(String filePath, String keyword, int searchColumnIndex, List<Integer> outputColumnIndices) {
        List<String> matchingLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");

                // Vérifie si le mot-clé est présent dans la colonne de recherche
                if (searchColumnIndex >= 0 && searchColumnIndex < columns.length) {
                    if (columns[searchColumnIndex].trim().equalsIgnoreCase(keyword.trim())) {
                        // Vérifie si les colonnes de sortie sont valides
                        for (int outputColumnIndex : outputColumnIndices) {
                            if (outputColumnIndex >= 0 && outputColumnIndex < columns.length) {
                                matchingLines.add(columns[outputColumnIndex]);
                            } else {
                                System.out.println("Invalid output column index: " + outputColumnIndex);
                            }
                        }
                    }
                } else {
                    System.out.println("Invalid search column index");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return matchingLines;
    }


    public static List<String[]> searchCSV(String csvFilePath, String keyword, int searchColumn, int... outputColumnIndices) {
        List<String[]> results = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");

                // Vérifier si la colonne de recherche contient le mot-clé
                if (searchColumn >= 0 && searchColumn < columns.length && columns[searchColumn].contains(keyword)) {
                    // Construire le tableau résultant en utilisant les indices de colonnes spécifiés
                    String[] resultArray = new String[outputColumnIndices.length];
                    for (int i = 0; i < outputColumnIndices.length; i++) {
                        int outputColumnIndex = outputColumnIndices[i];
                        if (outputColumnIndex >= 0 && outputColumnIndex < columns.length) {
                            resultArray[i] = columns[outputColumnIndex];
                        }
                    }

                    // Ajouter le tableau résultant à la liste des résultats
                    results.add(resultArray);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return results;
    }


    // méthode pour rechercher le mot-clé et un argument qui indique les colonnes quon veut
    public static List<String> searchAndFilterColumnsInCSV(String filePath, String keyword, int searchColumnIndex, List<Integer> outputColumnIndices) {
        List<String> matchingLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");

                // Vérifie si le mot-clé est présent dans la colonne de recherche
                if (searchColumnIndex >= 0 && searchColumnIndex < columns.length) {
                    if (columns[searchColumnIndex].toLowerCase().contains(keyword.toLowerCase().trim())) {
                        // Vérifie si les colonnes de sortie sont valides
                        for (int outputColumnIndex : outputColumnIndices) {
                            if (outputColumnIndex >= 0 && outputColumnIndex < columns.length) {
                                matchingLines.add(columns[outputColumnIndex]);
                            } else {
                                System.out.println("Invalid output column index: " + outputColumnIndex);
                            }
                        }
                    }
                } else {
                    System.out.println("Invalid search column index");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return matchingLines;
    }


    public static List<String> searchAndFilterColumnsInCSV(String filePath, String keyword, List<Integer> searchColumnIndices, List<Integer> outputColumnIndices) {
        List<String> matchingLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");

                // Vérifie si le mot-clé est présent dans au moins l'une des colonnes de recherche
                boolean keywordPresentInAnySearchColumn = false;
                for (int searchColumnIndex : searchColumnIndices) {
                    if (searchColumnIndex >= 0 && searchColumnIndex < columns.length &&
                            columns[searchColumnIndex].toLowerCase().contains(keyword.toLowerCase().trim())) {
                        keywordPresentInAnySearchColumn = true;
                        break;
                    }
                }

                if (keywordPresentInAnySearchColumn) {
                    // Vérifie si les colonnes de sortie sont valides
                    for (int outputColumnIndex : outputColumnIndices) {
                        if (outputColumnIndex >= 0 && outputColumnIndex < columns.length) {
                            matchingLines.add(columns[outputColumnIndex]);
                        } else {
                            System.out.println("Invalid output column index: " + outputColumnIndex);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return matchingLines;
    }







    /**
     * Recherche et récupère toutes les lignes d'un fichier CSV.
     *
     * @param filePath Le chemin du fichier CSV.
     * @return Une liste contenant toutes les lignes du fichier CSV.
     */
    public static List<String> readAllLinesFromCSV(String filePath) {
        List<String> allLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                allLines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return allLines;
    }

    /**
     * Recherche et récupère toutes les lignes d'un fichier CSV sous forme de liste d'entiers.
     *
     * @param filePath Le chemin du fichier CSV.
     * @return Une liste d'entiers contenant toutes les lignes du fichier CSV.
     */
    public static List<Integer> readAllLinesFromCSVAsIntegers(String filePath) {
        List<Integer> allLinesAsIntegers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {

                int intValue = Integer.parseInt(line.trim());
                allLinesAsIntegers.add(intValue);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return allLinesAsIntegers;
    }


    /**
     * Recherche les indices des lignes contenant un mot-clé dans un fichier CSV.
     *
     * @param filePath Le chemin du fichier CSV.
     * @param keyword  Le mot-clé à rechercher.
     * @return Une liste d'indices des lignes correspondantes.
     */
    public static List<Integer> searchKeywordInCSVIndex(String filePath, String keyword) {
        List<Integer> matchingIndices = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int currentIndex = 1; // Commencez à l'index 1 (au lieu de 0)

            while ((line = reader.readLine()) != null) {
                if (line.toLowerCase().contains(keyword.toLowerCase())) {
                    matchingIndices.add(currentIndex);
                }
                currentIndex++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return matchingIndices;
    }



    /**
     * Ajuste une ligne dans un fichier CSV en fonction de l'index spécifié.
     *
     * @param filePath  Le chemin du fichier CSV.
     * @param lineIndex L'index de la ligne à ajuster.
     * @param newData   Les nouvelles données à utiliser.
     */
    public static void ajusterCSVSelonIndex(String filePath, int lineIndex, String[] newData) {

        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        if (lineIndex < 0 || lineIndex >= lines.size()) {
            System.out.println("Invalid line index");
            return;
        }

        lines.set(lineIndex, String.join(",", newData));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String modifiedLine : lines) {
                writer.write(modifiedLine);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Vérifie si une liste de termes existe dans un fichier CSV.
     *
     * @param csvFilePath  Le chemin du fichier CSV.
     * @param searchTerms  La liste des termes à rechercher.
     * @return true si la liste de termes existe consécutivement, sinon false.
     */
    public static boolean verifieSiExiste(String csvFilePath, List<String> searchTerms) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length < searchTerms.size()) {
                    continue;
                }

                for (int i = 0; i <= values.length - searchTerms.size(); i++) {
                    boolean consecutiveMatch = true;

                    for (int j = 0; j < searchTerms.size(); j++) {
                        if (!values[i + j].equals(searchTerms.get(j))) {
                            consecutiveMatch = false;
                            break;
                        }
                    }

                    if (consecutiveMatch) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Vérifie si un terme existe dans un fichier CSV.
     *
     * @param csvFilePath Le chemin du fichier CSV.
     * @param terme        Le terme à rechercher.
     * @return true si le terme existe, sinon false.
     */
    public static boolean isExiste(String csvFilePath, String terme) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // Supposant que les valeurs sont séparées par des virgules

                // Parcourir toutes les valeurs de la ligne
                for (String value : values) {
                    if (value.equals(terme)) {
                        return true; // Terme trouvé dans la ligne
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Terme non trouvé dans le fichier CSV
        return false;
    }




    /**
     * Trouve l'index de la première occurrence d'un terme dans une colonne spécifiée du fichier CSV.
     *
     * @param csvFilePath  Le chemin du fichier CSV.
     * @param searchTerm   Le terme à rechercher.
     * @param columnIndex  L'index de la colonne où effectuer la recherche.
     * @return L'index de la première occurrence du terme dans la colonne spécifiée, ou -1 s'il n'est pas trouvé.
     */
    public static int findOccurrenceIndex(String csvFilePath, String searchTerm, int columnIndex) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;
                String[] values = line.split(",");

                if (values.length <= columnIndex) {
                    continue;
                }

                if (values[columnIndex].equals(searchTerm)) {
                    return lineNumber;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }



    /**
     * Vérifie si la valeur dans une cellule spécifiée correspond à une valeur donnée.
     *
     * @param csvFilePath Le chemin du fichier CSV.
     * @param index        L'index de la ligne à vérifier.
     * @param column       L'index de la colonne à vérifier.
     * @param value        La valeur à comparer.
     * @return true si la valeur dans la cellule correspond, sinon false.
     */
    public static boolean isValueAtIndexAndColumn(String csvFilePath, int index, int column, String value) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            int currentLine = 0;

            while ((line = br.readLine()) != null) {
                if (currentLine == index) {
                    String[] values = line.split(",");

                    if (column >= 0 && column < values.length) {
                        return values[column].equals(value);
                    } else {
                        throw new IllegalArgumentException("Column index is out of bounds.");
                    }
                }
                currentLine++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * Lit une ligne d'un fichier CSV en fonction de l'index spécifié.
     *
     * @param csvFilePath  Le chemin du fichier CSV.
     * @param targetIndex  L'index de la ligne à lire.
     * @return La ligne correspondante ou null si l'index n'est pas trouvé.
     */
    public static String readLineByIndex(String csvFilePath, int targetIndex) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            int currentIndex = 0;

            while ((line = br.readLine()) != null) {
                currentIndex++;

                if (currentIndex == targetIndex) {
                    return line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Obtient la valeur dans une colonne spécifiée d'une ligne CSV.
     *
     * @param csvLine     La ligne CSV.
     * @param columnIndex L'index de la colonne.
     * @return La valeur dans la colonne spécifiée ou null si l'index n'est pas valide.
     */
    public static String getColumnValue(String csvLine, int columnIndex) {
        List<String> columns = Arrays.asList(csvLine.split(","));

        if (columnIndex >= 0 && columnIndex < columns.size()) {
            return columns.get(columnIndex);
        } else {
            return null;
        }
    }


    /**
     * Met à jour une ligne dans un fichier CSV avec de nouvelles données.
     *
     * @param filePath   Le chemin du fichier CSV.
     * @param index      L'index de la ligne à mettre à jour.
     * @param newCSVLine Les nouvelles données de la ligne.
     */
    public static void uploadCSVLine(String filePath, int index, List<String> newCSVLine) {
        // Lire le fichier CSV existant
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Vérifier si l'index spécifié est valide
        if (index >= 0 && index < lines.size()) {
            lines.set(index, String.join(",", newCSVLine));
        } else {
            System.out.println("L'index specifie n'est pas valide.");
            return;
        }

        // Écrire les modifications dans le fichier
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("mise à jour avec succès.");
    }



    /**
     * Met à jour une ligne dans un fichier CSV avec une nouvelle ligne de données.
     *
     * @param filePath    Le chemin du fichier CSV.
     * @param index       L'index de la ligne à mettre à jour.
     * @param newCSVLine  La nouvelle ligne de données.
     */
    public static void uploadCSVLine(String filePath, int index, String newCSVLine) {
        // Lire le fichier CSV existant
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Vérifier si l'index spécifié est valide
        if (index >= 0 && index < lines.size()) {
            lines.set(index, newCSVLine);
        } else {
            System.out.println("L'index specifie n'est pas valide.");
            return;
        }

        // Écrire les modifications dans le fichier
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Ligne mise a jour avec succes.");
    }


    /**
     * Met à jour une colonne dans une ligne CSV avec une nouvelle valeur.
     *
     * @param csvLine     La ligne CSV à mettre à jour.
     * @param columnIndex L'index de la colonne à mettre à jour.
     * @param newValue    La nouvelle valeur à assigner.
     * @return La ligne CSV mise à jour.
     */
    public static String updateCSVColumn(String csvLine, int columnIndex, String newValue) {
        // Diviser la ligne en un tableau de valeurs
        String[] values = csvLine.split(",");

        // Vérifier si l'index de la colonne à mettre à jour est valide
        if (columnIndex >= 0 && columnIndex < values.length) {
            // Mettre à jour la valeur de la colonne
            values[columnIndex] = newValue;
        } else {
            System.out.println("Index de colonne invalide.");
        }
        // Recombiner les valeurs en une nouvelle chaîne CSV mise à jour
        return String.join(",", values);
    }


    /**
     * Trie les données CSV en fonction de la colonne spécifiée et de l'ordre.
     *
     * @param data    Les données CSV à trier.
     * @param column  L'index de la colonne pour le tri.
     * @param order   L'ordre de tri (ASCENDING ou DESCENDING).
     */
    public static void sortByColumn(List<String[]> data, int column, SortOrder order) {
        // Utilisation d'un comparateur personnalisé
        Comparator<String[]> comparator = (row1, row2) -> {
            String value1 = column < row1.length ? row1[column] : "";
            String value2 = column < row2.length ? row2[column] : "";

            // Comparaison des valeurs en fonction de l'ordre spécifié
            if (isNumeric(value1) && isNumeric(value2)) {
                // Si les deux valeurs sont numériques, comparez en tant que nombres
                double num1 = Double.parseDouble(value1);
                double num2 = Double.parseDouble(value2);

                return (order == SortOrder.ASCENDING) ? Double.compare(num1, num2) : Double.compare(num2, num1);
            } else {
                // Sinon, comparez en tant que chaînes de caractères
                return (order == SortOrder.ASCENDING) ? value1.compareTo(value2) : value2.compareTo(value1);
            }
        };
        // Trie la liste en utilisant le comparateur
        data.sort(comparator);
    }

    /**
     * Vérifie si une chaîne peut être convertie en nombre.
     *
     * @param str La chaîne à vérifier.
     * @return true si la conversion en nombre est réussie, sinon false.
     */
    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Enumération pour spécifier l'ordre de tri.
     */
    public enum SortOrder {
        ASCENDING,
        DESCENDING
    }


    /**
     * Obtient la valeur dans une colonne spécifiée d'une ligne CSV.
     *
     * @param csvFilePath Le chemin du fichier CSV.
     * @param index        L'index de la ligne.
     * @param column       L'index de la colonne.
     * @return La valeur dans la colonne spécifiée ou null si l'index n'est pas valide.
     */
    public static String getValueAtIndexAndColumn(String csvFilePath, int index, int column) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            int currentLine = 0;

            while ((line = br.readLine()) != null) {
                if (currentLine == index) {
                    String[] values = line.split(","); // Supposons que les valeurs sont séparées par des virgules

                    if (column >= 0 && column < values.length) {
                        return values[column];
                    } else {
                        throw new IllegalArgumentException("Column index is out of bounds.");
                    }
                }
                currentLine++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void removeLineFromCSV(String filePath, int lineIndex) {
        // Lire le fichier CSV et stocker chaque ligne dans une liste
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Vérifier si l'index de la ligne à supprimer est valide
        if (lineIndex >= 0 && lineIndex < lines.size()) {
            // Supprimer la ligne spécifiée
            lines.remove(lineIndex);

            // Réécrire le fichier avec les lignes mises à jour
            try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
                for (String updatedLine : lines) {
                    writer.println(updatedLine);
                }
                System.out.println("retiree avec succes.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Index de ligne invalide.");
        }
    }



    public static int countLines(String filePath) {
        int lineCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.readLine() != null) {
                lineCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineCount;
    }


    public static void clearCSV(String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            // Le fichier est ouvert en mode écriture, ce qui efface son contenu
            // Le bloc try-with-resources garantit que le fichier sera correctement fermé
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * Modifie la valeur d'une cellule spécifique dans un fichier CSV.
     *
     * @param filePath  Chemin du fichier CSV.
     * @param rowIndex  Indice de la ligne à modifier.
     * @param colIndex  Indice de la colonne à modifier.
     * @param newValue  Nouvelle valeur à assigner à la cellule.
     * @throws IOException En cas d'erreur d'entrée/sortie lors de la modification du fichier.
     */
    public static void modifyCSVValue(String filePath, int rowIndex, int colIndex, String newValue) throws IOException {
        List<String[]> lines = readCSV(filePath, Integer.MAX_VALUE);

        if (rowIndex >= 0 && rowIndex < lines.size()) {
            String[] row = lines.get(rowIndex);

            if (colIndex >= 0 && colIndex < row.length) {
                row[colIndex] = newValue;
                lines.set(rowIndex, row);

                writeCSV(filePath, lines);
            } else {
                System.out.println("Invalid column index");
            }
        } else {
            System.out.println("Invalid row index");
        }
    }

    /**
     * Écrit les données CSV dans un fichier.
     *
     * @param filePath Chemin du fichier CSV.
     * @param data     Liste des lignes CSV à écrire.
     * @throws IOException En cas d'erreur d'entrée/sortie lors de l'écriture dans le fichier.
     */
    public static void writeCSV(String filePath, List<String[]> data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] row : data) {
                writer.write(String.join(",", row));
                writer.newLine();
            }
        }
    }



    public static void writeLinesToCSV(String filePath, List<String> lines) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.println(line);
            }
            System.out.println("Données écrites avec succès dans le fichier CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static int sumIntegersFromCSV(String filePath) {
        int sum = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Séparer les valeurs par virgule
                String[] values = line.split(",");

                // Parcourir les valeurs et les ajouter à la somme
                for (String value : values) {
                    try {
                        int intValue = Integer.parseInt(value.trim());
                        sum += intValue;
                    } catch (NumberFormatException e) {
                        // Ignorer les valeurs qui ne sont pas des entiers
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sum;
    }


    public static Set<String> readColumnValuesFromCSV(String csvFilePath, int columnIndex) {
        Set<String> values = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Supposons que les colonnes du CSV soient séparées par des virgules
                String[] columns = line.split(",");
                if (columns.length > columnIndex) {
                    values.add(columns[columnIndex].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return values;
    }

}