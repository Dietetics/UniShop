import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DatabasePath {

    private static String produitPath = "src/main/resources/data/produits.csv";
    private static String acheteurPath = "src/main/resources/data/acheteurs.csv";
    private static String revendeurPath = "src/main/resources/data/revendeurs.csv";

    private static String produitInfoPath = "src/main/resources/data/produit/";
    private static String acheteurComptePath = "src/main/resources/data/acheteur/";
    private static String revendeurComptePath = "src/main/resources/data/revendeur/";


    private static String baseProduitFolderPath = "src/main/resources/data/produit";
    private static String baseAcheteurFolderPath = "src/main/resources/data/acheteur";
    private static String baseRevendeurFolderPath = "src/main/resources/data/revendeur";


    private static String outputProduitFile = "src/main/resources/data/produits.csv";
    private static String outputAcheteurFile = "src/main/resources/data/acheteurs.csv";
    private static String outputRevendeurFile = "src/main/resources/data/revendeurs.csv";

    private static String fichiersInfoProduit= "src/main/resources/data/databaseInitiale/fichiersProduits.csv";
    private static String fichiersInfoAcheteur= "src/main/resources/data/databaseInitiale/fichiersAcheteurs.csv";
    private static String fichiersInfoRevendeur= "src/main/resources/data/databaseInitiale/fichiersRevendeurs.csv";


    static void checkProduitPath(){
        try {
            checkFileExistence(produitPath);
        } catch (FileNotFoundException e) {
            System.out.println("File no trouver: " + produitPath);
            e.printStackTrace();
        }
    }

    static void checkAcheteurPath(){
        try {
            checkFileExistence(acheteurPath);
        } catch (FileNotFoundException e) {
            System.out.println("File no trouver: " + acheteurPath);
            e.printStackTrace();
        }
    }


    static void checkRevendeurPath(){
        try {
            checkFileExistence(revendeurPath);
        } catch (FileNotFoundException e) {
            System.out.println("File no trouver: " + revendeurPath);
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



    public static String getProduitPath() {
        return produitPath;
    }

    public static String getRevendeurPath() {
        return revendeurPath;
    }

    public static String getAcheteurPath() {
        return acheteurPath;
    }

    public static String getProduitInfoPath() {
        return produitInfoPath;
    }

    public static String getAcheteurComptePath() {
        return acheteurComptePath;
    }

    public static String getRevendeurComptePath() {
        return revendeurComptePath;
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

    public static String getOutputProduitFile() {
        return outputProduitFile;
    }

    public static String getOutputAcheteurFile() {
        return outputAcheteurFile;
    }

    public static String getOutputRevendeurFile() {
        return outputRevendeurFile;
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
}
