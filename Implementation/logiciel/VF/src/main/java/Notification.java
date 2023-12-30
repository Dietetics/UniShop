import java.util.List;

public class Notification {


    public Notification() {
    }

    public static void voirNotifications(String path, String auteur){
        System.out.println("Voici vos notifications");
        System.out.println("-----------------------");
        CSVHandler.printCSV(CSVHandler.readCSV(path,999));
        System.out.println("-----------------------\n");
    }

}
