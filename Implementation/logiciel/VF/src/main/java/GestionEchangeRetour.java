public class GestionEchangeRetour {


    private static String pathEchangeRetour;
    private static String revendeur;

    public GestionEchangeRetour(String revendeur){
        this.pathEchangeRetour = DatabasePath.getPathRevendeurCompte() + revendeur + "/retourEchange.csv";
        this.revendeur = revendeur;
    }

    public static void afficherEchangeRetour(){
        System.out.println("\nVoici les echange retour recu");
        System.out.println("----------------------------------------------");
        CSVHandler.printCSV(CSVHandler.readCSV(getPathEchangeRetour(),9999));
        System.out.println("----------------------------------------------");
    }


    public static void resoudre(){
        System.out.println("\nVeuillez entrer le nom de l acheteur qui demande un echange retour");
        String acheteur = myScanner.getStringInput();
        String pathAcheteur = DatabasePath.getPathTousAcheteurs();
        String pathNotification = DatabasePath.getPathAcheteurCompte() + acheteur + "/notifications.csv";

        if(CSVHandler.isExiste(pathAcheteur,acheteur)){
            System.out.println("\nVeuillez entrer une reponse pour notre cher ami\n1 pour confirmer la demande\nautre pour non");
            String choix = myScanner.getStringInput();

            if ("1".equals(choix)) {
                expedierProduit(pathNotification);
            } else {
                System.out.println("\nVeuillez entrer une raison pour notre cher ami");
                String reponse = myScanner.getStringInput();
                CSVHandler.appendCSV(pathNotification,getRevendeur()+", REFUSER,car: " + reponse);
                System.out.println("Votre decision est prise, l'acheteur rececevra en sous peu");
            }
        }else{
            System.out.println("L acheteur entre n existe pas, veuillez reessayer de nouveau");
        }
    }


    public static void expedierProduit(String pathNotification){
        CSVHandler.appendCSV(pathNotification,getRevendeur()+", on a bien recu le produit de retour, le produit de remplacement sera expedier dans trois jours ouvrables. Veuillez patienters. Merci " );
        System.out.println("Votre decision est prise, l'acheteur rececevra en sous peu");
    }



    public static String getPathEchangeRetour() {
        return pathEchangeRetour;
    }

    public static String getRevendeur() {
        return revendeur;
    }
}
