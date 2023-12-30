import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DatabasePath {

    //database
    private static String pathTousProduits = "src/main/resources/data/produits.csv";
    private static String pathTousAcheteurs = "src/main/resources/data/acheteurs.csv";
    private static String pathTousRevendeurs = "src/main/resources/data/revendeurs.csv";

    //vers l interieur du dossier
    private static String pathProduitCompte = "src/main/resources/data/produit/";
    private static String pathAcheteurCompte = "src/main/resources/data/acheteur/";
    private static String pathRevendeurCompte = "src/main/resources/data/revendeur/";

    //vers dossier
    private static String baseProduitFolderPath = "src/main/resources/data/produit";
    private static String baseAcheteurFolderPath = "src/main/resources/data/acheteur";
    private static String baseRevendeurFolderPath = "src/main/resources/data/revendeur";


    // database des fichiers initiales
    private static String fichiersInfoProduit= "src/main/resources/data/databaseInitiale/fichiersProduits.csv";
    private static String fichiersInfoAcheteur= "src/main/resources/data/databaseInitiale/fichiersAcheteurs.csv";
    private static String fichiersInfoRevendeur= "src/main/resources/data/databaseInitiale/fichiersRevendeurs.csv";

    // ceci pour initialiser le database
    private static String database0Produit= "src/main/resources/data/databaseInitiale/Database0Produits.csv";
    private static String database0Acheteur= "src/main/resources/data/databaseInitiale/Database0Acheteurs.csv";
    private static String database0Revendeur= "src/main/resources/data/databaseInitiale/Database0Revendeurs.csv";




    static void checkDatabaseFiles(){
        checkExistance(pathTousProduits);
        checkExistance(pathTousAcheteurs);
        checkExistance(pathTousRevendeurs);

        // les files initiales
        checkExistance(fichiersInfoProduit);
        checkExistance(fichiersInfoAcheteur);
        checkExistance(fichiersInfoRevendeur);

        // les databases initiales
        checkExistance(database0Produit);
        checkExistance(database0Acheteur);
        checkExistance(database0Revendeur);
    }


    static void checkExistance(String path){
        try {
            checkFileExistence(path);
        } catch (FileNotFoundException e) {
            System.out.println("File no trouver: " + path);
            e.printStackTrace();
        }
    }

    static void checkFileExistence(String filePath) throws FileNotFoundException {
        File file = new File(filePath);

        if (file.exists() && file.isFile()) {
            return;
        } else {
            throw new FileNotFoundException("File no trouver: " + filePath);
        }
    }



    public static String getPathTousProduits() {
        return pathTousProduits;
    }
    public static String getPathTousRevendeurs() {
        return pathTousRevendeurs;
    }
    public static String getPathTousAcheteurs() {
        return pathTousAcheteurs;
    }


    public static String getPathProduitCompte() {
        return pathProduitCompte;
    }
    public static String getPathAcheteurCompte() {
        return pathAcheteurCompte;
    }
    public static String getPathRevendeurCompte() {
        return pathRevendeurCompte;
    }


    public static String getBaseProduitFolderPath() {
        return baseProduitFolderPath;
    }
    public static String getBaseAcheteurFolderPath() {
        return baseAcheteurFolderPath;
    }
    public static String getBaseRevendeurFolderPath() {
        return baseRevendeurFolderPath;
    }


    public static String getFichiersInfoProduit() {
        return fichiersInfoProduit;
    }
    public static String getFichiersInfoAcheteur() {
        return fichiersInfoAcheteur;
    }
    public static String getFichiersInfoRevendeur() {
        return fichiersInfoRevendeur;
    }


    public static String getDatabase0Produit() {
        return database0Produit;
    }
    public static String getDatabase0Acheteur() {
        return database0Acheteur;
    }
    public static String getDatabase0Revendeur() {
        return database0Revendeur;
    }
}
