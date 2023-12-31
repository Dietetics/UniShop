public class GestionProbleme {


    private static String pathProbleme;
    private static String revendeur;

    public GestionProbleme(String revendeur){
        this.pathProbleme = DatabasePath.getPathRevendeurCompte() + revendeur + "/signalProbleme.csv";
        this.revendeur = revendeur;
    }

    public static void afficherProblemes(){
        System.out.println("\nVoici les problemes recu");
        System.out.println("----------------------------------------------");
        CSVHandler.printCSV(CSVHandler.readCSV(getPathProbleme(),9999));
        System.out.println("----------------------------------------------");
    }


    public static void resoudre(){
        System.out.println("\nVeuillez entrer le nom de l acheteur qui signal un probleme");
        String acheteur = myScanner.getStringInput();
        String pathAcheteur = DatabasePath.getPathTousAcheteurs();
        String pathNotification = DatabasePath.getPathAcheteurCompte() + acheteur + "/notifications.csv";

        if(CSVHandler.isExiste(pathAcheteur,acheteur)){
            System.out.println("\nVeuillez entrer une solution pour notre cher ami");
            String solution = myScanner.getStringInput();
            CSVHandler.appendCSV(pathNotification,getRevendeur()+", Voici une solution:" + solution);
        }else{
            System.out.println("L acheteur entre n existe pas, veuillez reessayer de nouveau");
        }

    }



    public static String getPathProbleme() {
        return pathProbleme;
    }

    public static String getRevendeur() {
        return revendeur;
    }
}
