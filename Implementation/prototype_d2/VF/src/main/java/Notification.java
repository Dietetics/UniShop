import java.util.List;

public class Notification {


    public Notification() {
    }

    public static void recevoirNotifications() {
        System.out.println("----- Nouvelles Notifications -----");

        // Simulation d'evenements declencheurs de notifications
        simulerNouveauSuiveur();
        simulerNouveauProduitMarche();
        simulerNouvellePromotion();
        simulerChangementEtatCommande();
        simulerPromotionProduitLike();
        simulerSolutionProposee();

        // Affichage des notifications recues
        afficherNotifications();
    }

    private static void simulerNouveauSuiveur() {
        System.out.println("Notification: Nouvel acheteur a commence a suivre votre profil.");
    }

    private static void simulerNouveauProduitMarche() {
        System.out.println("Notification: Revendeur like a mis au marche un nouveau produit.");
    }

    private static void simulerNouvellePromotion() {
        System.out.println("Notification: Nouvelle promotion sur un produit ou un revendeur like.");
    }

    private static void simulerChangementEtatCommande() {
        System.out.println("Notification: L'etat d'une commande, d'un retour ou d'un echange a change.");
    }

    private static void simulerPromotionProduitLike() {
        System.out.println("Notification: Promotion sur un produit like par vous ou vos suiveurs.");
    }

    private static void simulerSolutionProposee() {
        System.out.println("Notification: Revendeur a propose une solution sur un produit signale.");
    }

    private static void afficherNotifications() {
        List<String> notifications = recupererNotifications();
        System.out.println("\n----- Notifications Recues -----");
        for (String notification : notifications) {
            System.out.println(notification);
        }
    }

    // Simulation de recuperation des notifications (a remplacer par votre logique reelle)
    private static List<String> recupererNotifications() {
        return List.of("Notification 1", "Notification 2", "Notification 3");
    }
}
