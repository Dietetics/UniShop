import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Database {

    // lit toutes les datas des acheteurs, prendre le main.csv de chaque puis output sur acheteur.csv
    public static void refreshAcheteurs() {
        String baseFolderPath = DatabasePath.getBaseAcheteurFolderPath();
        String outputFile = DatabasePath.getOutputAcheteurFile();

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




    public static void refreshRevendeurs() {
        String baseFolderPath = DatabasePath.getBaseRevendeurFolderPath();
        String outputFile = DatabasePath.getOutputRevendeurFile();

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


    public static void refreshProduits() {
        String baseFolderPath = DatabasePath.getBaseProduitFolderPath();
        String outputFile = DatabasePath.getOutputProduitFile();

        List<String> orderOfFiles = Arrays.asList("suiviPar", "suivre", "main");

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



    public static void main(String[] args) {refreshAcheteurs();refreshRevendeurs();refreshProduits();}
}
