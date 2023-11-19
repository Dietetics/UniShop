import java.util.*;
// auteur :Yongkang He 
public class D2 {
    private static String produitPath = "src/data/produits.csv";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        displayMenuPrincipale();
        scanner.close();
    }

    public static void displayMenuPrincipale() {
        int choix = 90;
        while (choix != 0) {
            try {
                System.out.println("\n");
                System.out.println("------ Bienvenu a notre plateforme UniShop -------");
                System.out.println("0. Quitter");
                System.out.println("1. Rechercher un produit");
                System.out.println("2. Inscription");
                System.out.println("3. Connexion");
                System.out.print("\n");

                System.out.print("Choix : ");
                choix = scanner.nextInt();

                switch (choix) {
                    case 0: System.out.println("Au revoir !");
                    System.exit(choix);
                        break;
                    case 1: rechercheProduits(); break;
                    case 2: inscription(); break;
                    case 3: connecter(); break;
                    default:
                        System.out.println("Choix invalide. Veuillez reessayer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer un nombre entier.");
                scanner.nextLine(); // Effacer la ligne incorrecte dans le scanner
            }
        }
    }



    public static void rechercheProduits() {
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




    public static void inscription() {
        boolean quitter = false;

        String essai = "----- Bienvenu a la page d'inscription ----- \n:a pour acheteur \n:r pour revendeur " +
                "\n:q pour quitter";

        while (!quitter) {
            try {
            System.out.println("");
            System.out.println(essai);
            essai = "D'autres choses? \n:a pour acheteur \n:r pour revendeur \n:q pour quitter";
            String keyword = scanner.next();

            switch (keyword.toLowerCase()) {
                case ":q": quitter = true; break;
                case ":a": inscriptionAcheteur(); break;
                case ":r": inscriptionRevendeur(); break;
                default: System.out.println("Choix invalide. Veuillez entrer :a, :r ou :q."); break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur de saisie. Veuillez entrer une option valide.");
                scanner.nextLine(); // Effacer la ligne incorrecte dans le scanner
            }
        }
        displayMenuPrincipale();
    }






    public static void inscriptionAcheteur() {
        String acheteurPath = "src/data/acheteur.csv";
        System.out.println("----- Bienvenu a notre page d'inscription pour devenir Acheteur -----");

        Boolean condition = true;
        while (condition) {
            try {
                System.out.print("Veuillez entrer votre nom: ");
                String nom = scanner.next();


                System.out.print("Veuillez entrer votre prenom: ");
                String prenom = scanner.next();

                if (!InputRestreint.isValidInput(nom) || !InputRestreint.isValidInput(prenom)) {
                    throw new IllegalArgumentException("Le nom et prenom doivent contenir au moins 2 caracteres alphabetiques.");
                }

                System.out.print("Veuillez entrer votre adresse dexpedition: ");
                String adresse = scanner.next();

                if (!InputRestreint.isValidAddress(adresse)) {
                    throw new IllegalArgumentException("adresse doit avoir un longueur inferieur a 20");
                }

                System.out.print("Veuillez entrer votre adresse courriel: ");
                String courriel = scanner.next();


                if (!InputRestreint.isValidCourriel(courriel)) {
                    throw new IllegalArgumentException("Le courriel doit terminer par @gamil.com ou @umontreal.ca");
                }


                System.out.print("Veuillez entrer votre telephone: ");
                String telephone = scanner.next();

                if (!InputRestreint.isValidTelephone(telephone)) {
                    throw new IllegalArgumentException("Le telephone doit etre composer de 10 chiffres");
                }


                System.out.print("Veuillez entrer votre pseudo(unique): ");
                String pseudo = scanner.next();


                if (!InputRestreint.isValidUniqueRow(acheteurPath,pseudo,2)) {
                    throw new IllegalArgumentException("votre pseudo est pris, veuillez entre un nouveau");
                }

                condition = false;

                System.out.println("Donnees enregistrees avec succes");

                List<String[]> userData = new ArrayList<>();
                userData.add(new String[]{nom, prenom, adresse, courriel, telephone, pseudo});

                CSVHandler.appendCSV(acheteurPath, userData);

                inscription();
            } catch (Exception e) {
                System.out.println("Erreur: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }



    public static void inscriptionRevendeur() {
        String revendeurPath = "src/data/revendeur.csv";
        System.out.println("----- Bienvenu a notre page d'inscription pour devenir revendeur -----");

        Boolean condition = true;
        while (condition) {
            try {
                System.out.print("Veuillez entrer votre nom(unique): ");
                String nom = scanner.next();


                if (!InputRestreint.isValidUniqueRow(revendeurPath, nom, 0)) {
                    throw new IllegalArgumentException("Le nom doit etre unique.");
                }

                System.out.print("Veuillez entrer votre adresse: ");
                String adresse = scanner.next();

                if (!InputRestreint.isValidAddress(adresse)) {
                    throw new IllegalArgumentException("adresse doit avoir un longueur inferieur a 20");
                }

                System.out.print("Veuillez entrer votre adresse courriel: ");
                String courriel = scanner.next();


                if (!InputRestreint.isValidCourriel(courriel)) {
                    throw new IllegalArgumentException("Le courriel doit terminer par @gamil.com ou @umontreal.ca");
                }


                System.out.print("Veuillez entrer votre telephone: ");
                String telephone = scanner.next();

                if (!InputRestreint.isValidTelephone(telephone)) {
                    throw new IllegalArgumentException("Le telephone doit etre composer de 10 chiffres");
                }

                condition = false;

                System.out.println("Donnees enregistrees avec succes");

                List<String[]> userData = new ArrayList<>();
                userData.add(new String[]{nom, adresse, courriel, telephone});

                CSVHandler.appendCSV(revendeurPath, userData);

                inscription();
            } catch (Exception e) {
                System.out.println("Erreur: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }









    public static void connecter() {
        System.out.println("----- Bienvenu a notre page de connexion -----");
        System.out.println("Etes-vous un revendeur ou un acheteur");
        System.out.println(":q Retourner a la page d'acceuil");
        System.out.println(":a Acheteur");
        System.out.println(":r Revendeur");
        System.out.println("Veuillez choisir un choix: ");


        boolean quitter = false;

        while (!quitter) {
            try {
                String keyword = scanner.next();

                switch (keyword.toLowerCase()) {
                    case ":q": quitter = true; break;
                    case ":a": connectAcheteur(); break;
                    case ":r": connectRevendeur(); break;

                    default: System.out.println("Choix invalide. Veuillez entrer :a, :r ou :q."); break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur de saisie. Veuillez entrer une option valide.");
                scanner.nextLine(); // Effacer la ligne incorrecte dans le scanner
            }
        }
    }



    public static void connectRevendeur(){

        String revendeurPath = "src/data/revendeur.csv";
        System.out.println("----- Veuillez vous connectez -----");

        Boolean condition = true;
        while (condition) {
            try {
                System.out.println("Nom: ");
                String nom = scanner.next();
                int index = CSVHandler.findOccurrenceIndex(revendeurPath,nom,0);
                if (index == -1) {
                    throw new IllegalArgumentException("votre compte n'est pas trouver dans le systeme. " +
                            "entre de nouveau votre nom");
                }

                System.out.println("Courriel: ");
                String courriel = scanner.next();
                Boolean valide = CSVHandler.isValueAtIndexAndColumn(revendeurPath, index-1, 2, courriel );
                if (!valide) {
                    throw new IllegalArgumentException("vous avez saisi le mauvais couriel, rapplez que ca termine par" +
                            " @gamil.com ou @umontreal.ca");
                }

                condition = false;

                new ProfilRevendeur(nom,courriel);
            } catch (Exception e) {
                System.out.println("Erreur: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }




    public static void connectAcheteur(){

        String acheteurPath = "src/data/acheteur.csv";
        System.out.println("----- Veuillez vous connectez -----");

        Boolean condition = true;
        while (condition) {
            try {
                System.out.println("Pseudo: ");
                String pseudo = scanner.next();
                int index = CSVHandler.findOccurrenceIndex(acheteurPath,pseudo,2);
                if (index == -1) {
                    throw new IllegalArgumentException("votre compte n'est pas trouver dans le systeme. " +
                            "entre de nouveau votre pseudo");
                }

                System.out.println("Courriel: ");
                String courriel = scanner.next();
                Boolean valide = CSVHandler.isValueAtIndexAndColumn(acheteurPath, index-1, 3, courriel );
                if (!valide) {
                    throw new IllegalArgumentException("vous avez saisi le mauvais couriel, rapplez que ca termine par" +
                            " @gamil.com ou @umontreal.ca");
                }

                condition = false;

                new ProfilAcheteur(pseudo,courriel);
            } catch (Exception e) {
                System.out.println("Erreur: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }


    public static String getProduitPath() {
        return produitPath;
    }
}



