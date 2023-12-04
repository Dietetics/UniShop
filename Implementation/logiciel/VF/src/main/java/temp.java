import java.io.*;
import java.util.List;

public class temp {

    public static void resetDatabase(String folderName, String csvLine, List<String> additionalFiles) {
        String baseFolderPath = "src/main/resources/data"; // Chemin vers le dossier principal
        String mainFileName = "main.csv"; // Nom du fichier principal dans chaque dossier

        File folder = new File(baseFolderPath, folderName);

        if (!folder.exists()) {
            folder.mkdirs(); // Créer le dossier s'il n'existe pas
        }

        File mainFile = new File(folder, mainFileName);

        try (PrintWriter writer = new PrintWriter(new FileWriter(mainFile))) {
            writer.println(csvLine); // Écrire la ligne dans le fichier principal

            // Créer d'autres fichiers CSV si nécessaire
            for (String additionalFile : additionalFiles) {
                File file = new File(folder, additionalFile);
                file.createNewFile(); // Créer un fichier vide
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Reset complete for folder: " + folderName);
    }

    public static void main(String[] args) {
        // Exemple d'utilisation
        String folderName = "exampleFolder";
        String csvLine = "auteur1,#We123123,teng.wanting@umontreal.ca,Teng,Wanting,5148888888,H3C3J7,0";
        List<String> additionalFiles = List.of("file1.csv", "file2.csv", "file3.csv");

        resetDatabase(folderName, csvLine, additionalFiles);
    }
}
