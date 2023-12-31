import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.List;

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
    public static void saveAcheteurData(String nom, String prenom, String adresse, String courriel, String telephone, String pseudo, String password) {

        String csvLine = FormatAdjust.convertToCSV(pseudo, password, courriel, nom, prenom, telephone, adresse, "0");

        String csvAddiFilePath = DatabasePath.getFichiersInfoAcheteur();
        List<String> additionalFiles = CSVHandler.readLinesFromCSV(csvAddiFilePath);

        ajoutAcheteur(pseudo, csvLine, additionalFiles);

        Database.refreshAcheteurs();
    }
    public static void ajoutAcheteur(String folderName, String csvLine, List<String> additionalFiles) {
        String baseFolderPath = DatabasePath.getBaseAcheteurFolderPath(); // Chemin vers le dossier principal
        String mainFileName = "main.csv"; // Nom du fichier principal dans chaque dossier

        File folder = new File(baseFolderPath, folderName);

        if (!folder.exists()) {
            folder.mkdirs(); // Créer le dossier s'il n'existe pas
        }

        File mainFile = new File(folder, mainFileName);

        try (PrintWriter writer = new PrintWriter(new FileWriter(mainFile))) {
            writer.println(csvLine); // Écrire la ligne dans le fichier principal

            // Créer d'autres fichiers CSV si nécessaire
            for (String additionalFile : additionalFiles) {
                File file = new File(folder, additionalFile);
                file.createNewFile(); // Créer un fichier vide
            }
        } catch (IOException e) {
            e.printStackTrace();
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
    public static void saveRevendeurData(String nom, String adresse, String courriel, String telephone, String password) {

        String csvLine = FormatAdjust.convertToCSV(nom, password, courriel, adresse, telephone, "0");

        String csvAddiFilePath = DatabasePath.getFichiersInfoRevendeur();
        List<String> additionalFiles = CSVHandler.readLinesFromCSV(csvAddiFilePath);

        ajoutRevendeur(nom, csvLine, additionalFiles);

        Database.refreshRevendeurs();
    }
    public static void ajoutRevendeur(String folderName, String csvLine, List<String> additionalFiles) {
        String baseFolderPath = DatabasePath.getBaseRevendeurFolderPath(); // Chemin vers le dossier principal
        String mainFileName = "main.csv"; // Nom du fichier principal dans chaque dossier

        File folder = new File(baseFolderPath, folderName);

        if (!folder.exists()) {
            folder.mkdirs(); // Créer le dossier s'il n'existe pas
        }

        File mainFile = new File(folder, mainFileName);

        try (PrintWriter writer = new PrintWriter(new FileWriter(mainFile))) {
            writer.println(csvLine); // Écrire la ligne dans le fichier principal

            // Créer d'autres fichiers CSV si nécessaire
            for (String additionalFile : additionalFiles) {
                File file = new File(folder, additionalFile);
                file.createNewFile(); // Créer un fichier vide
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public static void inscriptionProduit(String revendeur) {

        System.out.println("\nCher revendeur: " + revendeur + "      " + "Page: ajout des produits");
        System.out.println("-----------------------------------------------------");

        try {
            String titre = InputRestreint.getValidTitre("Veuillez entrer un titre unique: ");

            System.out.println("\nVoici la liste de categorie: ");
            System.out.println("-----------------------------");
            InputRestreint.CATEGORIES_AUTORISEES.forEach(System.out::println);
            System.out.println("-----------------------------");
            String categorie = InputRestreint.getValidCategorie("Veuillez entrer un categorie: ");

            String description = InputRestreint.getValidDescription("Veuillez entrer un description: ");
            String quantite0 = InputRestreint.getValidQuantite("Veuillez entrer une quantite initiale: ");
            String prix = InputRestreint.getValidPrix("Veuillez entrer un prix: ");
            String pointsBoni = InputRestreint.getValidPointsBoni("Veuillez entrer des points Bonus: ");
            System.out.println("-----------------------------");

            System.out.println("Vous pouvez ajouter des images, videos et si en promo par la suite");
            String uuid = GenerateurAuto.uniqueUUID(DatabasePath.getPathTousProduits(),8);
            System.out.println("L'uuid genere automatiquement par le systeme est: " + uuid);



            saveProduitData(titre, categorie, description, quantite0, prix, pointsBoni, uuid);
            offer(revendeur, titre);

            System.out.println("Donnees enregistrees avec succes");
            System.out.println("--------------------------------");

        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }
    public static void saveProduitData(String titre, String categorie, String description, String quantite0, String prix, String pointsBoni, String uuid) {

        String csvLine = FormatAdjust.convertToCSV(titre, categorie, description, quantite0, prix, pointsBoni, "aucun", "aucun", uuid, "0", "0", "non");

        String csvAddiFilePath = DatabasePath.getFichiersInfoProduit();
        List<String> additionalFiles = CSVHandler.readLinesFromCSV(csvAddiFilePath);

        ajoutProduits(titre, csvLine, additionalFiles);

        Database.refreshProduits();
    }
    public static void offer(String revendeur,String titre){
        String path_offrir = DatabasePath.getPathRevendeurCompte() + revendeur + "/offrir.csv";
        CSVHandler.appendCSV(path_offrir,titre);

        String path_offerPar = DatabasePath.getPathProduitCompte() + titre + "/offerPar.csv";
        CSVHandler.appendCSV(path_offerPar,revendeur);
    };
    public static void ajoutProduits(String folderName, String csvLine, List<String> additionalFiles) {
        String baseFolderPath = DatabasePath.getBaseProduitFolderPath(); // Chemin vers le dossier principal
        String mainFileName = "main.csv"; // Nom du fichier principal dans chaque dossier

        File folder = new File(baseFolderPath, folderName);

        if (!folder.exists()) {
            folder.mkdirs(); // Créer le dossier s'il n'existe pas
        }

        File mainFile = new File(folder, mainFileName);

        try (PrintWriter writer = new PrintWriter(new FileWriter(mainFile))) {
            writer.println(csvLine); // Écrire la ligne dans le fichier principal

            // Créer d'autres fichiers CSV si nécessaire
            for (String additionalFile : additionalFiles) {
                File file = new File(folder, additionalFile);
                file.createNewFile(); // Créer un fichier vide
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
