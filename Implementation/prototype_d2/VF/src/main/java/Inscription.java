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
            String nom = getValidInput("Veuillez entrer votre nom: ");
            String prenom = getValidInput("Veuillez entrer votre prenom: ");
            String adresse = getValidAdresse("Veuillez entrer votre adresse d'expedition: ");
            String courriel = getValidCourriel("Veuillez entrer votre adresse courriel(unique): ");
            String telephone = getValidTelephone("Veuillez entrer votre telephone: ");
            String pseudo = getValidPseudo("Veuillez entrer votre pseudo(unique): ");
            String password = getValidPassword("Veuillez entrer votre password: ");

            saveUserData(nom, prenom, adresse, courriel, telephone, pseudo, password);

            System.out.println("Donnees enregistrees avec succes");
            System.out.println("--------------------------------");

        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
            scanner.nextLine();
        }
    }




    private static String getValidInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.next();

                if (!InputRestreint.isValidInput(input)) {
                    throw new IllegalArgumentException("Le nom et prenom doivent contenir au moins 2 caracteres alphabetiques.");
                }

                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private static String getValidAdresse(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.next();

                if (!InputRestreint.isValidAddress(input)) {
                    throw new IllegalArgumentException("adresse doit avoir un longueur inferieur a 20");
                }

                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private static String getValidCourriel(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.next();

                if (!InputRestreint.isValidCourriel(input)) {
                    throw new IllegalArgumentException("Le courriel doit terminer par @gamil.com ou @umontreal.ca");
                }

                if (!InputRestreint.isUnique(DatabasePath.getAcheteurPath(),input,3)) {
                    throw new IllegalArgumentException("Ce courriel est deja utillise, veuillez entre un nouveau");
                }

                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur: " + e.getMessage());
                scanner.nextLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static String getValidTelephone(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.next();

                if (!InputRestreint.isValidTelephone(input)) {
                    throw new IllegalArgumentException("Le telephone doit etre composer de 10 chiffres");
                }

                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }


    private static String getValidPseudo(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.next();

                if (!InputRestreint.isValidPseudo(DatabasePath.getAcheteurPath(),input,2)) {
                    throw new IllegalArgumentException("votre pseudo est pris, veuillez entre un nouveau");
                }

                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur: " + e.getMessage());
                scanner.nextLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private static String getValidPassword(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.next();

                if (!InputRestreint.isValidPass(input)) {
                    throw new IllegalArgumentException("votre paseword doit contenir au moins 8 chars, 1 majuscule, 1 minuscule, 1 chiffre, 1char special");
                }

                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }





    private static void saveUserData(String nom, String prenom, String adresse, String courriel, String telephone, String pseudo, String password) {
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


                if (!InputRestreint.isValidUniqueRow(DatabasePath.getRevendeurPath(), nom, 0)) {
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

                String directoryPath = DatabasePath.getRevendeurComptePath() + nom + "/";

                // Vérifier si le répertoire existe, sinon le créer
                createDirectory(directoryPath);

                List<String[]> userData = new ArrayList<>();
                userData.add(new String[]{nom, adresse, courriel, telephone});

                String revendeurCompte = directoryPath + nom + ".csv";
                String revendeurResolution = directoryPath + "resolution.csv";

                CSVHandler.appendCSV(DatabasePath.getRevendeurPath(), userData);
                CSVHandler.coverCSV(revendeurCompte,userData);
                CSVHandler.coverCSV(revendeurResolution,stringArrayList);

                break;
            } catch (Exception e) {
                System.out.println("Erreur: " + e.getMessage());
                scanner.nextLine();
            }
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
