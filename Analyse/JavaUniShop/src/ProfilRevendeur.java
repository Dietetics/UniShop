import java.util.*;

public class ProfilRevendeur {
    private static Scanner scanner = new Scanner(System.in);

    private String nom;
    private String courriel;
    private String revendeurPath="src/data/revendeur.csv";

    public ProfilRevendeur(String nom, String courriel) {

        this.nom = nom;
        this.courriel = courriel;

        displayMenuRevendeur();
    }

    public static void main(String[] args) {
        scanner.close();
    }


    public void displayMenuRevendeur() {
        int choix = 90;
        while (choix != 0) {
            try {
                System.out.println("\n");
                System.out.println("Bonsoir cher Revendeur:" + getNom() + "              " + getCourriel() );
                System.out.println("----------------------------------------------------------" );
                System.out.println("0. deconnecter");
                System.out.println("1. modifier le Profil");
                System.out.println("2. Offrir un Produit");
                System.out.println("3. modifier l'etat d'une commande");
                System.out.println("4. voir les metriques");
                System.out.println("5. gestion de probleme");
                System.out.println("6. confirmer la reception d'un retour");
                System.out.println("7. offrir une promo");
                System.out.println("8. voir les notifications");
                System.out.print("\n");

                System.out.print("Choix : ");
                choix = scanner.nextInt();

                switch (choix) {
                    case 0:
                        System.out.println("Votre compte est bien deconnecter: " + nom + "-------");
                        D2.displayMenuPrincipale();
                        break;
                    case 1: modifie_profil(); break;
                    case 2: offrir_produit(); break;
                    case 3: modifierEtat(); break;
                    case 4: voir_metriques(); break;
                    case 5: gestion_probleme(); break;
                    case 6: confirmer_reception(); break;
                    case 7: offrir_promo(); break;
                    case 8: voir_notification(); break;
                    default:
                        System.out.println("Choix invalide. Veuillez reessayer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer un nombre entier.");
                scanner.nextLine(); // Effacer la ligne incorrecte dans le scanner
            }
        }
    }

    public void modifie_profil(){

        int lineIndex = CSVHandler.findOccurrenceIndex(getRevendeurPath(),getNom(),0);
        String data = CSVHandler.readLineByIndex(getRevendeurPath(),lineIndex);

        String nom = CSVHandler.getColumnValue(data, 0);
        String adresse = CSVHandler.getColumnValue(data, 1);
        String courriel = CSVHandler.getColumnValue(data, 2);
        String telephone = CSVHandler.getColumnValue(data, 3);

        String choix = "90";
        while (choix != ":q") {
            try {
                System.out.println("\n");
                System.out.println("Cher revendeur: " + getNom() + ", Voici votre profil");
                System.out.println("-----------------------------------------------------");
                System.out.println("nom: " + nom);
                System.out.println("adresse: " + adresse);
                System.out.println("courriel: " + courriel);
                System.out.println("telephone: " + telephone);
                System.out.println("-----------------------------------------------------");
                System.out.println(":e pour enregistrer la modification ou :q pour quitter");
                System.out.print("\n");

                System.out.print("Choix : ");
                choix = scanner.next();


                switch (choix) {

                    case ":q": displayMenuRevendeur(); break;
                    case "nom":
                        Boolean conditionNom = true;
                        while (conditionNom) {
                            try{
                                System.out.print("Votre recent nom est: " + nom + "\nquel est votre nouveau nom: ");
                                String scannedNom = scanner.next();

                                if (!InputRestreint.isValidInput(scannedNom)) {
                                    throw new IllegalArgumentException("Le nom doit contenir au moins 2 caracteres alphabetiques.");
                                }
                                nom = scannedNom;
                                conditionNom = false;}
                            catch (Exception e) {
                                System.out.println("Erreur: " + e.getMessage());
                                scanner.nextLine();
                            }
                        }
                        break;
                    case "courriel":
                        Boolean conditionCourriel = true;
                        while (conditionCourriel) {try{
                            System.out.print("Votre recent courriel est: " + courriel + "\nquel est votre nouveau courriel: ");
                            String scannedCourriel = scanner.next();

                            if (!InputRestreint.isValidCourriel(scannedCourriel)) {
                                throw new IllegalArgumentException("Le courriel doit terminer par @gamil.com ou @umontreal.ca");
                            }
                            courriel = scannedCourriel;
                            conditionCourriel = false;}
                        catch (Exception e) {
                            System.out.println("Erreur: " + e.getMessage());
                            scanner.nextLine();
                        }
                        }
                        break;
                    case "telephone":
                        Boolean conditionTelephone = true;
                        while (conditionTelephone) {try{
                            System.out.print("Votre recent telephone est: " + telephone + "\nquel est votre nouveau telephone: ");
                            String scannedTelephone = scanner.next();

                            if (!InputRestreint.isValidTelephone(scannedTelephone)) {
                                throw new IllegalArgumentException("Le telephone doit etre composer de 10 chiffres");
                            }
                            telephone = scannedTelephone;
                            conditionTelephone = false;}
                        catch (Exception e) {
                            System.out.println("Erreur: " + e.getMessage());
                            scanner.nextLine();
                        }
                        }
                        break;
                    case "adresse":
                        Boolean conditionAdresse = true;
                        while (conditionAdresse) {try{
                            System.out.print("Votre recent adresse est: " + adresse + "\nquel est votre nouveau adresse: ");
                            String scannedAdresse = scanner.next();

                            if (!InputRestreint.isValidAddress(scannedAdresse)) {
                                throw new IllegalArgumentException("adresse doit avoir un longueur inferieur a 20");
                            }
                            adresse = scannedAdresse;
                            conditionAdresse = false;}
                        catch (Exception e) {
                            System.out.println("Erreur: " + e.getMessage());
                            scanner.nextLine();
                        }
                        }
                        break;
                    case ":e":
                        List<String> newCSVLine = Arrays.asList(nom, adresse, courriel, telephone);
                        int lineIndex2 = CSVHandler.findOccurrenceIndex(getRevendeurPath(),getNom(),0);
                        CSVHandler.uploadCSVLine(revendeurPath,lineIndex2-1,newCSVLine);
                        setNom(nom);
                        break;
                    default:
                        System.out.println("Choix invalide. Veuillez reessayer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erreur : Veuillez entrer un nombre entier.");
                scanner.nextLine(); // Effacer la ligne incorrecte dans le scanner
            }
        }
    }
    public void offrir_produit(){
        System.out.println("En developement");
    }
    public void modifierEtat(){
        System.out.println("En developement");
    }
    public void voir_metriques(){
        System.out.println("En developement");
    }
    public void gestion_probleme(){
        System.out.println("En developement");
    }
    public void confirmer_reception(){
        System.out.println("En developement");
    }
    public void offrir_promo(){
        System.out.println("En developement");
    }
    public void voir_notification(){
        System.out.println("En developement");
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCourriel() {
        return courriel;
    }


    public String getRevendeurPath() {
        return revendeurPath;
    }
}



