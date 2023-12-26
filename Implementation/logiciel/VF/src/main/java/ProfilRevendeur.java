import java.util.*;

public class ProfilRevendeur {

    private static String nom,password,courriel,adresse,telephone;
    private static int nbLikes;


    public ProfilRevendeur(String nom) {

        this.nom = nom;


        int lineIndex = CSVHandler.findOccurrenceIndex(DatabasePath.getRevendeurPath(),getNom(),0);
        String data = CSVHandler.readLineByIndex(DatabasePath.getRevendeurPath(),lineIndex);

        String password = CSVHandler.getColumnValue(data, 1);
        String courriel = CSVHandler.getColumnValue(data, 2);
        String adresse = CSVHandler.getColumnValue(data, 3);
        String telephone = CSVHandler.getColumnValue(data, 4);
        String likes = CSVHandler.getColumnValue(data, 5);

        int nbLikes = Integer.parseInt(likes);

        this.password = password;
        this.courriel = courriel;
        this.telephone = telephone;
        this.adresse = adresse;
        this.nbLikes = nbLikes;


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
                    case 4: gererProduits(); break;
                    case 5: diffuserMedias(); break;
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

    public static void gererProduits(){}
    public static void diffuserMedias(){}


    private void menuMsg() {
        System.out.println("\n");
        System.out.println("Bonsoir cher Revendeur:" + getNom() + "              " + getCourriel() );
        System.out.println("----------------------------------------------------------" );
        System.out.println("0. deconnecter");
        System.out.println("1. Recherche");
        System.out.println("2. Modifier le Profil");
        System.out.println("3. Offrir un Produit");
        System.out.println("4. Gerer kes oridyuts");
        System.out.println("5. Diffuser les medias");
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
        String nbLikes = Integer.toString(getNbLikes());

        List<String> newCSVLine = Arrays.asList(nom, getPassword(), courriel, getAdresse(), getTelephone(),nbLikes);

        String directoryPath = DatabasePath.getRevendeurComptePath() + nom + "/main.csv";
        CSVHandler.coverCSV(directoryPath, FormatAdjust.transformList(newCSVLine));

        Database.refreshRevendeurs();
        System.out.println("les donnees sont bien enregistrer \n\n");

    }











    public static void modified(){
        List<String[]> userData = new ArrayList<>();

        System.out.println(getNbLikes());
        String likes1 = Integer.toString(getNbLikes());



        userData.add(new String[]{getNom(),getPassword(),getCourriel(),getAdresse(),getTelephone(),likes1});

        String directoryPath = DatabasePath.getRevendeurComptePath() + getNom() + "/main.csv";
        CSVHandler.coverCSV(directoryPath, userData);

        Database.refreshRevendeurs();
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

    public static int getNbLikes() {
        return nbLikes;
    }

    public static String getPassword() {
        return password;
    }

    public static String getCourriel() {
        return courriel;
    }

    public void setNbLikes(int nbLikes) {
        this.nbLikes = nbLikes;
    }
}



