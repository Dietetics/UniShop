import java.util.InputMismatchException;
import java.util.Scanner;

public class Connexion {


    private static Scanner scanner = new Scanner(System.in);



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
                int index = CSVHandler.findOccurrenceIndex(DatabasePath.getRevendeurPath(),nom,0);
                if (index == -1) {
                    throw new IllegalArgumentException("votre compte n'est pas trouver dans le systeme. " +
                            "entre de nouveau votre nom");
                }

                System.out.println("Courriel: ");
                String courriel = scanner.next();
                Boolean valide = CSVHandler.isValueAtIndexAndColumn(DatabasePath.getRevendeurPath(), index-1, 2, courriel );
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
                int index = CSVHandler.findOccurrenceIndex(DatabasePath.getAcheteurPath(),pseudo,2);
                if (index == -1) {
                    throw new IllegalArgumentException("votre compte n'est pas trouver dans le systeme. " +
                            "entre de nouveau votre pseudo");
                }

                System.out.println("Courriel: ");
                String courriel = scanner.next();
                Boolean valide = CSVHandler.isValueAtIndexAndColumn(DatabasePath.getAcheteurPath(), index-1, 3, courriel );
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


}
