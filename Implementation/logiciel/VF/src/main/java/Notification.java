import java.util.List;

public class Notification {


    public Notification() {
    }

    public static void voirNotifications(String path){
        System.out.println("Voici vos notifications");
        System.out.println("-----------------------");
        CSVHandler.printCSV(CSVHandler.readCSV(path,999));
        System.out.println("-----------------------\n");
    }


    public static void notificationRetirageAuSuiveur(String nomSuiveur, String pseudo){
        String path = DatabasePath.getPathAcheteurCompte() + nomSuiveur + "/notifications.csv";
        String msg = "vous etes retirer de la liste de suiveurs par " + pseudo;
        CSVHandler.appendCSV(path,msg);
    }
    public static void notificationRetirageAuto(String pseudo, String cible){
        String path = DatabasePath.getPathAcheteurCompte() + pseudo + "/notifications.csv";
        String msg = "vous avez bien retirer de la liste de suiveur: " + cible;
        CSVHandler.appendCSV(path,msg);
    }

    public static void notificationLiker(String pseudo,String produit){
        String path = DatabasePath.getPathAcheteurCompte() + pseudo + "/notifications.csv";
        String msg = "vous avez bien liker: " + produit;
        CSVHandler.appendCSV(path, msg);
    }

    public static void notificationCommande(String pseudo,String produit){
        String path = DatabasePath.getPathAcheteurCompte() + pseudo + "/notifications.csv";
        String msg = "vous avez bien mise au panier: " + produit;
        CSVHandler.appendCSV(path, msg);
    }

    public static void notificationSuivre(String pseudo,String cible){
        String pathPseudo = DatabasePath.getPathAcheteurCompte() + pseudo + "/notifications.csv";
        String pathCible = DatabasePath.getPathAcheteurCompte() + cible + "/notifications.csv";
        String msgPseudo = "vous avez bien suivi: " + cible;
        String msgCible = "vous etes suivi par: " + pseudo;
        CSVHandler.appendCSV(pathPseudo, msgPseudo);
        CSVHandler.appendCSV(pathCible, msgCible);
    }

    public static void notificationLikerRevendeur(String pseudo,String cible){
        String pathPseudo = DatabasePath.getPathAcheteurCompte() + pseudo + "/notifications.csv";
        String pathCible = DatabasePath.getPathRevendeurCompte() + cible + "/notifications.csv";
        String msgPseudo = "vous avez bien liker le revendeur: " + cible;
        String msgCible = "vous avez recu un like par: " + pseudo;
        CSVHandler.appendCSV(pathPseudo, msgPseudo);
        CSVHandler.appendCSV(pathCible, msgCible);
    }

    public static void notificationSuivreRevendeur(String pseudo,String cible){
        String pathPseudo = DatabasePath.getPathAcheteurCompte() + pseudo + "/notifications.csv";
        String pathCible = DatabasePath.getPathRevendeurCompte() + cible + "/notifications.csv";
        String msgPseudo = "vous avez bien suivi le revendeur: " + cible;
        String msgCible = "vous avez recu un suivi par: " + pseudo;
        CSVHandler.appendCSV(pathPseudo, msgPseudo);
        CSVHandler.appendCSV(pathCible, msgCible);
    }


    public static void notificationActionChangerEtat(String pseudo,String produit){
        String pathPseudo = DatabasePath.getPathAcheteurCompte() + pseudo + "/notifications.csv";
        String msgPseudo = "letat du produit a ete changer: " + produit;
        CSVHandler.appendCSV(pathPseudo, msgPseudo);
    }

    public static void notificationActionChangerEtatRevendeur(String auteur,String produit){
        String pathPseudo = DatabasePath.getPathRevendeurCompte() + auteur + "/notifications.csv";
        String msgPseudo = "letat du produit a ete changer: " + produit;
        CSVHandler.appendCSV(pathPseudo, msgPseudo);
    }

    public static void notificationActionSignalerProbleme(String pseudo,String revendeur){
        String pathPseudo = DatabasePath.getPathAcheteurCompte() + pseudo + "/notifications.csv";
        String pathRevendeur = DatabasePath.getPathRevendeurCompte() + revendeur + "/notifications.csv";
        String msgPseudo = "vous avez bien envoyer un signalement de probleme a: " + revendeur;
        String msgRevendeur = "vous avez recu un signalement de probleme par: " + pseudo;
        CSVHandler.appendCSV(pathPseudo, msgPseudo);
        CSVHandler.appendCSV(pathRevendeur, msgRevendeur);
    }


    public static void notificationActionRetourEchange(String pseudo,String revendeur){
        String pathPseudo = DatabasePath.getPathAcheteurCompte() + pseudo + "/notifications.csv";
        String pathRevendeur = DatabasePath.getPathRevendeurCompte() + revendeur + "/notifications.csv";
        String msgPseudo = "vous avez bien envoyer une demande de change ou retour a: " + revendeur;
        String msgRevendeur = "vous avez recu une demande de change ou retour par: " + pseudo;
        CSVHandler.appendCSV(pathPseudo, msgPseudo);
        CSVHandler.appendCSV(pathRevendeur, msgRevendeur);
    }


    public static void notificationActionNoteEval(String pseudo,String produit){
        String pathPseudo = DatabasePath.getPathAcheteurCompte() + pseudo + "/notifications.csv";
        String msgPseudo = "Votre note et evaluation sont bien enregistrer par le produit: " + produit;
        CSVHandler.appendCSV(pathPseudo, msgPseudo);
    }




}
