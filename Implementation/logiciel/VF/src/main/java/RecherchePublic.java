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
                        Recherche.recuperer(DatabasePath.getProduitPath(), 0);
                        decisionProduit(DatabasePath.getProduitPath(),0);
                        choix = "0";
                        break;
                    case 2:
                        Recherche.recuperer(DatabasePath.getAcheteurPath(), 0);
                        decisionAcheteur(DatabasePath.getAcheteurPath(),0);
                        choix = "0";
                        break;
                    case 3:
                        Recherche.recuperer(DatabasePath.getRevendeurPath(), 0);
                        choix = "0";
                        decisionRevendeur(DatabasePath.getRevendeurPath(),0);
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
        System.out.println("Recuperer la liste ... pour voir plus d informations");
        System.out.println("    1. des produits");
        System.out.println("    2. des acheteurs");
        System.out.println("    3. des revendeurs");
        System.out.println("Rechercher ... par mot-cle et filtrer");
        System.out.println("    4. un produit");
        System.out.println("    5. un acheteur");
        System.out.println("    6. un revendeur");
        System.out.print("\n");
    }
    public static String decisionProduit(String path, int colonne) {
        System.out.println("\n");
        System.out.println("0 pour retourner ou taper le nom en demande pour voir plus d information");
        System.out.println("---------------------------------------------------");
        System.out.print("Reponse: ");
        String decision = myScanner.getStringInput();

        if (decision == "0") return "0";
        else if (CSVHandler.findOccurrenceIndex(path, decision, colonne) != -1){
            InfoProduit.voirProduit(path, decision, colonne);
        }else System.out.println("ce n'est pas un choix, veuillez entrer de nouveau \n\n\n");
        return "1";
    }

    public static String decisionAcheteur(String path, int colonne) {
        System.out.println("\n");
        System.out.println("0 pour retourner ou taper le pseudo en demande pour voir plus d information");
        System.out.println("---------------------------------------------------");
        System.out.print("Reponse: ");
        String decision = myScanner.getStringInput();

        if (decision == "0") return "0";
        else if (CSVHandler.findOccurrenceIndex(path, decision, colonne) != -1){
            InfoAcheteur.voirProfil(path, decision, colonne);
        }else System.out.println("ce n'est pas un choix, veuillez entrer de nouveau \n\n\n");
        return "1";
    }

    public static String decisionRevendeur(String path, int colonne) {
        System.out.println("\n");
        System.out.println("0 pour retourner ou taper le nom en demande pour voir plus d information");
        System.out.println("---------------------------------------------------");
        System.out.print("Reponse: ");
        String decision = myScanner.getStringInput();

        if (decision == "0") return "0";
        else if (CSVHandler.findOccurrenceIndex(path, decision, colonne) != -1){
            InfoRevendeur.voirProfil(path, decision, colonne);
        }else System.out.println("ce n'est pas un choix, veuillez entrer de nouveau \n\n\n");
        return "1";
    }




















}
