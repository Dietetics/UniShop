import java.util.*;

public class PanierAchat {

    private static Scanner scanner = new Scanner(System.in);
    public PanierAchat() {
    }

    public static void panierAchat() {

        try {
            Boolean condition = true;
            while (condition) {
                System.out.println("\nMenu du Panier:");
                System.out.println("--------------------------------");
                System.out.println("1. Ajouter un produit");
                System.out.println("2. Retirer un produit");
                System.out.println("3. Afficher le panier");
                System.out.println("4. Recap du panier");
                System.out.println("5. Passer une commande");
                System.out.println("0. quitter");

                System.out.print("Votre choix : ");

                condition = optionPanier();

            }
        } catch (InputMismatchException e) {
            System.out.println("Erreur : Veuillez entrer un nombre entier.");
            scanner.nextLine(); // Effacer la ligne incorrecte dans le scanner
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
            scanner.nextLine();
        }
    }

    public static Boolean optionPanier() {
        try {
            List<String> panier = new ArrayList<>();
            double montantTotal = 0;
            int pointsTotal = 0;

            int choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    System.out.println("----- Ajouter un Produit -----");
                    System.out.println("Entrez le UUID du produit que vous voulez ajouter ainsi que ses informations");
                    int ajoutProduit = scanner.nextInt();
                    System.out.println("Le produit est bien enregistre au systeme");
                    break;
                case 2:
                    System.out.println("----- Retirer un Produit -----");
                    System.out.println("Entrez le UUID du produit que vous voulez retirer de votre panier");
                    int removeProduit = scanner.nextInt();
                    System.out.println("Le produit est bien retirer de votre panier");
                    break;
                case 3:
                    System.out.println("----- Panier Actuel -----");
                    afficherPanier(panier);
                    break;
                case 4:
                    System.out.println("----- Recap du panier -----");
                    afficherPanier(panier);
                    montantTotal = calculerMontantTotal(panier);
                    pointsTotal = calculerPointsTotal(montantTotal);
                    afficherRecapitulatif(montantTotal, pointsTotal);
                    break;
                case 5:
                    passerCommande();
                    break;
                case 0:
                    return false;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Erreur : Veuillez entrer un nombre entier.");
            scanner.nextLine(); // Effacer la ligne incorrecte dans le scanner
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
            scanner.nextLine();
        }
        return true;
    }

// Ajoutez des blocs try-catch similaires pour les méthodes appelées dans optionPanier



    /**
     * Affiche les produits actuels dans le panier.
     *
     * @param panier Liste des produits dans le panier.
     */
    private static void afficherPanier(List<String> panier) {
        if (panier.isEmpty()) {
            System.out.println("Le panier est vide.");
        } else {
            System.out.println("Produits dans le Panier :");
            for (String produit : panier) {
                System.out.println("- " + produit);
            }
        }
    }

    /**
     * Calcule le montant total des produits dans le panier.
     *
     * @param panier Liste des produits dans le panier.
     * @return Montant total.
     */
    private static double calculerMontantTotal(List<String> panier) {
        return 0;
    }

    /**
     * Calcule le nombre de points à accumuler pour l'achat.
     *
     * @param montantTotal Montant total de l'achat.
     * @return Nombre de points à accumuler.
     */
    private static int calculerPointsTotal(double montantTotal) {
        return 10;
    }

    /**
     * Affiche un récapitulatif de l'achat avec le montant total et les points accumulés.
     *
     * @param montantTotal Montant total de l'achat.
     * @param pointsTotal  Nombre de points accumulés.
     */
    private static void afficherRecapitulatif(double montantTotal, int pointsTotal) {
        System.out.println("Récapitulatif de l'Achat :");
        System.out.println("Montant Total : " + montantTotal);
        System.out.println("Points Accumulés : " + pointsTotal);
        System.out.println("Merci pour votre achat !");
    }














    /**
     * Methode pour permettre aux acheteurs de passer une commande.
     */
    public static void passerCommande() {
        try {
            System.out.println("----- Passer une Commande -----");

            // Recuperer les informations de l'acheteur
            String pseudoAcheteur = "auteur2";
            String adresseLivraison = "H3C3J7";
            String numeroTelephone = "5140003333";

            // Afficher les informations de l'acheteur
            System.out.println("Vos informations :");
            System.out.println("Pseudo : " + pseudoAcheteur);
            System.out.println("Adresse de Livraison : " + adresseLivraison);
            System.out.println("Numero de Telephone : " + numeroTelephone);

            // Selection des produits
            List<String> produitsSelectionnes = new ArrayList<>();
            produitsSelectionnes.add("Cahier1");
            produitsSelectionnes.add("OrdinateurPortable1");

            // Afficher les produits selectionnes
            System.out.println("\nProduits Selectionnes :");
            afficherProduits(produitsSelectionnes);

            // Calculer le montant total
            double montantTotal = calculerMontantTotal(produitsSelectionnes);

            // generer un identifiant unique pour la commande
            String numeroCommande = UUID.randomUUID().toString();

            // Afficher le recapitulatif de la commande
            System.out.println("\nRecapitulatif de la Commande :");
            System.out.println("Numero de Commande : " + numeroCommande);
            System.out.println("Montant Total : " + montantTotal);
            System.out.println("Adresse de Livraison : " + adresseLivraison);

            // metre a jour l'inventaire du revendeur
            mettreAJourInventaire(produitsSelectionnes);

            System.out.println("\nCommande Passee avec Succes !");
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
            scanner.nextLine();
        }
    }

    /**
     * Affiche les produits selectionnes dans la commande.
     *
     * @param produits Liste des produits selectionnes.
     */
    private static void afficherProduits(List<String> produits) {
        for (String produit : produits) {
            System.out.println("- " + produit);
        }
    }


    /**
     * Met a jour l'inventaire du revendeur apres que la commande a ete passee.
     *
     * @param produits Liste des produits selectionnes dans la commande.
     */
    private static void mettreAJourInventaire(List<String> produits) {
    }






}
