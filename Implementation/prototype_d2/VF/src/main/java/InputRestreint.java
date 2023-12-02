import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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

    /**
     * Verifie si un numero de telephone a exactement 10 chiffres.
     *
     * @param telephone Le numero de telephone a verifier.
     * @return Vrai si le numero de telephone a exactement 10 chiffres, sinon faux.
     */
    static boolean isValidTelephone(String telephone) {
        return telephone.matches("^\\d{10}$");
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
    static boolean isValidUniqueRow(String filePath, String valeur, int column) throws IOException {

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


    // Liste des catégories autorisées
    private static final List<String> CATEGORIES_AUTORISEES = Arrays.asList(
            "LivresManuels",
            "ArticlesPapeterie",
            "MaterielInformatique",
            "EquipementBureau",
            "RessourcesDapprentissage"
    );

    /**
     * Vérifie si la catégorie spécifiée est autorisée.
     *
     * @param categorie La catégorie à vérifier.
     * @return true si la catégorie est autorisée, sinon false.
     */
    public static boolean isValidType(String categorie) {
        return CATEGORIES_AUTORISEES.contains(categorie);
    }

    /**
     * Vérifie si la longueur de la chaîne de caractères spécifiée ne dépasse pas 100 caractères.
     *
     * @param mots La chaîne de caractères à vérifier.
     * @return true si la longueur est valide, sinon false.
     */
    static boolean isValidMots(String mots) {
        return mots.length() <= 100;
    }

    /**
     * Vérifie si le nombre entier spécifié est inférieur ou égal à 9999.
     *
     * @param nb Le nombre entier à vérifier.
     * @return true si le nombre est valide, sinon false.
     */
    static boolean isValidInt(Integer nb) {
        return nb <= 9999;
    }

    /**
     * Vérifie si le nombre à virgule flottante spécifié est inférieur à 20.
     *
     * @param points Le nombre à virgule flottante à vérifier.
     * @return true si le nombre est valide, sinon false.
     */
    static boolean isValidDouble(Double points) {
        return points < 20;
    }


}



