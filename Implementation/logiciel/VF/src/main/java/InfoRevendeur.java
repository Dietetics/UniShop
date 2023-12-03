public class InfoRevendeur {


    public static void voirProfil(String path, String motCle, int colonne){
        String data = CSVHandler.readLineByIndex(path,CSVHandler.findOccurrenceIndex(path, motCle, colonne));

        String nom = CSVHandler.getColumnValue(data, 0);
        String adresse = CSVHandler.getColumnValue(data, 1);
        String courriel = CSVHandler.getColumnValue(data, 2);
        String telephone = CSVHandler.getColumnValue(data, 3);
        String likes = CSVHandler.getColumnValue(data, 4);


        System.out.println("\n\n\n\n\n\n\n\n\n\n");
        System.out.println("voici les informations du produit: " + nom);
        System.out.println("---------------------------------------------------");
        System.out.println("likes: " + likes);
        System.out.println(" ");

        System.out.println("\n\n\n");

        System.out.println("Entrez quelque chose pour retourner a la recherche");
        String decision = myScanner.getStringInput();

        if (decision != null) Information.displayInformation();

    }

}
