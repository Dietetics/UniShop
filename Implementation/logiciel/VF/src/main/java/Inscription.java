import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Inscription {

    private static Scanner scanner = new Scanner(System.in);



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
                String keyword = scanner.next();

                switch (keyword.toLowerCase()) {
                    case ":q": quitter = true; break;
                    case ":a": inscriptionAcheteur(); break;
                    case ":r": inscriptionRevendeur(); break;
                    default: System.out.println("Choix invalide. Veuillez entrer :a, :r ou :q."); break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur de saisie. Veuillez entrer une option valide.");
                scanner.nextLine(); // Effacer la ligne incorrecte dans le scanner
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
            String courriel = InputRestreint.getValidCourriel("Veuillez entrer votre adresse courriel(unique): ",3);
            String telephone = InputRestreint.getValidTelephone("Veuillez entrer votre telephone: ");
            String pseudo = InputRestreint.getValidPseudo("Veuillez entrer votre pseudo(unique): ");
            String password = InputRestreint.getValidPassword("Veuillez entrer votre password: ");

            saveAcheteurData(nom, prenom, adresse, courriel, telephone, pseudo, password);

            System.out.println("Donnees enregistrees avec succes");
            System.out.println("--------------------------------");

        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
            scanner.nextLine();
        }
    }


    public static void inscriptionRevendeur() {
        System.out.println("----- Bienvenu a notre page d'inscription pour devenir Revendeur -----");

        try {
            String nom = InputRestreint.getValidInputVRevendeur("Veuillez entrer votre nom: ");
            String adresse = InputRestreint.getValidAdresse("Veuillez entrer votre adresse d'expedition: ");
            String courriel = InputRestreint.getValidCourriel("Veuillez entrer votre adresse courriel(unique): ",2);
            String telephone = InputRestreint.getValidTelephone("Veuillez entrer votre telephone: ");
            String password = InputRestreint.getValidPassword("Veuillez entrer votre password: ");

            saveRevendeurData(nom, adresse, courriel, telephone, password);

            System.out.println("Donnees enregistrees avec succes");
            System.out.println("--------------------------------");

        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
            scanner.nextLine();
        }
    }






    private static void saveAcheteurData(String nom, String prenom, String adresse, String courriel, String telephone, String pseudo, String password) {
        List<String[]> userData = new ArrayList<>();
        userData.add(new String[]{nom, prenom, pseudo, courriel, telephone, adresse, "0", password});

        String directoryPath = DatabasePath.getAcheteurComptePath() + pseudo + "/";
        createDirectory(directoryPath);

        String acheteurCompte = directoryPath + pseudo + ".csv";
        String acheteurPanier = directoryPath + "panier.csv";
        String acheteurHistoire = directoryPath + "histoire.csv";
        String acheteurBillet = directoryPath + "billet.csv";
        String acheteurSuivre = directoryPath + "suivre.csv";
        String acheteurSuiviPar = directoryPath + "suiviPar.csv";
        String acheteurNotification = directoryPath + "notification.csv";

        CSVHandler.appendCSV(DatabasePath.getAcheteurPath(), userData);
        CSVHandler.coverCSV(acheteurCompte, userData);
        CSVHandler.coverCSV(acheteurPanier, new ArrayList<>());
        CSVHandler.coverCSV(acheteurHistoire, new ArrayList<>());
        CSVHandler.coverCSV(acheteurBillet, new ArrayList<>());
        CSVHandler.coverCSV(acheteurSuivre, new ArrayList<>());
        CSVHandler.coverCSV(acheteurSuiviPar, new ArrayList<>());
        CSVHandler.coverCSV(acheteurNotification, new ArrayList<>());
    }


    private static void saveRevendeurData(String nom, String adresse, String courriel, String telephone, String password) {
        List<String[]> userData = new ArrayList<>();
        userData.add(new String[]{nom, adresse, courriel, telephone, "0", password});

        String directoryPath = DatabasePath.getRevendeurComptePath() + nom + "/";
        createDirectory(directoryPath);

        String revendeurCompte = directoryPath + nom + ".csv";
        String revendeurResolution = directoryPath + "resolution.csv";

        CSVHandler.appendCSV(DatabasePath.getRevendeurPath(), userData);
        CSVHandler.coverCSV(revendeurCompte, userData);
        CSVHandler.coverCSV(revendeurResolution, new ArrayList<>());
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
