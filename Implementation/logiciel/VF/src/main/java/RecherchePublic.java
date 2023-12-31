import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class RecherchePublic {

    public static void display() {
        String choix = "90";
        while (choix != "0") {
            try {
                displayMessage();
                System.out.print("Selectionnez un chiffre: ");
                int input = myScanner.getIntInput();

                switch (input) {
                    case 0:
                        return;
                    case 1:
                        Recherche.recuperer(DatabasePath.getPathTousProduits(), 0);
                        decisionProduit(DatabasePath.getPathTousProduits(),0);
                        break;
                    case 2:
                        Recherche.recuperer(DatabasePath.getPathTousAcheteurs(), 0);
                        decisionAcheteur(DatabasePath.getPathTousAcheteurs(),0);
                        break;
                    case 3:
                        Recherche.recuperer(DatabasePath.getPathTousRevendeurs(), 0);
                        decisionRevendeur(DatabasePath.getPathTousRevendeurs(),0);
                        break;
                    case 4:
                        Recherche.rechercheProduits();
                        break;
                    case 5:
                        Recherche.rechercheAcheteurs();
                        break;
                    case 6:
                        Recherche.rechercheRevendeurs();
                        break;
                    default:
                        System.out.println("Choix invalide. Veuillez reessayer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer un nombre entier.");
            }
        }
    }
    public static void displayMessage(){
        System.out.println("\n");
        System.out.println("------ Bienvenu a notre section de recherche -------");
        System.out.println("0. Quitter");
        System.out.println("-- Recuperer la liste ... pour voir plus d informations --");
        System.out.println("    1. des produits");
        System.out.println("    2. des acheteurs");
        System.out.println("    3. des revendeurs");
        System.out.println("-- Rechercher ........... par mot-cle et filtrer        --");
        System.out.println("    4. un produit");
        System.out.println("    5. un acheteur");
        System.out.println("    6. un revendeur");
        System.out.print("\n");
    }
    public static void decisionProduit(String path, int colonne) {
        String decision;
        Boolean boucle = true;
        while (boucle) {
            System.out.println("\n");
            System.out.println("0 pour retourner ou taper le nom en demande pour voir plus d'information");
            System.out.println("---------------------------------------------------");
            System.out.print("Reponse: ");
            decision = myScanner.getStringInput();

            if ("0".equals(decision)) {
                System.out.println("Retour");
                break;  // Sortir de la boucle while
            } else if (CSVHandler.findOccurrenceIndex(path, decision, colonne) != -1) {
                ProfilProduit produit = new ProfilProduit(decision);
                produit.display();
                boucle = false;
            } else {
                System.out.println("Ce n'est pas un choix, veuillez entrer de nouveau \n\n\n");
            }
        }
    }

    public static void decisionAcheteur(String path, int colonne) {
        String decision;
        Boolean boucle = true;
        while (boucle) {
            System.out.println("\n");
            System.out.println("0 pour retourner ou taper le pseudo en demande pour voir plus d'information");
            System.out.println("---------------------------------------------------");
            System.out.print("Reponse: ");
            decision = myScanner.getStringInput();

            if ("0".equals(decision)) {
                System.out.println("Retour");
                break;  // Sortir de la boucle while
            } else if (CSVHandler.findOccurrenceIndex(path, decision, colonne) != -1) {

                String data = CSVHandler.readLineByIndex(path,CSVHandler.findOccurrenceIndex(path, decision, colonne));
                String pseudo = CSVHandler.getColumnValue(data, 0);
                String password = CSVHandler.getColumnValue(data, 1);
                ProfilAcheteur acheteur = new ProfilAcheteur(pseudo);
                acheteur.displayInfoAuPublic();

                boucle = false;
            } else {
                System.out.println("Ce n'est pas un choix, veuillez entrer de nouveau \n\n\n");
            }
        }
    }

    public static void decisionRevendeur(String path, int colonne) {
        String decision;
        Boolean boucle = true;
        while (boucle) {
            System.out.println("\n");
            System.out.println("0 pour retourner ou taper le nom en demande pour voir plus d'information");
            System.out.println("---------------------------------------------------");
            System.out.print("Reponse: ");
            decision = myScanner.getStringInput();

            if ("0".equals(decision)) {
                System.out.println("Retour");
                break;  // Sortir de la boucle while
            } else if (CSVHandler.findOccurrenceIndex(path, decision, colonne) != -1) {

                String data = CSVHandler.readLineByIndex(path,CSVHandler.findOccurrenceIndex(path, decision, colonne));
                String nom = CSVHandler.getColumnValue(data, 0);
                String password = CSVHandler.getColumnValue(data, 1);
                ProfilRevendeur revendeur = new ProfilRevendeur(nom);
                revendeur.displayInfoAuPublic();

                boucle = false;
            } else {
                System.out.println("Ce n'est pas un choix, veuillez entrer de nouveau \n\n\n");
            }
        }
    }



}
