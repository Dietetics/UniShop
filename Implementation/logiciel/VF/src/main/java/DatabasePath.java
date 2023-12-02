import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DatabasePath {

    private static String produitPath = "src/main/resources/data/produits.csv";

    private static String acheteurPath = "src/main/resources/data/acheteur.csv";
    private static String revendeurPath = "src/main/resources/data/revendeur.csv";


    private static String acheteurComptePath = "src/main/resources/data/acheteur/";
    private static String revendeurComptePath = "src/main/resources/data/revendeur/";



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

    public static String getAcheteurComptePath() {
        return acheteurComptePath;
    }

    public static String getRevendeurComptePath() {
        return revendeurComptePath;
    }
}
