public class Evaluation {

    public Evaluation() {
    }

    public static void display(String revendeur){

        String pathOffrir = DatabasePath.getRevendeurComptePath() + revendeur + "/offrir.csv";
        displayOfferMsg(pathOffrir,revendeur);

        Boolean boucle = true;
        while (boucle == true) {
            System.out.print("\nmentionner le nom du produit pour voir les evaluations ou :q pour quitter: ");
            String scanned = myScanner.getStringInput();
            String nomProduit = scanned;

            Boolean existe = CSVHandler.isExiste(pathOffrir, scanned);
            if (existe == true) {scanned = "1";}

            switch (scanned) {
                case ":q":
                    boucle = false;
                    break;
                case "1":
                    String pathEvaluation = DatabasePath.getProduitInfoPath() + nomProduit + "/evaluations.csv";
                    voirEvaluations(pathEvaluation,revendeur);
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez reessayer.");
            }
        }
    }
    public static void displayOfferMsg(String path, String revendeur) {
        System.out.println("\n\nVoici la liste des produits offer par vous: " + revendeur);
        System.out.println("-----------------------------------");
        afficherProduitsOffer(path);
    }

    public static void afficherProduitsOffer(String path){
        CSVHandler.printCSV(CSVHandler.readCSV(path,9999));
    }

    public static void voirEvaluations(String path, String auteur){
        System.out.println("\n\nCher " + auteur + " Voici vos Evaluations");
        System.out.println("-----------------------");
        CSVHandler.printCSV(CSVHandler.readCSV(path,999));
        System.out.println("-----------------------\n");
    }


}
