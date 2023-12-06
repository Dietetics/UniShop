import java.util.*;

import java.util.Scanner;

public class Histoire {

    private static String pathHistoire;
    private static String acheteur;

    public Histoire(String acheteur) {
        this.pathHistoire = DatabasePath.getAcheteurComptePath() + acheteur + "/histoire.csv";
        this.acheteur = acheteur;
    }

    public static void menu() {

        try {
            Boolean condition = true;
            while (condition) {
                System.out.println("\nMenu de l'histoire:");
                System.out.println("--------------------------------");
                System.out.println("1. Suivre les Commandes");
                System.out.println("2. Confirmer la reception");
                System.out.println("3. Sgnaler un probleme");
                System.out.println("4. Retouner ou Echanger un produit");
                System.out.println("5. Donner note et evaluation");
                System.out.println("0. quitter");

                System.out.print("Votre choix : ");

                condition = optionHistoire();

            }
        } catch (InputMismatchException e) {
            System.out.println("Erreur : Veuillez entrer un nombre entier.");
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    public static Boolean optionHistoire() {
        try {

            int choix = myScanner.getIntInput();

            switch (choix) {
                case 1:
                    suivreCommande();
                    break;
                case 2:
                    confirmerReception();
                    break;
                case 3:
                    signalerProbleme();
                    break;
                case 4:
                    retourEchangeProduit();
                    break;
                case 5:
                    noteEvaluation();
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



    public static void suivreCommande() {

        System.out.println("\nVoici vos achats");
        System.out.println("----------------------------------------------");
        CSVHandler.printCSV(CSVHandler.readCSV(getPathHistoire(),9999));
        System.out.println("----------------------------------------------");
    }


    public static void confirmerReception(){

        System.out.println("\nVeuillez entrer le nom du produit arriver");
        String suspect = myScanner.getStringInput();
        if (rechercherDansPremiereColonne(getPathHistoire(),suspect)) {
            modifierEtatProduit(getPathHistoire(),suspect,"enLivraison");
        };
    }

    public static boolean rechercherDansPremiereColonne(String filePath, String valeurRecherchee) {
        try {
            // Lire toutes les lignes du fichier CSV
            List<String> lines = CSVHandler.readLinesFromCSV(filePath);

            for (String line : lines) {
                String[] columns = line.split(",");

                // Vérifier si la valeur recherchée est présente dans la première colonne
                if (columns.length > 0 && columns[0].equals(valeurRecherchee)) {
                    System.out.println("Le produit est recherche.");
                    return true;
                }
            }

            System.out.println("On na pas pu trouver le. Veuillez entrer de nouveau");
            return false;
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite : " + e.getMessage());
            return false;
        }
    }


    public static void modifierEtatProduit(String filePath, String nomProduit, String nouvelEtat) {
        try {
            // Lire toutes les lignes du fichier CSV
            List<String> lines = CSVHandler.readLinesFromCSV(filePath);

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] columns = line.split(",");

                // Vérifier si le nom du produit correspond
                if (columns.length > 1 && columns[0].equals(nomProduit)) {
                    // Vérifier si l'état correspond
                    if (columns[1].equals(nouvelEtat)) {
                        // Modifier l'état
                        columns[1] = "livre";
                        lines.set(i, String.join(",", columns));

                        // Écrire les modifications dans le fichier
                        CSVHandler.writeLinesToCSV(filePath, lines);
                        System.out.println("L'etat du produit est maintenant livre.");
                        return; // Arrêter la boucle après la modification
                    }
                }
            }

            System.out.println("Votre produit nest pas en etat de livraison, elle nest donc pas possible de lavoir recu, si jamais quoi que ce soit " +
                    "veuillez nos contact");
        } catch (Exception e) {
            System.out.println("Une erreur s'est produite : " + e.getMessage());
        }
    }




    public static void signalerProbleme() {
        Boolean condition = true;

        while (condition) {
            try {
                System.out.println("\nVeuillez entrer le nom du revendeur pour signaler un problème ou :q pour quitter");
                System.out.println("---------------------------------------------------------------");
                String revendeur = myScanner.getStringInput();

                if (revendeur.equals(":q")) return;
                int index = CSVHandler.findOccurrenceIndex(DatabasePath.getRevendeurPath(), revendeur, 0);

                if (index != -1) {
                    System.out.println("Veuillez entrer un message pour signaler le problème");
                    String msg = myScanner.getStringInput();

                    String pathRevendeurSignaler = DatabasePath.getRevendeurComptePath() + revendeur + "/signalProbleme.csv";
                    String pathRevendeurNotification = DatabasePath.getRevendeurComptePath() + revendeur + "/notification.csv";
                    String pathAcheteurSignaler = DatabasePath.getAcheteurComptePath() + getAcheteur() + "/signaler.csv";

                    // Ajoutez le message dans les fichiers CSV correspondants
                    CSVHandler.appendCSV(pathRevendeurSignaler, getAcheteur()+ ": " + msg);
                    CSVHandler.appendCSV(pathRevendeurNotification, getAcheteur()+ " vous a envoyer un signalement de probleme" );
                    CSVHandler.appendCSV(pathAcheteurSignaler, msg);


                    condition = false;
                    System.out.println("Votre message est bien envoyer");
                } else {
                    System.out.println("Revendeur non trouvé dans la base de données.");
                }

                // Sortez de la boucle après avoir effectué la tâche avec succès
            } catch (Exception e) {
                // Gérez les exceptions ici (par exemple, IOException, InputMismatchException)
                System.out.println("Une erreur s'est produite : " + e.getMessage());
            }
        }
    }

    public static void retourEchangeProduit(){
        Boolean condition = true;

        while (condition) {
            try {
                System.out.println("\nVeuillez entrer le nom du revendeur pour demander un retour ou une echange");
                System.out.println("---------------------------------------------------------------");
                String revendeur = myScanner.getStringInput();

                if (revendeur.equals(":q")) return;
                int index = CSVHandler.findOccurrenceIndex(DatabasePath.getRevendeurPath(), revendeur, 0);

                if (index != -1) {
                    System.out.println("Veuillez entrer la raison pour un retour ou une echange");
                    String msg = myScanner.getStringInput();

                    String pathRevendeurRetourEchange = DatabasePath.getRevendeurComptePath() + revendeur + "/retourEchange.csv";
                    String pathRevendeurNotification = DatabasePath.getRevendeurComptePath() + revendeur + "/notification.csv";
                    String pathAcheteurRetourEchange = DatabasePath.getAcheteurComptePath() + getAcheteur() + "/retourEchange.csv";

                    // Ajoutez le message dans les fichiers CSV correspondants
                    CSVHandler.appendCSV(pathRevendeurRetourEchange, getAcheteur()+ ": " + msg);
                    CSVHandler.appendCSV(pathRevendeurNotification, getAcheteur()+ " vous a envoyer une demande de change ou retour" );
                    CSVHandler.appendCSV(pathAcheteurRetourEchange, msg);


                    condition = false;
                    System.out.println("Votre message est bien envoyer, le revendeur recevra sous peu, puis evaluer votre situation, s'il accepte votre parole" +
                            " il vous contactera");
                } else {
                    System.out.println("Revendeur non trouvé dans la base de données.");
                }

                // Sortez de la boucle après avoir effectué la tâche avec succès
            } catch (Exception e) {
                // Gérez les exceptions ici (par exemple, IOException, InputMismatchException)
                System.out.println("Une erreur s'est produite : " + e.getMessage());
            }
        }
    }



    public static void noteEvaluation(){
        Boolean condition = true;

        while (condition) {
            try {
                System.out.println("\nVeuillez entrer le nom du produit pour donner une note et evaluation");
                System.out.println("---------------------------------------------------------------");
                String produit = myScanner.getStringInput();

                if (produit.equals(":q")) return;
                int index = CSVHandler.findOccurrenceIndex(DatabasePath.getProduitPath(), produit, 0);

                if (index != -1) {

                    int note;
                    do {
                        try {
                            System.out.print("Veuillez entrer une note (entre 0 et 100) : ");
                            note = myScanner.getIntInput();

                            if (note < 0 || note > 100) {
                                throw new IllegalArgumentException("La note doit être comprise entre 0 et 100.");
                            }
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Erreur : Veuillez entrer un nombre entier.");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Erreur : " + e.getMessage());
                        }
                    } while (true);

                    System.out.print("Veuillez entrer une evaluation: ");
                    String eval = myScanner.getStringInput();

                    String pathProduitNote = DatabasePath.getProduitInfoPath() + produit + "/note.csv";
                    String pathProduitEval = DatabasePath.getProduitInfoPath() + produit + "/evaluation.csv";

                    String pathAcheteurEval = DatabasePath.getAcheteurComptePath() + getAcheteur() + "/evaluation.csv";

                    // Ajoutez le message dans les fichiers CSV correspondants
                    CSVHandler.appendCSV(pathProduitNote, "Par acheteur: " + getAcheteur()+ ". Note: " + note);
                    CSVHandler.appendCSV(pathProduitEval, "Par acheteur: " + getAcheteur()+ ". Eval: " + eval );
                    CSVHandler.appendCSV(pathAcheteurEval, produit + " a note: " + note + " evaluation: " + eval);


                    condition = false;
                    System.out.println("Votre message est bien envoyer, le revendeur recevra sous peu, puis evaluer votre situation, s'il accepte votre parole" +
                            " il vous contactera");
                } else {
                    System.out.println("Revendeur non trouvé dans la base de données.");
                }

                // Sortez de la boucle après avoir effectué la tâche avec succès
            } catch (Exception e) {
                // Gérez les exceptions ici (par exemple, IOException, InputMismatchException)
                System.out.println("Une erreur s'est produite : " + e.getMessage());
            }
        }
    }














    public static String getPathHistoire() {
        return pathHistoire;
    }

    public static String getAcheteur() {
        return acheteur;
    }
}

