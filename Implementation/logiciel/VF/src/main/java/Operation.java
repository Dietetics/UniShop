import java.io.IOException;
import java.util.List;

public class Operation {


    // ajouter dans liker produit ssi elle nest pas encore like et ajouter 1 dans le profil du produit
    public static void acheteurLikerProduit(String produit, String auteur) {
        String pathAcheteur = DatabasePath.getPathAcheteurCompte() + auteur + "/likerProduit.csv";
        String pathProduit = DatabasePath.getPathProduitCompte() + produit + "/likes.csv";

        try {
            Boolean condition = CSVHandler.isExiste(pathAcheteur, produit);

            if (!condition) {
                CSVHandler.appendCSV(pathAcheteur, produit);
                CSVHandler.appendCSV(pathProduit, auteur);
                System.out.println("Produit like avec succss.");
                Notification.notificationLiker(auteur,produit);

                ProfilProduit unProduit = new ProfilProduit(produit);
                int likes = unProduit.getNbLikes();
                likes = likes +1;
                unProduit.setNbLikes(likes);
                unProduit.modified();

            } else {
                System.out.println("Vous avez dejà like ce produit.");
            }
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite : " + e.getMessage());
        }
    }

    public static void acheteurCommandeProduit(String produit, String auteur) {
        String path = DatabasePath.getPathAcheteurCompte() + auteur + "/panier.csv";

        try {
            Boolean condition = CSVHandler.isExiste(path, produit);

            if (!condition) {
                CSVHandler.appendCSV(path, produit);
                System.out.println("Produit ajouter avec succss.");
                Notification.notificationCommande(auteur,produit);
            } else {
                System.out.println("Vous avez ce produit dans le panier.");
            }
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite : " + e.getMessage());
        }
    }







    public static void acheteurSuivreAcheteur(String acheteur, String auteur) {
        String path = DatabasePath.getPathAcheteurCompte() + auteur + "/suivreAcheteur.csv";

        try {
            Boolean condition = CSVHandler.isExiste(path, acheteur);

            // si pas deja dedans et pas la meme pers
            if (!condition && auteur != acheteur) {
                CSVHandler.appendCSV(path, acheteur);

                String ciblePath = DatabasePath.getPathAcheteurCompte() + acheteur + "/suiviPar.csv";
                CSVHandler.appendCSV(ciblePath, auteur);
                System.out.println("Acheteur suivi avec succss.");
                Notification.notificationSuivre(auteur,acheteur);
            } else {
                System.out.println("Vous avez deja suivi cet acheteur.");
            }
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite : " + e.getMessage());
        }
    }




    public static void acheteurLikerRevendeur(String revendeur, String auteur) {
        String pathAuteur = DatabasePath.getPathAcheteurCompte() + auteur + "/likerRevendeur.csv";
        String pathRevendeur = DatabasePath.getPathRevendeurCompte() + revendeur + "/likerPar.csv";

        try {
            Boolean condition = CSVHandler.isExiste(pathAuteur, revendeur);

            if (!condition) {
                CSVHandler.appendCSV(pathAuteur, revendeur);
                System.out.println("Revendeur like avec succss.");
                Notification.notificationLikerRevendeur(auteur,revendeur);

                ProfilRevendeur unRevendeur = new ProfilRevendeur(revendeur);
                int likes = unRevendeur.getNbLikerPar();
                likes = likes +1;
                unRevendeur.setNbLikerPar(likes);
                unRevendeur.modified();


            } else {
                System.out.println("Vous avez deja like ce revendeur.");
            }
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite : " + e.getMessage());
        }
    }

    public static void acheteurSuivreRevendeur(String revendeur, String auteur) {
        String path = DatabasePath.getPathAcheteurCompte() + auteur + "/suivreRevendeur.csv";

        try {
            Boolean condition = CSVHandler.isExiste(path, revendeur);

            if (!condition) {
                CSVHandler.appendCSV(path, revendeur);
                System.out.println("Revendeur suivi avec succss.");
                Notification.notificationSuivreRevendeur(auteur,revendeur);

            } else {
                System.out.println("Vous avez deja suivi ce revendeur.");
            }
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite : " + e.getMessage());
        }
    }







}
