import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class GestionProduit {



    public static void display(String nom){


        String pathOffrir = DatabasePath.getRevendeurComptePath() + nom + "/offrir.csv";
        displayProfile(pathOffrir);

        Boolean boucle = true;
        while (boucle == true) {
            System.out.print("\nmentionner le nom du produit faire une action ou :q pour quitter: ");
            String scanned = myScanner.getStringInput();
            String nomProduit = scanned;

            Boolean existe = CSVHandler.isExiste(pathOffrir, scanned);
            if (existe == true) {scanned = "1";}

            switch (scanned) {
                case ":q":
                    boucle = false;
                    break;
                case "1":
                    action(nom,pathOffrir,nomProduit);
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez reessayer.");
            }
        }
    }

    private static void displayProfile(String path) {
        System.out.println("\n\nVoici la liste des produits offer par vous");
        System.out.println("-----------------------------------");
        afficherProduitsOffer(path);
    }


    public static void actionMsg(){
        System.out.println("\n\nVoici les options possibles");
        System.out.println("-----------------------------------");
        System.out.println(":q pour Quitter");
        System.out.println(":a pour Afficher en detail");
        System.out.println("-----------modification------------");
        System.out.println("1 pour description");
        System.out.println("2 pour quantite");
        System.out.println("3 pour prix");
        System.out.println("4 pour pointsBoni");
        System.out.println("5 pour promo");
        System.out.println("6 pour ajouter une image");
        System.out.println("7 pour ajouter une video");
        System.out.println("-----------------------------------");
        System.out.println(":e pour enregistrer les modifications");
        System.out.print("\n");
    }

    public static void action(String nom, String path, String titre){

        actionMsg();

        // faire une instance du produit

        String choix = "90";
        while (choix != ":q") {
            try {
                System.out.print("\nEntrez une option : ");
                choix = myScanner.getStringInput();

                switch (choix) {

                    case ":q": display(nom); break;
                    case ":a": break;
                    case "1":  break;
                    case "2":  break;
                    case "3":  break;
                    case "4":  break;
                    case "5":  break;
                    case "6":  break;
                    case "7":  break;
                    case ":e": saveChanges();break;
                    default:
                        System.out.println("Choix invalide. Veuillez reessayer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer un nombre entier.");
            }
        }
    }



    private static void saveChanges() {
        // get les instances pour append dans les deux spots
//        String nbLikes = Integer.toString(getNbLikes());
//
//        List<String> newCSVLine = Arrays.asList(nom, getPassword(), courriel, getAdresse(), getTelephone(),nbLikes);
//
//        String directoryPath = DatabasePath.getRevendeurComptePath() + nom + "/main.csv";
//        CSVHandler.coverCSV(directoryPath, FormatAdjust.transformList(newCSVLine));
//
//        Database.refreshRevendeurs();
//        System.out.println("les donnees sont bien enregistrer \n\n");

    }



    public static void ajouterImage(String revendeur, String pathOffrir){

        Boolean boucle = true;
        while (boucle == true) {
            System.out.print("\nmentionner le nom du produit pour ajouter une image ou :q pour quitter: ");
            String scanned = myScanner.getStringInput();
            String nomProduit = scanned;

            Boolean existe = CSVHandler.isExiste(pathOffrir,scanned);
            if (existe == true){ scanned = "1";}

            switch (scanned) {
                case ":q":
                    boucle = false;
                    break;
                case "1":
                    String image;

                    try {
                        String imageURL = InputRestreint.getValidUrl("Veuillez entrer un url: ");

                        ProfilProduit unProduit = new ProfilProduit(nomProduit);

                        String ligne = unProduit.getImage();
                        if (ligne.equals("non")){
                            image = imageURL;
                        }else{
                            image = ligne + "; " + imageURL;
                        }
                        String path = DatabasePath.getProduitInfoPath() + nomProduit + "/main.csv";

                        String temp = CSVHandler.readLineByIndex(path,1);
                        String temp1 = CSVHandler.updateCSVColumn(temp,6,image);
                        CSVHandler.coverCSV(path,temp1);

                        String pathAuteurNotification = DatabasePath.getRevendeurComptePath() + revendeur + "/notification.csv";
                        notificationSystemAuto(pathAuteurNotification, nomProduit);

                        Database.refreshProduits();
                        break;
                    } catch (Exception e) {
                        System.out.println("Erreur: " + e.getMessage());
                    }

                default:
                    System.out.println("Commande inconnue. Veuillez reessayer de nouveau");
                    break;
            }
        }
    }

    public static void afficherProduitsOffer(String path){
        CSVHandler.printCSV(CSVHandler.readCSV(path,9999));
    }



    public static void notificationSystemAuto(String path, String cible){
        String msg = "vous avez bien ajouter une image a " + cible;
        CSVHandler.appendCSV(path,msg);
    }





    public static void ajouterImage() {


        String image;
        String produit = InputRestreint.getValidUrl("Veuillez entrer un nom du produit a retirer: ");

        try {
            String imageURL = InputRestreint.getValidUrl("Veuillez entrer un url: ");

            ProfilProduit unProduit = new ProfilProduit(produit);

            String ligne = unProduit.getImage();
            if (ligne.equals("non")){
                image = imageURL;
            }else{
                image = ligne + "; " + imageURL;
            }
            String path = DatabasePath.getProduitInfoPath() + produit + "/main.csv";

            String temp = CSVHandler.readLineByIndex(path,1);
            String temp1 = CSVHandler.updateCSVColumn(temp,6,image);
            CSVHandler.coverCSV(path,temp1);

            Database.refreshProduits();

        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }

    }




//    public static void ajouterVideo(String produit, String auteur, String videoURL) {
//        List<String> video = unProduit.getVideos();
//        video.add(videoURL);}

}
