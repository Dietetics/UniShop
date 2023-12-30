import java.io.File;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class GestionProduit {



    public static void display(String revendeur){


        String pathOffrir = DatabasePath.getRevendeurComptePath() + revendeur + "/offrir.csv";
        displayOfferMsg(pathOffrir,revendeur);

        Boolean boucle = true;
        while (boucle == true) {
            System.out.print("\nmentionner le nom du produit pour faire une action ou :q pour quitter: ");
            String scanned = myScanner.getStringInput();
            String nomProduit = scanned;

            Boolean existe = CSVHandler.isExiste(pathOffrir, scanned);
            if (existe == true) {scanned = "1";}

            switch (scanned) {
                case ":q":
                    boucle = false;
                    break;
                case "1":
                    action(revendeur,pathOffrir,nomProduit);
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez reessayer.");
            }
        }
    }
    public static void displayOfferMsg(String path, String revendeur) {
        System.out.println("\n\nVoici la liste des produits offer par vous: " + revendeur);
        System.out.println("-----------------------------------");
        afficherProduitsOffer(path);
    }
    public static void afficherProduitsOffer(String path){
        CSVHandler.printCSV(CSVHandler.readCSV(path,9999));
    }




    public static void action(String revendeur, String path, String titre){



        ProfilProduit produitDetail = new ProfilProduit(titre);

        String choix = "90";
        while (choix != ":q") {
            try {
                actionMsg();
                System.out.print("\nEntrez une option : ");
                choix = myScanner.getStringInput();

                switch (choix) {

                    case ":q": choix = ":q"; break;
                    case ":a": produitDetail.display(); break;
                    case "1":  modification_description(produitDetail); break;
                    case "2":  modification_quantite(produitDetail); break;
                    case "3":  modification_prix(produitDetail); break;
                    case "4":  modification_pointsBoni(produitDetail); break;
                    case "5":  ajouterImage(revendeur, produitDetail);  break;
                    case "6":  retirerImage(revendeur, produitDetail);  break;
                    case "7":  ajouterVideo(revendeur, produitDetail);break;
                    case "8":  retirerVideo(revendeur, produitDetail);break;
                    case ":e": produitDetail.modified(); break;
                    default:
                        System.out.println("Choix invalide. Veuillez reessayer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer un nombre entier.");
            }
        }
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
        System.out.println("5 pour ajouter une image");
        System.out.println("6 pour retirer une image");
        System.out.println("7 pour ajouter une video");
        System.out.println("8 pour retirer une video");
        System.out.println("-----------------------------------");
        System.out.println(":e pour enregistrer les modifications");
        System.out.print("\n");
    }


    public static void modification_description(ProfilProduit produitDetail){
        System.out.print("Votre recent description est: " + produitDetail.getDescription() + "\n");
        String description = InputRestreint.getValidDescription("Veuillez entrer une nouvelle description: ");
        produitDetail.setDescription(description);
    }
    public static void modification_quantite(ProfilProduit produitDetail){
        System.out.print("Votre recent quantite est: " + produitDetail.getQuantite() + "\n");
        String quantite = InputRestreint.getValidQuantite("Veuillez entrer une nouvelle quantite: ");
        int quantite1 = Integer.parseInt(quantite);
        produitDetail.setQuantite(quantite1);
    }
    public static void modification_prix(ProfilProduit produitDetail){
        System.out.print("Votre recent prix est: " + produitDetail.getPrix() + "\n");
        String prix = InputRestreint.getValidPrix("Veuillez entrer un prix: ");
        int prix1 = Integer.parseInt(prix);
        produitDetail.setPrix(prix1);
    }
    public static void modification_pointsBoni(ProfilProduit produitDetail){
        System.out.print("Votre recent points bonus est: " + produitDetail.getPointsBoni() + "\n");
        String pointsBoni = InputRestreint.getValidPointsBoni("Veuillez entrer des points Bonus: ");
        int pointsBoni1 = Integer.parseInt(pointsBoni);
        produitDetail.setPointsBoni(pointsBoni1);
    }



    public static void ajouterImage(String revendeur, ProfilProduit produitDetail){
        try {
            // Afficher les images actuelles
            System.out.println("Vos images actuelles sont : " + produitDetail.getImage());

            // Ajouter une image
            String imageURL = InputRestreint.getValidUrl("Veuillez entrer une URL (ex: http://www.example.com): ");
            String ligne = produitDetail.getImage();
            String image = (ligne.equals("aucun")) ? imageURL : ligne + "; " + imageURL;

            // Mettre à jour l'instance
            produitDetail.setImage(image);

            // Mettre à jour le fichier CSV
            String path = DatabasePath.getProduitInfoPath() + produitDetail.getTitre() + "/main.csv";
            String temp = CSVHandler.readLineByIndex(path, 1);
            String temp1 = CSVHandler.updateCSVColumn(temp, 6, image);
            CSVHandler.coverCSV(path, temp1);

            // Notification automatique
            String pathAuteurNotification = DatabasePath.getRevendeurComptePath() + revendeur + "/notification.csv";
            notificationSystemAuto(pathAuteurNotification, produitDetail.getTitre(), "Vous avez bien ajouter une nouvelle image a: ");


        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }
    public static void retirerImage(String revendeur, ProfilProduit produitDetail){
        try {
            // Afficher les images actuelles
            System.out.println("Vos images actuelles sont : " + produitDetail.getImage());

            // Retirer une image
            System.out.print("Veuillez entrer le numéro de l'image que vous souhaitez retirer (ex: 1): ");
            int imageIndexToRemove = myScanner.getIntInput() - 1;

            // Supprimer l'image de la liste
            String ligne = produitDetail.getImage();
            String[] images = ligne.split(";");
            if (imageIndexToRemove >= 0 && imageIndexToRemove < images.length) {
                StringBuilder updatedImageList = new StringBuilder();
                for (int i = 0; i < images.length; i++) {
                    if (i != imageIndexToRemove) {
                        updatedImageList.append(images[i].trim());
                        if (i < images.length - 1) {
                            updatedImageList.append("; ");
                        }
                    }
                }

                if (images.length == 1){updatedImageList.append("aucun");}

                // Mettre à jour l'instance
                produitDetail.setImage(updatedImageList.toString());

                // Mettre à jour le fichier CSV
                String path = DatabasePath.getProduitInfoPath() + produitDetail.getTitre() + "/main.csv";
                String temp = CSVHandler.readLineByIndex(path, 1);
                String temp1 = CSVHandler.updateCSVColumn(temp, 6, updatedImageList.toString());
                CSVHandler.coverCSV(path, temp1);

                // Notification automatique
                String pathAuteurNotification = DatabasePath.getRevendeurComptePath() + revendeur + "/notification.csv";
                notificationSystemAuto(pathAuteurNotification, produitDetail.getTitre(),"Vous avez bien retirer une image du produit: ");
            } else {
                System.out.println("Index d'image invalide.");
            }
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }


    public static void ajouterVideo(String revendeur, ProfilProduit produitDetail){
        try {
            // Afficher les videdos actuelles
            System.out.println("Vos videos actuelles sont : " + produitDetail.getVideo());

            // Ajouter une video
            String videoURL = InputRestreint.getValidUrl("Veuillez entrer une URL (ex: http://www.example.com): ");
            String ligne = produitDetail.getVideo();
            String video = (ligne.equals("aucun")) ? videoURL : ligne + "; " + videoURL;

            // Mettre à jour l'instance
            produitDetail.setVideo(video);

            // Mettre à jour le fichier CSV
            String path = DatabasePath.getProduitInfoPath() + produitDetail.getTitre() + "/main.csv";
            String temp = CSVHandler.readLineByIndex(path, 1);
            String temp1 = CSVHandler.updateCSVColumn(temp, 7, video);
            CSVHandler.coverCSV(path, temp1);

            // Notification automatique
            String pathAuteurNotification = DatabasePath.getRevendeurComptePath() + revendeur + "/notification.csv";
            notificationSystemAuto(pathAuteurNotification, produitDetail.getTitre(),"Vous avez bien ajouter une nouvelle video pour: ");


        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }
    public static void retirerVideo(String revendeur, ProfilProduit produitDetail){
        try {
            // Afficher les videos actuelles
            System.out.println("Vos videdos actuelles sont : " + produitDetail.getVideo());

            // Retirer une video
            System.out.print("Veuillez entrer le numéro de le video que vous souhaitez retirer (ex: 1): ");
            int videoIndexToRemove = myScanner.getIntInput() - 1;

            // Supprimer le video de la liste
            String ligne = produitDetail.getVideo();
            String[] videos = ligne.split(";");
            if (videoIndexToRemove >= 0 && videoIndexToRemove < videos.length) {
                StringBuilder updatedVideoList = new StringBuilder();
                for (int i = 0; i < videos.length; i++) {
                    if (i != videoIndexToRemove) {
                        updatedVideoList.append(videos[i].trim());
                        if (i < videos.length - 1) {
                            updatedVideoList.append("; ");
                        }
                    }
                }

                if (videos.length == 1){updatedVideoList.append("aucun");}

                // Mettre à jour l'instance
                produitDetail.setVideo(updatedVideoList.toString());

                // Mettre à jour le fichier CSV
                String path = DatabasePath.getProduitInfoPath() + produitDetail.getTitre() + "/main.csv";
                String temp = CSVHandler.readLineByIndex(path, 1);
                String temp1 = CSVHandler.updateCSVColumn(temp, 6, updatedVideoList.toString());
                CSVHandler.coverCSV(path, temp1);

                // Notification automatique
                String pathAuteurNotification = DatabasePath.getRevendeurComptePath() + revendeur + "/notification.csv";
                notificationSystemAuto(pathAuteurNotification, produitDetail.getTitre(), "Vous avez bien retirer le video du produit: ");
            } else {
                System.out.println("Index de video invalide.");
            }
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }











    public static void supprimerProduits(String revendeur){

        String path = DatabasePath.getRevendeurComptePath() + revendeur + "/offrir.csv";
        System.out.println("\n\nVoici la liste des produits offer par vous");
        System.out.println("-----------------------------------");
        afficherProduitsOffer(path);
        Boolean boucle = true;

        while (boucle == true) {
            System.out.print("\nmentionner le nom du produit pour le retirer de la liste ou :q pour quitter: ");
            String scanned = myScanner.getStringInput();
            String nomProduit = scanned;

            Boolean existe = CSVHandler.isExiste(path,scanned);
            if (existe == true){ scanned = "1";}

            switch (scanned) {
                case ":q":
                    boucle = false;
                    break;
                case "1":
                    String pathProduit = DatabasePath.getProduitInfoPath() + nomProduit;

                    retireProduitOffer(path,nomProduit);

                    // Créer un objet File avec le chemin du dossier à supprimer
                    File dossierASupprimer = new File(pathProduit);
                    // Appeler la méthode pour supprimer le dossier
                    supprimerDossier(dossierASupprimer);

                    String pathAuteurNotification = DatabasePath.getRevendeurComptePath() + revendeur + "/notification.csv";
                    notificationSystemAuto(pathAuteurNotification, nomProduit, "Vous avez bien retirer le produit suivant: ");

                    Database.refreshProduits();

                    break;
                default:
                    System.out.println("Commande inconnue. Veuillez reessayer de nouveau");
                    break;
            }
        }
    }
    public static void retireProduitOffer(String path, String nomProduit){
        int index = CSVHandler.findOccurrenceIndex(path,nomProduit,0);
        index--;

        CSVHandler.removeLineFromCSV(path,index);
    }
    public static void supprimerDossier(File dossier) {
        // Vérifier si le dossier existe
        if (dossier.exists()) {
            // Vérifier si c'est un dossier
            if (dossier.isDirectory()) {
                // Liste des fichiers dans le dossier
                File[] fichiers = dossier.listFiles();

                // Supprimer tous les fichiers et sous-dossiers dans le dossier
                if (fichiers != null) {
                    for (File fichier : fichiers) {
                        if (fichier.isDirectory()) {
                            // Appeler récursivement la méthode pour supprimer les sous-dossiers
                            supprimerDossier(fichier);
                        } else {
                            // Supprimer le fichier
                            fichier.delete();
                        }
                    }
                }
            }
            // Supprimer le dossier lui-même
            dossier.delete();
        }
    }





    public static void notificationSystemAuto(String path, String cible, String message){
        String msg = message + cible;
        CSVHandler.appendCSV(path,msg);
    }

}
