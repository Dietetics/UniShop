import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class HistoireTest {

    @Test
    void testGetValidatedNote() {
        String simulatedInput = "50\n";
        InputStream savedSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        try {
            int note = Histoire.getValidatedNote();
            assertEquals(50, note);
        } finally {
            System.setIn(savedSystemIn);
        }
    }


    @Test
    void testGetEvaluation() {
        String simulatedInput = "just un test\n";
        InputStream savedSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        try {
            String eval = Histoire.getEvaluation();
            assertEquals("just un test", eval);
        } finally {
            System.setIn(savedSystemIn);
        }
    }



    @Test
    void testEvaluateProduct() {

        String produit = "test";
        String acheteur = "test";
        int note = 5;
        String eval = "just un test";

        PanierAchat panier = new PanierAchat(acheteur);
        String produitNotesPath = DatabasePath.getPathProduitCompte() + "test/notes.csv";
        String produitEvalPath = DatabasePath.getPathProduitCompte() + "test/evaluations.csv";
        String acheteurEvalPath = DatabasePath.getPathAcheteurCompte() + "test/evaluations.csv";
        String acheteurNotifPath = DatabasePath.getPathAcheteurCompte() + "test/notifications.csv";

        panier.viderPanier(produitNotesPath);
        panier.viderPanier(produitEvalPath);
        panier.viderPanier(acheteurEvalPath);
        panier.viderPanier(acheteurNotifPath);

        Histoire.evaluateProduct(produit,note,eval,acheteur);

        List<String[]> produitNotes = CSVHandler.readCSV(produitNotesPath,9999);
        List<String> produitNotesListe = FormatAdjust.convertirListeTableauxEnListeChaines(produitNotes);

        List<String[]> produitEval = CSVHandler.readCSV(produitEvalPath,9999);
        List<String> produitEvalListe = FormatAdjust.convertirListeTableauxEnListeChaines(produitEval);

        List<String[]> acheteurEval = CSVHandler.readCSV(acheteurEvalPath,9999);
        List<String> acheteurEvalListe = FormatAdjust.convertirListeTableauxEnListeChaines(acheteurEval);

        List<String[]> acheteurNotif = CSVHandler.readCSV(acheteurNotifPath,9999);
        List<String> acheteurNotifListe = FormatAdjust.convertirListeTableauxEnListeChaines(acheteurNotif);


        String produitNotesExpec = "Par acheteur: test. Note: 5";
        String produitEvalExpec = "Par acheteur: test. Eval: just un test";
        String acheteurEvalExpec = "Pour le produit: test. Vous avez donne note: 5; evaluation: just un test";
        String acheteurNotiExpec = "Votre note et evaluation sont bien enregistrer par le produit: test";

        List<String> produitNotesExpected = new ArrayList<>();
        produitNotesExpected.add(produitNotesExpec);

        List<String> produitEvalExpected = new ArrayList<>();
        produitEvalExpected.add(produitEvalExpec);

        List<String> acheteurEvalExpected = new ArrayList<>();
        acheteurEvalExpected.add(acheteurEvalExpec);

        List<String> acheteurNotiExpected = new ArrayList<>();
        acheteurNotiExpected.add(acheteurNotiExpec);

        assertFalse(CSVHandler.readCSV(produitNotesPath, 9999).isEmpty());
        assertFalse(CSVHandler.readCSV(produitNotesPath, 9999).isEmpty());
        assertFalse(CSVHandler.readCSV(acheteurEvalPath, 9999).isEmpty());
        assertFalse(CSVHandler.readCSV(acheteurNotifPath, 9999).isEmpty());

        assertEquals(produitNotesExpected, produitNotesListe);
        assertEquals(produitEvalExpected, produitEvalListe);
        assertEquals(acheteurEvalExpected, acheteurEvalListe);
        assertEquals(acheteurNotiExpected, acheteurNotifListe);
    }





}