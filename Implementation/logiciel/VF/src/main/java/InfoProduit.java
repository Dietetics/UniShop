public class InfoProduit {




    public static void voirProduit(String path, String motCle, int colonne){
        String data = CSVHandler.readLineByIndex(path,CSVHandler.findOccurrenceIndex(path, motCle, colonne));

        String titre = CSVHandler.getColumnValue(data, 0);
        String categorie = CSVHandler.getColumnValue(data, 1);
        String description = CSVHandler.getColumnValue(data, 2);
        String quantite = CSVHandler.getColumnValue(data, 3);
        String prix = CSVHandler.getColumnValue(data, 4);
        String pointsBoni = CSVHandler.getColumnValue(data, 5);
        String images = CSVHandler.getColumnValue(data, 6);
        String videos = CSVHandler.getColumnValue(data, 7);
        String identifiant = CSVHandler.getColumnValue(data, 8);
        String nbLikes = CSVHandler.getColumnValue(data, 9);
        String note_moyen = CSVHandler.getColumnValue(data, 10);
        String promo = CSVHandler.getColumnValue(data, 11);


        String note = DatabasePath.getProduitInfoPath() + titre + "/notes.csv" ;
        String eval = DatabasePath.getProduitInfoPath() + titre + "/evaluations.csv" ;


        System.out.println("\n\n\n\n\n\n\n\n\n\n");
        System.out.println("voici les informations du produit: " + titre);
        System.out.println("---------------------------------------------------");
        System.out.println("Categorie: " + categorie);
        System.out.println("Description: " + description);
        System.out.println("Quantite: " + quantite);
        System.out.println("Prix: " + prix);
        System.out.println("PointsBoni: " + pointsBoni);
        System.out.println("Images: " + images);
        System.out.println("Videos: " + videos);
        System.out.println("Identifiant: " + identifiant);
        System.out.println("nbLikes: " + nbLikes);
        System.out.println("Note moyen: " + note_moyen);
        System.out.println("Promo: " + promo);
        System.out.println(" ");

        System.out.println("Note: ");
        System.out.println("------------");
        CSVHandler.printCSV(CSVHandler.readCSV(note,99999));
        System.out.println("------------");
        System.out.println(" ");

        System.out.println("Evaluation: ");
        System.out.println("------------");
        CSVHandler.printCSV(CSVHandler.readCSV(eval,99999));
        System.out.println("------------");
        System.out.println("\n\n\n");

        System.out.print("Entrez quelque chose pour retourner a la recherche");
        String decision = myScanner.getStringInput();

        if (decision != null) return;

    }

}
