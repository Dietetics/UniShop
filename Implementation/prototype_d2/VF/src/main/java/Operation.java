import java.util.List;

public class Operation {

    private static String produitPath = "src/main/resources/data/produits.csv";
    private static String acheteurPath = "src/main/resources/data/acheteur.csv";
    private static String revendeurPath = "src/main/resources/data/revendeur.csv";



    /**
     * Augmente le nombre de likes d'un produit à partir de l'index spécifié dans le fichier CSV des produits.
     *
     * @param index L'index du produit dans le fichier CSV.
     */
    public static void likerProduit(int index) {
        try {
            // Cherche la valeur à partir d'une ligne
            String line = CSVHandler.readLineByIndex(getProduitPath(), index);
            String valeur = CSVHandler.getColumnValue(line, 9); // 9e colonne, commencer par 0

            // Modifie la valeur
            int intValue = Integer.parseInt(valeur);
            intValue++;
            String strValue = String.valueOf(intValue);

            // Modifie la ligne par la nouvelle valeur
            String newLine = CSVHandler.updateCSVColumn(line, 9, strValue);

            // Met à jour la ligne
            CSVHandler.uploadCSVLine(getProduitPath(), index - 1, newLine);

        } catch (NumberFormatException e) {
            System.out.println("La chaine n'est pas un nombre valide.");
        }
    }

    /**
     * Augmente le nombre de likes d'un acheteur à partir de l'index specifie dans le fichier CSV des acheteurs.
     *
     * @param index L'index de l'acheteur dans le fichier CSV.
     */
    public static void likerAcheteur(int index) {
        try {
            // Cherche la valeur à partir d'une ligne
            String line = CSVHandler.readLineByIndex(getAcheteurPath(), index);
            String valeur = CSVHandler.getColumnValue(line, 6); // 6e colonne, commencer par 0

            // Modifie la valeur
            int intValue = Integer.parseInt(valeur);
            intValue++;
            String strValue = String.valueOf(intValue);

            // Modifie la ligne par la nouvelle valeur
            String newLine = CSVHandler.updateCSVColumn(line, 6, strValue);

            // Met à jour la ligne
            CSVHandler.uploadCSVLine(getAcheteurPath(), index - 1, newLine);

        } catch (NumberFormatException e) {
            System.out.println("La chaine n'est pas un nombre valide.");
        }
    }

    /**
     * L'acheteur declenche l'enregistrement dans le compte (suivre) du déclencheur et dans le compte (suiviPar) de la cible.
     *
     * @param targetLigne L'index de la cible dans le fichier CSV des acheteurs.
     * @throws SuiviDejaEffectueException Si le suivi a déjà été effectué.
     */
    public static void suivreAcheteur(int targetLigne) throws SuiviDejaEffectueException {
        try {
            // Pseudo de la cible
            String targetPseudo = CSVHandler.getValueAtIndexAndColumn(getAcheteurPath(), targetLigne - 1, 2);

            // Chemin de la cible pour enregistrer les fans
            String targetPath = "src/main/resources/data/acheteur/" + targetPseudo + "/suiviPar.csv";
            if (!CSVHandler.isExiste(targetPath, ProfilAcheteur.getPseudo())) {
                CSVHandler.appendCSV(targetPath, ProfilAcheteur.getPseudo());

                // Chemin du déclencheur pour enregistrer les suiveurs
                String declencheurPath = "src/main/resources/data/acheteur/" + ProfilAcheteur.getPseudo() + "/suivre.csv";
                CSVHandler.appendCSV(declencheurPath, targetPseudo);
            } else {
                throw new SuiviDejaEffectueException("Vous avez déjà suivi cet acheteur.");
            }
        } catch (SuiviDejaEffectueException e) {
            System.out.println("Erreur : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erreur inattendue : " + e.getMessage());
        }
    }

    /**
     * Exception levee si le suivi a deja ete effectue.
     */
    public static class SuiviDejaEffectueException extends Exception {
        public SuiviDejaEffectueException(String message) {
            super(message);
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
