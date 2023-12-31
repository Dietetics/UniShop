import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PanierAchatTest {


//    @Test
//    void testEnregistrementAuDatabasePanier() {
//
//        String path = DatabasePath.getPathAcheteurCompte() + "test/panier.csv";
//        PanierAchat.viderPanier(path);
//
//        CSVHandler.appendCSV(path, "Cahier1");
//        CSVHandler.appendCSV(path, "Livre1");
//
//        List<String[]> lecture = CSVHandler.readCSV(path,9999);
//
//        // Définir la sortie attendue
//        List<String[]> expectedOutput = new ArrayList<>();
//        expectedOutput.add(new String[]{"Cahier1"});
//        expectedOutput.add(new String[]{"Livre1"});
//
//        List<String> recu = FormatAdjust.convertirListeTableauxEnListeChaines(lecture);
//        List<String> veut = FormatAdjust.convertirListeTableauxEnListeChaines(expectedOutput);
//
//        // Comparer la sortie attendue avec la sortie réelle
//        assertEquals(veut, recu);
//    }

    @Test
    void testCalculPtsPrix() {
        PanierAchat panier = new PanierAchat("test");
        String path = DatabasePath.getPathAcheteurCompte() + "test/panier.csv";
        panier.viderPanier(path);

        CSVHandler.appendCSV(path, "Cahier1");
        CSVHandler.appendCSV(path, "Livre1");

        panier.recap();

        assertEquals(4, panier.getPts());
    }


    @Test
    void testPasserCommande() {

        PanierAchat panier = new PanierAchat("test");
        String panierPath = DatabasePath.getPathAcheteurCompte() + "test/panier.csv";
        String histoirePath = DatabasePath.getPathAcheteurCompte() + "test/histoire.csv";
        String ptsPath = DatabasePath.getPathAcheteurCompte() + "test/points.csv";
        String notiPath = DatabasePath.getPathAcheteurCompte() + "test/notifications.csv";

        panier.viderPanier(panierPath);
        panier.viderPanier(histoirePath);
        panier.viderPanier(ptsPath);
        panier.viderPanier(notiPath);


        CSVHandler.appendCSV(panierPath, "Cahier1");
        CSVHandler.appendCSV(panierPath, "Livre1");

        PanierAchat.recap();

        panier.passerCommande();


        // verifie si panier est vide, transferer vers histoire, pts, noti

        //verifie histoire
        List<String[]> lectureHistoire = CSVHandler.readCSV(histoirePath,9999);

        List<String[]> expectedOutputHistoire = new ArrayList<>();
        expectedOutputHistoire.add(new String[]{"Cahier1,enProduction"});
        expectedOutputHistoire.add(new String[]{"Livre1,enProduction"});

        List<String> recuHistoire = FormatAdjust.convertirListeTableauxEnListeChaines(lectureHistoire);
        List<String> veutHistoire = FormatAdjust.convertirListeTableauxEnListeChaines(expectedOutputHistoire);



        List<String[]> pts = CSVHandler.readCSV(ptsPath,9999);
        List<String> ptsListe = FormatAdjust.convertirListeTableauxEnListeChaines(pts);

        List<String[]> noti = CSVHandler.readCSV(notiPath,9999);
        List<String> notiListe = FormatAdjust.convertirListeTableauxEnListeChaines(noti);

        List<String> expectPts = new ArrayList<>();
        expectPts.add("4");

        List<String> expectNotis = new ArrayList<>();
        expectNotis.add("Merci davoir magasiner chez UniShop");

        //verifie panier
        assertTrue(CSVHandler.readCSV(panierPath, 9999).isEmpty());
        assertFalse(CSVHandler.readCSV(histoirePath, 9999).isEmpty());
        assertEquals(expectPts, ptsListe);
        assertEquals(expectNotis, notiListe);
    }

    @Test
    public void testViderPanier() {
        String path = DatabasePath.getPathAcheteurCompte() + "test/panier.csv";
        CSVHandler.appendCSV(path, "Cahier1");
        CSVHandler.appendCSV(path, "Livre1");

        PanierAchat.viderPanier(path);

        // Vérifier que le panier est vide après l'appel à viderPanier
        assertTrue(CSVHandler.readCSV(path, 9999).isEmpty());
    }
}