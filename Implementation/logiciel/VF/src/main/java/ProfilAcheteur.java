import java.util.*;

public class ProfilAcheteur {
    private static String pseudo, password, courriel, nom, prenom, telephone, adresse;
    private static int nbLikesDonnnees;
    private static List<String> evaluations, histoire, likerProduit, likerRevendeur, notifications, panier, retourEchange,
            signaler, suiviPar, suivreAcheteur, suivreRevendeur;


    public ProfilAcheteur(String pseudo) {

        this.pseudo = pseudo;
        this.password = password;

        String path = DatabasePath.getPathAcheteurCompte() + pseudo;

        // load les datas generales
        String data = CSVHandler.readLineByIndex(path + "/main.csv",1);

        this.courriel = CSVHandler.getColumnValue(data, 2);
        this.nom = CSVHandler.getColumnValue(data, 3);
        this.prenom = CSVHandler.getColumnValue(data, 4);
        this.telephone = CSVHandler.getColumnValue(data, 5);
        this.adresse = CSVHandler.getColumnValue(data, 6);


        // load les datas intermediaires
        this.evaluations = load(path, "/evaluations.csv");
        this.histoire = load(path, "/histoire.csv");
        this.likerProduit = load(path, "/likerProduit.csv");
        this.likerRevendeur = load(path, "/likerRevendeur.csv");
        this.notifications = load(path, "/notifications.csv");
        this.panier = load(path, "/panier.csv");
        this.retourEchange = load(path, "/retourEchange.csv");
        this.signaler = load(path, "/signaler.csv");
        this.suiviPar = load(path, "/suiviPar.csv");
        this.suivreAcheteur = load(path, "/suivreAcheteur.csv");
        this.suivreRevendeur = load(path, "/suivreRevendeur.csv");

        this.nbLikesDonnnees = Calculator.calculNb(likerProduit);

        saveChanges();
    }


    // csv data enregistrer dans une list de string
    public static List<String> load(String path, String suffixe){
        return CSVHandler.readLinesFromCSV(path + suffixe);
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
                    case 3: GestionSuiveur.gererSuiveurs(getPseudo()); break;
                    case 4:
                        PanierAchat panier = new PanierAchat(getPseudo());
                        panier.menu();
                        break;
                    case 5:
                        Histoire histoire = new Histoire(getPseudo());
                        histoire.menu();
                        break;
                    case 6:
                        VisualiserAcheteur voir = new VisualiserAcheteur(getPseudo());
                        voir.menu();
                        break;
                    default:
                        System.out.println("Choix invalide. Veuillez reessayer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer un nombre entier.");
            }
        }
    }
    public void menuMsg() {
        System.out.println("\n");
        System.out.println("Bonsoir cher Acheteur:" + getPseudo() + "              " + getCourriel() );
        System.out.println("----------------------------------------------------------" );
        System.out.println("0. deconnecter");
        System.out.println("1. modifier le Profil");
        System.out.println("2. recherche");
        System.out.println("3. gerer les suiveurs");
        System.out.println("4. panier d'achat");
        System.out.println("5. Histoire");
        System.out.println("6. Voir ses informations");
        System.out.print("\n");
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
                        System.out.println("les donnees sont bien enregistrer \n\n");
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
        List<String> newCSVLine = Arrays.asList(pseudo,getPassword(),courriel,getNom(),getPrenom(),getTelephone(),getAdresse(),String.valueOf(nbLikesDonnnees));

        String directoryPath = DatabasePath.getPathAcheteurCompte() + pseudo + "/main.csv";

        String temp = OutilSupplementaire.removeSpacesBetweenCommas(FormatAdjust.transformVersString(newCSVLine));
        CSVHandler.coverCSV(directoryPath,temp);

        Database.refreshAcheteurs();
    }


    public void displayInfoAuPublic() {
        System.out.println("\n\nVoici les infos de l'acheteur: " + getPseudo());
        System.out.println("-----------------------------------------------------");
        System.out.println("nom: " + getNom());
        System.out.println("prenom: " + getPrenom());
        System.out.println("-----------------------------------------------------");

        System.out.println("\nListes de produits like: ");
        System.out.println(getLikerProduit());
        System.out.println("------------\n");

        System.out.println("\nListes de revendeurs like: ");
        System.out.println(getLikerRevendeur());
        System.out.println("------------\n");

        System.out.print("Entrez quelque chose pour retourner a la recherche");
        String decision = myScanner.getStringInput();

        if (decision != null) return;
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

    public static int getNbLikesDonnnees() {
        return nbLikesDonnnees;
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

    public static List<String> getEvaluations() {
        return evaluations;
    }

    public static List<String> getHistoire() {
        return histoire;
    }

    public static List<String> getLikerProduit() {
        return likerProduit;
    }

    public static List<String> getLikerRevendeur() {
        return likerRevendeur;
    }

    public static List<String> getNotifications() {
        return notifications;
    }

    public static List<String> getPanier() {
        return panier;
    }

    public static List<String> getRetourEchange() {
        return retourEchange;
    }

    public static List<String> getSignaler() {
        return signaler;
    }

    public static List<String> getSuiviPar() {
        return suiviPar;
    }

    public static List<String> getSuivreAcheteur() {
        return suivreAcheteur;
    }

    public static List<String> getSuivreRevendeur() {
        return suivreRevendeur;
    }
}