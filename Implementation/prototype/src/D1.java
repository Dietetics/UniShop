import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;

public class D1 {




    public void main_menu() {
        System.out.println("\n");
        System.out.println("------ Bienvenu à notre plateforme UniShop -------");
        System.out.println("1. Rechercher un produit");
        System.out.println("2. S'inscrire en tant que revendeur ");
        System.out.println("3. S'inscrire en tant qu'acheteur");
        System.out.println("4. Connecter");
        System.out.println("5. Quitter");
        System.out.print("\n");

        Scanner scanner = new Scanner(System.in);

        int chiffre = scanner.nextInt();
        switch (chiffre) {
            case 1: Rechercher_produit(); break;
            case 2: inscrire_revendeur(); break;
            case 3: inscrire_acheteur(); break;
            case 4: connecter(); break;
            case 5: break;
            default:
                System.out.println("En construction" + "\n");
                main_menu();
        }
    }
    public void Rechercher_produit() {

        Scanner scanner = new Scanner(System.in);
        Boolean condition = true;

        while (condition) {
            System.out.print("Entré un mot clé: ");
            String valeur = scanner.next();
            char firstChar = valeur.charAt(0);

            if (firstChar >= 'a' && firstChar <= 'o') {
                System.out.println("------- bienvenu à la page " + valeur + "--------");
                System.out.println("blablabla");
                System.out.println("\n");
                System.out.println("Entré 'q' pour quitter ou");

            } else if (firstChar == 'q'){ main_menu();}
                else {System.out.println("Nous sommes désolés, nous avons aucun produit ayant " +
                            "la première lettre commencant par: " + valeur);
                    System.out.println("Entré un autre mot.");
                    System.out.print("\n");
            }
        }
    }
    public void inscrire_revendeur() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----- Bienvenu à notre page d'inscription pour devenir revendeur -----");

        String finalNom = null;
        String finalAdresse = null;
        String finalCourriel = null;
        String finalTelephone = null;


        Boolean conditionNom = true;
        while(conditionNom){
            System.out.print("Veuillez entré votre nom: ");
            String nom = scanner.next();
            char verifieNom = nom.charAt(0);

            if (verifieNom == 'y' || verifieNom == 'z' || verifieNom == 'w' || verifieNom == 'v' || verifieNom == 'u' ){
                System.out.print("\n");
                System.out.println("Votre nom ne respect pas nos critères d'exigences, il faut éviter de commencer par les chars: y,z,w,v,u ");
            } else { conditionNom = false; finalNom = nom;}
        }




        Boolean conditionAdresse = true;
        while(conditionAdresse){
            System.out.print("Veuillez entré votre adresse: ");
            String adresse = scanner.next();
            char verifieAdresse = adresse.charAt(0);

            if (verifieAdresse == 'y' || verifieAdresse == 'z' || verifieAdresse == 'w' || verifieAdresse == 'v' || verifieAdresse == 'u' ){
                System.out.print("\n");
                System.out.println("Votre Adresse ne respect pas nos critères d'exigences, il faut éviter de commencer par les chars: y,z,w,v,u ");
            } else { conditionAdresse = false; finalAdresse = adresse;}
        }



        Boolean conditionCourriel = true;
        while(conditionCourriel){
            System.out.print("Veuillez entré votre Courriel: ");
            String courriel = scanner.next();
            char verifieCourriel = courriel.charAt(0);

            if (verifieCourriel == 'y' || verifieCourriel == 'z' || verifieCourriel == 'w' || verifieCourriel == 'v' || verifieCourriel == 'u' ){
                System.out.print("\n");
                System.out.println("Votre Courriel ne respect pas nos critères d'exigences, il faut éviter de commencer par les chars: y,z,w,v,u ");
            } else { conditionCourriel = false; finalCourriel = courriel;}
        }


        Boolean conditionTelephone = true;
        while(conditionTelephone){
            System.out.print("Veuillez entré votre Telephone: ");
            String telephone = scanner.next();
            char verifieTelephone = telephone.charAt(0);

            if (verifieTelephone == 'y' || verifieTelephone == 'z' || verifieTelephone == 'w' || verifieTelephone == 'v' || verifieTelephone == 'u' ){
                System.out.print("\n");
                System.out.println("Votre Telephone ne respect pas nos critères d'exigences, il faut éviter de commencer par les chars: y,z,w,v,u ");
            } else { conditionTelephone = false; finalTelephone = telephone;}
        }

        System.out.print("\n");
        System.out.print("------------------------");
        System.out.println("Voila vos données enregistrer dans notre système");
        System.out.println("nom: " + finalNom);
        System.out.println("Adresse: " + finalAdresse);
        System.out.println("Courriel: " + finalCourriel);
        System.out.println("Telephone: " + finalTelephone);


        String[] rowData = {finalNom, finalCourriel, finalAdresse, finalTelephone};
        appendRowToCSV("src/list_revendeur.csv", rowData);


        System.out.println("votre inscription est bien enregistré");
        System.out.println("\n");

        System.out.print("Pour retourner à notre page principale, Veuillez entré 'q' ");
        String whatNext = scanner.next();
        char firstChar = whatNext.charAt(0);
        if (firstChar == 'q'){ main_menu();}
        scanner.close();




    }
    public void inscrire_acheteur() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----- Bienvenu à notre page d'inscription pour devenir Acheteur -----");

        String finalNom = null;
        String finalPrenom = null;
        String finalPseudo = null;
        String finalCourriel = null;
        String finalTelephone = null;

        String finalAdresse = null;


        Boolean conditionNom = true;
        while(conditionNom){
            System.out.print("Veuillez entré votre nom: ");
            String nom = scanner.next();
            char verifieNom = nom.charAt(0);

            if (verifieNom == 'y' || verifieNom == 'z' || verifieNom == 'w' || verifieNom == 'v' || verifieNom == 'u' ){
                System.out.print("\n");
                System.out.println("Votre nom ne respect pas nos critères d'exigences, il faut éviter de commencer par les chars: y,z,w,v,u ");
            } else { conditionNom = false; finalNom = nom;}
        }

        Boolean conditionPrenom = true;
        while(conditionPrenom){
            System.out.print("Veuillez entré votre prenom: ");
            String prenom = scanner.next();
            char verifiePrenom = prenom.charAt(0);

            if (verifiePrenom == 'y' || verifiePrenom == 'z' || verifiePrenom == 'w' || verifiePrenom == 'v' || verifiePrenom == 'u' ){
                System.out.print("\n");
                System.out.println("Votre Prenom ne respect pas nos critères d'exigences, il faut éviter de commencer par les chars: y,z,w,v,u ");
            } else { conditionPrenom = false; finalPrenom = prenom;}
        }


        Boolean conditionPseudo = true;
        while(conditionPseudo){
            System.out.print("Veuillez entré votre pseudo: ");
            String pseudo = scanner.next();
            char verifiePseudo = pseudo.charAt(0);

            if (verifiePseudo == 'y' || verifiePseudo == 'z' || verifiePseudo == 'w' || verifiePseudo == 'v' || verifiePseudo == 'u' ){
                System.out.print("\n");
                System.out.println("Votre pseudo ne respect pas nos critères d'exigences, il faut éviter de commencer par les chars: y,z,w,v,u ");
            } else { conditionPseudo = false; finalPseudo = pseudo;}
        }

        Boolean conditionCourriel = true;
        while(conditionCourriel){
            System.out.print("Veuillez entré votre Courriel: ");
            String courriel = scanner.next();
            char verifieCourriel = courriel.charAt(0);

            if (verifieCourriel == 'y' || verifieCourriel == 'z' || verifieCourriel == 'w' || verifieCourriel == 'v' || verifieCourriel == 'u' ){
                System.out.print("\n");
                System.out.println("Votre Courriel ne respect pas nos critères d'exigences, il faut éviter de commencer par les chars: y,z,w,v,u ");
            } else { conditionCourriel = false; finalCourriel = courriel;}
        }

        Boolean conditionTelephone = true;
        while(conditionTelephone){
            System.out.print("Veuillez entré votre Telephone: ");
            String telephone = scanner.next();
            char verifieTelephone = telephone.charAt(0);

            if (verifieTelephone == 'y' || verifieTelephone == 'z' || verifieTelephone == 'w' || verifieTelephone == 'v' || verifieTelephone == 'u' ){
                System.out.print("\n");
                System.out.println("Votre Telephone ne respect pas nos critères d'exigences, il faut éviter de commencer par les chars: y,z,w,v,u ");
            } else { conditionTelephone = false; finalTelephone = telephone;}
        }

        Boolean conditionAdresse = true;
        while(conditionAdresse){
            System.out.print("Veuillez entré votre adresse: ");
            String adresse = scanner.next();
            char verifieAdresse = adresse.charAt(0);

            if (verifieAdresse == 'y' || verifieAdresse == 'z' || verifieAdresse == 'w' || verifieAdresse == 'v' || verifieAdresse == 'u' ){
                System.out.print("\n");
                System.out.println("Votre Adresse ne respect pas nos critères d'exigences, il faut éviter de commencer par les chars: y,z,w,v,u ");
            } else { conditionAdresse = false; finalAdresse = adresse;}
        }

        System.out.print("\n");
        System.out.print("------------------------");
        System.out.println("Voila vos données enregistrer dans notre système");
        System.out.println("nom: " + finalNom);
        System.out.println("prenom: " + finalPrenom);
        System.out.println("pseudo: " + finalPseudo);
        System.out.println("Courriel: " + finalCourriel);
        System.out.println("Telephone: " + finalTelephone);
        System.out.println("Adresse: " + finalAdresse);


        String[] rowData = {finalPseudo, finalCourriel, finalNom, finalPrenom, finalTelephone, finalAdresse};
        appendRowToCSV("src/list_acheteur.csv", rowData);


        System.out.println("votre inscription est bien enregistré");
        System.out.println("\n");

        System.out.print("Pour retourner à notre page principale, Veuillez entré 'q' ");
        String whatNext = scanner.next();
        char firstChar = whatNext.charAt(0);
        if (firstChar == 'q'){ main_menu();}
        scanner.close();
    }
    public void connecter() {
        System.out.println("----- Bienvenu à notre page de connexion -----");
        System.out.println("Êtes-vous un revendeur ou un acheteur");
        System.out.println("1. Revendeur");
        System.out.println("2. Acheteur");
        System.out.println("3. Retourner à la page d'acceuil");
        System.out.println("Veuillez entré un chiffre");

        Scanner scanner1 = new Scanner(System.in);

        int chiffre = scanner1.nextInt();
        switch (chiffre) {
            case 1: profilRevendeur(); break;
            case 2: profilAcheteur(); break;
            case 3: main_menu(); break;
            default:
                System.out.println("En construction" + "\n");
                connecter();
        }

    }
    public void profilRevendeur(){

        Scanner scanner1 = new Scanner(System.in);
        System.out.println("----- Veuillez vous connectez -----");

        System.out.println("Nom: ");
        String nom = scanner1.next();

        System.out.println("Courriel: ");
        String courriel = scanner1.next();

        String csvFilePath = "src/list_revendeur.csv"; // Replace with the path to your CSV file
        List<String> searchTerms = List.of(nom, courriel); // Specify the consecutive terms to search for

        boolean found = checkConsecutiveMatches(csvFilePath, searchTerms);

        if (found) {
            System.out.println("-------------------------------");
            System.out.println("Veuillez entrer un option");
            System.out.println("1. modifier votre profil");
            System.out.println("2. Changer l'état d'une commande");
            System.out.println("3. Accueil");

            Boolean condition = true;
            while(condition) {
                int option = scanner1.nextInt();

                if (option == 1) modifierProfilRevendeur();
                else if (option == 2) changerEtatCommande();
                else if (option == 3) main_menu();
                else System.out.println("On n'a pas cet option, veuillez composer de nouveau");
            }
        } else {
            System.out.println("On ne trouve pas votre compte, veuillez entrer le bon nom et courriel");
            profilRevendeur();
        }
    }
    public void modifierProfilRevendeur(){


        Scanner scanner1 = new Scanner(System.in);
        boolean condition = true;

        while(condition) {
            System.out.println("-------------------------------");
            System.out.println("Quel information voulez-vous modifier?");
            System.out.println("1. nom");
            System.out.println("2. adresse");
            System.out.println("3. courriel");
            System.out.println("4. telephone");
            System.out.println("5. quitter");
            int option = scanner1.nextInt();

            if (option == 1 || option == 2 || option == 3 || option == 4) {
                System.out.println("Quel est la correction?");
                String nouveauVal = scanner1.next();
                System.out.println("\n \n le changement est commis");
            } else if (option == 5) main_menu();
            else System.out.println("option en developpement, veuillez essayer plus tard");
            System.out.println("Avez-vous d'autre chose? Veuillez entrée un chiffre");
        }
    }
    public void changerEtatCommande(){
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("-------------------------------");
        System.out.println("L'état de quel commande aimerez-vous faire modification?");
        System.out.println("Veuillez donné le nom");
        String temp = scanner1.next();
        System.out.println("On a bien trouvé la commande, Vous voulez faire des mise à jours de l'état à:");
        System.out.println("1. en livraison");
        System.out.println("2. livré");
        String option = scanner1.next();
        System.out.println("Votre option est bien enregistré");
        System.out.println("avez-vous d'autres choses?");
        System.out.println("entrez true: continuer \n false: quitter ");
        Boolean bool = scanner1.nextBoolean();
        if (bool == true) changerEtatCommande();
        else main_menu();
    }


    public void profilAcheteur(){

        Scanner scanner1 = new Scanner(System.in);
        System.out.println("----- Veuillez vous connectez -----");

        System.out.println("Pseudo: ");
        String pseudo = scanner1.next();

        System.out.println("Courriel: ");
        String courriel = scanner1.next();

        String csvFilePath = "src/list_acheteur.csv"; // Replace with the path to your CSV file
        List<String> searchTerms = List.of(pseudo, courriel); // Specify the consecutive terms to search for

        boolean found = checkConsecutiveMatches(csvFilePath, searchTerms);

        if (found) {
            System.out.println("-------------------------------");
            System.out.println("Veuillez entrer un options");
            System.out.println("1. modifier votre profil");
            System.out.println("2. liker");
            System.out.println("3. Évaluer");
            System.out.println("4. Confirmer la reception");
            System.out.println("5. Consulter panier d'achat");
            System.out.println("6. Consulter l'état des commandes");
            System.out.println("7. Accueil");

            Boolean condition = true;
            while(condition) {
                int option = scanner1.nextInt();

                if (option == 1) modifierProfilAcheteur();
                else if (option == 2) liker();
                else if (option == 3) evaluer();
                else if (option == 4) confirmer();
                else if (option == 5) consulter_panier();
                else if (option == 6) consulter_etat();
                else if (option == 7) main_menu();
                else System.out.println("On n'a pas cet option, veuillez composer de nouveau");
            }
        } else {
            System.out.println("On ne trouve pas votre compte, veuillez entrer le bon pseudo et courriel");
            profilRevendeur();
        }

    }

    public void modifierProfilAcheteur(){


        Scanner scanner1 = new Scanner(System.in);
        boolean condition = true;

        while(condition) {
            System.out.println("-------------------------------");
            System.out.println("Quel information voulez-vous modifier?");
            System.out.println("1. nom");
            System.out.println("2. prenom");
            System.out.println("3. pseudo");
            System.out.println("4. courriel");
            System.out.println("5. telephone");
            System.out.println("6. adresse");
            System.out.println("7. quitter");

            int option = scanner1.nextInt();

            if (option == 1 || option == 2 || option == 3 || option == 4 || option == 5 || option == 6) {
                System.out.println("Quel est la correction?");
                String nouveauVal = scanner1.next();
                System.out.println("\n \n le changement est commis");
            } else if (option == 7) main_menu();
            else System.out.println("option en developpement, veuillez essayer plus tard");
            System.out.println("Avez-vous d'autre chose? Veuillez entrée un chiffre");
        }
    }

    public void liker(){
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("-------------------------------");
        System.out.println("Quel produit aimerez-vous faire indice d'appreciation?");
        System.out.println("Veuillez donné le nom");
        String nom = scanner1.next();
        System.out.println("On a bien trouvé le produit");
        System.out.println("Votre demande est bien enregistré");
        System.out.println("avez-vous d'autres choses à liker?");
        System.out.println("entrez true: continuer \n false: quitter ");
        Boolean bool = scanner1.nextBoolean();
        if (bool == true) liker();
        else main_menu();
    }

    public void evaluer(){
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("-------------------------------");
        System.out.println("Quel produit aimerez-vous laisser une commentaire?");
        System.out.println("Veuillez donné le nom");
        String nom = scanner1.next();
        System.out.println("On a bien trouvé la commande, quel est le message que vous voulez laisser?");
        String message = scanner1.next();
        System.out.println("Votre message est bien enregistré");
        System.out.println("avez-vous d'autres choses?");
        System.out.println("entrez true: continuer \n false: quitter ");
        Boolean bool = scanner1.nextBoolean();
        if (bool == true) evaluer();
        else main_menu();
    }

    public void confirmer(){
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("-------------------------------");
        System.out.println("Quel commande est arrivé à votre porte");
        System.out.println("Veuillez donné le nom");
        String nom = scanner1.next();
        System.out.println("On a bien trouvé la commande:");
        System.out.println("Votre option est bien enregistré");
        System.out.println("avez-vous d'autres choses?");
        System.out.println("entrez true: continuer \n false: quitter ");
        Boolean bool = scanner1.nextBoolean();
        if (bool == true) confirmer();
        else main_menu();
    }

    public void consulter_panier(){
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("-------------------------------");
        System.out.println("Bienvenu dans votre panier d'achat");
        System.out.println("Voici les produits dans votre panier: \n 1.blablabla \n 2.blablabla \n 3.blablabla \n 4.blablabla \n");
        System.out.println("Veuillez entré un option:");
        System.out.println("1. ajouter une commande");
        System.out.println("2. passer une commande");
        System.out.println("3. retirer une commande");
        System.out.println("4. quitter");
        int option = scanner1.nextInt();


        if (option == 1) ajouter_commande();
        if (option == 2) passer_commande();
        if (option == 3) retirer_commande();
        else main_menu();
    }

    public void ajouter_commande(){
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("-------------------------------");
        System.out.println("Quel produit aimerez-vous ajouter dans le panier?");
        System.out.println("Veuillez donné le nom");
        String nom = scanner1.next();
        System.out.println("On a bien trouvé le produit");
        System.out.println("Votre produit est bien ajouté dans le panier");

        System.out.println("avez-vous d'autres choses?");
        System.out.println("entrez true: continuer \n false: quitter ");
        Boolean bool = scanner1.nextBoolean();
        if (bool == true) consulter_panier();
        else main_menu();
    }
    public void passer_commande(){
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("-------------------------------");
        System.out.println("Si vous être prêt à passer votre commande");
        System.out.println("Veuillez nous fournir vos informations bancaire");
        String nom = scanner1.next();
        System.out.println("Vos données sont bien valide");
        System.out.println("votre commande a bien passé");

        System.out.println("avez-vous d'autres choses?");
        System.out.println("entrez true: continuer \n false: quitter ");
        Boolean bool = scanner1.nextBoolean();
        if (bool == true) consulter_panier();
        else main_menu();
    }
    public void retirer_commande(){
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("-------------------------------");
        System.out.println("Quel produit aimerez-vous retirer de votre panier?");
        System.out.println("Veuillez donné le nom");
        String nom = scanner1.next();
        System.out.println("On a bien trouvé le produit");
        System.out.println("Votre produit est bien retiré de votre panier");

        System.out.println("avez-vous d'autres choses?");
        System.out.println("entrez true: continuer \n false: quitter ");
        Boolean bool = scanner1.nextBoolean();
        if (bool == true) consulter_panier();
        else main_menu();
    }



    public void consulter_etat(){
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("-------------------------------");
        System.out.println("Voilà toutes les produits que vous avez acheter:");
        System.out.println("\n 1.blablabla \n 2.blablabla \n 3.blablabla \n 4.blablabla \n");
        System.out.println("Veuillez entré un option:");
        System.out.println("1. annuler un produit");
        System.out.println("2. échanger un produit");
        System.out.println("3. retourner un produit");
        System.out.println("4. quitter");
        int option = scanner1.nextInt();

        if (option == 1) annuler_produit();
        if (option == 2) echanger_produit();
        if (option == 3) retourner_produit();
        else main_menu();
    }


    public void annuler_produit(){
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("-------------------------------");
        System.out.println("Quel produit aimerez-vous annuler la commande?");
        System.out.println("Veuillez donné le nom");
        String nom = scanner1.next();
        System.out.println("On a bien trouvé le produit");
        System.out.println("Votre produit est bien annulé");

        System.out.println("avez-vous d'autres choses?");
        System.out.println("entrez true: continuer \n false: quitter ");
        Boolean bool = scanner1.nextBoolean();
        if (bool == true) consulter_etat();
        else main_menu();
    }
    public void echanger_produit(){
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("-------------------------------");
        System.out.println("Quel produit aimerez-vous faire un échange?");
        System.out.println("Veuillez donné le nom et la raison");
        String nom = scanner1.next();
        System.out.println("On a bien trouvé le produit");
        System.out.println("Votre produit va être échanger si le revendeur trouve que votre raisonement est acceptable");

        System.out.println("avez-vous d'autres choses?");
        System.out.println("entrez true: continuer \n false: quitter ");
        Boolean bool = scanner1.nextBoolean();
        if (bool == true) consulter_etat();
        else main_menu();
    }
    public void retourner_produit(){
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("-------------------------------");
        System.out.println("Quel produit aimerez-vous faire un retourne?");
        System.out.println("Veuillez donné le nom et la raison");
        String nom = scanner1.next();
        System.out.println("On a bien trouvé le produit");
        System.out.println("Veuillez attendre la confirmer du revendeur, on vous envoyera une message s'il accepte votre raisonnment");

        System.out.println("avez-vous d'autres choses?");
        System.out.println("entrez true: continuer \n false: quitter ");
        Boolean bool = scanner1.nextBoolean();
        if (bool == true) consulter_etat();
        else main_menu();
    }



    public static void appendRowToCSV(String filePath, String[] rowData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            StringBuilder rowString = new StringBuilder();
            for (String cell : rowData) {
                // Add each cell to the rowString, separated by commas
                rowString.append(cell).append(",");
            }
            // Remove the trailing comma and write the row to the file
            writer.write(rowString.substring(0, rowString.length() - 1));
            writer.newLine(); // Move to the next line for the next row
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean checkConsecutiveMatches(String csvFilePath, List<String> searchTerms) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // Assuming values are separated by commas

                if (values.length < searchTerms.size()) {
                    continue; // Skip lines with fewer values than the search terms
                }

                for (int i = 0; i <= values.length - searchTerms.size(); i++) {
                    boolean consecutiveMatch = true;

                    for (int j = 0; j < searchTerms.size(); j++) {
                        if (!values[i + j].equals(searchTerms.get(j))) {
                            consecutiveMatch = false;
                            break;
                        }
                    }

                    if (consecutiveMatch) {
                        return true; // Consecutive matches found
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // No consecutive matches found
        return false;
    }






public static void main(String[] args) {
        D1 person = new D1();
        person.main_menu();
    }
}
