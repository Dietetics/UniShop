import java.util.*;

public class ProfilAcheteur {
    private static String pseudo, password, courriel, nom, prenom, telephone, adresse, nbLikes;
    public ProfilAcheteur(String pseudo, String password) {

        this.pseudo = pseudo;
        this.password = password;

        int lineIndex = CSVHandler.findOccurrenceIndex(DatabasePath.getAcheteurPath(),getPseudo(),0);
        String data = CSVHandler.readLineByIndex(DatabasePath.getAcheteurPath(),lineIndex);

        String courriel = CSVHandler.getColumnValue(data, 2);
        String nom = CSVHandler.getColumnValue(data, 3);
        String prenom = CSVHandler.getColumnValue(data, 4);
        String telephone = CSVHandler.getColumnValue(data, 5);
        String adresse = CSVHandler.getColumnValue(data, 6);
        String nbLikes = CSVHandler.getColumnValue(data, 7);

        this.nom = nom;
        this.prenom = prenom;
        this.courriel = courriel;
        this.telephone = telephone;
        this.adresse = adresse;
        this.nbLikes = nbLikes;

        displayMenuAcheteur();
    }


    /**
     * Affiche le menu principal pour l'acheteur, lui permettant de choisir parmi différentes options telles que
     * la modification du profil, la consultation du catalogue de produits, la recherche de produits, etc.
     */
    public void displayMenuAcheteur() {
        int choix = 90;
        while (choix != 0) {
            try {
                menuMsg();
                System.out.print("Choix : ");
                choix = myScanner.getIntInput();

                switch (choix) {
                    case 0:
                        System.out.println("Votre compte est bien deconnecter: " + pseudo + "-------");
                        VF.displayMenuPrincipale();
                        break;
                    case 1: modifie_profil(); break;
                    case 2: new RechercheAcheteur(getPseudo()); break;
                    case 3: gererSuiveurs(); break;
                    case 4:
                        PanierAchat panier = new PanierAchat(getPseudo());
                        panier.menu();
                        break;

//                    case 7: Histoire.pageHistoire(); break;
//                    case 8: Metrique.voir_metriques(); break;
//                    case 9: Notification.recevoirNotifications(); break;
//                    case 10: voir_pts(); break;
//                    case 11: SignalementProbleme.signalerProblemeProduit(); break;

                    default:
                        System.out.println("Choix invalide. Veuillez reessayer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer un nombre entier.");
            }
        }
    }
    private void menuMsg() {
        System.out.println("\n");
        System.out.println("Bonsoir cher Acheteur:" + getPseudo() + "              " + getCourriel() );
        System.out.println("----------------------------------------------------------" );
        System.out.println("0. deconnecter");
        System.out.println("1. modifier le Profil");
        System.out.println("2. recherche");
        System.out.println("3. gerer les suiveurs");
        System.out.println("4. panier d'achat"); // passer une commande

//        System.out.println("12. voir les points du programme de fidelite");

//        System.out.println("7. gestion de commandes"); // confirmer la reception,  retournerUechanger, commenter et evaluer un produit
//
//        System.out.println("8. voir les metriques");
//        System.out.println("9. voir les notifications");
//        System.out.println("10. voir les points du programme de fidelite");

//        System.out.println("11. signaler un probleme");

        System.out.print("\n");
    }


    public void gererSuiveurs(){
        String pathSuiveurs = DatabasePath.getAcheteurComptePath() + getPseudo() + "/suiviPar.csv";

        System.out.println("\n\nVoici la liste de vos suiveurs");
        System.out.println("-----------------------------------");
        trouverSuiveurs(pathSuiveurs);
        Boolean boucle = true;

        while (boucle == true) {
            System.out.print("\nmentionner le nom du suiveur pour le retirer de la liste ou :q pour quitter: ");
            String scanned = myScanner.getStringInput();
            String nomSuiveur = scanned;

            Boolean existe = CSVHandler.isExiste(pathSuiveurs,scanned);
            if (existe == true){ scanned = "1";}

                switch (scanned) {
                    case ":q":
                        boucle = false;
                        break;
                    case "1":
                        String pathCibleSuivi = DatabasePath.getAcheteurComptePath() + nomSuiveur + "/suivreAcheteur.csv";
                        String pathCibleNotification = DatabasePath.getAcheteurComptePath() + nomSuiveur + "/notification.csv";
                        String pathAuteurNotification = DatabasePath.getAcheteurComptePath() + getPseudo() + "/notification.csv";

                        retireSuiveur(pathSuiveurs,nomSuiveur);
                        retireSuivi(pathCibleSuivi,getPseudo());
                        notificationSystemAuto(pathAuteurNotification, nomSuiveur);
                        notificationAuSuiveur(pathCibleNotification);
                        break;
                    default:
                        System.out.println("Commande inconnue. Veuillez reessayer de nouveau");
                        break;
                }
            }
    }

    public void trouverSuiveurs(String path){
        CSVHandler.printCSV(CSVHandler.readCSV(path,9999));
    }

    public void retireSuiveur(String path,String nomSuiveur){
        int index = CSVHandler.findOccurrenceIndex(path,nomSuiveur,0);
        index--;
        CSVHandler.removeLineFromCSV(path,index);
    }

    public void retireSuivi(String path, String auteur){
        int index = CSVHandler.findOccurrenceIndex(path,auteur,0);
        index--;
        CSVHandler.removeLineFromCSV(path,index);
    }

    public void notificationAuSuiveur(String path){
        String msg = "vous etes retirer de la liste de suiveurs par " + getPseudo();
        CSVHandler.appendCSV(path,msg);
    }
    public void notificationSystemAuto(String path, String cible){
        String msg = "vous avez bien retirer de la liste de suiveur: " + cible;
        CSVHandler.appendCSV(path,msg);
    }





    /**
     * Modifie le profil de l'acheteur en affichant et permettant la modification des informations telles que le nom,
     * le prénom, le courriel, le téléphone et l'adresse.
     */
    public void modifie_profil(){

        String choix = "90";
        while (choix != ":q") {
            try {
                displayProfile();
                System.out.println("tapez un titre pour modifier ou :e pour enregistrer la modification ou :q pour quitter");
                System.out.print("\n");

                System.out.print("Entrez une option : ");
                choix = myScanner.getStringInput();

                switch (choix) {

                    case ":q": displayMenuAcheteur(); break;
                    case "nom":
                        System.out.println("Votre recent nom est: " + nom + "\n");
                        String nom = InputRestreint.getValidInput("quel est votre nouveau nom: ");
                        this.nom = nom;
                        break;
                    case "prenom":
                        System.out.println("Votre recent prenom est: " + prenom + "\n");
                        String prenom = InputRestreint.getValidInput("quel est votre nouveau prenom: ");
                        this.prenom = prenom;
                        break;
                    case "telephone":
                        System.out.println("Votre recent telephone est: " + telephone + "\n");
                        String telephone = InputRestreint.getValidTelephone("quel est votre nouveau telephone: ");
                        this.telephone = telephone;
                        break;
                    case "adresse":
                        System.out.println("Votre recent adresse est: " + adresse + "\n");
                        String adresse = InputRestreint.getValidAdresse("quel est votre nouveau adresse: ");
                        this.adresse = adresse;
                        break;
                    case "password":
                        System.out.println("Votre recent password est: " + "********" + "\n");
                        String password = InputRestreint.getValidPassword("quel est votre nouveau password: ");
                        this.password = password;
                        break;
                    case ":e":
                        saveChanges();
                        break;
                    default:
                        System.out.println("Choix invalide. Veuillez reessayer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer un nombre entier.");
            }
        }
    }


    private void displayProfile() {
        System.out.println("\n\nCher acheteur: " + getPseudo() + ", Voici votre profil");
        System.out.println("-----------------------------------------------------");
        System.out.println("nom: " + nom);
        System.out.println("prenom: " + prenom);
        System.out.println("telephone: " + telephone);
        System.out.println("adresse: " + adresse);
        System.out.println("password: " + "********");
        System.out.println("-----------------------------------------------------");
    }

    private void saveChanges() {
        List<String> newCSVLine = Arrays.asList(pseudo, getPassword(), courriel, getNom(), getPrenom(), getTelephone(), getAdresse(), nbLikes);

        String directoryPath = DatabasePath.getAcheteurComptePath() + pseudo + "/main.csv";
        CSVHandler.coverCSV(directoryPath, FormatAdjust.transformList(newCSVLine));

        Database.refreshAcheteurs();
        System.out.println("les donnees sont bien enregistrer \n\n");
    }












    public static void voir_pts(){
        System.out.println("Apres les calculs, vous avez 120 dans votre compte");
    }


    public static String getNom() {
        return nom;
    }

    public static String getPrenom() {
        return prenom;
    }

    public static String getTelephone() {
        return telephone;
    }

    public static String getAdresse() {
        return adresse;
    }

    public static String getNbLikes() {
        return nbLikes;
    }

    public static String getPseudo() {
        return pseudo;
    }

    public static String getCourriel() {
        return courriel;
    }

    public String getPassword() {
        return password;
    }

}