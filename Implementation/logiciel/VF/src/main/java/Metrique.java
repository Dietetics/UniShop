import java.util.HashMap;
import java.util.Map;

public class Metrique {

    private static final int NOMBRE_COMMANDES = 50;
    private static final int NOMBRE_ARTICLES_ACHETES = 120;
    private static final int LIKES_EVALUATIONS = 300;


    public static void voir_metriques() {
        System.out.println("----- Métriques des Activités -----");

        // Métriques liées à ses propres activités
        System.out.println("Vos Métriques :");
        System.out.println("Nombre de Commandes Passées : " + NOMBRE_COMMANDES);
        System.out.println("Nombre d'Articles Achetés : " + NOMBRE_ARTICLES_ACHETES);
        System.out.println("Likes Reçus sur les Évaluations : " + LIKES_EVALUATIONS);

        // Métriques liées aux produits
        System.out.println("\nMétriques des Produits :");
        Map<String, Integer> metriquesProduits = recupererMetriquesProduits();
        for (Map.Entry<String, Integer> entry : metriquesProduits.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    // Simulation de métriques pour les produits (à remplacer par votre logique réelle)
    private static Map<String, Integer> recupererMetriquesProduits() {
        Map<String, Integer> metriques = new HashMap<>();
        metriques.put("Nombre de Likes", 150);
        metriques.put("Note Moyenne", 4);
        metriques.put("Nombre d'Évaluations", 50);
        return metriques;
    }



}
