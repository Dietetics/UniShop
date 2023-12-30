import java.io.*;
import java.util.List;

public class Reset {

    public static void resetDatabases(List<String> lines, List<String> additionalFiles,String baseFolderPath) {
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


    public static void main(String[] args) {

        // lire les databases initiales du projet(achateurs)
        List<String> acheteurDatabase = CSVHandler.readLinesFromCSV(DatabasePath.getDatabase0Acheteur());
        List<String> acheteurFichierInfo = CSVHandler.readLinesFromCSV(DatabasePath.getFichiersInfoAcheteur());
        String acheteurBasePath = DatabasePath.getBaseAcheteurFolderPath();


        // lire les databases initiales du projet(revendeurs)
        List<String> revendeurDatabase = CSVHandler.readLinesFromCSV(DatabasePath.getDatabase0Revendeur());
        List<String> revendeurFichierInfo = CSVHandler.readLinesFromCSV(DatabasePath.getFichiersInfoRevendeur());
        String revendeurBasePath = DatabasePath.getBaseRevendeurFolderPath();


        // lire les databases initiales du projet(produits)
        List<String> produitDatabase = CSVHandler.readLinesFromCSV(DatabasePath.getDatabase0Produit());
        List<String> produitFichierInfo = CSVHandler.readLinesFromCSV(DatabasePath.getFichiersInfoProduit());
        String produitBasePath = DatabasePath.getBaseProduitFolderPath();

        // vider le tout
        deleteAllFolders(acheteurBasePath);
        deleteAllFolders(revendeurBasePath);
        deleteAllFolders(produitBasePath);

        // reinitialiser avec les databases initiales
        resetDatabases(acheteurDatabase, acheteurFichierInfo,acheteurBasePath);
        resetDatabases(revendeurDatabase, revendeurFichierInfo,revendeurBasePath);
        resetDatabases(produitDatabase, produitFichierInfo,produitBasePath);
    }
}
