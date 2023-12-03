import java.util.InputMismatchException;
import java.util.Scanner;

public class Connexion {




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



        boolean quitter = false;

        while (!quitter) {
            try {
                System.out.print("Reponse: ");
                String keyword = myScanner.getStringInput();

                switch (keyword.toLowerCase()) {
                    case ":q": quitter = true; break;
                    case ":a": connectAcheteur(); break;
                    case ":r": connectRevendeur(); break;

                    default: System.out.println("Choix invalide. Veuillez entrer :a, :r ou :q."); break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur de saisie. Veuillez entrer une option valide.");
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
                System.out.print("Nom: ");
                String nom = myScanner.getStringInput();
                int index = CSVHandler.findOccurrenceIndex(DatabasePath.getRevendeurPath(),nom,0);
                if (index == -1) {
                    throw new IllegalArgumentException("votre compte n'est pas trouver dans le systeme. " +
                            "entre de nouveau votre nom");
                }

                System.out.print("Password: ");
                String password = myScanner.getStringInput();
                Boolean valide = CSVHandler.isValueAtIndexAndColumn(DatabasePath.getRevendeurPath(), index-1, 1, password );
                if (!valide) {
                    throw new IllegalArgumentException("vous avez saisi le mauvais password");
                }

                condition = false;

                new ProfilRevendeur(nom,password);
            } catch (Exception e) {
                System.out.println("Erreur: " + e.getMessage());
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
                System.out.print("Pseudo: ");
                String pseudo = myScanner.getStringInput();
                int index = CSVHandler.findOccurrenceIndex(DatabasePath.getAcheteurPath(),pseudo,0);
                if (index == -1) {
                    throw new IllegalArgumentException("votre compte n'est pas trouver dans le systeme. " +
                            "entre de nouveau votre pseudo");
                }

                System.out.print("Password: ");
                String password = myScanner.getStringInput();
                Boolean valide = CSVHandler.isValueAtIndexAndColumn(DatabasePath.getAcheteurPath(), index-1, 1, password );
                if (!valide) {
                    throw new IllegalArgumentException("vous avez saisi le mauvais password");
                }

                condition = false;

                System.out.println("passer");

                new ProfilAcheteur(pseudo,password);
            } catch (Exception e) {
                System.out.println("Erreur: " + e.getMessage());
            }
        }
    }


}
