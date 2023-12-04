import java.io.File;

public class Delete {

    public static void main(String[] args) {
        String baseFolderPath = "src/main/resources/data/acheteur"; // Remplacez par votre chemin réel

        deleteAllFolders(baseFolderPath);
    }

    public static void deleteAllFolders(String baseFolderPath) {
        File baseFolder = new File(baseFolderPath);

        // Vérifie si le chemin spécifié correspond à un dossier
        if (baseFolder.isDirectory()) {
            File[] allFilesAndFolders = baseFolder.listFiles();

            if (allFilesAndFolders != null) {
                for (File fileOrFolder : allFilesAndFolders) {
                    // Vérifie si c'est un dossier avant de le supprimer
                    if (fileOrFolder.isDirectory()) {
                        deleteFolder(fileOrFolder);
                    }
                }
            }
        }
    }

    public static void deleteFolder(File folder) {
        File[] allFilesAndFolders = folder.listFiles();

        if (allFilesAndFolders != null) {
            for (File fileOrFolder : allFilesAndFolders) {
                if (fileOrFolder.isDirectory()) {
                    // Appel récursif pour supprimer les sous-dossiers
                    deleteFolder(fileOrFolder);
                } else {
                    fileOrFolder.delete(); // Supprimer les fichiers dans le dossier
                }
            }
        }

        folder.delete(); // Supprimer le dossier lui-même après avoir supprimé son contenu
    }
}
