import java.util.Scanner;

public class SignalementProbleme {


    private static Scanner scanner = new Scanner(System.in);





    public SignalementProbleme() {
    }


    /**
     * Methode pour permettre a l'acheteur de signaler un probleme avec un produit.
     */
    public static void signalerProblemeProduit() {
        try {
            System.out.println("Nous ne dedectons que vous avez un produit dans les 6 dernieres annees");
            System.out.println("Nous ne gerons pas les cas plus de six ans");
            System.out.println("Vu que vous avez que un produit, elle est choisie par defaut pour signaler des problemes");

            // Recuperer l'identifiant de la commande
            String numeroCommande = "1231231";

            // Afficher les details de la commande
            afficherDetailsCommande(numeroCommande);

            System.out.println("\nVoici les infos, entrez quelque chose pour continuer :");
            scanner.nextLine();

            // Demander la description du probleme par l'acheteur
            System.out.println("\nVeuillez entrer la description du probleme :");
            String descriptionProbleme = scanner.nextLine();

            // Afficher la description du probleme
            System.out.println("\nDescription du Probleme :");
            System.out.println(descriptionProbleme);

            // Proposer une solution
            String solutionProposee = "Reexpedier le produit defectueux a l'entrepot.";
            System.out.println("\nSolution Proposee par le Revendeur :");
            System.out.println(solutionProposee);

            // Demander la confirmation de livraison du produit defectueux par le revendeur
            System.out.println("\nConfirmez-vous la livraison du produit defectueux ? (Oui/Non) :");
            String confirmationLivraisonRevendeur = scanner.next().toLowerCase();

            if (confirmationLivraisonRevendeur.equals("oui")) {
                // Demander la description et le numero de suivi du produit de remplacement
                System.out.println("\nVeuillez entrer la description du produit de remplacement :");
                String descriptionProduitRemplacement = scanner.next();
                System.out.println("Veuillez entrer le numero de suivi du produit de remplacement :");
                String numeroSuiviProduitRemplacement = scanner.next();

                // Afficher la description et le numero de suivi du produit de remplacement
                System.out.println("\nDescription du Produit de Remplacement :");
                System.out.println(descriptionProduitRemplacement);
                System.out.println("Numero de Suivi du Produit de Remplacement : " + numeroSuiviProduitRemplacement);

                // Demander la confirmation de livraison du produit de remplacement par l'acheteur
                System.out.println("\nConfirmez-vous la livraison du produit de remplacement ? (Oui/Non) :");
                String confirmationLivraisonAcheteur = scanner.next().toLowerCase();

                if (confirmationLivraisonAcheteur.equals("oui")) {
                    System.out.println("Probleme Signale avec Succes et Resolu !");
                } else {
                    System.out.println("Confirmation de Livraison du Produit de Remplacement Annulee.");
                }
            } else {
                System.out.println("Confirmation de Livraison du Produit Defectueux Annulee.");
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
        System.out.println("Date d'Arrivee Estimee : 15/12/2024");
        System.out.println("Produits Commandes : PC");
    }

}
