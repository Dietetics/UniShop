import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Recherche {

    private static String produitPath = "src/main/resources/data/produits.csv";

    private static String acheteurPath = "src/main/resources/data/acheteur.csv";
    private static String revendeurPath = "src/main/resources/data/revendeur.csv";
    private static Scanner scanner = new Scanner(System.in);






    public static void rechercherAcheteur(){}
    public static void rechercherRevendeur(){}
















    /**
     * Recherche et affiche les acheteurs en fonction du mot-clé.
     *
     * @param ligne L'index de la ligne du compte de l'utilisateur.
     * @param excludedColumns Les colonnes à exclure lors de l'affichage.
     */
    public static void trouverAcheteur(int ligne, int[] excludedColumns) {
        String essai = "Entrez l'Information de l'acheteur recherche ou ':q' (pour quitter):";

        while (true) {
            try {
                System.out.println("");
                System.out.println(essai);
                essai = "Continuez? Entrez l'Information de l'acheteur recherche ou ':q' (pour quitter):";
                String keyword = scanner.next();

                switch (keyword.toLowerCase()) {
                    case ":q": return;
                    // case ":t":    pour le tout
                    default:
                        List<String> matchingLines = CSVHandler.searchKeywordInCSV(getAcheteurPath(), keyword);
                        List<Integer> matchingLinesIndex = CSVHandler.searchKeywordInCSVIndex(getAcheteurPath(), keyword);
                        int indexNous = matchingLinesIndex.indexOf(ligne);

                        // VERIFIE SI NOTRE COMPTE EST INCLUDE, SI OUI EXCLURE, SINON CONTINUER
                        if (indexNous != -1) {
                            matchingLines.remove(indexNous);
                            matchingLinesIndex.remove(indexNous);
                        }

                        afficherListeAcheteurs(matchingLines, matchingLinesIndex, excludedColumns);

                        while (true) {
                            if (!choisirOption(matchingLinesIndex)) {
                                return;
                            }
                        }
                }
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
                scanner.nextLine(); // Effacer la ligne incorrecte dans le scanner
            }
        }
    }

    /**
     * Affiche la liste des acheteurs en fonction des critères de recherche.
     *
     * @param matchingLines Les lignes correspondant aux acheteurs.
     * @param matchingLinesIndex Les index correspondant aux lignes affichées.
     * @param excludedColumns Les colonnes à exclure lors de l'affichage.
     */
    private static void afficherListeAcheteurs(List<String> matchingLines, List<Integer> matchingLinesIndex, int[] excludedColumns) {
        int nb = 1;
        System.out.println("Voici la liste des acheteurs selon vos recherches");
        System.out.println("-------------------------------------------------- ");

        if (!matchingLines.isEmpty()) {
            List<String[]> formattedLines = FormatAdjust.transformList(matchingLines);

            for (String[] line : formattedLines) {
                for (int excludedColumn : excludedColumns) {
                    if (excludedColumn >= 0 && excludedColumn < line.length) {
                        line[excludedColumn] = "non visible";  // Remplacer la valeur par une chaîne vide
                    }
                }

                int index = matchingLinesIndex.get(nb - 1);
                nb++;

                // Affiche la ligne sans les colonnes spécifiées
                System.out.println(index + " " + Arrays.toString(line));
            }
        } else {
            System.out.println("Aucun acheteur trouve selon le mot-cle.");
        }
        System.out.println("-------------------------------------------------- ");
        System.out.println("");
    }

    /**
     * Permet à l'utilisateur de choisir une option parmi Suivi, Liker, ou Quitter.
     *
     * @param matchingLinesIndex Les index correspondant aux lignes affichées.
     * @return True si l'utilisateur choisit une option valide, sinon False.
     */
    private static boolean choisirOption(List<Integer> matchingLinesIndex) {
        try {
            System.out.println("Choisissez une option :");
            System.out.println("1. Suivi");
            System.out.println("2. Liker");
            System.out.println("0. Quitter");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    // cas "Suivi" un achteur
                    System.out.println("Vous avez choisi l'option Suivi.");
                    buttonSuivre(matchingLinesIndex);
                    return true;
                case 2:
                    // cas "Liker" un acheteur
                    System.out.println("Vous avez choisi l'option Liker.");
                    buttonLiker(matchingLinesIndex);
                    return true;
                case 0:
                    return false;
                default:
                    System.out.println("Option invalide. Veuillez choisir une option valide.");
                    return true;
            }
        } catch (InputMismatchException e) {
            System.out.println("Erreur : Veuillez entrer un entier valide.");
            scanner.next(); // Effacer la mauvaise entrée du scanner
            return true;
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
            scanner.next(); // Effacer l'entrée incorrecte du scanner
            return true;
        }
    }



    /**
     * Permet à l'utilisateur de liker un acheteur en saisissant le chiffre correspondant.
     *
     * @param suspectPos La liste des lignes disponibles pour liker.
     */
    public static void buttonLiker(List<Integer> suspectPos){
        while (true) {
            try {
                System.out.println("0 pour quitter ou Entrez le chiffre du acheteur pour liker: ");
                int valeur = scanner.nextInt();

                if (suspectPos.contains(valeur)) {
                    Operation.likerAcheteur(valeur);
                } else if (valeur == 0) {
                    return;
                } else {
                    System.out.println("Ceci n'est pas un option, veuillez entrer le chiffre du acheteur");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer un entier valide.");
                scanner.next(); // Effacer la mauvaise entrée du scanner
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
                scanner.next(); // Effacer l'entrée incorrecte du scanner
            }
        }
    }


    /**
     * Permet à l'utilisateur de suivre un acheteur en saisissant le chiffre correspondant.
     *
     * @param suspectPos La liste des positions d'acheteurs disponibles pour suivre.
     */
    public static void buttonSuivre(List<Integer> suspectPos){

        while (true) {
            try {
                System.out.println("0 pour quitter ou Entrez le chiffre du acheteur pour suivre: ");
                int aimsLigne = scanner.nextInt();

                if (suspectPos.contains(aimsLigne)) {
                    Operation.suivreAcheteur(aimsLigne);
                } else if (aimsLigne == 0) {
                    return;
                } else {
                    System.out.println("Ceci n'est pas un option, veuillez entrer le chiffre du acheteur");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer un entier valide.");
                scanner.next(); // Effacer la mauvaise entrée du scanner
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
                scanner.next(); // Effacer l'entrée incorrecte du scanner
            }
        }
    }











    public static void trouverRevendeur() {

        while (true) {
            try {
                System.out.println("");
                System.out.println("Choisissez un des options en indiquant le numero");
                System.out.println("-------------------------");
                System.out.println("1. recherche un revendeur");
                System.out.println("2. recherche un revendeur avec filtrage");
                System.out.println("3. liker un revendeur");
                System.out.println("0. quitter");
                String keyword = scanner.next();

                switch (keyword.toLowerCase()) {
                    case "0": return;
                    case "1":
                        System.out.println("veuillez fournir un indice") ;
                        String indice = scanner.next();
                        System.out.println("voici la liste de revendeur chercher") ;
                        CSVHandler.printCSV(CSVHandler.readCSV(getRevendeurPath(),9999)); break;
                    case "2":
                        System.out.println("veuillez fournir un indice") ;
                        String indice2 = scanner.next();
                        System.out.println("veuillez fournir le filtrage base sur:") ;
                        String indice3 = scanner.next();
                        System.out.println("voici la liste de revendeur chercher") ;
                        CSVHandler.printCSV(CSVHandler.readCSV(getRevendeurPath(),9999)); break;
                    case "3":
                        System.out.println("votre like est enregistrer") ;break;
                    default:
                        System.out.println("veuillez fournir un chiffre valide") ;
                }
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
                scanner.nextLine(); // Effacer la ligne incorrecte dans le scanner
            }
        }
    }

    public static String getProduitPath() {
        return produitPath;
    }

    public static String getAcheteurPath() {
        return acheteurPath;
    }

    public static String getRevendeurPath() {
        return revendeurPath;
    }
}
