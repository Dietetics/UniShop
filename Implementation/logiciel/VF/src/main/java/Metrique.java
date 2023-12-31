import java.util.HashMap;
import java.util.Map;

public class Metrique {



    public static void voirMetriquesAcheteur(String auteur){

        String pathHistoire = DatabasePath.getPathAcheteurCompte() + auteur + "/histoire.csv";
        String pathSuiviPar = DatabasePath.getPathAcheteurCompte() + auteur + "/suiviPar.csv";
        String pathSuivreAcheteur = DatabasePath.getPathAcheteurCompte() + auteur + "/suivreAcheteur.csv";
        String pathSuivreRevendeur = DatabasePath.getPathAcheteurCompte() + auteur + "/suivreRevendeur.csv";
        String pathLikerProduit = DatabasePath.getPathAcheteurCompte() + auteur + "/likerProduit.csv";
        String pathLikerRevendeur = DatabasePath.getPathAcheteurCompte() + auteur + "/likerRevendeur.csv";


        int articles = CSVHandler.countLines(pathHistoire);
        int fans = CSVHandler.countLines(pathSuiviPar);
        int acheteurSuivi = CSVHandler.countLines(pathSuivreAcheteur);
        int revendeurSuivi = CSVHandler.countLines(pathSuivreRevendeur);

        int nbLikerProduit = CSVHandler.countLines(pathLikerProduit);
        int nbLikerRevendeur = CSVHandler.countLines(pathLikerRevendeur);
        int sommeLikes = nbLikerProduit + nbLikerRevendeur;


        System.out.println("Voici vos metriques");
        System.out.println("-----------------------");
        System.out.println("Nombre d'Articles Achetés : " + articles);
        System.out.println("Nombre de fans : " + fans);
        System.out.println("Nombre d'acheteur suivi : " + acheteurSuivi);
        System.out.println("Nombre de revendeur suivi : " + revendeurSuivi);
        System.out.println("Nombre de likes envoyer : " + sommeLikes);
        // System.out.println("Likes Reçus sur les Évaluations : " + LIKES_EVALUATIONS);
        System.out.println("-----------------------\n");
    }






    public static void voirMetriquesRevendeur(String auteur){

        String pathOffrir = DatabasePath.getPathRevendeurCompte() + auteur + "/offrir.csv";
        String pathFans = DatabasePath.getPathRevendeurCompte() + auteur + "/fans.csv";
        String pathLikes = DatabasePath.getPathRevendeurCompte() + auteur + "/likerPar.csv";

        int nbProduitOffrir = CSVHandler.countLines(pathOffrir);
        int nbFans = CSVHandler.countLines(pathFans);
        int nbLikes = CSVHandler.countLines(pathLikes);

        System.out.println("Voici vos metriques");
        System.out.println("-----------------------");
        System.out.println("Nombre de produits offers : " + nbProduitOffrir);
        System.out.println("Nombre de fans : " + nbFans);
        System.out.println("Nombre de likes : " + nbLikes);
//        System.out.println("Nombre d'evaluation : " + nbEvaluation);
//        System.out.println("Note moyen des produits: " + notes);
        System.out.println("-----------------------\n");
    }

}
