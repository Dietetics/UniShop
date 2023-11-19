import java.util.*;

public class ProfilAcheteur {
    private static Scanner scanner = new Scanner(System.in);

    private String pseudo;
    private String courriel;
    private String acheteurPath = "src/data/acheteur.csv";
    private String produitPath = "src/data/produits.csv";



    public ProfilAcheteur(String pseudo, String courriel) {

        this.pseudo = pseudo;
        this.courriel = courriel;

        displayMenuAcheteur();
    }

    public static void main(String[] args) {
        scanner.close();
    }


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
                System.out.println("10. void les points du programme de fidelite");
                System.out.print("\n");

                System.out.print("Choix : ");
                choix = scanner.nextInt();

                switch (choix) {
                    case 0:
                        System.out.println("Votre compte est bien deconnecter: " + pseudo + "-------");
                        D2.displayMenuPrincipale();
                        break;
                    case 1: modifie_profil(); break;
                    case 2: catalogue(); break;
                    case 3: rechercheProduits(); break;
                    case 4: trouverAcheteur(); break;
                    case 5: trouverRevendeur(); break;
                    case 6: panierAchat(); break;
                    case 7: gestionCommandes(); break;
                    case 8: voir_metriques(); break;
                    case 9: voir_notification(); break;
                    case 10: voir_pts(); break;
                    default:
                        System.out.println("Choix invalide. Veuillez reessayer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer un nombre entier.");
                scanner.nextLine(); // Effacer la ligne incorrecte dans le scanner
            }
        }
    }

    public void modifie_profil(){

        int lineIndex = CSVHandler.findOccurrenceIndex(getAcheteurPath(),getPseudo(),2);
        String data = CSVHandler.readLineByIndex(getAcheteurPath(),lineIndex);

        String nom = CSVHandler.getColumnValue(data, 0);
        String prenom = CSVHandler.getColumnValue(data, 1);
        String pseudo = CSVHandler.getColumnValue(data, 2);
        String courriel = CSVHandler.getColumnValue(data, 3);
        String telephone = CSVHandler.getColumnValue(data, 4);
        String adresse = CSVHandler.getColumnValue(data, 5);

        String choix = "90";
        while (choix != ":q") {
            try {
                System.out.println("\n");
                System.out.println("Cher acheteur: " + getPseudo() + ", Voici votre profil");
                System.out.println("-----------------------------------------------------");
                System.out.println("nom: " + nom);
                System.out.println("prenom: " + prenom);
                System.out.println("pseudo: " + pseudo);
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
                    case "pseudo":
                        Boolean conditionPseudo = true;
                        while (conditionPseudo) {
                            try{
                            System.out.print("Votre recent pseudo est: " + pseudo + "\nquel est votre nouveau pseudo: ");
                            String scannedPseudo = scanner.next();

                            if (!InputRestreint.isValidUniqueRow(acheteurPath,scannedPseudo,2)) {
                                throw new IllegalArgumentException("votre pseudo est pris, veuillez entre un nouveau");
                            }
                            pseudo = scannedPseudo;
                            conditionPseudo = false;}
                            catch (Exception e) {
                                System.out.println("Erreur: " + e.getMessage());
                                scanner.nextLine();
                            }
                        }
                        break;
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
                        List<String> newCSVLine = Arrays.asList(nom, prenom, pseudo, courriel, telephone, adresse);
                        int lineIndex2 = CSVHandler.findOccurrenceIndex(getAcheteurPath(),getPseudo(),2);
                        CSVHandler.uploadCSVLine(acheteurPath,lineIndex2-1,newCSVLine);
                        setPseudo(pseudo);
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
                    case 1: liker(1); break;
                    case 2: liker(2); break;
                    case 3: liker(3); break;
                    case 4: liker(4); break;
                    case 5: liker(5); break;
                    case 6: liker(6); break;
                    case 7: liker(7); break;
                    case 8: liker(8); break;
                    case 9: liker(9); break;
                    case 10: liker(10); break;
                    default:
                        System.out.println("Choix invalide. Veuillez reessayer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer un nombre entier.");
                scanner.nextLine(); // Effacer la ligne incorrecte dans le scanner
            }
        }
    }

    public void liker(int index){

        try {
            String line = CSVHandler.readLineByIndex(getProduitPath(),index);
            String valeur = CSVHandler.getColumnValue(line,9);

            int intValue = Integer.parseInt(valeur);
            intValue = intValue + 1;
            String strValue = String.valueOf(intValue);

            String newLine = CSVHandler.updateCSVColumn(line,9,strValue);

            CSVHandler.uploadCSVLine(getProduitPath(),index-1,newLine);

        } catch (NumberFormatException e) {
            System.out.println("La chaine n'est pas un nombre valide.");
        }
    }
    public void rechercheProduits() {
        boolean quitter = false;
        String essai = "Entrez le mot-cle de recherche ou ':q' (pour quitter):";
        int colomn = 0;

        while (!quitter) {
            try {
                System.out.println("");
                System.out.println(essai);
                essai = "Entrez un nouveau mot-cle de recherche ou ':q' (pour quitter):";
                String keyword = scanner.next();

                switch (keyword.toLowerCase()) {
                    case ":q":
                        quitter = true;
                        break;
                    default:
                        List<String> matchingLines = CSVHandler.searchKeywordInCSV(getProduitPath(), keyword);
                        if (!matchingLines.isEmpty()) {
                            CSVHandler.SortOrder order = null;

                            do {
                                try {
                                    System.out.println(" ");
                                    System.out.println("choisir le type:");
                                    System.out.println("---------------------");
                                    System.out.println("categorie");
                                    System.out.println("prix");
                                    System.out.println("noteMoyenne");
                                    System.out.println("popularite");
                                    System.out.println("promo");
                                    String type = scanner.next();

                                    switch (type.toLowerCase()) {
                                        case "categorie":
                                            colomn = 2;
                                            break;
                                        case "prix":
                                            colomn = 5;
                                            break;
                                        case "noteMoyenne":
                                            colomn = 12;
                                            break;
                                        case "popularite":
                                            colomn = 11;
                                            break;
                                        case "promo":
                                            colomn = 13;
                                            break;
                                        default:
                                            System.out.println("Choix invalide. Veuillez reessayer.");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Erreur : " + e.getMessage());
                                    scanner.nextLine(); // Effacer la ligne incorrecte dans le scanner
                                }
                            } while (colomn == 0); // Répéter tant que colomn n'est pas initialisé

                            do {
                                try {
                                    System.out.println(" ");
                                    System.out.println("Choisir l'ordre (ASCENDING ou DESCENDING):");
                                    System.out.println("---------------------");
                                    order = CSVHandler.SortOrder.valueOf(scanner.next());
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Ordre invalide. Veuillez choisir ASCENDING ou DESCENDING.");
                                }
                            } while (order == null); // Répéter tant que order n'est pas initialisé

                            List<String[]> data = FormatAdjust.transformList(matchingLines);
                            CSVHandler.sortByColumn(data, colomn - 1, order);
                            for (String[] row : data) {
                                for (String value : row) {
                                    System.out.print(value + " ");
                                }
                                System.out.println();
                            }
                        } else {
                            System.out.println("Aucun produit trouve selon le mot-cle.");
                        }
                        break;
                }
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
                scanner.nextLine(); // Effacer la ligne incorrecte dans le scanner
            }
        }
    }




    public void trouverAcheteur(){
        System.out.println("En developement");
    }
    public void trouverRevendeur(){
        System.out.println("En developement");
    }
    public void panierAchat(){
        System.out.println("En developement");
    }
    public void gestionCommandes(){
        System.out.println("En developement");
    }
    public void voir_metriques(){
        System.out.println("En developement");
    }
    public void voir_notification(){
        System.out.println("En developement");
    }
    public void voir_pts(){
        System.out.println("En developement");
    }




    public String getPseudo() {
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