import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateFileInDirectory {


    /**
     * Crée un nouveau fichier dans le répertoire spécifié.
     *
     * @param directory Le chemin du répertoire dans lequel créer le fichier.
     * @param file Le nom du fichier à créer.
     */
    public static void newFile(String directory, String file) {
        // Specify the path for the new directory
        Path directoryPath = Paths.get(directory);

        try {
            // Create the directory
            Files.createDirectories(directoryPath);

            // Specify the path for the new file within the directory
            Path filePath = directoryPath.resolve(file);

            // Create the file
            Files.createFile(filePath);

        } catch (IOException e) {
            System.err.println("Failed to create directory or file: " + e.getMessage());
        }
    }
}