import java.io.IOException;
import java.util.List;

public class Operation {


    // ajouter dans liker produit ssi elle nest pas encore like et ajouter 1 dans le profil du produit
    public static void acheteurLikerProduit(String produit, String auteur) {
        String path = DatabasePath.getAcheteurComptePath() + auteur + "/likerProduit.csv";

        try {
            Boolean condition = CSVHandler.isExiste(path, produit);

            if (!condition) {
                CSVHandler.appendCSV(path, produit);
                System.out.println("Produit like avec succss.");

                ProfilProduit unProduit = new ProfilProduit(produit);
                int likes = unProduit.getLikes();
                likes = likes +1;
                unProduit.setLikes(likes);
                unProduit.modified();

            } else {
                System.out.println("Vous avez dejà like ce produit.");
            }
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite : " + e.getMessage());
        }
    }


    public static void acheteurSuivreAcheteur(String acheteur, String auteur) {
        String path = DatabasePath.getAcheteurComptePath() + auteur + "/suivreAcheteur.csv";

        try {
            Boolean condition = CSVHandler.isExiste(path, acheteur);

            // si pas deja dedans et pas la meme pers
            if (!condition && auteur != acheteur) {
                CSVHandler.appendCSV(path, acheteur);

                String ciblePath = DatabasePath.getAcheteurComptePath() + acheteur + "/suiviPar.csv";
                CSVHandler.appendCSV(ciblePath, auteur);
                System.out.println("Acheteur suivi avec succss.");
            } else {
                System.out.println("Vous avez deja suivi cet acheteur.");
            }
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite : " + e.getMessage());
        }
    }




    public static void acheteurLikerRevendeur(String revendeur, String auteur) {
        String path = DatabasePath.getAcheteurComptePath() + auteur + "/likerRevendeur.csv";

        try {
            Boolean condition = CSVHandler.isExiste(path, revendeur);

            if (!condition) {
                CSVHandler.appendCSV(path, revendeur);
                System.out.println("Revendeur like avec succss.");

                ProfilRevendeur unRevendeur = new ProfilRevendeur(revendeur);
                int likes = unRevendeur.getNbLikes();
                likes = likes +1;
                unRevendeur.setNbLikes(likes);
                unRevendeur.modified();


            } else {
                System.out.println("Vous avez deja like ce revendeur.");
            }
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite : " + e.getMessage());
        }
    }

    public static void acheteurSuivreRevendeur(String revendeur, String auteur) {
        String path = DatabasePath.getAcheteurComptePath() + auteur + "/suivreRevendeur.csv";

        try {
            Boolean condition = CSVHandler.isExiste(path, revendeur);

            if (!condition) {
                CSVHandler.appendCSV(path, revendeur);
                System.out.println("Revendeur suivi avec succss.");

            } else {
                System.out.println("Vous avez deja suivi ce revendeur.");
            }
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite : " + e.getMessage());
        }
    }

















    /**
     * Augmente le nombre de likes d'un produit à partir de l'index spécifié dans le fichier CSV des produits.
     *
     * @param index L'index du produit dans le fichier CSV.
     */
    public static void likerProduit(int index) {
        try {
            // Cherche la valeur à partir d'une ligne
            String line = CSVHandler.readLineByIndex(DatabasePath.getProduitPath(), index);
            String valeur = CSVHandler.getColumnValue(line, 9); // 9e colonne, commencer par 0

            // Modifie la valeur
            int intValue = Integer.parseInt(valeur);
            intValue++;
            String strValue = String.valueOf(intValue);

            // Modifie la ligne par la nouvelle valeur
            String newLine = CSVHandler.updateCSVColumn(line, 9, strValue);

            // Met à jour la ligne
            CSVHandler.uploadCSVLine(DatabasePath.getProduitPath(), index - 1, newLine);

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
            String line = CSVHandler.readLineByIndex(DatabasePath.getAcheteurPath(), index);
            String valeur = CSVHandler.getColumnValue(line, 6); // 6e colonne, commencer par 0

            // Modifie la valeur
            int intValue = Integer.parseInt(valeur);
            intValue++;
            String strValue = String.valueOf(intValue);

            // Modifie la ligne par la nouvelle valeur
            String newLine = CSVHandler.updateCSVColumn(line, 6, strValue);

            // Met à jour la ligne
            CSVHandler.uploadCSVLine(DatabasePath.getAcheteurPath(), index - 1, newLine);

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
            String targetPseudo = CSVHandler.getValueAtIndexAndColumn(DatabasePath.getAcheteurPath(), targetLigne - 1, 2);

            // Chemin de la cible pour enregistrer les fans
            String targetPath = "src/main/resources/data/acheteur/" + targetPseudo + "/suivreAcheteur.csv";
            if (!CSVHandler.isExiste(targetPath, ProfilAcheteur.getPseudo())) {
                CSVHandler.appendCSV(targetPath, ProfilAcheteur.getPseudo());

                // Chemin du déclencheur pour enregistrer les suiveurs
                String declencheurPath = "src/main/resources/data/acheteur/" + ProfilAcheteur.getPseudo() + "/suivreAcheteur.csv";
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





}
