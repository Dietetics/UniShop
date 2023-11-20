import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class VF {
    private static String produitPath = "src/main/resources/data/produits.csv";
    private static String revendeurPath = "src/main/resources/data/revendeur.csv";
    private static String acheteurPath = "src/main/resources/data/acheteur.csv";

    private static String acheteurComptePath = "src/main/resources/data/acheteur/";  // prefix fixe, ajouter la postfix base sur pseudo
    private static String revendeurComptePath = "src/main/resources/data/revendeur/";// prefix fixe, ajouter la postfix base sur pseudo
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        displayMenuPrincipale();
        scanner.close();
    }

    /**
     * Affiche le menu principal de l'application UniShop, permettant à l'utilisateur de choisir parmi
     * différentes options telles que rechercher un produit, s'inscrire, se connecter, ou quitter l'application.
     * La méthode utilise une boucle interactive pour gérer les choix de l'utilisateur.
     *
     * @return Aucune valeur de retour.
     */
    public static void displayMenuPrincipale() {
        int choix = 90;
        while (choix != 0) {
            try {
                System.out.println("\n");
                System.out.println("------ Bienvenu a notre plateforme UniShop -------");
                System.out.println("0. Quitter");
                System.out.println("1. Rechercher un produit");
                System.out.println("2. Inscription");
                System.out.println("3. Connexion");
                System.out.print("\n");

                System.out.print("Choix : ");
                choix = scanner.nextInt();

                switch (choix) {
                    case 0: System.out.println("Au revoir !");
                    System.exit(choix);
                        break;
                    case 1: Recherche.rechercheProduits(); break;
                    case 2: inscription(); break;
                    case 3: connecter(); break;
                    default:
                        System.out.println("Choix invalide. Veuillez reessayer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer un nombre entier.");
                scanner.nextLine(); // Effacer la ligne incorrecte dans le scanner
            }
        }
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
        displayMenuPrincipale();
    }





    /**
     * Gère le processus d'inscription pour devenir un acheteur. L'utilisateur est invité à fournir
     * des informations telles que son nom, prénom, adresse, adresse courriel, téléphone, et pseudo unique.
     * Les données fournies sont ensuite validées, et si elles sont correctes, l'acheteur est enregistré
     * dans le système avec un répertoire personnel créé pour stocker ses informations.
     *
     * @return Aucune valeur de retour.
     */
    public static void inscriptionAcheteur() {

        System.out.println("----- Bienvenu a notre page d'inscription pour devenir Acheteur -----");

        Boolean condition = true;
        while (condition) {
            try {
                System.out.print("Veuillez entrer votre nom: ");
                String nom = scanner.next();


                System.out.print("Veuillez entrer votre prenom: ");
                String prenom = scanner.next();

                if (!InputRestreint.isValidInput(nom) || !InputRestreint.isValidInput(prenom)) {
                    throw new IllegalArgumentException("Le nom et prenom doivent contenir au moins 2 caracteres alphabetiques.");
                }

                System.out.print("Veuillez entrer votre adresse dexpedition: ");
                String adresse = scanner.next();

                if (!InputRestreint.isValidAddress(adresse)) {
                    throw new IllegalArgumentException("adresse doit avoir un longueur inferieur a 20");
                }

                System.out.print("Veuillez entrer votre adresse courriel: ");
                String courriel = scanner.next();


                if (!InputRestreint.isValidCourriel(courriel)) {
                    throw new IllegalArgumentException("Le courriel doit terminer par @gamil.com ou @umontreal.ca");
                }


                System.out.print("Veuillez entrer votre telephone: ");
                String telephone = scanner.next();

                if (!InputRestreint.isValidTelephone(telephone)) {
                    throw new IllegalArgumentException("Le telephone doit etre composer de 10 chiffres");
                }


                System.out.print("Veuillez entrer votre pseudo(unique): ");
                String pseudo = scanner.next();


                if (!InputRestreint.isValidUniqueRow(getAcheteurPath(),pseudo,2)) {
                    throw new IllegalArgumentException("votre pseudo est pris, veuillez entre un nouveau");
                }

                condition = false;

                System.out.println("Donnees enregistrees avec succes");
                System.out.println("--------------------------------");


                List<String[]> stringArrayList = new ArrayList<>();

                String directoryPath = getAcheteurComptePath() + pseudo + "/";

                // Vérifier si le répertoire existe, sinon le créer
                createDirectory(directoryPath);

                List<String[]> userData = new ArrayList<>();
                userData.add(new String[]{nom, prenom, pseudo, courriel, telephone, adresse, "0"});

                String acheteurCompte = directoryPath + pseudo + ".csv";
                String acheteurPanier = directoryPath + "panier.csv";
                String acheteurHistoire = directoryPath + "histoire.csv";
                String acheteurBillet = directoryPath + "billet.csv";
                String acheteurSuivre = directoryPath + "suivre.csv";
                String acheteurSuiviPar = directoryPath + "suiviPar.csv";
                String acheteurNotification = directoryPath + "notification.csv";


                CSVHandler.appendCSV(getAcheteurPath(), userData);
                CSVHandler.coverCSV(acheteurCompte,userData);
                CSVHandler.coverCSV(acheteurPanier,stringArrayList);
                CSVHandler.coverCSV(acheteurHistoire,stringArrayList);
                CSVHandler.coverCSV(acheteurBillet,stringArrayList);
                CSVHandler.coverCSV(acheteurSuivre,stringArrayList);
                CSVHandler.coverCSV(acheteurSuiviPar,stringArrayList);
                CSVHandler.coverCSV(acheteurNotification,stringArrayList);

                break;
            } catch (Exception e) {
                System.out.println("Erreur: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }


    /**
     * Gère le processus d'inscription pour devenir un revendeur. L'utilisateur est invité à fournir
     * des informations telles que son nom unique, adresse, adresse courriel, et téléphone. Les données
     * fournies sont ensuite validées, et si elles sont correctes, le revendeur est enregistré dans le
     * système avec un répertoire personnel créé pour stocker ses informations.
     *
     * @return Aucune valeur de retour.
     */
    public static void inscriptionRevendeur() {

        System.out.println("----- Bienvenu a notre page d'inscription pour devenir revendeur -----");

        Boolean condition = true;
        while (condition) {
            try {
                System.out.print("Veuillez entrer votre nom(unique): ");
                String nom = scanner.next();


                if (!InputRestreint.isValidUniqueRow(getRevendeurPath(), nom, 0)) {
                    throw new IllegalArgumentException("Le nom doit etre unique.");
                }

                System.out.print("Veuillez entrer votre adresse: ");
                String adresse = scanner.next();

                if (!InputRestreint.isValidAddress(adresse)) {
                    throw new IllegalArgumentException("adresse doit avoir un longueur inferieur a 20");
                }

                System.out.print("Veuillez entrer votre adresse courriel: ");
                String courriel = scanner.next();


                if (!InputRestreint.isValidCourriel(courriel)) {
                    throw new IllegalArgumentException("Le courriel doit terminer par @gamil.com ou @umontreal.ca");
                }


                System.out.print("Veuillez entrer votre telephone: ");
                String telephone = scanner.next();

                if (!InputRestreint.isValidTelephone(telephone)) {
                    throw new IllegalArgumentException("Le telephone doit etre composer de 10 chiffres");
                }

                condition = false;

                System.out.println("Donnees enregistrees avec succes");
                System.out.println("--------------------------------");


                List<String[]> stringArrayList = new ArrayList<>();

                String directoryPath = getRevendeurComptePath() + nom + "/";

                // Vérifier si le répertoire existe, sinon le créer
                createDirectory(directoryPath);

                List<String[]> userData = new ArrayList<>();
                userData.add(new String[]{nom, adresse, courriel, telephone});

                String revendeurCompte = directoryPath + nom + ".csv";
                String revendeurResolution = directoryPath + "resolution.csv";

                CSVHandler.appendCSV(getRevendeurPath(), userData);
                CSVHandler.coverCSV(revendeurCompte,userData);
                CSVHandler.coverCSV(revendeurResolution,stringArrayList);

                break;
            } catch (Exception e) {
                System.out.println("Erreur: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }








    /**
     * Gère le processus de connexion en demandant à l'utilisateur s'il est un acheteur ou un revendeur.
     * Il l'oriente ensuite vers les méthodes de connexion appropriées. Permet également de revenir à la
     * page d'accueil en entrant ":q".
     *
     * @return Aucune valeur de retour.
     */
    public static void connecter() {
        System.out.println("----- Bienvenu a notre page de connexion -----");
        System.out.println("Etes-vous un revendeur ou un acheteur");
        System.out.println(":q Retourner a la page d'acceuil");
        System.out.println(":a Acheteur");
        System.out.println(":r Revendeur");
        System.out.println("Veuillez choisir un choix: ");


        boolean quitter = false;

        while (!quitter) {
            try {
                String keyword = scanner.next();

                switch (keyword.toLowerCase()) {
                    case ":q": quitter = true; break;
                    case ":a": connectAcheteur(); break;
                    case ":r": connectRevendeur(); break;

                    default: System.out.println("Choix invalide. Veuillez entrer :a, :r ou :q."); break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur de saisie. Veuillez entrer une option valide.");
                scanner.nextLine(); // Effacer la ligne incorrecte dans le scanner
            }
        }
    }


    /**
     * Gère le processus de connexion pour un revendeur en demandant le nom et le courriel.
     * Crée ensuite un objet ProfilRevendeur pour représenter la session de connexion réussie.
     *
     * @return Aucune valeur de retour.
     */
    public static void connectRevendeur(){

        System.out.println("----- Veuillez vous connectez -----");

        Boolean condition = true;
        while (condition) {
            try {
                System.out.println("Nom: ");
                String nom = scanner.next();
                int index = CSVHandler.findOccurrenceIndex(getRevendeurPath(),nom,0);
                if (index == -1) {
                    throw new IllegalArgumentException("votre compte n'est pas trouver dans le systeme. " +
                            "entre de nouveau votre nom");
                }

                System.out.println("Courriel: ");
                String courriel = scanner.next();
                Boolean valide = CSVHandler.isValueAtIndexAndColumn(getRevendeurPath(), index-1, 2, courriel );
                if (!valide) {
                    throw new IllegalArgumentException("vous avez saisi le mauvais couriel, rapplez que ca termine par" +
                            " @gamil.com ou @umontreal.ca");
                }

                condition = false;

                new ProfilRevendeur(nom,courriel);
            } catch (Exception e) {
                System.out.println("Erreur: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }



    /**
     * Gère le processus de connexion pour un acheteur en demandant le pseudo et le courriel.
     * Crée ensuite un objet ProfilAcheteur pour représenter la session de connexion réussie.
     *
     * @return Aucune valeur de retour.
     */
    public static void connectAcheteur(){

        System.out.println("----- Veuillez vous connectez -----");

        Boolean condition = true;
        while (condition) {
            try {
                System.out.println("Pseudo: ");
                String pseudo = scanner.next();
                int index = CSVHandler.findOccurrenceIndex(getAcheteurPath(),pseudo,2);
                if (index == -1) {
                    throw new IllegalArgumentException("votre compte n'est pas trouver dans le systeme. " +
                            "entre de nouveau votre pseudo");
                }

                System.out.println("Courriel: ");
                String courriel = scanner.next();
                Boolean valide = CSVHandler.isValueAtIndexAndColumn(getAcheteurPath(), index-1, 3, courriel );
                if (!valide) {
                    throw new IllegalArgumentException("vous avez saisi le mauvais couriel, rapplez que ca termine par" +
                            " @gamil.com ou @umontreal.ca");
                }

                condition = false;

                new ProfilAcheteur(pseudo,courriel);
            } catch (Exception e) {
                System.out.println("Erreur: " + e.getMessage());
                scanner.nextLine();
            }
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



