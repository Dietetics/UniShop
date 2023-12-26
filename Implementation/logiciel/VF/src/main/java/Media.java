import java.io.File;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class Media {



//    public void modifie_Produit(){
//
//        String choix = "90";
//        while (choix != ":q") {
//            try {
//                displayProfile();
//                System.out.println("tapez un titre pour modifier ou :e pour enregistrer la modification ou :q pour quitter");
//                System.out.print("\n");
//
//                System.out.print("Entrez une option : ");
//                choix = myScanner.getStringInput();
//
//
//                switch (choix) {
//
//                    case ":q": displayMenuRevendeur(); break;
//                    case "telephone":
//                        System.out.print("Votre recent telephone est: " + telephone + "\n");
//                        String telephone = InputRestreint.getValidTelephone("quel est votre nouveau telephone: ");
//                        this.telephone = telephone;
//                        break;
//                    case "adresse":
//                        System.out.print("Votre recent adresse est: " + adresse + "\n");
//                        String adresse = InputRestreint.getValidAdresse("quel est votre nouveau adresse: ");
//                        this.adresse = adresse;
//                        break;
//                    case "password":
//                        System.out.print("Votre recent password est: " + "********" + "\n");
//                        String password = InputRestreint.getValidPassword("quel est votre nouveau password: ");
//                        this.password = password;
//                        break;
//                    case ":e":
//                        saveChanges();
//                        break;
//                    default:
//                        System.out.println("Choix invalide. Veuillez reessayer.");
//                }
//            } catch (InputMismatchException e) {
//                System.out.println("Erreur : Veuillez entrer un nombre entier.");
//            }
//        }
//    }
//
//    private void displayProfile() {
//        System.out.println("\n");
//        System.out.println("Cher revendeur: " + getNom() + ", Voici votre profil");
//        System.out.println("-----------------------------------------------------");
//        System.out.println("adresse: " + adresse);
//        System.out.println("telephone: " + telephone);
//        System.out.println("password: " + "********");
//        System.out.println("-----------------------------------------------------");
//
//    }
//
//
//    private void saveChanges() {
//        String nbLikes = Integer.toString(getNbLikes());
//
//        List<String> newCSVLine = Arrays.asList(nom, getPassword(), courriel, getAdresse(), getTelephone(),nbLikes);
//
//        String directoryPath = DatabasePath.getRevendeurComptePath() + nom + "/main.csv";
//        CSVHandler.coverCSV(directoryPath, FormatAdjust.transformList(newCSVLine));
//
//        Database.refreshRevendeurs();
//        System.out.println("les donnees sont bien enregistrer \n\n");
//
//    }

    public static void display(String revendeur){
        String pathOffrir = DatabasePath.getRevendeurComptePath() + revendeur + "/offrir.csv";

        System.out.println("\n\nVoici la liste des produits offer par vous");
        System.out.println("-----------------------------------");
        afficherProduitsOffer(pathOffrir);
        ajouterImage(revendeur,pathOffrir);
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
