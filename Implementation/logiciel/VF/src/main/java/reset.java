import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class reset {

    public static void resetDatabases(List<String> lines, List<String> additionalFiles) {
        String baseFolderPath = "src/main/resources/data/acheteur"; // Chemin vers le dossier principal
        String mainFileName = "main.csv"; // Nom du fichier principal dans chaque dossier

        for (String line : lines) {
            String[] columns = line.split(",");

            if (columns.length > 0) {
                String folderName = columns[0];
                File folder = new File(baseFolderPath, folderName);

                if (!folder.exists()) {
                    folder.mkdirs(); // Créer le dossier s'il n'existe pas
                }

                File mainFile = new File(folder, mainFileName);

                try (PrintWriter writer = new PrintWriter(new FileWriter(mainFile))) {
                    writer.println(line); // Écrire la ligne dans le fichier principal

                    // Créer d'autres fichiers CSV si nécessaire
                    for (String additionalFile : additionalFiles) {
                        File file = new File(folder, additionalFile);
                        file.createNewFile(); // Créer un fichier vide
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Reset complete.");
    }

    public static void main(String[] args) {
        String csvFilePath = "src/main/resources/data/databaseInitiale/Database0Acheteurs.csv";
        List<String> lines = CSVHandler.readLinesFromCSV(csvFilePath);

        String csvAddiFilePath = "src/main/resources/data/databaseInitiale/fichiersAcheteurs.csv";
        List<String> additionalFiles = CSVHandler.readLinesFromCSV(csvAddiFilePath);


        resetDatabases(lines, additionalFiles);
    }
}
