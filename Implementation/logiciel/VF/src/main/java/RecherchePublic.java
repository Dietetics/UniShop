import java.util.Arrays;
import java.util.List;

public class RecherchePublic {


    public static void recuperer(String path, int info){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("Voici les resultats de recherche");
        System.out.println("--------------------------------");
        CSVHandler.printCSV(CSVHandler.readCSV(path,99999),info);
    }



    /**
     * Méthode principale pour la recherche et le tri des produits.
     */
    public static void rechercheProduits() {
        boolean quitter = false;
        String essai = "Entrez le mot-cle de recherche ou ':q' (pour quitter):";

        while (!quitter) {
            try {
                System.out.println("");
                System.out.print(essai);
                essai = "Nouveau mot-cle de recherche ou ':q' (pour quitter): ";

                String keyword = myScanner.getStringInput();

                switch (keyword.toLowerCase()) {
                    case ":q":
                        quitter = true;
                        break;
                    default:
                        // on fait la search seulement base sur les colonnes specifiers
                        List<Integer> searchColumnIndices = Arrays.asList(0, 1,4);
                        List<Integer> outputColumnIndices = Arrays.asList(0);
                        List<String> matchingLines = CSVHandler.searchAndFilterColumnsInCSV(DatabasePath.getProduitPath(), keyword,searchColumnIndices,outputColumnIndices);
                        if (!matchingLines.isEmpty()) {
                            trierProduits(matchingLines);
                        } else {
                            System.out.println("Aucun produit trouve selon le mot-cle.");
                        }
                        break;
                }
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
    }

    /**
     * Méthode pour trier les produits en fonction de la colonne choisie et de l'ordre spécifié.
     *
     * @param matchingLines Liste des lignes de produits correspondantes à la recherche.
     */
    public static void trierProduits(List<String> matchingLines) {
        CSVHandler.SortOrder order = null;
        int colomn = 0;

        do {
            try {
                trierMessage();
                System.out.print("Choix: ");
                String type = myScanner.getStringInput();

                switch (type.toLowerCase()) {
                    case "titre": colomn = 1;break;
                    case "categorie": colomn = 2;break;
                    case "prix": colomn = 5;break;
                    case "note": colomn = 12;break;
                    case "popularite": colomn = 11;break;
                    case "promo": colomn = 13;break;
                    default: System.out.println("Choix invalide. Veuillez reessayer.");
                }
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        } while (colomn == 0); // Répéter tant que colomn n'est pas initialisé

        do {
            try {
                System.out.println(" ");
                System.out.println("Choisir l'ordre (ASCENDING ou DESCENDING): ");
                System.out.println("---------------------");
                System.out.print("Choix: ");
                order = CSVHandler.SortOrder.valueOf(myScanner.getStringInput());
            } catch (IllegalArgumentException e) {
                System.out.println("Ordre invalide. Veuillez choisir ASCENDING ou DESCENDING.");
            }
        } while (order == null); // Répéter tant que order n'est pas initialisé

        List<String[]> data = FormatAdjust.transformList(matchingLines);
        CSVHandler.sortByColumn(data, colomn - 1, order);
        afficherProduits(data);
    }
    public static void trierMessage(){
        System.out.println(" ");
        System.out.println("choisir le filtrage base sur quel type:");
        System.out.println("---------------------");
        System.out.println("titre");
        System.out.println("categorie");
        System.out.println("prix");
        System.out.println("note");
        System.out.println("popularite");
        System.out.println("promo");
    }



    /**
     * Méthode pour afficher les produits après la recherche et le tri.
     *
     * @param data Liste des lignes de produits triées.
     */
    public static void afficherProduits(List<String[]> data) {
        System.out.println("\n\n\n\n\n");
        System.out.println("Voici la liste selon votre mot-cle et apres trier");
        System.out.println("-------------------------------------------------");
        for (String[] row : data) {
            for (String value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }











    /**
     * Méthode principale pour la recherche et le tri des acheteurs.
     */
    public static void rechercheAcheteurs() {
        boolean quitter = false;

        while (!quitter) {
            try {
                rechercheAcheteurMsg();

                System.out.print("Choix: ");
                int keyword = myScanner.getIntInput();

                switch (keyword) {
                    case 0:
                        quitter = true;
                        break;
                    case 1: infoDunAcheteur(); break;
                    case 2: infoSuiveursDunAcheteur();break;
                    case 3: infoSuiviDunAcheteur();break;
                    default:
                        System.out.println("Veuillez entrer un entier(option) valide");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
    }

    public static void rechercheAcheteurMsg(){
        System.out.println(" ");
        System.out.println("choisir un option:");
        System.out.println("---------------------");
        System.out.println("0. quitter");
        System.out.println("1. recherche par pseudo");
        System.out.println("2. recherche la liste des suiveurs dun acheteur specifique ");
        System.out.println("3. recherche la liste des suivi dun acheteur specifique ");
    }

    public static void infoDunAcheteur(){
        System.out.println("\n");
        System.out.print("Quel est le pseudo: ");
        String keyword = myScanner.getStringInput();

        System.out.println("\n");
        System.out.println("pseudo, nom, prenom, likes");
        System.out.println("--------------------------");
        List<Integer> outputColumnIndices = Arrays.asList(0, 3, 4, 7);
        List<String> matchingLines = CSVHandler.searchExaAndFilterColumnsInCSV(DatabasePath.getAcheteurPath(), keyword,0,outputColumnIndices);
        if (!matchingLines.isEmpty()) {
            System.out.println(matchingLines);
        } else {
            System.out.println("Aucun acheteur trouve selon le mot-cle.");
        }
    }

    public static void infoSuiveursDunAcheteur(){
        System.out.println("\n");
        System.out.print("Quel est le pseudo: ");
        String keyword = myScanner.getStringInput();

        System.out.println("\n");
        System.out.println("Voici la liste des suiveurs");
        System.out.println("--------------------------");

        int index = CSVHandler.findOccurrenceIndex(DatabasePath.getAcheteurPath(),keyword,0);

        if (index != -1) {
            String data = CSVHandler.readLineByIndex(DatabasePath.getAcheteurPath(),index);
            String pseudo = CSVHandler.getColumnValue(data,0);

            String suiviPar = DatabasePath.getAcheteurComptePath() + pseudo + "/suiviPar.csv" ;

            System.out.println("suiviPar: ");
            System.out.println("------------");
            CSVHandler.printCSV(CSVHandler.readCSV(suiviPar,99999));
            System.out.println("------------");
            System.out.println(" ");

        } else {
            System.out.println("Aucun acheteur trouve selon le mot-cle.");
        }
    }

    public static void infoSuiviDunAcheteur(){
        System.out.println("\n");
        System.out.print("Quel est le pseudo: ");
        String keyword = myScanner.getStringInput();

        System.out.println("\n");
        System.out.println("Voici la liste des suivis");
        System.out.println("--------------------------");

        int index = CSVHandler.findOccurrenceIndex(DatabasePath.getAcheteurPath(),keyword,0);

        if (index != -1) {
            String data = CSVHandler.readLineByIndex(DatabasePath.getAcheteurPath(),index);
            String pseudo = CSVHandler.getColumnValue(data,0);

            String suivre = DatabasePath.getAcheteurComptePath() + pseudo + "/suivre.csv" ;

            System.out.println("suivre: ");
            System.out.println("------------");
            CSVHandler.printCSV(CSVHandler.readCSV(suivre,99999));
            System.out.println("------------");
            System.out.println(" ");

        } else {
            System.out.println("Aucun acheteur trouve selon le mot-cle.");
        }
    }













    public static void rechercheRevendeurs() {
        boolean quitter = false;
        String essai = "Entrez le mot-cle de recherche ou ':q' (pour quitter):";

        while (!quitter) {
            try {
                System.out.println("");
                System.out.print(essai);
                essai = "Nouveau mot-cle de recherche ou ':q' (pour quitter): ";

                String keyword = myScanner.getStringInput();

                switch (keyword.toLowerCase()) {
                    case ":q":
                        quitter = true;
                        break;
                    default:
                        List<Integer> searchColumnIndices = Arrays.asList(0, 3);
                        List<Integer> outputColumnIndices = Arrays.asList(0);
                        List<String> matchingLines = CSVHandler.searchAndFilterColumnsInCSV(DatabasePath.getRevendeurPath(), keyword,searchColumnIndices,outputColumnIndices);
                        if (!matchingLines.isEmpty()) {
                            trierRevendeurs(matchingLines);
                        } else {
                            System.out.println("Aucun revendeur trouve selon le mot-cle.");
                        }
                        break;
                }
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
    }


    public static void trierRevendeurs(List<String> matchingLines) {
        CSVHandler.SortOrder order = null;
        int colomn = 0;

        do {
            try {
                trierMessageRevendeur();
                System.out.print("Choix: ");
                String type = myScanner.getStringInput();

                switch (type.toLowerCase()) {
                    case "nom": colomn = 1;break;
                    case "likes": colomn = 6;break;
                    default: System.out.println("Choix invalide. Veuillez reessayer.");
                }
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        } while (colomn == 0); // Répéter tant que colomn n'est pas initialisé

        do {
            try {
                System.out.println(" ");
                System.out.print("Choisir l'ordre (ASCENDING ou DESCENDING): ");
                System.out.println("---------------------");
                order = CSVHandler.SortOrder.valueOf(myScanner.getStringInput());
            } catch (IllegalArgumentException e) {
                System.out.println("Ordre invalide. Veuillez choisir ASCENDING ou DESCENDING.");
            }
        } while (order == null); // Répéter tant que order n'est pas initialisé

        List<String[]> data = FormatAdjust.transformList(matchingLines);
        CSVHandler.sortByColumn(data, colomn - 1, order);
        afficherRevendeurs(data);
    }
    public static void trierMessageRevendeur(){
        System.out.println(" ");
        System.out.println("choisir le filtrage base sur quel type:");
        System.out.println("---------------------");
        System.out.println("nom");
        System.out.println("likes");
    }



    /**
     * Méthode pour afficher les produits après la recherche et le tri.
     *
     * @param data Liste des lignes de produits triées.
     */
    public static void afficherRevendeurs(List<String[]> data) {
        System.out.println("\n\n\n\n\n");
        System.out.println("Voici la liste selon votre mot-cle et après trier");
        System.out.println("-------------------------------------------------");
        for (String[] row : data) {
            if (row.length > 0) { // Vérifier si le tableau a au moins un élément
                System.out.println(row[0]); // Imprimer seulement le premier élément
            }
        }
    }














}
