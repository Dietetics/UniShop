import java.util.HashMap;
import java.util.Map;

public class Metrique {



    public static void voirMetriquesAcheteur(String auteur){

        String pathHistoire = DatabasePath.getAcheteurComptePath() + auteur + "/histoire.csv";
        String pathSuiviPar = DatabasePath.getAcheteurComptePath() + auteur + "/suiviPar.csv";
        String pathSuivreAcheteur = DatabasePath.getAcheteurComptePath() + auteur + "/suivreAcheteur.csv";
        String pathSuivreRevendeur = DatabasePath.getAcheteurComptePath() + auteur + "/suivreRevendeur.csv";
        String pathLikerProduit = DatabasePath.getAcheteurComptePath() + auteur + "/likerProduit.csv";
        String pathLikerRevendeur = DatabasePath.getAcheteurComptePath() + auteur + "/likerRevendeur.csv";


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

//        String pathHistoire = DatabasePath.getAcheteurComptePath() + auteur + "/histoire.csv";
//        String pathSuiviPar = DatabasePath.getAcheteurComptePath() + auteur + "/suiviPar.csv";
//        String pathSuivreAcheteur = DatabasePath.getAcheteurComptePath() + auteur + "/suivreAcheteur.csv";
//        String pathSuivreRevendeur = DatabasePath.getAcheteurComptePath() + auteur + "/suivreRevendeur.csv";
//        String pathLikerProduit = DatabasePath.getAcheteurComptePath() + auteur + "/likerProduit.csv";
//        String pathLikerRevendeur = DatabasePath.getAcheteurComptePath() + auteur + "/likerRevendeur.csv";
//
//
//        int articles = CSVHandler.countLines(pathHistoire);
//        int fans = CSVHandler.countLines(pathSuiviPar);
//        int acheteurSuivi = CSVHandler.countLines(pathSuivreAcheteur);
//        int revendeurSuivi = CSVHandler.countLines(pathSuivreRevendeur);
//
//        int nbLikerProduit = CSVHandler.countLines(pathLikerProduit);
//        int nbLikerRevendeur = CSVHandler.countLines(pathLikerRevendeur);
//        int sommeLikes = nbLikerProduit + nbLikerRevendeur;
//
//
//        System.out.println("Voici vos metriques");
//        System.out.println("-----------------------");
//        System.out.println("Nombre d'Articles Achetés : " + articles);
//        System.out.println("Nombre de fans : " + fans);
//        System.out.println("Nombre d'acheteur suivi : " + acheteurSuivi);
//        System.out.println("Nombre de revendeur suivi : " + revendeurSuivi);
//        System.out.println("Nombre de likes envoyer : " + sommeLikes);
//        // System.out.println("Likes Reçus sur les Évaluations : " + LIKES_EVALUATIONS);
//        System.out.println("-----------------------\n");
    }

}
