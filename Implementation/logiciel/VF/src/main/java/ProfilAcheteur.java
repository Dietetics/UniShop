import java.util.*;

public class ProfilAcheteur {
    private static Scanner scanner = new Scanner(System.in);
    private static String pseudo, password, nom, prenom, courriel, telephone, adresse, nbLikes;
    public ProfilAcheteur(String pseudo, String password) {

        this.pseudo = pseudo;
        this.password = password;

        int lineIndex = CSVHandler.findOccurrenceIndex(DatabasePath.getAcheteurPath(),getPseudo(),2);
        String data = CSVHandler.readLineByIndex(DatabasePath.getAcheteurPath(),lineIndex);

        String nom = CSVHandler.getColumnValue(data, 0);
        String prenom = CSVHandler.getColumnValue(data, 1);
        String courriel = CSVHandler.getColumnValue(data, 3);
        String telephone = CSVHandler.getColumnValue(data, 4);
        String adresse = CSVHandler.getColumnValue(data, 5);
        String nbLikes = CSVHandler.getColumnValue(data, 6);

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
                offrirOption();
                System.out.print("Choix : ");
                choix = scanner.nextInt();

                switch (choix) {
                    case 0:
                        System.out.println("Votre compte est bien deconnecter: " + pseudo + "-------");
                        VF.displayMenuPrincipale();
                        break;
                    case 1: modifie_profil(); break;
                    case 2: catalogue(); break;
                    case 3: RecherchePublic.rechercheProduits(); break;
                    case 4:
                        int index = CSVHandler.findOccurrenceIndex(DatabasePath.getAcheteurPath(),getPseudo(),2);
                        int[] excludedColumns = {2, 3};
                        Recherche.trouverAcheteur(index,excludedColumns); break;
                    case 5: Recherche.trouverRevendeur();; break;
                    case 6: PanierAchat.panierAchat(); break;
                    case 7: Histoire.pageHistoire(); break;
                    case 8: Metrique.voir_metriques(); break;
                    case 9: Notification.recevoirNotifications(); break;
                    case 10: voir_pts(); break;
                    case 11: SignalementProbleme.signalerProblemeProduit(); break;
                    default:
                        System.out.println("Choix invalide. Veuillez reessayer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer un nombre entier.");
                scanner.nextLine(); // Effacer la ligne incorrecte dans le scanner
            }
        }
    }
    private void offrirOption() {
        System.out.println("\n");
        System.out.println("Bonsoir cher Acheteur:" + getPseudo() + "              " + getCourriel() );
        System.out.println("----------------------------------------------------------" );
        System.out.println("0. deconnecter");
        System.out.println("1. modifier le Profil");
        System.out.println("2. catalogue de produits");
        System.out.println("3. recherche de produits");
        System.out.println("4. trouver un acheteur"); // suivre
        System.out.println("5. trouver un revendeur");
        System.out.println("6. panier d'achat"); // passer une commande
        System.out.println("7. gestion de commandes"); // confirmer la reception,  retournerUechanger, commenter et evaluer un produit
        System.out.println("8. voir les metriques");
        System.out.println("9. voir les notifications");
        System.out.println("10. voir les points du programme de fidelite");
        System.out.println("11. signaler un probleme");
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
                System.out.println(":e pour enregistrer la modification ou :q pour quitter");
                System.out.print("\nChoix : ");
                choix = scanner.next();

                switch (choix) {

                    case ":q": displayMenuAcheteur(); break;
                    case "nom":
                        System.out.print("Votre recent nom est: " + nom + "\n");
                        String nom = InputRestreint.getValidInput("quel est votre nouveau nom: ");
                        this.nom = nom;
                        break;
                    case "prenom":
                        System.out.print("Votre recent prenom est: " + prenom + "\n");
                        String prenom = InputRestreint.getValidInput("quel est votre nouveau prenom: ");
                        this.prenom = prenom;
                        break;
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
                scanner.nextLine(); // Effacer la ligne incorrecte dans le scanner
            }
        }
    }


    private void displayProfile() {
        System.out.println("\nCher acheteur: " + getPseudo() + ", Voici votre profil");
        System.out.println("-----------------------------------------------------");
        System.out.println("nom: " + nom);
        System.out.println("prenom: " + prenom);
        System.out.println("telephone: " + telephone);
        System.out.println("adresse: " + adresse);
        System.out.println("password: " + "********");
        System.out.println("-----------------------------------------------------");
    }

    private void saveChanges() {
        List<String> newCSVLine = Arrays.asList(getNom(), getPrenom(), pseudo, courriel, getTelephone(), getAdresse(), nbLikes, getPassword());
        int lineIndex2 = CSVHandler.findOccurrenceIndex(DatabasePath.getAcheteurPath(),getPseudo(),2);
        CSVHandler.uploadCSVLine(DatabasePath.getAcheteurPath(),lineIndex2-1,newCSVLine);
    }



    /**
     * Affiche le catalogue des produits et permet à l'acheteur de liker un produit.
     */
    public void catalogue(){
        int choix = 99;
        while (choix != 0) {
            try {
                System.out.println("\n");
                System.out.println("Cher acheteur: " + getPseudo() + ", Voici nos produits");
                System.out.println("-----------------------------------------------------");
                CSVHandler.printCSV(CSVHandler.readCSV(DatabasePath.getProduitPath(),10));
                System.out.println("-----------------------------------------------------");
                System.out.print("\n");

                System.out.print("0 pour retourner ou Cliquant le numero pour liker : ");
                choix = scanner.nextInt();

                switch (choix) {
                    case 0:
                        displayMenuAcheteur();
                        break;
                    case 1: Operation.likerProduit(1); break;
                    case 2: Operation.likerProduit(2); break;
                    case 3: Operation.likerProduit(3); break;
                    case 4: Operation.likerProduit(4); break;
                    case 5: Operation.likerProduit(5); break;
                    case 6: Operation.likerProduit(6); break;
                    case 7: Operation.likerProduit(7); break;
                    case 8: Operation.likerProduit(8); break;
                    case 9: Operation.likerProduit(9); break;
                    case 10: Operation.likerProduit(10); break;
                    default:
                        System.out.println("Choix invalide. Veuillez reessayer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer un nombre entier.");
                scanner.nextLine(); // Effacer la ligne incorrecte dans le scanner
            }
        }
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