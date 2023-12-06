import java.util.InputMismatchException;

public class VisualiserRevendeur {

    private static String revendeur;
    private static String pathNotifications;

    public VisualiserRevendeur(String revendeur) {

        this.revendeur = revendeur;
        this.pathNotifications = DatabasePath.getRevendeurComptePath() + revendeur + "/notifications.csv";
    }

    public static void menu() {

        try {
            Boolean condition = true;
            while (condition) {
                System.out.println("\nVisualiser vos informations:");
                System.out.println("--------------------------------");
                System.out.println("1. Voir les evaluations");
                System.out.println("2. Voir les metriques");
                System.out.println("3. Voir les notifications");
                System.out.println("0. quitter");

                System.out.print("Votre choix : ");

                condition = optionVisualiser();

            }
        } catch (InputMismatchException e) {
            System.out.println("Erreur : Veuillez entrer un nombre entier.");
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    public static Boolean optionVisualiser() {
        try {

            int choix = myScanner.getIntInput();
            System.out.println("\n\n");

            switch (choix) {
                case 1:
                    // Evaluation;
                    break;
                case 2:
                    Metrique.voirMetriquesRevendeur(getRevendeur());
                    break;
                case 3:
                    Notification.voirNotifications(getPathNotifications(),getRevendeur());
                    break;
                case 0:
                    return false;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Erreur : Veuillez entrer un nombre entier.");
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }
        return true;
    }

    public static String getRevendeur() {
        return revendeur;
    }

    public static String getPathNotifications() {
        return pathNotifications;
    }
}
