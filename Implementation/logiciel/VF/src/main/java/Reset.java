import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.Csv;

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

    public static void infoSupplementaires(){
        //data de base auteur2 offrir des produits
        CSVHandler.appendCSV(DatabasePath.getPathRevendeurCompte() + "auteur2/offrir.csv", "Cahier1");
        CSVHandler.appendCSV(DatabasePath.getPathRevendeurCompte() + "auteur2/offrir.csv", "guideEtude1");
        CSVHandler.appendCSV(DatabasePath.getPathRevendeurCompte() + "auteur2/offrir.csv", "lampesBureau1");
        CSVHandler.appendCSV(DatabasePath.getPathRevendeurCompte() + "auteur2/offrir.csv", "Livre1");
        CSVHandler.appendCSV(DatabasePath.getPathRevendeurCompte() + "auteur2/offrir.csv", "OrdinateurPortable1");

        CSVHandler.appendCSV(DatabasePath.getPathRevendeurCompte() + "auteur1/offrir.csv", "test");
        CSVHandler.appendCSV(DatabasePath.getPathRevendeurCompte() + "auteur1/offrir.csv", "Livre2");
        CSVHandler.appendCSV(DatabasePath.getPathRevendeurCompte() + "auteur1/offrir.csv", "Livre3");
        CSVHandler.appendCSV(DatabasePath.getPathRevendeurCompte() + "auteur1/offrir.csv", "Livre4");
        CSVHandler.appendCSV(DatabasePath.getPathRevendeurCompte() + "auteur1/offrir.csv", "Livre5");
        CSVHandler.appendCSV(DatabasePath.getPathRevendeurCompte() + "auteur1/offrir.csv", "Livre6");
        CSVHandler.appendCSV(DatabasePath.getPathRevendeurCompte() + "auteur1/offrir.csv", "Livre7");
        CSVHandler.appendCSV(DatabasePath.getPathRevendeurCompte() + "auteur1/offrir.csv", "Livre8");
        CSVHandler.appendCSV(DatabasePath.getPathRevendeurCompte() + "auteur1/offrir.csv", "Livre9");
        CSVHandler.appendCSV(DatabasePath.getPathRevendeurCompte() + "auteur1/offrir.csv", "Livre10");
        CSVHandler.appendCSV(DatabasePath.getPathRevendeurCompte() + "auteur1/offrir.csv", "Livre11");
        CSVHandler.appendCSV(DatabasePath.getPathRevendeurCompte() + "auteur1/offrir.csv", "Livre12");
        CSVHandler.appendCSV(DatabasePath.getPathRevendeurCompte() + "auteur1/offrir.csv", "Livre13");
        CSVHandler.appendCSV(DatabasePath.getPathRevendeurCompte() + "auteur1/offrir.csv", "Livre14");
        CSVHandler.appendCSV(DatabasePath.getPathRevendeurCompte() + "auteur1/offrir.csv", "AEviterUsagePourTest");

        //data de base des produits offer par auteur2
        CSVHandler.appendCSV(DatabasePath.getPathProduitCompte() + "Cahier1/offerPar.csv", "auteur2");
        CSVHandler.appendCSV(DatabasePath.getPathProduitCompte() + "guideEtude1/offerPar.csv", "auteur2");
        CSVHandler.appendCSV(DatabasePath.getPathProduitCompte() + "lampesBureau1/offerPar.csv", "auteur2");
        CSVHandler.appendCSV(DatabasePath.getPathProduitCompte() + "Livre1/offerPar.csv", "auteur2");
        CSVHandler.appendCSV(DatabasePath.getPathProduitCompte() + "OrdinateurPortable1/offerPar.csv", "auteur2");

        CSVHandler.appendCSV(DatabasePath.getPathProduitCompte() + "test/offerPar.csv", "auteur1");
        CSVHandler.appendCSV(DatabasePath.getPathProduitCompte() + "Livre2/offerPar.csv", "auteur1");
        CSVHandler.appendCSV(DatabasePath.getPathProduitCompte() + "Livre3/offerPar.csv", "auteur1");
        CSVHandler.appendCSV(DatabasePath.getPathProduitCompte() + "Livre4/offerPar.csv", "auteur1");
        CSVHandler.appendCSV(DatabasePath.getPathProduitCompte() + "Livre5/offerPar.csv", "auteur1");
        CSVHandler.appendCSV(DatabasePath.getPathProduitCompte() + "Livre6/offerPar.csv", "auteur1");
        CSVHandler.appendCSV(DatabasePath.getPathProduitCompte() + "Livre7/offerPar.csv", "auteur1");
        CSVHandler.appendCSV(DatabasePath.getPathProduitCompte() + "Livre8/offerPar.csv", "auteur1");
        CSVHandler.appendCSV(DatabasePath.getPathProduitCompte() + "Livre9/offerPar.csv", "auteur1");
        CSVHandler.appendCSV(DatabasePath.getPathProduitCompte() + "Livre10/offerPar.csv", "auteur1");
        CSVHandler.appendCSV(DatabasePath.getPathProduitCompte() + "Livre11/offerPar.csv", "auteur1");
        CSVHandler.appendCSV(DatabasePath.getPathProduitCompte() + "Livre12/offerPar.csv", "auteur1");
        CSVHandler.appendCSV(DatabasePath.getPathProduitCompte() + "Livre13/offerPar.csv", "auteur1");
        CSVHandler.appendCSV(DatabasePath.getPathProduitCompte() + "Livre14/offerPar.csv", "auteur1");


        //data de base auteur2 suivi par auteur1/auteur3. CAD auteur1/auteur3 suivent auteur2
        CSVHandler.appendCSV(DatabasePath.getPathAcheteurCompte() + "auteur2/suiviPar.csv", "auteur1");
        CSVHandler.appendCSV(DatabasePath.getPathAcheteurCompte() + "auteur2/suiviPar.csv", "auteur3");
        CSVHandler.appendCSV(DatabasePath.getPathAcheteurCompte() + "auteur1/suivreAcheteur.csv", "auteur2");
        CSVHandler.appendCSV(DatabasePath.getPathAcheteurCompte() + "auteur3/suivreAcheteur.csv", "auteur2");

        //data de base auteur1 suivi par auteur2. CAD auteur2 suit auteur1
        CSVHandler.appendCSV(DatabasePath.getPathAcheteurCompte() + "auteur1/suiviPar.csv", "auteur2");
        CSVHandler.appendCSV(DatabasePath.getPathAcheteurCompte() + "auteur2/suivreAcheteur.csv", "auteur1");

        //data de base avec une notes et une evaluation
        CSVHandler.appendCSV(DatabasePath.getPathProduitCompte() + "Cahier1/notes.csv", "Par acheteur: auteur2. Note: 60");
        CSVHandler.appendCSV(DatabasePath.getPathProduitCompte() + "Cahier1/evaluations.csv", "Par acheteur: auteur2. Eval: pas si pire");
        CSVHandler.appendCSV(DatabasePath.getPathAcheteurCompte() + "auteur2/evaluations.csv", "Pour le produit: Cahier1. Vous avez donne note: 60; evaluation: pas si pire");

        //data ded base auteur2 avec dix commande
        CSVHandler.appendCSV(DatabasePath.getPathAcheteurCompte() + "AEviterUsagePourTest/histoire.csv", "AEviterUsagePourTest,livre");
        CSVHandler.appendCSV(DatabasePath.getPathAcheteurCompte() + "AEviterUsagePourTest/histoire.csv", "AEviterUsagePourTest,livre");
        CSVHandler.appendCSV(DatabasePath.getPathAcheteurCompte() + "AEviterUsagePourTest/histoire.csv", "AEviterUsagePourTest,livre");
        CSVHandler.appendCSV(DatabasePath.getPathAcheteurCompte() + "AEviterUsagePourTest/histoire.csv", "AEviterUsagePourTest,enLivraison");
        CSVHandler.appendCSV(DatabasePath.getPathAcheteurCompte() + "AEviterUsagePourTest/histoire.csv", "AEviterUsagePourTest,enLivraison");
        CSVHandler.appendCSV(DatabasePath.getPathAcheteurCompte() + "AEviterUsagePourTest/histoire.csv", "AEviterUsagePourTest,enLivraison");
        CSVHandler.appendCSV(DatabasePath.getPathAcheteurCompte() + "AEviterUsagePourTest/histoire.csv", "AEviterUsagePourTest,enLivraison");
        CSVHandler.appendCSV(DatabasePath.getPathAcheteurCompte() + "AEviterUsagePourTest/histoire.csv", "AEviterUsagePourTest,enProduction");
        CSVHandler.appendCSV(DatabasePath.getPathAcheteurCompte() + "AEviterUsagePourTest/histoire.csv", "AEviterUsagePourTest,enProduction");
        CSVHandler.appendCSV(DatabasePath.getPathAcheteurCompte() + "AEviterUsagePourTest/histoire.csv", "AEviterUsagePourTest,enProduction");

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

        // ajouter des infos supplementaires dans les fichiers
        infoSupplementaires();

        Database.refreshTous();
    }
}
