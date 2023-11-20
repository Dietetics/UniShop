import java.io.*;
import java.util.*;

public class CSVHandler {

    public static List<String[]> readCSV(String fileName, int numLines) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(CSVHandler.class.getClassLoader().getResourceAsStream(fileName))))) {

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

    public static void printCSV(List<String[]> data) {
        int nb = 1;
        for (String[] row : data) {
            System.out.print(nb + ". ");
            System.out.println(String.join(",", row));
            nb++;
        }
    }

    public static void coverCSV(String fileName, List<String[]> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFileFromResources(fileName)))) {
            for (String[] row : data) {
                // Joindre les colonnes avec une virgule et écrire dans le fichier
                writer.write(String.join(",", row));
                writer.newLine(); // Ajouter un saut de ligne après chaque ligne
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendCSV(String fileName, List<String[]> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFileFromResources(fileName), true))) {
            for (String[] row : data) {
                writer.write(String.join(",", row));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendCSV(String fileName, String rowData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFileFromResources(fileName), true))) {
            writer.write(rowData);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> searchKeywordInCSV(String fileName, String keyword) {
        List<String> matchingLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(CSVHandler.class.getClassLoader().getResourceAsStream(fileName))))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.toLowerCase().contains(keyword.toLowerCase())) {
                    matchingLines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // It's generally better to log or handle exceptions appropriately
        }

        return matchingLines;
    }

    public static List<String> readAllLinesFromCSV(String fileName) {
        List<String> allLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(CSVHandler.class.getClassLoader().getResourceAsStream(fileName))))) {
            String line;
            while ((line = reader.readLine()) != null) {
                allLines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return allLines;
    }

    public static List<Integer> readAllLinesFromCSVAsIntegers(String fileName) {
        List<Integer> allLinesAsIntegers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(CSVHandler.class.getClassLoader().getResourceAsStream(fileName))))) {
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

    public static List<Integer> searchKeywordInCSVIndex(String fileName, String keyword) {
        List<Integer> matchingIndices = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(CSVHandler.class.getClassLoader().getResourceAsStream(fileName))))) {
            String line;
            int currentIndex = 1;

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

    public static void ajusterCSVSelonIndex(String fileName, int lineIndex, String[] newData) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(CSVHandler.class.getClassLoader().getResourceAsStream(fileName))))) {
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

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFileFromResources(fileName)))) {
            for (String modifiedLine : lines) {
                writer.write(modifiedLine);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean verifieSiExiste(String fileName, List<String> searchTerms) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(CSVHandler.class.getClassLoader().getResourceAsStream(fileName))))) {
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

    public static boolean isExiste(String fileName, String terme) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(CSVHandler.class.getClassLoader().getResourceAsStream(fileName))))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                for (String value : values) {
                    if (value.equals(terme)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int findOccurrenceIndex(String fileName, String searchTerm, int columnIndex) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(CSVHandler.class.getClassLoader().getResourceAsStream(fileName))))) {
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

    public static boolean isValueAtIndexAndColumn(String fileName, int index, int column, String value) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(CSVHandler.class.getClassLoader().getResourceAsStream(fileName))))) {
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

    public static String readLineByIndex(String fileName, int targetIndex) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(CSVHandler.class.getClassLoader().getResourceAsStream(fileName))))) {
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

    public static String getColumnValue(String csvLine, int columnIndex) {
        List<String> columns = Arrays.asList(csvLine.split(","));

        if (columnIndex >= 0 && columnIndex < columns.size()) {
            return columns.get(columnIndex);
        } else {
            return null;
        }
    }

    public static void uploadCSVLine(String fileName, int index, List<String> newCSVLine) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(CSVHandler.class.getClassLoader().getResourceAsStream(fileName))))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        if (index >= 0 && index < lines.size()) {
            lines.set(index, String.join(",", newCSVLine));
        } else {
            System.out.println("L'index specifie n'est pas valide.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFileFromResources(fileName)))) {
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("mise à jour avec succès.");
    }

    public static void uploadCSVLine(String fileName, int index, String newCSVLine) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(CSVHandler.class.getClassLoader().getResourceAsStream(fileName))))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        if (index >= 0 && index < lines.size()) {
            lines.set(index, newCSVLine);
        } else {
            System.out.println("L'index specifie n'est pas valide.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFileFromResources(fileName)))) {
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Ligne mise a jour avec succes.");
    }

    public static String updateCSVColumn(String csvLine, int columnIndex, String newValue) {
        String[] values = csvLine.split(",");

        if (columnIndex >= 0 && columnIndex < values.length) {
            values[columnIndex] = newValue;
        } else {
            System.out.println("Index de colonne invalide.");
        }

        return String.join(",", values);
    }

    public static void sortByColumn(List<String[]> data, int column, SortOrder order) {
        Comparator<String[]> comparator = (row1, row2) -> {
            String value1 = column < row1.length ? row1[column] : "";
            String value2 = column < row2.length ? row2[column] : "";

            if (isNumeric(value1) && isNumeric(value2)) {
                double num1 = Double.parseDouble(value1);
                double num2 = Double.parseDouble(value2);

                return (order == SortOrder.ASCENDING) ? Double.compare(num1, num2) : Double.compare(num2, num1);
            } else {
                return (order == SortOrder.ASCENDING) ? value1.compareTo(value2) : value2.compareTo(value1);
            }
        };
        data.sort(comparator);
    }

    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public enum SortOrder {
        ASCENDING,
        DESCENDING
    }

    private static File getFileFromResources(String fileName) {
        ClassLoader classLoader = CSVHandler.class.getClassLoader();
        return new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
    }

    public static String getValueAtIndexAndColumn(String fileName, int index, int column) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(CSVHandler.class.getClassLoader().getResourceAsStream(fileName))))) {
            String line;
            int currentLine = 0;

            while ((line = br.readLine()) != null) {
                if (currentLine == index) {
                    String[] values = line.split(",");

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
}
