import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CSVHandler {

    // lire csv, returner les donnees
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

    // imprimer les donnes lu (format csv)
    public static void printCSV(List<String[]> data) {
        int nb = 1;
        for (String[] row : data) {
            System.out.print(nb + ". ");
            System.out.println(String.join(",", row));
            nb++;
        }
    }



    // Remplacer les donnes dun tableau de csv
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



    // Ajouter des donnees dans un tableau de csv
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



    // rechercher un mot-cle selon le filepath
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


    // ajuster csv avec filepath, index, nouveau String[]
    public static void ajusterCSVSelonIndex(String filePath, int lineIndex, String[] newData) {
        // Read the entire CSV file into memory
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return; // Exit if there's an issue reading the file
        }

        // Check if the lineIndex is within the bounds
        if (lineIndex < 0 || lineIndex >= lines.size()) {
            System.out.println("Invalid line index");
            return;
        }

        // Adjust the specified line with the new data
        lines.set(lineIndex, String.join(",", newData));

        // Write the modified content back to the CSV file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String modifiedLine : lines) {
                writer.write(modifiedLine);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // dans un csvFile, verifie si la list existe
    public static boolean verifieSiExiste(String csvFilePath, List<String> searchTerms) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // Assuming values are separated by commas

                if (values.length < searchTerms.size()) {
                    continue; // Skip lines with fewer values than the search terms
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
                        return true; // Consecutive matches found
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // No consecutive matches found
        return false;
    }




    // dans un csvfile, trouver l index du searchTerm   ATTENTION commence par 1
    public static int findOccurrenceIndex(String csvFilePath, String searchTerm, int columnIndex) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;
                String[] values = line.split(","); // Assuming values are separated by commas

                if (values.length <= columnIndex) {
                    continue; // Skip lines with not enough values
                }

                if (values[columnIndex].equals(searchTerm)) {
                    return lineNumber; // Return the line number of the match in the specified column
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 if the term is not found in the specified column
    }



    // verifier si dans un csvfile, selon index line et column colonne, on a la valeur value
    public static boolean isValueAtIndexAndColumn(String csvFilePath, int index, int column, String value) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            int currentLine = 0;

            while ((line = br.readLine()) != null) {
                if (currentLine == index) {
                    String[] values = line.split(","); // Assuming values are separated by commas

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

        // The specified index was not found or an error occurred
        return false;
    }


    //
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

        // Return null if the target index is not found
        return null;
    }


    public static String getColumnValue(String csvLine, int columnIndex) {
        List<String> columns = Arrays.asList(csvLine.split(","));

        // Vérifiez si l'index de la colonne spécifiée est valide
        if (columnIndex >= 0 && columnIndex < columns.size()) {
            return columns.get(columnIndex);
        } else {
            return null; // Retourne null si l'index de la colonne n'est pas valide
        }
    }



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
            System.out.println("L'index spécifié n'est pas valide.");
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

        System.out.println("Ligne mise à jour avec succès.");
    }



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
            System.out.println("L'index spécifié n'est pas valide.");
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

        System.out.println("Ligne mise à jour avec succès.");
    }


    // Méthode pour mettre à jour une colonne dans une ligne CSV
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

    // Vérifie si la chaîne peut être convertie en nombre
    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Enum pour spécifier l'ordre de tri
    public enum SortOrder {
        ASCENDING,
        DESCENDING
    }
}