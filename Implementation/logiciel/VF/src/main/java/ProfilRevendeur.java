import java.io.File;
import java.util.*;

public class ProfilRevendeur {

    private static String nom,password,courriel,adresse,telephone;
    private static int nbFans, nbOffrir, nbLikerPar;

    private static List<String> fans, notifications, offrir, resolution, retourEchange, signalProbleme, likerPar;

    public ProfilRevendeur(String nom) {

        this.nom = nom;


        String path = DatabasePath.getPathRevendeurCompte() + nom;

        // load les datas generales
        String data = CSVHandler.readLineByIndex(path + "/main.csv",1);

        this.password = CSVHandler.getColumnValue(data, 1);
        this.courriel = CSVHandler.getColumnValue(data, 2);
        this.adresse = CSVHandler.getColumnValue(data, 3);
        this.telephone = CSVHandler.getColumnValue(data, 4);


        // load les datas intermediaires
        this.fans = load(path, "/fans.csv");
        this.notifications = load(path, "/notifications.csv");
        this.offrir = load(path, "/offrir.csv");
        this.resolution = load(path, "/resolution.csv");
        this.retourEchange = load(path, "/retourEchange.csv");
        this.signalProbleme = load(path, "/signalProbleme.csv");
        this.likerPar = load(path, "/likerPar.csv");

        this.nbFans = Calculator.calculNb(fans);
        this.nbOffrir = Calculator.calculNb(offrir);
        this.nbLikerPar = Calculator.calculNb(likerPar);

        saveChanges();
    }

    // csv data enregistrer dans une list de string
    public static List<String> load(String path, String suffixe){
        return CSVHandler.readLinesFromCSV(path + suffixe);
    }

    /**
     * Affiche le menu principal pour le revendeur, lui permettant de choisir parmi différentes options telles que
     * la modification du profil, l'offre d'un produit, la gestion de l'état d'une commande, etc.
     */
    public void displayMenuRevendeur() {
        int choix = 90;
        while (choix != 0) {
            try {
                menuMsg();
                System.out.print("Choix : ");
                choix = myScanner.getIntInput();

                switch (choix) {
                    case 0:
                        System.out.println("Votre compte est bien deconnecter: " + nom + "-------");
                        VF.displayMenuPrincipale();
                        break;
                    case 1: RecherchePublic.display(); break;
                    case 2: modifie_profil(); break;
                    case 3: Inscription.inscriptionProduit(getNom()); break;
                    case 4: GestionProduit.display(getNom()); break;
                    case 5: GestionProduit.supprimerProduits(getNom()); break;
                    case 6: //actions(); break;    modifier etat, repond aux problemes, confirmer reception, expedier les produits
                    case 7: VisualiserRevendeur voir = new VisualiserRevendeur(getNom());
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


    private void menuMsg() {
        System.out.println("\n");
        System.out.println("Bonsoir cher Revendeur:" + getNom() + "              " + getCourriel() );
        System.out.println("----------------------------------------------------------" );
        System.out.println("0. deconnecter");
        System.out.println("1. Recherche");
        System.out.println("2. Modifier le Profil");
        System.out.println("3. Offrir un Produit");
        System.out.println("4. Gerer les produits");
        System.out.println("5. Supprimer un produit");
        System.out.println("6. Tous actions");
        System.out.println("7. Voir nos informations");
        System.out.print("\n");
    }


















    /**
     * Modifie le profil du revendeur, permettant de mettre à jour des informations telles que l'adresse, le courriel,
     * le téléphone, etc.
     *
     * @return Aucune valeur de retour.
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

                    case ":q": displayMenuRevendeur(); break;
                    case "telephone":
                        System.out.print("Votre recent telephone est: " + telephone + "\n");
                        String telephone = InputRestreint.getValidTelephone("quel est votre nouveau telephone: ");
                        this.telephone = telephone;
                        break;
                    case "adresse":
                        System.out.print("Votre recent adresse est: " + adresse + "\n");
                        String adresse = InputRestreint.getValidAdresse("quel est votre nouveau adresse: ");
                        this.adresse = adresse;
                        break;
                    case "password":
                        System.out.print("Votre recent password est: " + "********" + "\n");
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
        System.out.println("\n");
        System.out.println("Cher revendeur: " + getNom() + ", Voici votre profil");
        System.out.println("-----------------------------------------------------");
        System.out.println("adresse: " + adresse);
        System.out.println("telephone: " + telephone);
        System.out.println("password: " + "********");
        System.out.println("-----------------------------------------------------");

    }


    private void saveChanges() {
        String nbLikes = Integer.toString(getNbLikerPar());

        List<String> newCSVLine = Arrays.asList(nom, getPassword(), courriel, getAdresse(), getTelephone(),nbLikes);

        String directoryPath = DatabasePath.getPathRevendeurCompte() + nom + "/main.csv";

        String temp = OutilSupplementaire.removeSpacesBetweenCommas(FormatAdjust.transformVersString(newCSVLine));
        CSVHandler.coverCSV(directoryPath, temp);

        Database.refreshRevendeurs();


    }

    public static void modified(){
        List<String[]> userData = new ArrayList<>();

        System.out.println(getNbLikerPar());
        String likes1 = Integer.toString(getNbLikerPar());



        userData.add(new String[]{getNom(),getPassword(),getCourriel(),getAdresse(),getTelephone(),likes1});

        String directoryPath = DatabasePath.getPathRevendeurCompte() + getNom() + "/main.csv";
        CSVHandler.coverCSV(directoryPath, userData);

        Database.refreshRevendeurs();
    }

    public void displayInfoAuPublic() {
        System.out.println("\n\nVoici les infos du revendeur: " + getNom());
        System.out.println("-----------------------------------------------------");

        System.out.println("nombre de produits offer: " + getNbOffrir());
        System.out.println(getOffrir());
        System.out.println("------------\n");


        System.out.println("nombre de fans: " + getNbFans());
        System.out.println(getFans());
        System.out.println("------------\n");

        System.out.println("nombre de likes: " + getNbLikerPar());
        System.out.println(getLikerPar());
        System.out.println("------------\n");

        System.out.print("Entrez quelque chose pour retourner a la recherche");
        String decision = myScanner.getStringInput();

        if (decision != null) return;
    }




    public static String getNom() {
        return nom;
    }

    public static String getTelephone() {
        return telephone;
    }

    public static String getAdresse() {
        return adresse;
    }

    public static int getNbLikerPar() {
        return nbLikerPar;
    }

    public static String getPassword() {
        return password;
    }

    public static String getCourriel() {
        return courriel;
    }

    public void setNbLikerPar(int nbLikes) {
        this.nbLikerPar = nbLikerPar;
    }

    public static int getNbFans() {
        return nbFans;
    }

    public static int getNbOffrir() {
        return nbOffrir;
    }

    public static List<String> getFans() {
        return fans;
    }

    public static List<String> getNotifications() {
        return notifications;
    }

    public static List<String> getOffrir() {
        return offrir;
    }

    public static List<String> getResolution() {
        return resolution;
    }

    public static List<String> getRetourEchange() {
        return retourEchange;
    }

    public static List<String> getSignalProbleme() {
        return signalProbleme;
    }

    public static List<String> getLikerPar() {
        return likerPar;
    }
}



