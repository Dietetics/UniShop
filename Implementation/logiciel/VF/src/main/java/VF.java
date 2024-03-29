import java.util.*;

public class VF {

    static myScanner myScanner = new myScanner();

    public static void main(String[] args) {

        // verifier si les files de databases existent ou non
        DatabasePath.checkDatabaseFiles();
        Database.refreshTous();
        displayMenuPrincipale();
    }

    /**
     * Affiche le menu principal de l'application UniShop, permettant à l'utilisateur de choisir parmi
     * différentes options telles que rechercher un produit, s'inscrire, se connecter, ou quitter l'application.
     * La méthode utilise une boucle interactive pour gérer les choix de l'utilisateur.
     *
     */
    public static void displayMenuPrincipale() {
        int choix = 90;
        while (choix != 0) {
            try {
                msg();
                System.out.print("Choix : ");
                choix = myScanner.getIntInput();

                switch (choix) {
                    case 0: System.out.println("Au revoir !");
                    System.exit(choix);
                        break;
                    case 1: RecherchePublic.display(); break;
                    case 2: Inscription.inscription(); break;
                    case 3: Connexion.connecter(); break;
                    default:
                        System.out.println("Choix invalide. Veuillez reessayer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer un nombre entier.");
            }
        }
    }

    public static void msg(){
        System.out.println("\n");
        System.out.println("------ Bienvenu a notre plateforme UniShop -------");
        System.out.println("0. Quitter");
        System.out.println("1. Recherche");
        System.out.println("2. Inscription");
        System.out.println("3. Connexion");
        System.out.print("\n");
    }



















}



