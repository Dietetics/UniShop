public class GestionSuiveur {

    public static void gererSuiveurs(String pseudo){
        String pathSuiveurs = DatabasePath.getPathAcheteurCompte() + pseudo + "/suiviPar.csv";

        System.out.println("\n\nVoici la liste de vos suiveurs");
        System.out.println("-----------------------------------");
        trouverSuiveurs(pathSuiveurs);
        Boolean boucle = true;

        while (boucle == true) {
            System.out.print("\nmentionner le nom du suiveur pour le retirer de la liste ou :q pour quitter: ");
            String scanned = myScanner.getStringInput();
            String nomSuiveur = scanned;

            Boolean existe = CSVHandler.isExiste(pathSuiveurs,scanned);
            if (existe == true){ scanned = "1";}

            switch (scanned) {
                case ":q":
                    boucle = false;
                    break;
                case "1":
                    String pathCibleSuivi = DatabasePath.getPathAcheteurCompte() + nomSuiveur + "/suivreAcheteur.csv";

                    retireSuiveur(pathSuiveurs,nomSuiveur);
                    retireSuivi(pathCibleSuivi,pseudo);

                    Notification.notificationRetirageAuto(pseudo, nomSuiveur);
                    Notification.notificationRetirageAuSuiveur(nomSuiveur, pseudo);
                    break;
                default:
                    System.out.println("Commande inconnue. Veuillez reessayer de nouveau");
                    break;
            }
        }
    }

    public static void trouverSuiveurs(String path){
        CSVHandler.printCSV(CSVHandler.readCSV(path,9999));
    }

    public static void retireSuiveur(String path,String nomSuiveur){
        int index = CSVHandler.findOccurrenceIndex(path,nomSuiveur,0);
        index--;
        CSVHandler.removeLineFromCSV(path,index);
    }

    public static void retireSuivi(String path, String auteur){
        int index = CSVHandler.findOccurrenceIndex(path,auteur,0);
        index--;
        CSVHandler.removeLineFromCSV(path,index);
    }
}
