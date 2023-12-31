import java.util.List;

public class GestionCommandeParRevendeur {

    private static String pathAchats;
    private static String revendeur;

    public GestionCommandeParRevendeur(String revendeur){
        this.pathAchats = DatabasePath.getPathRevendeurCompte() + revendeur + "/achats.csv";
        this.revendeur = revendeur;
    }

    public static void afficherAchats(){
        System.out.println("\nVoici vos produits vendus");
        System.out.println("----------------------------------------------");
        CSVHandler.printCSV(CSVHandler.readCSV(getPathAchats(),9999));
        System.out.println("----------------------------------------------");
    }






    public static void confirmerReception(){

        System.out.println("\nVeuillez entrer le nom du acheteur pret a livrer");
        String acheteur = myScanner.getStringInput();
        if (rechercherDansColonneX(getPathAchats(),acheteur,0)) {
            System.out.println("\nVeuillez entrer le nom du produit pret a livrer");
            String produit = myScanner.getStringInput();
            if(rechercherDansColonneX(getPathAchats(),produit,1)){
                modifierEtatProduit(getPathAchats(),produit,acheteur,"enProduction");
            }
        };
    }

    public static boolean rechercherDansColonneX(String filePath, String valeurRecherchee,int index) {
        try {
            // Lire toutes les lignes du fichier CSV
            List<String> lines = CSVHandler.readLinesFromCSV(filePath);

            for (String line : lines) {
                String[] columns = line.split(",");

                // Vérifier si la valeur recherchée est présente dans la première colonne
                if (columns.length > 0 && columns[index].equals(valeurRecherchee)) {
                    System.out.println("Reussi");
                    return true;
                }
            }

            System.out.println("On na pas pu trouver le. Veuillez entrer de nouveau");
            return false;
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite : " + e.getMessage());
            return false;
        }
    }


    public static void modifierEtatProduit(String filePath, String nomProduit, String acheteur, String etatInitial) {
        try {
            // Lire toutes les lignes du fichier CSV
            List<String> lines = CSVHandler.readLinesFromCSV(filePath);

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] columns = line.split(",");

                // Vérifier si le nom du produit correspond
                if (columns.length > 1 && columns[0].equals(acheteur) && columns[1].equals(nomProduit) ) {
                    // Vérifier si l'état correspond
                    if (columns[2].equals(etatInitial)) {
                        // Modifier l'état
                        columns[2] = "enLivraison";
                        lines.set(i, String.join(",", columns));

                        // Écrire les modifications dans le fichier
                        CSVHandler.writeLinesToCSV(filePath, lines);
                        System.out.println("L'etat du produit est maintenant en livraison.");
                        Notification.notificationActionChangerEtatRevendeur(getRevendeur(),nomProduit);
                        Notification.notificationActionChangerEtat(acheteur,nomProduit);
                        modifierEtatProduitDuAcheteur(DatabasePath.getPathAcheteurCompte()+acheteur+"/histoire.csv",nomProduit,acheteur,"enProduction");
                        return; // Arrêter la boucle après la modification
                    }
                }
            }

            System.out.println("Votre produit nest pas en etat de livraison, elle nest donc pas possible de lavoir recu, si jamais quoi que ce soit " +
                    "veuillez nos contact");
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite : " + e.getMessage());
        }
    }


    public static void modifierEtatProduitDuAcheteur(String filePath, String nomProduit,String acheteur, String oldEtat) {
        try {
            // Lire toutes les lignes du fichier CSV
            List<String> lines = CSVHandler.readLinesFromCSV(filePath);

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] columns = line.split(",");

                // Vérifier si le nom du produit correspond
                if (columns.length > 1 && columns[0].equals(nomProduit)) {
                    // Vérifier si l'état correspond
                    if (columns[1].equals(oldEtat)) {
                        // Modifier l'état
                        columns[1] = "enLivraison";
                        lines.set(i, String.join(",", columns));

                        // Écrire les modifications dans le fichier
                        CSVHandler.writeLinesToCSV(filePath, lines);
                        return;
                    }
                }
            }

            System.out.println("Votre produit nest pas en etat de livraison, elle nest donc pas possible de lavoir recu, si jamais quoi que ce soit " +
                    "veuillez nos contact");
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite : " + e.getMessage());
        }
    }




    public static String getPathAchats() {
        return pathAchats;
    }

    public static String getRevendeur() {
        return revendeur;
    }
}
