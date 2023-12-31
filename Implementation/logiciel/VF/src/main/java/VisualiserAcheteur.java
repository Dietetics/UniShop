import java.util.InputMismatchException;

public class VisualiserAcheteur {

    private static String acheteur;
    private static String pathPts,pathNotifications;

    public VisualiserAcheteur(String acheteur) {

        this.acheteur = acheteur;
        this.pathPts = DatabasePath.getPathAcheteurCompte() + acheteur + "/points.csv";
        this.pathNotifications = DatabasePath.getPathAcheteurCompte() + acheteur + "/notifications.csv";
    }

    public static void menu() {

        try {
            Boolean condition = true;
            while (condition) {
                System.out.println("\nVisualiser vos informations:");
                System.out.println("--------------------------------");
                System.out.println("1. Voir les points du programme de fidelite");
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
                    Points.voirPts(getPathPts(),getAcheteur());
                    break;
                case 2:
                    Metrique.voirMetriquesAcheteur(getAcheteur());
                    break;
                case 3:
                    Notification.voirNotifications(getPathNotifications());
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

    public static String getAcheteur() {
        return acheteur;
    }

    public static String getPathPts() {
        return pathPts;
    }


    public static String getPathNotifications() {
        return pathNotifications;
    }
}
