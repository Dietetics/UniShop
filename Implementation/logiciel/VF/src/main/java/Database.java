import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Database {

    /**
     * Rafraîchit toutes les données, y compris les acheteurs, les revendeurs et les produits.
     * Appelle les méthodes de rafraîchissement respectives pour chaque entité.
     */
    public static void refreshTous(){
        refreshAcheteurs();refreshRevendeurs();refreshProduits();
    }


    /**
     * Rafraîchit les données des acheteurs en combinant les fichiers CSV de chaque acheteur dans un seul fichier.
     */
    public static void refreshAcheteurs() {
        String baseFolderPath = DatabasePath.getBaseAcheteurFolderPath();
        String outputFile = DatabasePath.getPathTousAcheteurs();

        List<String> orderOfFiles = Arrays.asList("main");

        StringBuilder allLines = new StringBuilder();

        File baseFolder = new File(baseFolderPath);
        File[] subFolders = baseFolder.listFiles(File::isDirectory);

        if (subFolders != null) {
            for (File subFolder : subFolders) {
                boolean firstFile = true; // Pour gérer les virgules entre les fichiers
                for (String fileName : orderOfFiles) {
                    File csvFile = new File(subFolder, fileName + ".csv");

                    if (csvFile.exists()) {
                        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                if (!firstFile) {
                                    allLines.append(",");
                                }
                                allLines.append(line);
                                firstFile = false;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                allLines.append("\n"); // Ajoute un saut de ligne entre les dossiers
            }
        }

        // Écrire le contenu dans le fichier de sortie
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            writer.print(allLines.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Rafraîchit les données des revendeurs en combinant les fichiers CSV de chaque revendeur dans un seul fichier.
     */
    public static void refreshRevendeurs() {
        String baseFolderPath = DatabasePath.getBaseRevendeurFolderPath();
        String outputFile = DatabasePath.getPathTousRevendeurs();

        List<String> orderOfFiles = Arrays.asList("main");

        StringBuilder allLines = new StringBuilder();

        File baseFolder = new File(baseFolderPath);
        File[] subFolders = baseFolder.listFiles(File::isDirectory);

        if (subFolders != null) {
            for (File subFolder : subFolders) {
                boolean firstFile = true; // Pour gérer les virgules entre les fichiers
                for (String fileName : orderOfFiles) {
                    File csvFile = new File(subFolder, fileName + ".csv");

                    if (csvFile.exists()) {
                        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                if (!firstFile) {
                                    allLines.append(",");
                                }
                                allLines.append(line);
                                firstFile = false;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                allLines.append("\n"); // Ajoute un saut de ligne entre les dossiers
            }
        }

        // Écrire le contenu dans le fichier de sortie
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            writer.print(allLines.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Rafraîchit les données des produits en combinant les fichiers CSV de chaque produit dans un seul fichier.
     */
    public static void refreshProduits() {
        String baseFolderPath = DatabasePath.getBaseProduitFolderPath();
        String outputFile = DatabasePath.getPathTousProduits();

        List<String> orderOfFiles = Arrays.asList("main");

        StringBuilder allLines = new StringBuilder();

        File baseFolder = new File(baseFolderPath);
        File[] subFolders = baseFolder.listFiles(File::isDirectory);

        if (subFolders != null) {
            for (File subFolder : subFolders) {
                boolean firstFile = true; // Pour gérer les virgules entre les fichiers
                for (String fileName : orderOfFiles) {
                    File csvFile = new File(subFolder, fileName + ".csv");

                    if (csvFile.exists()) {
                        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                if (!firstFile) {
                                    allLines.append(",");
                                }
                                allLines.append(line);
                                firstFile = false;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                allLines.append("\n"); // Ajoute un saut de ligne entre les dossiers
            }
        }

        // Écrire le contenu dans le fichier de sortie
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            writer.print(allLines.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
