import java.util.*;

public class PanierAchat {

    private static String acheteur;
    private static String pathPanier;
    private static String pathPts;
    private static String pathHistoire;
    private static String pathNotifications;
    private static int pts;

    public PanierAchat(String acheteur) {

        this.acheteur = acheteur;
        this.pathPanier = DatabasePath.getPathAcheteurCompte() + acheteur + "/panier.csv";
        this.pathPts = DatabasePath.getPathAcheteurCompte() + acheteur + "/points.csv";
        this.pathNotifications = DatabasePath.getPathAcheteurCompte() + acheteur + "/notifications.csv";
        this.pathHistoire = DatabasePath.getPathAcheteurCompte() + acheteur + "/histoire.csv";
    }

    public static void menu() {

        try {
            Boolean condition = true;
            while (condition) {
                try {
                    msg();
                    int choix = myScanner.getIntInput();

                    switch (choix) {
                        case 2:
                            retirer();
                            break;
                        case 3:
                            afficherPanier();
                            break;
                        case 4:
                            recap();
                            break;
                        case 5:
                            confirmePasserCommande();
                            break;
                        case 6:
                            viderPanier(getPathPanier());
                            break;
                        case 0:
                            return;
                        default:
                            System.out.println("Choix invalide. Veuillez réessayer.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Erreur : Veuillez entrer un nombre entier.");
                } catch (Exception e) {
                    System.out.println("Erreur: " + e.getMessage());
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Erreur : Veuillez entrer un nombre entier.");
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    public static void msg() {
        System.out.println("\nMenu du Panier:");
        System.out.println("--------------------------------");
        System.out.println("X. Ajouter un produit (a partir de recherche) ");
        System.out.println("2. Retirer un produit");
        System.out.println("3. Afficher le panier");
        System.out.println("4. Recap du panier");
        System.out.println("5. Passer une commande");
        System.out.println("6. vider panier");
        System.out.println("0. quitter");

        System.out.print("Votre choix : ");
    }




    public static void retirer() {

        System.out.println("\nVeuillez entrer le nom du produit a retirer");
        String produit = myScanner.getStringInput();
        try {
            Boolean condition = CSVHandler.isExiste(getPathPanier(), produit);

            if (condition) {
                int index = CSVHandler.findOccurrenceIndex(getPathPanier(),produit,0);
                CSVHandler.removeLineFromCSV(getPathPanier(), index-1);
                System.out.println("Produit est retirer avec succss.");
            } else {
                System.out.println("Vous n'avez pas ce produit dans le panier.");
            }
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite : " + e.getMessage());
        }
    }
    public static void afficherPanier() {
        System.out.println("\nVoici les produits dans votre panier d'achat");
        System.out.println("----------------------------------------------");
        CSVHandler.printCSV(CSVHandler.readCSV(getPathPanier(),9999));
        System.out.println("----------------------------------------------");
    }



    public static void recap(){
        System.out.println("\n\n\nVu Black friday, grand liquidation!!! tous les produits sont a 5$ et chaques produits donnent 2points!!!");
        afficherPanier();
        int nbArticles = CSVHandler.countLines(getPathPanier());
        int prix = nbArticles *5;
        int pts = nbArticles *2;
        setPts(pts);
        System.out.println("Apres nos calculs, le prix total est "+prix + "$");
        System.out.println("Apres nos calculs, le nombre de points total est "+pts);
    }

    public static void confirmePasserCommande(){
        recap();
        System.out.print("\nConfirmez-vous la commande ? (1 pour confirmer, tout autre pour annuler) : ");
        int choix = myScanner.getIntInput();

        switch (choix) {
            case 1:
                String msg = "Veuillez entrer les informations de votre carte de crédit(un cvv est de longueur de trois)";
                InputRestreint.getValidCredit(msg);
                passerCommande();
                break;
            default:
                System.out.println("Commande annulée.");
                break;
        }
    }


    public static void passerCommande(){
        System.out.println("Commande confirmee ! Merci pour votre achat.");

        String pts = String.valueOf(getPts());


        List<String[]> dataList = CSVHandler.readCSV(getPathPanier(),9999);

        for (String[] row : dataList) {
            for (String value : row) {
                ProfilProduit produit = new ProfilProduit(value);
                String revendeur = produit.getOfferParUnitaire();
                String temp = getAcheteur()+","+value+",enProduction";
                String pathAchat = DatabasePath.getPathRevendeurCompte() + revendeur + "/achats.csv";
                String pathNotifications = DatabasePath.getPathRevendeurCompte() + revendeur + "/notifications.csv";
                CSVHandler.appendCSV(pathAchat,temp);
                CSVHandler.appendCSV(pathNotifications,"Un unite de votre produit offer est vendu!");
            }
        }


        CSVHandler.transfereCSVEnproduction(getPathPanier(),getPathHistoire());

        CSVHandler.appendCSV(getPathPts(),pts);
        CSVHandler.appendCSV(getPathNotifications(),"Merci davoir magasiner chez UniShop");
        viderPanier(getPathPanier());
    }


    public static void viderPanier(String path){
        CSVHandler.clearCSV(path);
        System.out.println("Le panier a ete vide avec succes.");
    }






    public static String getAcheteur() {
        return acheteur;
    }

    public static String getPathPanier() {
        return pathPanier;
    }

    public static int getPts() {
        return pts;
    }

    public static void setPts(int pts) {
        PanierAchat.pts = pts;
    }

    public static String getPathPts() {
        return pathPts;
    }

    public static String getPathHistoire() {
        return pathHistoire;
    }

    public static String getPathNotifications() {
        return pathNotifications;
    }
}
