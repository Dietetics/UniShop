import java.util.InputMismatchException;
import java.util.Scanner;

public class GestionCommande {

    private static Scanner scanner = new Scanner(System.in);

    public GestionCommande() {
    }


    public static void GererCommandes() {

        try {
            while (true) {
                System.out.println("\nPage Commandes:");
                System.out.println("---------------------------");
                System.out.println("Voici vos produits vendues:");
                System.out.println("1,Livre1,13nov2012,35$");
                System.out.println("2,Livre2,3nov2012,135$");
                System.out.println("3,Souris1,15dec2012,325$");
                System.out.println("4,Clavier1,15oct2013,100$");
                System.out.println("5,livre3,13jan2014,45$");
                System.out.println("6,Calculatrice,5nov2014,25$");
                System.out.println("7,PC,10nov2023,1335$");
                System.out.println("0. quitter");

                System.out.print("Entrez un chiffre pour gerer une commande : ");
                int choix = scanner.nextInt();

                switch (choix) {
                    case 1:
                        System.out.println("Ce produit est deja livre");
                        break;
                    case 2:
                        System.out.println("Ce produit est deja livre");
                        break;
                    case 3:
                        System.out.println("Ce produit est deja livre");
                        break;
                    case 4:
                        System.out.println("Ce produit est deja livre");
                        break;
                    case 5:
                        System.out.println("Ce produit est deja livre");
                        break;
                    case 6:
                        System.out.println("Ce produit est deja livre");
                        break;
                    case 7:
                        option();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Choix invalide. Veuillez réessayer.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Erreur : Veuillez entrer un nombre entier.");
            scanner.nextLine(); // Effacer la ligne incorrecte dans le scanner
        }
    }

    public static void option() {
        try {
            while (true) {
                System.out.println("\nVous avez selectionner le produit: PC,10nov2023,1335$:");
                System.out.println("---------------------------");
                System.out.println("Voici vos choix:");
                System.out.println("1, changer etat dune commande");
                System.out.println("2, confirmer la reception dun retour ");
                System.out.println("0. quitter");

                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        changerEtatCommande();
                        break;
                    case 2:
                        confirmerReceptionRetour();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Choix invalide. Veuillez réessayer.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Erreur : Veuillez entrer un nombre entier.");
            scanner.nextLine(); // Effacer la ligne incorrecte dans le scanner
        }
    }





    public static void changerEtatCommande() {
        System.out.println("L'etat de la commande a ete ajuster au etape superieure");
    }

    





    public static void confirmerReceptionRetour() {
        try {
            System.out.println("----- Confirmation de Reception d'un Retour -----");

            // Simulation du revendeur recevant un retour
            System.out.println("Revendeur: Un retour de commande est arrive a l'entrepot.");

            // Simulation du revendeur confirmant la reception du retour
            System.out.print("Revendeur: Confirmez-vous la reception du retour ? (Oui/Non): ");
            String confirmation = scanner.next();

            if (confirmation.equalsIgnoreCase("Oui")) {
                System.out.println("Revendeur: Le retour a ete confirme comme livre.");
            } else if (confirmation.equalsIgnoreCase("Non")) {
                System.out.println("Revendeur: Le retour n'a pas ete confirme comme livre.");
            } else {
                throw new IllegalArgumentException("Reponse invalide. Veuillez repondre par 'Oui' ou 'Non'.");
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la confirmation de reception du retour: " + e.getMessage());
        }
    }








}
