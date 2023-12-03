import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class reset {

    public static void resetDatabases(List<String> lines) {
        String baseFolderPath = "src/main/resources/data"; // Chemin vers le dossier principal
        String mainFileName = "main.csv"; // Nom du fichier principal dans chaque dossier

        for (String line : lines) {
            String[] columns = line.split(",");

            if (columns.length > 0) {
                String folderName = columns[0];
                File folder = new File(baseFolderPath, folderName);

                if (!folder.exists()) {
                    folder.mkdirs(); // Créer le dossier s'il n'existe pas
                }

                File mainFile = new File(folder, mainFileName);

                try (PrintWriter writer = new PrintWriter(new FileWriter(mainFile))) {
                    writer.println(line); // Écrire la ligne dans le fichier principal

                    // Créer d'autres fichiers CSV ici si nécessaire
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Reset complete.");
    }

    public static void main(String[] args) {
        List<String> lines = Arrays.asList(
                "auteur1,#We123123,teng.wanting@umontreal.ca,Teng,Wanting,5148888888,H3C3J7,0",
                "auteur2,#We123123,he.yongkang@gmail.com,he,yongkang,5140003333,H3C3J7,0",
                "auteur3,#We123123,lin.yuxiang@umontreal.ca,Lin,Yuxiang,5147775555,H3C3J7,0",
                "test,#We123123,test@gmail.com,testNom,testPrenom,5141231231,H5W3X3,0",
                "we,#We123123,we@gmail.com,weNom,wePrenom,4381231231,H5W3X3,0",
                "temp,#We123123,temp@gmail.com,tempNom,tempPrenom,1281231231,H5W3X3,0"
        );

        resetDatabases(lines);
    }
}
