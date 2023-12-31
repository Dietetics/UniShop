import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputRestreint {


    public InputRestreint() {
    }

    /**
     * Verifie si une chaine d'entree contient au moins deux caracteres alphabetiques.
     *
     * @param input La chaine d'entree a verifier.
     * @return Vrai si la chaine contient au moins deux caracteres alphabetiques, sinon faux.
     */
    static boolean isValidInput(String input) {
        return input.matches("^[a-zA-Z]{2,}$");
    }

    /**
     * Verifie si une adresse a une longueur inferieure ou egale a 20 caracteres.
     *
     * @param address L'adresse a verifier.
     * @return Vrai si la longueur de l'adresse est inferieure ou egale a 20, sinon faux.
     */
    static boolean isValidAddress(String address) {
        return address.length() <= 20;
    }



    static boolean isValidDescription(String description) {
        return description.length() <= 100;
    }

    /**
     * Verifie si un numero de telephone a exactement 10 chiffres.
     *
     * @param telephone Le numero de telephone a verifier.
     * @return Vrai si le numero de telephone a exactement 10 chiffres, sinon faux.
     */
    static boolean isValidTelephone(String telephone) {
        return telephone.matches("^\\d{10}$");
    }

    static boolean isValidQuantite(String quantite) {
        try {
            int value = Integer.parseInt(quantite);
            return value > 0 && quantite.length() < 5;
        } catch (NumberFormatException e) {
            return false; // La conversion en entier échoue si la chaîne n'est pas un nombre valide
        }
    }
    static boolean isValidPrix(String prix) {
        try {
            int value = Integer.parseInt(prix);
            return value > 0 && prix.length() < 5;
        } catch (NumberFormatException e) {
            return false; // La conversion en entier échoue si la chaîne n'est pas un nombre valide
        }
    }
    static boolean isValidPointsBoni(String points) {
        return points.matches("\\d{1,2}");
    }

    /**
     * Verifie si une adresse e-mail se termine par "@gmail.com" ou "@umontreal.ca".
     *
     * @param courriel L'adresse e-mail a verifier.
     * @return Vrai si l'adresse e-mail se termine par les domaines specifies, sinon faux.
     */
    static boolean isValidCourriel(String courriel) {
        return courriel.endsWith("@gmail.com") || courriel.endsWith("@umontreal.ca");
    }

    static boolean isUnique_Mlen2(String filePath, String valeur, int column) throws IOException {

        List<String[]> data = CSVHandler.readCSV(filePath,9999);
        if (data != null) {
            for (String[] row : data) {
                if (row.length > 2 && valeur.equals(row[column])) {
                    return false;
                }
            }
        }
        return true;
    }


    /* Sources: https://stackoverflow.com/questions/19605150/regex-for-password-must-contain-at-least-eight-characters-at-least-one-number-a*/
    static boolean isValidPass(String password) {
        // At least 8 characters, one uppercase, one lowercase, one digit, one special character
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }



    /**
     * valide si la valeur est unique dans une colonne specifier du CSV fichier
     *
     *
     * @param filePath path du CSV file.
     * @param valeur   valeur a verifier l unicite
     * @param column   colonne indiquer pour verifier l unicite (0-based index).
     * @return Vrai si la valeur unique n'est pas trouvee dans la colonne specifiee, sinon faux.
     * @throws IOException En cas d'erreur d'entree/sortie lors de la lecture du fichier CSV.
     */
    static boolean isValidPseudo(String filePath, String valeur, int column) throws IOException {

        List<String[]> data = CSVHandler.readCSV(filePath,9999);
        if (data != null) {
            for (String[] row : data) {
                if (row.length > 2 && valeur.equals(row[column])) {
                    return false;
                }
            }
        }
        return true;
    }


    static boolean isValidTitre(String filePath, String valeur, int column) throws IOException {

        List<String[]> data = CSVHandler.readCSV(filePath,9999);
        if (data != null) {
            for (String[] row : data) {
                if (row.length > 2 && valeur.equals(row[column])) {
                    return false;
                }
            }
        }
        return true;
    }




    static String getValidInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = myScanner.getStringInput();

                if (!isValidInput(input)) {
                    throw new IllegalArgumentException("Le nom et prenom doivent contenir uniquement des caracteres alphabetiques et au moins deux.");
                }

                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur: " + e.getMessage());
            }
        }
    }
    static String getValidAdresse(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = myScanner.getStringInput();

                if (!isValidAddress(input)) {
                    throw new IllegalArgumentException("adresse doit avoir un longueur inferieur a 20");
                }

                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur: " + e.getMessage());
            }
        }
    }

    static String getValidDescription(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = myScanner.getStringInput();

                if (!isValidDescription(input)) {
                    throw new IllegalArgumentException("description doit avoir un longueur inferieur a 100");
                }

                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur: " + e.getMessage());
            }
        }
    }

    static String getValidQuantite(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = myScanner.getStringInput();

                if (!isValidQuantite(input)) {
                    throw new IllegalArgumentException("quantite doit etre inferieur que 10 000 et non 0");
                }

                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur: " + e.getMessage());
            }
        }
    }

    static String getValidPrix(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = myScanner.getStringInput();

                if (!isValidPrix(input)) {
                    throw new IllegalArgumentException("prix doit etre inferieur que 10 000$ et non 0");
                }

                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur: " + e.getMessage());
            }
        }
    }

    static String getValidPointsBoni(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = myScanner.getStringInput();

                if (!isValidPointsBoni(input)) {
                    throw new IllegalArgumentException("points boni doit etre inferieur que 100");
                }

                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur: " + e.getMessage());
            }
        }
    }

    static String getValidAcheteurCourriel(String message, int colonne) {
        while (true) {
            try {
                System.out.print(message);
                String input = myScanner.getStringInput();

                if (!isValidCourriel(input)) {
                    throw new IllegalArgumentException("Le courriel doit terminer par @gamil.com ou @umontreal.ca");
                }

                if (!isUnique_Mlen2(DatabasePath.getPathTousAcheteurs(),input,colonne)) {
                    throw new IllegalArgumentException("Ce courriel est deja utillise, veuillez entre un nouveau");
                }

                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur: " + e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static String getValidRevendeurCourriel(String message, int colonne) {
        while (true) {
            try {
                System.out.print(message);
                String input = myScanner.getStringInput();

                if (!isValidCourriel(input)) {
                    throw new IllegalArgumentException("Le courriel doit terminer par @gamil.com ou @umontreal.ca");
                }

                if (!isUnique_Mlen2(DatabasePath.getPathTousRevendeurs(),input,colonne)) {
                    throw new IllegalArgumentException("Ce courriel est deja utillise, veuillez entre un nouveau");
                }

                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur: " + e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    static String getValidTelephone(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = myScanner.getStringInput();

                if (!isValidTelephone(input)) {
                    throw new IllegalArgumentException("Le telephone doit etre composer de 10 chiffres");
                }

                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur: " + e.getMessage());
            }
        }
    }
    static String getValidPseudo(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = myScanner.getStringInput();

                if (!isValidPseudo(DatabasePath.getPathTousAcheteurs(),input,0)) {
                    throw new IllegalArgumentException("votre pseudo est pris, veuillez entre un nouveau");
                }

                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur: " + e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    static String getValidTitre(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = myScanner.getStringInput();

                if (!isValidTitre(DatabasePath.getPathTousProduits(),input,0)) {
                    throw new IllegalArgumentException("votre titre est pris, veuillez entre un nouveau");
                }

                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur: " + e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    static String getValidPassword(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = myScanner.getStringInput();

                if (!isValidPass(input)) {
                    throw new IllegalArgumentException("votre paseword doit contenir au moins 8 chars, 1 majuscule, 1 minuscule, 1 chiffre, 1char special");
                }

                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur: " + e.getMessage());
            }
        }
    }
    static String getValidInputVRevendeur(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = myScanner.getStringInput();

                if (!isValidInput(input)) {
                    throw new IllegalArgumentException("contenir uniquement des caracteres alphabetiques et au moins deux.");
                }
                if (!isUnique_Mlen2(DatabasePath.getPathTousRevendeurs(),input,0)) {
                    throw new IllegalArgumentException("Ce nom est deja utillise, veuillez entre un nouveau");
                }

                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur: " + e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }



    static String getValidCategorie(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = myScanner.getStringInput();

                if (!isValidCategorie(input)) {
                    throw new IllegalArgumentException("Vous avez entrez une mauvaise categorie, veuillez entrer de nouveau");
                }

                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur: " + e.getMessage());
            }
        }
    }





























    // Liste des catégories autorisées
    public static final List<String> CATEGORIES_AUTORISEES = Arrays.asList(
            "LivresManuels",
            "ArticlesPapeterie",
            "MaterielInformatique",
            "EquipementBureau",
            "RessourcesDapprentissage",
            "Autres"
    );




    static boolean isValidCategorie(String category) {
        return CATEGORIES_AUTORISEES.contains(category);
    }







    static String getValidCredit(String message) {
        while (true) {
            try {
                System.out.print(message);
                String cvv = myScanner.getStringInput();

                if (!isValidCredit(cvv)) {
                    throw new IllegalArgumentException("Les informations de la carte de crédit ne sont pas valides. le nombre cvv est une longueur de 3");
                }
                return cvv;
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
    }

    static boolean isValidCredit(String cvv) {
        return cvv.length() == 3;
    }








    public static boolean isValidUrl(String urlString) {
        try {
            new URL(urlString);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }


    static String getValidUrl(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = myScanner.getStringInput();

                if (!isValidUrl(input)) {
                    throw new IllegalArgumentException("Vous avez entrez une mauvaise url, veuillez entrer de nouveau");
                }
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Erreur: " + e.getMessage());
            }
        }
    }

}



