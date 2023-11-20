import java.util.*;

import java.util.Scanner;

public class Histoire {

    private static Scanner scanner = new Scanner(System.in);




    public static void pageHistoire() {

        try {
            while (true) {

                System.out.println("\nVoici vos commandes anterieurs:");
                System.out.println("---------------------------");
                System.out.println("1,Livre1,13nov2012,35$");
                System.out.println("2,Livre2,3nov2012,135$");
                System.out.println("3,Souris1,15dec2012,325$");
                System.out.println("4,Clavier1,15oct2013,100$");
                System.out.println("5,livre3,13jan2014,45$");
                System.out.println("6,Calculatrice,5nov2014,25$");
                System.out.println("7,PC,10nov2023,1335$");
                System.out.println("0. quitter");

                System.out.print("Entrez un chiffre pour regarder en detaille une commande : ");
                int choix = scanner.nextInt();

                switch (choix) {
                    case 1:
                        System.out.println("On peut seulement visualiser les commandes des 6 dernieres annees");
                        break;
                    case 2:
                        System.out.println("On peut seulement visualiser les commandes des 6 dernieres annees");
                        break;
                    case 3:
                        System.out.println("On peut seulement visualiser les commandes des 6 dernieres annees");
                        break;
                    case 4:
                        System.out.println("On peut seulement visualiser les commandes des 6 dernieres annees");
                        break;
                    case 5:
                        System.out.println("On peut seulement visualiser les commandes des 6 dernieres annees");
                        break;
                    case 6:
                        System.out.println("On peut seulement visualiser les commandes des 6 dernieres annees");
                        break;
                    case 7:
                        gestionCommandes();
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



    /**
     * Methode pour permettre aux acheteurs de gerer leurs commandes.
     */
    public static void gestionCommandes() {
        try {
            System.out.println("----- Gestion de Commandes -----");

            // Recuperer l'identifiant de la commande
            String numeroCommande = "1231231";

            // Afficher les details de la commande
            afficherDetailsCommande(numeroCommande);

            // Options de gestion de commande
            System.out.println("\nOptions de Gestion de Commande :");
            System.out.println("1. Suivre la Commande");
            System.out.println("2. Effectuer un Retour ou Echange");
            System.out.println("3. Annuler la Commande");
            System.out.println("4. Confirmer la reception");
            System.out.println("0. Retourner au Menu Principal");

            System.out.print("\nChoix : ");
            int choix = scanner.nextInt();

            switch (choix) {
                case 1: suivreCommande(); break;
                case 2: retourOuEchange(); break;
                case 3: annulerCommande(); break;
                case 4: confirmerReception(); break;
                case 0: System.out.println("Retour au Menu precedent"); break;
                default: System.out.println("Choix invalide. Veuillez entrer un nombre entre 0 et 4.");
            }
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
            scanner.nextLine(); // Effacer la ligne incorrecte dans le scanner
        }
    }

    /**
     * Affiche les details de la commande en fonction de son identifiant.
     *
     * @param numeroCommande Identifiant de la commande.
     */
    private static void afficherDetailsCommande(String numeroCommande) {
        System.out.println("\nDetails de la Commande " + numeroCommande + " :");
        System.out.println("Etat de la Commande : En Livraison");
        System.out.println("Date d'Arrivee Estimee : 12/20/2023");
        System.out.println("Produits Commandes : PC");
    }

    /**
     * Permet a l'acheteur de suivre l'etat de sa commande.
     */
    private static void suivreCommande() {
        System.out.println("Suivi de la Commande en Temps Reel : En Livraison");
        System.out.println("Arrivee Estimee : 12/20/2023");
    }

    /**
     * Permet a l'acheteur d'effectuer un retour ou un echange pour les produits de la commande.
     */
    private static void retourOuEchange() {
        System.out.println("----- Retour ou Echange -----");
        System.out.println("Veuillez indiquer le motif du retour ou de l'echange :");
        scanner.nextLine();
        String motif = scanner.nextLine();
        System.out.println("Demande de Retour ou Echange enregistree. Motif : " + motif);
    }

    /**
     * Permet a l'acheteur d'annuler la commande.
     */
    private static void annulerCommande() {
        System.out.println("----- Annulation de Commande -----");
        System.out.println("Veuillez confirmer l'annulation de la commande (Oui/Non) :");
        String confirmation = scanner.next().toLowerCase();

        if (confirmation.equals("Oui")) {
            System.out.println("Commande Annulee avec Succes !");
        } else {
            System.out.println("Annulation de Commande Annulee.");
        }
    }








    /**
     * Methode pour permettre a l'acheteur de confirmer la reception de sa commande.
     */
    public static void confirmerReception() {
        try {
            System.out.println("----- Confirmation de Reception -----");

            // Recuperer l'identifiant de la commande (simule ici)
            String numeroCommande = "1231231";

            // Afficher les details de la commande
            afficherDetailsCommande(numeroCommande);

            // Demander la confirmation de reception
            System.out.println("\nVeuillez confirmer la reception de la commande (Oui/Non) :");
            String confirmation = scanner.next().toLowerCase();

            if (confirmation.equals("oui")) {

                mettreAJourEtatLivraison(numeroCommande);
                System.out.println("Reception Confirmee avec Succes !");
            } else {
                System.out.println("Confirmation de Reception Annulee.");
            }
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
            scanner.nextLine();
        }
    }


    /**
     * Met a jour l'etat de la commande a "livre".
     *
     * @param numeroCommande Identifiant de la commande.
     */
    private static void mettreAJourEtatLivraison(String numeroCommande) {
        System.out.println("Etat de la Commande Mis a Jour : Livre");
    }

}

