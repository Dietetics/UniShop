import java.util.Scanner;

public class GestionProbleme {

    private static Scanner scanner = new Scanner(System.in);


    public GestionProbleme() {
    }


    public static void gestionProblemes() {
        try {
            System.out.println("----- Gestion de Problèmes -----");

            // Simulation de l'acheteur signalant un problème
            System.out.println("Acheteur: J'ai un problème avec le produit que j'ai acheté.");

            // Simulation du revendeur proposant des solutions
            proposerSolutions();

            // Simulation de l'acheteur acceptant la solution
            accepterSolution();
        } catch (Exception e) {
            System.out.println("Erreur lors de la gestion du problème: " + e.getMessage());
        }
    }

    private static void proposerSolutions() {
        System.out.println("System automatique: Nous sommes désolés pour le désagrément. Voici quelques solutions possibles:");
        System.out.println("1. Réparation du produit défectueux");
        System.out.println("2. Réexpédition d'un produit de remplacement");
        System.out.print("Veuillez choisir une solution pour aider l acheteur: ");
        int choix = scanner.nextInt();

        if (choix == 1) {
            System.out.println("Nous prendrons en charge la réparation du produit défectueux.");
        } else if (choix == 2) {
            System.out.println("Nous procéderons à la réexpédition d'un produit de remplacement.");
        } else {
            throw new IllegalArgumentException("Choix invalide. Veuillez choisir une solution valide.");
        }
    }

    private static void accepterSolution() {
        System.out.print("Acheteur: J'accepte la solution proposée ");
    }
}
