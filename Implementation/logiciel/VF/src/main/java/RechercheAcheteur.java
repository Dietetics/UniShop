import java.util.InputMismatchException;

public class RechercheAcheteur {

    private static String acheteur;

    public RechercheAcheteur(String acheteur) {
        this.acheteur = acheteur;
        display();
    }

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
                        choix = "0";
                        decisionProduit(DatabasePath.getPathTousProduits(),0); //faire decision
                        break;
                    case 2:
                        choix = "0";
                        decisionAcheteur(DatabasePath.getPathTousAcheteurs(),0);
                        break;
                    case 3:
                        choix = "0";
                        decisionRevendeur(DatabasePath.getPathTousRevendeurs(),0);
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
                    case 7:
                        catalogue(acheteur);
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
        System.out.println("------ Bienvenu a notre section de recherche, cher " + getAcheteur() + "-------");
        System.out.println("0. Quitter");
        System.out.println("Recuperer la liste ... pour voir plus d informations, liker ou suivre, passer au panier");
        System.out.println("    1. des produits");
        System.out.println("    2. des acheteurs");
        System.out.println("    3. des revendeurs");
        System.out.println("Rechercher ... par mot-cle et filtrer");
        System.out.println("    4. un produit");
        System.out.println("    5. un acheteur");
        System.out.println("    6. un revendeur");
        System.out.println("Rechercher tout ... pour liker ou passer au panier");
        System.out.println("    7. un produit");
        System.out.print("\n");
    }


    public static void decisionProduit(String path, int colonne) {
        String produit;
        String choix = "90";
        while (choix != "0") {
            try {
                displayDecisionProduitMsg();
                System.out.print("Selectionnez un chiffre: ");
                int input = myScanner.getIntInput();

                switch (input) {
                    case 0: return;
                    case 1: Recherche.recuperer(DatabasePath.getPathTousProduits(), 0);
                        break; //afficher
                    case 2:
                        voirProduit(path,colonne); //voir les produits
                        break;
                    case 3:
                        produit = siExiste(path); //verifie si existe
                        if(produit != "-1"){
                            Operation.acheteurLikerProduit(produit,getAcheteur());
                        };
                        break;
                    case 4:
                        produit = siExiste(path); //verifie si existe
                        if(produit != "-1"){
                            Operation.acheteurCommandeProduit(produit,getAcheteur());
                        };
                        break;
                    default:
                        System.out.println("Choix invalide. Veuillez reessayer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer un nombre entier.");
            }
        }
    }


    public static void displayDecisionProduitMsg(){
        System.out.println("\n------ Cher " + getAcheteur() + " veuillez choisir un option -------");
        System.out.println("0. Quitter");
        System.out.println("1. Recuperer liste des produits");
        System.out.println("2. Voir plus d'information");
        System.out.println("3. Liker");
        System.out.println("4. Placer dans le panier");
        System.out.print("\n");
    }

    public static String voirProduit(String path, int colonne) {
        System.out.println("\n0 pour retourner ou taper le nom du produit en demande pour voir plus d information");
        System.out.println("---------------------------------------------------");
        System.out.print("Reponse: ");
        String decision = myScanner.getStringInput();

        if (decision == "0") return "0";
        else if (CSVHandler.findOccurrenceIndex(path, decision, colonne) != -1){
            InfoProduit.voirProduit(path, decision, colonne);
        }else System.out.println("ce n'est pas un choix, veuillez entrer de nouveau \n\n\n");
        return "1";
    }









    public static void decisionAcheteur(String path, int colonne) {

        String choix = "90";
        while (choix != "0") {
            try {
                displayDecisionAcheteurMsg();
                System.out.print("Selectionnez un chiffre: ");
                int input = myScanner.getIntInput();

                switch (input) {
                    case 0: return;
                    case 1: Recherche.recuperer(DatabasePath.getPathTousAcheteurs(), 0);
                        break;
                    case 2:
                        voirAcheteur(path,colonne); //voir les produits
                        break;
                    case 3:
                        String acheteur = siExiste(path); //verifie si existe
                        if(acheteur != "-1"){
                            Operation.acheteurSuivreAcheteur(acheteur,getAcheteur());
                        };
                        break;
                    default:
                        System.out.println("Choix invalide. Veuillez reessayer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer un nombre entier.");
            }
        }
    }

    public static void displayDecisionAcheteurMsg(){
        System.out.println("\n------ Cher " + getAcheteur() + " veuillez choisir un option -------");
        System.out.println("0. Quitter");
        System.out.println("1. recuperer liste des acheteurs");
        System.out.println("2. Voir plus d'information");
        System.out.println("3. suivre");
        System.out.print("\n");
    }

    public static String voirAcheteur(String path, int colonne) {
        System.out.println("\n0 pour retourner ou taper le nom de l acheteur en demande pour voir plus d information");
        System.out.println("---------------------------------------------------");
        System.out.print("Reponse: ");
        String decision = myScanner.getStringInput();

        if (decision == "0") return "0";
        else if (CSVHandler.findOccurrenceIndex(path, decision, colonne) != -1){
            InfoAcheteur.voirProfil(path, decision, colonne);
        }else System.out.println("ce n'est pas un choix, veuillez entrer de nouveau \n\n\n");
        return "1";
    }







    public static void decisionRevendeur(String path, int colonne) {
        String revendeur;
        String choix = "90";
        while (choix != "0") {
            try {
                displayDecisionRevendeurMsg();
                System.out.print("Selectionnez un chiffre: ");
                int input = myScanner.getIntInput();

                switch (input) {
                    case 0: return;
                    case 1:
                        Recherche.recuperer(DatabasePath.getPathTousRevendeurs(), 0);
                        break;
                    case 2:
                        voirRevendeur(path,colonne); //voir les produits
                        break;
                    case 3:
                        revendeur = siExiste(path); //verifie si existe
                        if(revendeur != "-1"){
                            Operation.acheteurLikerRevendeur(revendeur,getAcheteur());
                        };
                        break;
                    case 4:
                        revendeur = siExiste(path); //verifie si existe
                        if(revendeur != "-1"){
                            Operation.acheteurSuivreRevendeur(revendeur,getAcheteur());
                        };
                        break;

                    default:
                        System.out.println("Choix invalide. Veuillez reessayer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer un nombre entier.");
            }
        }
    }

    public static void displayDecisionRevendeurMsg(){
        System.out.println("\n------ Cher " + getAcheteur() + " veuillez choisir un option -------");
        System.out.println("0. Quitter");
        System.out.println("1. recuperer liste des revendeurs");
        System.out.println("2. Voir plus d'information");
        System.out.println("3. liker");
        System.out.println("4. suivre");
        System.out.print("\n");
    }

    public static String voirRevendeur(String path, int colonne) {
        System.out.println("\n0 pour retourner ou taper le nom du revendeur en demande pour voir plus d information");
        System.out.println("---------------------------------------------------");
        System.out.print("Reponse: ");
        String decision = myScanner.getStringInput();

        if (decision == "0") return "0";
        else if (CSVHandler.findOccurrenceIndex(path, decision, colonne) != -1){
            InfoRevendeur.voirProfil(path, decision, colonne);
        }else System.out.println("ce n'est pas un choix, veuillez entrer de nouveau \n\n\n");
        return "1";
    }


    public static String siExiste(String path) {
        System.out.println("\ntaper le nom en demande");
        System.out.println("---------------------------------------------------");
        System.out.print("Reponse: ");
        String scanned = myScanner.getStringInput();
        try {
            Boolean condition = CSVHandler.isExiste(path, scanned);
            if (condition) {
                return scanned;
            } else {
                System.out.println("Nous ne trouvons pas ce que vous avez entrer.");
                return "-1";
            }
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite : " + e.getMessage());
        }
        return "-1";
    }















































    /**
     * Affiche le catalogue des produits et permet à l'acheteur de liker un produit.
     */
    public static void catalogueProduit(String auteur){
        String choix = "99";
        while (choix != "0") {
            try {
                System.out.println("\n\nCher acheteur: " + auteur + ", Voici nos produits");
                System.out.println("-----------------------------------------------------");
                CSVHandler.printCSV(CSVHandler.readCSV(DatabasePath.getPathTousProduits(),30));
                System.out.println("-----------------------------------------------------");
                System.out.println(" ");

                System.out.print("0 pour retourner ou entrer le nom du produit pour liker : ");
                String scanned = myScanner.getStringInput();

                if (scanned.equals("0")) return;

                int index = CSVHandler.findOccurrenceIndex(DatabasePath.getPathTousProduits(),scanned,0);
                if(index!= -1) {
                    choix = "0";
                    String produit = CSVHandler.getColumnValue(CSVHandler.readLineByIndex(DatabasePath.getPathTousProduits(),index),0);

                    Operation.acheteurLikerProduit(produit,auteur);
                }
                else System.out.println("Nous ne trouvons aucun produit correspond a ce que vous avez entrer, veuillez entrer de nouveau");
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer de nouveau.");
            }
        }
    }


    public static void catalogue(String auteur) {
        String choix = "99";

        while (!choix.equals("0")) {
            try {
                System.out.println("\n\nCher acheteur: " + auteur + ", Voici nos produits");
                System.out.println("-----------------------------------------------------");
                CSVHandler.printCSV(CSVHandler.readCSV(DatabasePath.getPathTousProduits(), 30));
                System.out.println("-----------------------------------------------------");
                System.out.println(" ");

                System.out.println("Options : ");
                System.out.println("1. Liker un produit");
                System.out.println("2. Passer au panier");
                System.out.println("0. Retourner");

                System.out.print("Entrez le numéro de l'option : ");
                String scanned = myScanner.getStringInput();

                switch (scanned) {
                    case "0":
                        choix = "0";
                        break;
                    case "1":
                        System.out.print("Entrez le nom du produit à liker : ");
                        String produitNom = myScanner.getStringInput();
                        int index = CSVHandler.findOccurrenceIndex(DatabasePath.getPathTousProduits(), produitNom, 0);

                        if (index != -1) {
                            String produit = CSVHandler.getColumnValue(CSVHandler.readLineByIndex(DatabasePath.getPathTousProduits(), index), 0);
                            Operation.acheteurLikerProduit(produit, auteur);
                        } else {
                            System.out.println("Aucun produit correspondant trouvé. Veuillez réessayer.");
                        }
                        break;
                    case "2":
                        String produit = siExiste(DatabasePath.getPathTousProduits()); //verifie si existe
                        if(produit != "-1"){
                            Operation.acheteurCommandeProduit(produit,getAcheteur());
                        };
                        break;
                    default:
                        System.out.println("Option invalide. Veuillez entrer de nouveau.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer de nouveau.");
            }
        }
    }






    public static String getAcheteur() {
        return acheteur;
    }
}
