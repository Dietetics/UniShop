import java.util.*;

public class ProfilAcheteur {
    private static Scanner scanner = new Scanner(System.in);

    private static String pseudo;
    private String courriel;
    private String acheteurPath = "src/main/resources/data/acheteur.csv";
    private String produitPath = "src/main/resources/data/produits.csv";



    public ProfilAcheteur(String pseudo, String courriel) {

        this.pseudo = pseudo;
        this.courriel = courriel;

        displayMenuAcheteur();
    }

    public static void main(String[] args) {
        scanner.close();
    }

    /**
     * Affiche le menu principal pour l'acheteur, lui permettant de choisir parmi différentes options telles que
     * la modification du profil, la consultation du catalogue de produits, la recherche de produits, etc.
     */
    public void displayMenuAcheteur() {
        int choix = 90;
        while (choix != 0) {
            try {
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

                System.out.print("Choix : ");
                choix = scanner.nextInt();

                switch (choix) {
                    case 0:
                        System.out.println("Votre compte est bien deconnecter: " + pseudo + "-------");
                        VF.displayMenuPrincipale();
                        break;
                    case 1: modifie_profil(); break;
                    case 2: catalogue(); break;
                    case 3: Recherche.rechercheProduits(); break;
                    case 4:
                        int index = CSVHandler.findOccurrenceIndex(getAcheteurPath(),getPseudo(),2);
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
    /**
     * Modifie le profil de l'acheteur en affichant et permettant la modification des informations telles que le nom,
     * le prénom, le courriel, le téléphone et l'adresse.
     */
    public void modifie_profil(){

        int lineIndex = CSVHandler.findOccurrenceIndex(getAcheteurPath(),getPseudo(),2);
        String data = CSVHandler.readLineByIndex(getAcheteurPath(),lineIndex);

        String nom = CSVHandler.getColumnValue(data, 0);
        String prenom = CSVHandler.getColumnValue(data, 1);
        String pseudo = CSVHandler.getColumnValue(data, 2);
        String courriel = CSVHandler.getColumnValue(data, 3);
        String telephone = CSVHandler.getColumnValue(data, 4);
        String adresse = CSVHandler.getColumnValue(data, 5);
        String nbLikes = CSVHandler.getColumnValue(data, 6);


        String choix = "90";
        while (choix != ":q") {
            try {
                System.out.println("\n");
                System.out.println("Cher acheteur: " + getPseudo() + ", Voici votre profil");
                System.out.println("-----------------------------------------------------");
                System.out.println("nom: " + nom);
                System.out.println("prenom: " + prenom);
//                System.out.println("pseudo: " + pseudo);
                System.out.println("courriel: " + courriel);
                System.out.println("telephone: " + telephone);
                System.out.println("adresse: " + adresse);
                System.out.println("-----------------------------------------------------");
                System.out.println(":e pour enregistrer la modification ou :q pour quitter");
                System.out.print("\n");

                System.out.print("Choix : ");
                choix = scanner.next();


                switch (choix) {

                    case ":q": displayMenuAcheteur(); break;
                    case "nom":
                        Boolean conditionNom = true;
                        while (conditionNom) {
                            try{
                            System.out.print("Votre recent nom est: " + nom + "\nquel est votre nouveau nom: ");
                            String scannedNom = scanner.next();

                            if (!InputRestreint.isValidInput(scannedNom)) {
                                throw new IllegalArgumentException("Le nom doit contenir au moins 2 caracteres alphabetiques.");
                            }
                            nom = scannedNom;
                            conditionNom = false;}
                            catch (Exception e) {
                                System.out.println("Erreur: " + e.getMessage());
                                scanner.nextLine();
                            }
                        }
                        break;
                    case "prenom":
                        Boolean conditionPrenom = true;
                        while (conditionPrenom) {
                            try{
                            System.out.print("Votre recent prenom est: " + prenom + "\nquel est votre nouveau prenom: ");
                            String scannedPrenom = scanner.next();

                            if (!InputRestreint.isValidInput(scannedPrenom)) {
                                throw new IllegalArgumentException("Le prenom doit contenir au moins 2 caracteres alphabetiques.");
                            }
                            prenom = scannedPrenom;
                            conditionPrenom = false;}
                            catch (Exception e) {
                                System.out.println("Erreur: " + e.getMessage());
                                scanner.nextLine();
                            }
                        }
                        break;
//                    case "pseudo":
//                        Boolean conditionPseudo = true;
//                        while (conditionPseudo) {
//                            try{
//                            System.out.print("Votre recent pseudo est: " + pseudo + "\nquel est votre nouveau pseudo: ");
//                            String scannedPseudo = scanner.next();
//
//                            if (!InputRestreint.isValidUniqueRow(acheteurPath,scannedPseudo,2)) {
//                                throw new IllegalArgumentException("votre pseudo est pris, veuillez entre un nouveau");
//                            }
//                            pseudo = scannedPseudo;
//                            conditionPseudo = false;}
//                            catch (Exception e) {
//                                System.out.println("Erreur: " + e.getMessage());
//                                scanner.nextLine();
//                            }
//                        }
//                        break;
                    case "courriel":
                        Boolean conditionCourriel = true;
                        while (conditionCourriel) {try{
                            System.out.print("Votre recent courriel est: " + courriel + "\nquel est votre nouveau courriel: ");
                            String scannedCourriel = scanner.next();

                            if (!InputRestreint.isValidCourriel(scannedCourriel)) {
                                throw new IllegalArgumentException("Le courriel doit terminer par @gamil.com ou @umontreal.ca");
                            }
                            courriel = scannedCourriel;
                            conditionCourriel = false;}
                        catch (Exception e) {
                            System.out.println("Erreur: " + e.getMessage());
                            scanner.nextLine();
                        }
                        }
                        break;
                    case "telephone":
                        Boolean conditionTelephone = true;
                        while (conditionTelephone) {try{
                            System.out.print("Votre recent telephone est: " + telephone + "\nquel est votre nouveau telephone: ");
                            String scannedTelephone = scanner.next();

                            if (!InputRestreint.isValidTelephone(scannedTelephone)) {
                                throw new IllegalArgumentException("Le telephone doit etre composer de 10 chiffres");
                            }
                            telephone = scannedTelephone;
                            conditionTelephone = false;}
                        catch (Exception e) {
                            System.out.println("Erreur: " + e.getMessage());
                            scanner.nextLine();
                        }
                        }
                        break;
                    case "adresse":
                        Boolean conditionAdresse = true;
                        while (conditionAdresse) {try{
                            System.out.print("Votre recent adresse est: " + adresse + "\nquel est votre nouveau adresse: ");
                            String scannedAdresse = scanner.next();

                            if (!InputRestreint.isValidAddress(scannedAdresse)) {
                                throw new IllegalArgumentException("adresse doit avoir un longueur inferieur a 20");
                            }
                            adresse = scannedAdresse;
                            conditionAdresse = false;}
                        catch (Exception e) {
                            System.out.println("Erreur: " + e.getMessage());
                            scanner.nextLine();
                        }
                        }
                        break;
                    case ":e":
                        List<String> newCSVLine = Arrays.asList(nom, prenom, pseudo, courriel, telephone, adresse, nbLikes);
                        int lineIndex2 = CSVHandler.findOccurrenceIndex(getAcheteurPath(),getPseudo(),2);
                        CSVHandler.uploadCSVLine(acheteurPath,lineIndex2-1,newCSVLine);
//                        setPseudo(pseudo);
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
                CSVHandler.printCSV(CSVHandler.readCSV(getProduitPath(),10));
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






    public static String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getCourriel() {
        return courriel;
    }

    public String getAcheteurPath() {
        return acheteurPath;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public String getProduitPath() {
        return produitPath;
    }
}