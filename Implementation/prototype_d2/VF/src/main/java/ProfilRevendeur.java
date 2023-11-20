import java.util.*;

public class ProfilRevendeur {
    private static Scanner scanner = new Scanner(System.in);

    private String nom;
    private String courriel;
    private String revendeurPath="data/revendeur.csv";
    private String produitsPath="data/produits.csv";

    public ProfilRevendeur(String nom, String courriel) {

        this.nom = nom;
        this.courriel = courriel;

        displayMenuRevendeur();
    }

    public static void main(String[] args) {
        scanner.close();
    }

    /**
     * Affiche le menu principal pour le revendeur, lui permettant de choisir parmi différentes options telles que
     * la modification du profil, l'offre d'un produit, la gestion de l'état d'une commande, etc.
     */
    public void displayMenuRevendeur() {
        int choix = 90;
        while (choix != 0) {
            try {
                System.out.println("\n");
                System.out.println("Bonsoir cher Revendeur:" + getNom() + "              " + getCourriel() );
                System.out.println("----------------------------------------------------------" );
                System.out.println("0. deconnecter");
                System.out.println("1. modifier le Profil");
                System.out.println("2. Offrir un Produit");
                System.out.println("3. modifier l'etat d'une commande");
                System.out.println("4. voir les metriques");
                System.out.println("5. gestion de probleme");
                System.out.println("6. gererCommandes");
                System.out.println("7. offrir une promo");
                System.out.println("8. voir les notifications");
                System.out.print("\n");

                System.out.print("Choix : ");
                choix = scanner.nextInt();

                switch (choix) {
                    case 0:
                        System.out.println("Votre compte est bien deconnecter: " + nom + "-------");
                        VF.displayMenuPrincipale();
                        break;
                    case 1: modifie_profil(); break;
                    case 2: offrir_produit(); break;
                    case 3: System.out.println("option choisi est en maintenance "); break;
                    case 4: Metrique.voir_metriques(); break;
                    case 5: GestionProbleme.gestionProblemes(); break;
                    case 6: GestionCommande.GererCommandes(); break;
                    case 7: offrirPromotion(); break;
                    case 8: Notification.recevoirNotifications(); break;
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
     * Modifie le profil du revendeur, permettant de mettre à jour des informations telles que l'adresse, le courriel,
     * le téléphone, etc.
     *
     * @return Aucune valeur de retour.
     */
    public void modifie_profil(){

        int lineIndex = CSVHandler.findOccurrenceIndex(getRevendeurPath(),getNom(),0);
        String data = CSVHandler.readLineByIndex(getRevendeurPath(),lineIndex);

        String nom = CSVHandler.getColumnValue(data, 0);
        String adresse = CSVHandler.getColumnValue(data, 1);
        String courriel = CSVHandler.getColumnValue(data, 2);
        String telephone = CSVHandler.getColumnValue(data, 3);

        String choix = "90";
        while (choix != ":q") {
            try {
                System.out.println("\n");
                System.out.println("Cher revendeur: " + getNom() + ", Voici votre profil");
                System.out.println("-----------------------------------------------------");
//                System.out.println("nom: " + nom);
                System.out.println("adresse: " + adresse);
                System.out.println("courriel: " + courriel);
                System.out.println("telephone: " + telephone);
                System.out.println("-----------------------------------------------------");
                System.out.println(":e pour enregistrer la modification ou :q pour quitter");
                System.out.print("\n");

                System.out.print("Choix : ");
                choix = scanner.next();


                switch (choix) {

                    case ":q": displayMenuRevendeur(); break;
//                    case "nom":
//                        Boolean conditionNom = true;
//                        while (conditionNom) {
//                            try{
//                                System.out.print("Votre recent nom est: " + nom + "\nquel est votre nouveau nom: ");
//                                String scannedNom = scanner.next();
//
//                                if (!InputRestreint.isValidInput(scannedNom)) {
//                                    throw new IllegalArgumentException("Le nom doit contenir au moins 2 caracteres alphabetiques.");
//                                }
//                                nom = scannedNom;
//                                conditionNom = false;}
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
                        List<String> newCSVLine = Arrays.asList(nom, adresse, courriel, telephone);
                        int lineIndex2 = CSVHandler.findOccurrenceIndex(getRevendeurPath(),getNom(),0);
                        CSVHandler.uploadCSVLine(revendeurPath,lineIndex2-1,newCSVLine);
//                        setNom(nom);
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
    public void offrir_produit(){

        System.out.println("\n");
        System.out.println("Cher revendeur: " + getNom() + "      " + "Page: ajout des produits");
        System.out.println("-----------------------------------------------------");

        Boolean condition = true;
        while (condition) {
            try {
                System.out.print("Veuillez entrer un titre(unique): ");
                String titre = scanner.next();


                if (!InputRestreint.isValidUniqueRow(getProduitsPath(), titre, 0)) {
                    throw new IllegalArgumentException("Le nom doit etre unique.");
                }

                System.out.println("Veuillez indiquer le categorie: ");
                System.out.println("-----------------------------------------------------");
                System.out.println("LivresManuels");
                System.out.println("ArticlesPapeterie");
                System.out.println("MaterielInformatique");
                System.out.println("EquipementBureau");
                System.out.println("RessourcesDapprentissage");
                System.out.println("-----------------------------------------------------");
                String categorie = scanner.next();

                if (!InputRestreint.isValidType(categorie)) {
                    throw new IllegalArgumentException("le categorie doit etre ceux du haut");
                }

                System.out.print("Veuillez entrer une description: ");
                String desc = scanner.next();


                if (!InputRestreint.isValidMots(desc)) {
                    throw new IllegalArgumentException("Le nombre de mots doit etre inferieur a 100");
                }


                System.out.print("Veuillez entrer le quantite initial: ");
                Integer quantite0 = scanner.nextInt();

                if (!InputRestreint.isValidInt(quantite0)) {
                    throw new IllegalArgumentException("Entrez un nombre et On ne peut que stocker au max 9999items");
                }

                System.out.print("Veuillez entrer un prix: ");
                Integer prix = scanner.nextInt();

                if (!InputRestreint.isValidInt(prix)) {
                    throw new IllegalArgumentException("Entrez un nombre et le prix doit etre inferieure a 9999$");
                }


                System.out.print("Veuillez entrer le nombre de points bonus ou 0: ");
                Double points = scanner.nextDouble();

                if (!InputRestreint.isValidDouble(points)) {
                    throw new IllegalArgumentException("Entrez un nombre a <=20, puis 0 pour aucun point");
                }

                System.out.println("Avez vous des images a ajouter? ");
                System.out.println("cette fonction est encore en developpement, veuillez rajouter des images plutard");
                System.out.println("Avez vous des videos a ajouter? ");
                System.out.println("cette fonction est encore en developpement, veuillez rajouter des videos plutard");

                condition = false;

                System.out.println("Donnees enregistrees avec succes");
                System.out.println("--------------------------------");

                List<String[]> produitData = new ArrayList<>();
                String quantite0new = String.valueOf(quantite0);
                String prixnew = String.valueOf(prix);
                String pointsnew = String.valueOf(points);
                produitData.add(new String[]{titre, categorie, desc, quantite0new, prixnew, pointsnew, "non", "non"});

                CSVHandler.appendCSV(getProduitsPath(), produitData);


                break;
            } catch (Exception e) {
                System.out.println("Erreur: " + e.getMessage());
                scanner.nextLine();
            }
        }

    }





    public void offrirPromotion() {
        try {
            System.out.println("----- Offrir une Promotion sur un Produit -----");

            // Simulation du revendeur offrant une promotion
            System.out.println("Entrez le nom du produit sur lequel vous souhaitez offrir une promotion : ");
            String nomProduit = scanner.nextLine();

            System.out.println("Entrez le montant de la promotion (baisse de prix ou points bonus) : ");
            double montantPromotion = scanner.nextDouble();

            System.out.println("Entrez la duree de la promotion (en jours) : ");
            int dureePromotion = scanner.nextInt();

            // Illustration de l'offre de la promotion
            System.out.println("\nPromotion Offerte :");
            System.out.println("Produit : " + nomProduit);
            System.out.println("Montant de la Promotion : " + montantPromotion);
            System.out.println("Duree de la Promotion : " + dureePromotion + " jours");

            // Simulation du revendeur appliquant la promotion sur le produit
            System.out.println("\nLa promotion a ete offerte avec succes sur le produit.");

        } catch (Exception e) {
            System.out.println("Erreur lors de l'offre de la promotion : " + e.getMessage());
        }
    }



    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCourriel() {
        return courriel;
    }


    public String getRevendeurPath() {
        return revendeurPath;
    }

    public String getProduitsPath() {
        return produitsPath;
    }
}



