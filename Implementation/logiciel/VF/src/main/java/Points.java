import java.util.InputMismatchException;

public class Points {





    public static void voirPts(String path, String auteur){

        int somme = CSVHandler.sumIntegersFromCSV(path);


        System.out.println("Voici vos points");
        System.out.println("-----------------------");
        System.out.println(somme);
        System.out.println("-----------------------\n");
    }




}
