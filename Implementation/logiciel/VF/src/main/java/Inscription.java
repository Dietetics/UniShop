import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Inscription {
    public Inscription() {
    }




    /**
     * Permet à l'utilisateur de s'inscrire en tant qu'acheteur ou revendeur. Affiche un menu interactif où
     * l'utilisateur peut choisir entre les options d'inscription et effectuer les actions correspondantes.
     *
     * @return Aucune valeur de retour.
     */
    public static void inscription() {
        boolean quitter = false;

        String essai = "----- Bienvenu a la page d'inscription ----- \n:a pour acheteur \n:r pour revendeur " +
                "\n:q pour quitter";

        while (!quitter) {
            try {
                System.out.println("");
                System.out.println(essai);
                essai = "continuez a inscrire d'autres comptes? \n:a pour acheteur \n:r pour revendeur \n:q pour quitter";
                System.out.print("Reponse: ");
                String keyword = myScanner.getStringInput();

                switch (keyword.toLowerCase()) {
                    case ":q": quitter = true; break;
                    case ":a": inscriptionAcheteur(); break;
                    case ":r": inscriptionRevendeur(); break;
                    default: System.out.println("Choix invalide. Veuillez entrer :a, :r ou :q."); break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur de saisie. Veuillez entrer une option valide.");
            }
        }
        VF.displayMenuPrincipale();
    }





    public static void inscriptionAcheteur() {
        System.out.println("----- Bienvenu a notre page d'inscription pour devenir Acheteur -----");

        try {
            String nom = InputRestreint.getValidInput("Veuillez entrer votre nom: ");
            String prenom = InputRestreint.getValidInput("Veuillez entrer votre prenom: ");
            String adresse = InputRestreint.getValidAdresse("Veuillez entrer votre adresse d'expedition: ");
            String courriel = InputRestreint.getValidAcheteurCourriel("Veuillez entrer votre adresse courriel(unique): ",2);
            String telephone = InputRestreint.getValidTelephone("Veuillez entrer votre telephone: ");
            String pseudo = InputRestreint.getValidPseudo("Veuillez entrer votre pseudo(unique): ");
            String password = InputRestreint.getValidPassword("Veuillez entrer votre password: ");

            saveAcheteurData(nom, prenom, adresse, courriel, telephone, pseudo, password);

            System.out.println("Donnees enregistrees avec succes");
            System.out.println("--------------------------------");

        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }


    public static void inscriptionRevendeur() {
        System.out.println("----- Bienvenu a notre page d'inscription pour devenir Revendeur -----");

        try {
            String nom = InputRestreint.getValidInputVRevendeur("Veuillez entrer votre nom: ");
            String adresse = InputRestreint.getValidAdresse("Veuillez entrer votre adresse d'expedition: ");
            String courriel = InputRestreint.getValidRevendeurCourriel("Veuillez entrer votre adresse courriel(unique): ",2);
            String telephone = InputRestreint.getValidTelephone("Veuillez entrer votre telephone: ");
            String password = InputRestreint.getValidPassword("Veuillez entrer votre password: ");

            saveRevendeurData(nom, adresse, courriel, telephone, password);

            System.out.println("Donnees enregistrees avec succes");
            System.out.println("--------------------------------");

        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }






    private static void saveAcheteurData(String nom, String prenom, String adresse, String courriel, String telephone, String pseudo, String password) {
        List<String[]> userData = new ArrayList<>();
        userData.add(new String[]{pseudo, password, courriel, nom, prenom, telephone, adresse, "0"});

        String directoryPath = DatabasePath.getAcheteurComptePath() + pseudo + "/";
        createDirectory(directoryPath);

        String acheteurCompte = directoryPath + "main.csv";
        String acheteurPanier = directoryPath + "panier.csv";
        String acheteurHistoire = directoryPath + "histoire.csv";
        String acheteurBillet = directoryPath + "billet.csv";
        String acheteurSuivre = directoryPath + "suivre.csv";
        String acheteurSuiviPar = directoryPath + "suiviPar.csv";
        String acheteurNotification = directoryPath + "notification.csv";
        String acheteurLikes = directoryPath + "likes.csv";

        CSVHandler.coverCSV(acheteurCompte, userData);
        CSVHandler.coverCSV(acheteurPanier, new ArrayList<>());
        CSVHandler.coverCSV(acheteurHistoire, new ArrayList<>());
        CSVHandler.coverCSV(acheteurBillet, new ArrayList<>());
        CSVHandler.coverCSV(acheteurSuivre, new ArrayList<>());
        CSVHandler.coverCSV(acheteurSuiviPar, new ArrayList<>());
        CSVHandler.coverCSV(acheteurNotification, new ArrayList<>());
        CSVHandler.coverCSV(acheteurLikes, new ArrayList<>());

        Database.refreshAcheteurs();
    }


    private static void saveRevendeurData(String nom, String adresse, String courriel, String telephone, String password) {
        List<String[]> userData = new ArrayList<>();
        userData.add(new String[]{nom, adresse, courriel, telephone, "0", password});

        String directoryPath = DatabasePath.getRevendeurComptePath() + nom + "/";
        createDirectory(directoryPath);

        String revendeurCompte = directoryPath + "main.csv";
        String revendeurResolution = directoryPath + "resolution.csv";
        String revendeurOffre = directoryPath + "offre.csv";
        String revendeurNotification = directoryPath + "notification.csv";


        CSVHandler.coverCSV(revendeurCompte, userData);
        CSVHandler.coverCSV(revendeurResolution, new ArrayList<>());
        CSVHandler.coverCSV(revendeurOffre, new ArrayList<>());
        CSVHandler.coverCSV(revendeurNotification, new ArrayList<>());

        Database.refreshRevendeurs();
    }







    public static void createDirectory(String directoryPath) {
        Path path = FileSystems.getDefault().getPath(directoryPath);

        if (Files.notExists(path)) {
            try {
                Files.createDirectory(path);
                System.out.println("Repertoire cree : " + directoryPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
