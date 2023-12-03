public class InfoAcheteur {



    public static void voirProfil(String path, String motCle, int colonne){
        String data = CSVHandler.readLineByIndex(path,CSVHandler.findOccurrenceIndex(path, motCle, colonne));

        String nom = CSVHandler.getColumnValue(data, 0);
        String prenom = CSVHandler.getColumnValue(data, 1);
        String pseudo = CSVHandler.getColumnValue(data, 2);
        String courriel = CSVHandler.getColumnValue(data, 3);
        String telephone = CSVHandler.getColumnValue(data, 4);
        String adresse = CSVHandler.getColumnValue(data, 5);
        String likes = CSVHandler.getColumnValue(data, 6);



        System.out.println("\n\n\n\n\n\n\n\n\n\n");
        System.out.println("voici les informations du produit: " + pseudo);
        System.out.println("---------------------------------------------------");
        System.out.println("nom: " + nom);
        System.out.println("prenom: " + prenom);
        System.out.println("likes: " + likes);
        System.out.println(" ");

        System.out.println("\n\n\n");

        System.out.println("Entrez quelque chose pour retourner a la recherche");
        String decision = myScanner.getStringInput();
        if (decision != null) Information.displayInformation();

    }

}
