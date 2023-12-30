import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class InscriptionTest {


    @Test
    public void testSaveProduitData() {

        Path mainFilePath = Paths.get(DatabasePath.getBaseProduitFolderPath(), "TitreTest", "main.csv");

        String pathProduit = DatabasePath.getPathProduitCompte() + "TitreTest";
        File dossierASupprimerApresTest = new File(pathProduit);

        try {
            // Appeler la méthode saveProduitData avec des données de test
            String titre = "TitreTest";
            String categorie = "CategorieTest";
            String description = "DescriptionTest";
            String quantite0 = "10";
            String prix = "50.0";
            String pointsBoni = "5";
            String uuid = "uuidTest";

            Inscription.saveProduitData(titre, categorie, description, quantite0, prix, pointsBoni, uuid);

            assertTrue(Files.exists(mainFilePath));
            List<String> lines = Files.readAllLines(mainFilePath);
            assertEquals(1, lines.size());
            assertEquals("TitreTest,CategorieTest,DescriptionTest,10,50.0,5,aucun,aucun,uuidTest,0,0,non", lines.get(0));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Nettoyer : supprimer le répertoire temporaire
            supprimerDossierTemp(dossierASupprimerApresTest);
            Database.refreshProduits();
        }
    }

    @Test
    public void testOffer() {

        // Appeler la méthode offer avec des données de test
        String revendeur = "RevendeurTest";
        String titre = "TitreTest";

        // creation temp des fichiers
        CreateFileInDirectory.newFile(DatabasePath.getPathRevendeurCompte() + revendeur,"offrir.csv");
        CreateFileInDirectory.newFile(DatabasePath.getPathProduitCompte() + titre,"offerPar.csv");

        // a soupprimer par la suite
        String pathProduit1 = DatabasePath.getPathRevendeurCompte() + revendeur;
        File dossierASupprimerApresTest1 = new File(pathProduit1);
        String pathProduit2 = DatabasePath.getPathProduitCompte() + titre;
        File dossierASupprimerApresTest2 = new File(pathProduit2);

        try {

            String path_offrir = DatabasePath.getPathRevendeurCompte() + revendeur + "/offrir.csv";
            String path_offerPar = DatabasePath.getPathProduitCompte() + titre + "/offerPar.csv";

            // execute
            Inscription.offer(revendeur, titre);

            // verification
            Path offerParPath = Paths.get(path_offerPar);
            Path offrirPath = Paths.get(path_offrir);

            assertTrue(Files.exists(offerParPath));
            List<String> linesOfferPar = Files.readAllLines(offerParPath);
            assertEquals(1, linesOfferPar.size());
            assertEquals("TitreTest", linesOfferPar.get(0));

            assertTrue(Files.exists(offrirPath));
            List<String> linesOffrir = Files.readAllLines(offrirPath);
            assertEquals(1, linesOffrir.size());
            assertEquals("TitreTest", linesOffrir.get(0));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Nettoyer : supprimer le répertoire temporaire
            supprimerDossierTemp(dossierASupprimerApresTest1);
            supprimerDossierTemp(dossierASupprimerApresTest2);
        }
    }



    @Test
    public void testAjoutProduits() {

        // Appeler la méthode ajoutProduits avec des données de test
        String titre = "TitreTest";
        String csvLine = "TitreTest,CategorieTest,DescriptionTest,10,50.0,5,aucun,aucun,uuidTest,0,0,non";
        List<String> additionalFiles = List.of("file1.csv", "file2.csv");

        // a supprimer apres
        String pathProduit2 = DatabasePath.getPathProduitCompte() + titre;
        File dossierASupprimerApresTest = new File(pathProduit2);
        try {


            Inscription.ajoutProduits(titre, csvLine, additionalFiles);

            // Vérifier que le fichier principal a été créé avec la ligne correcte
            Path mainFilePath = Paths.get(DatabasePath.getPathProduitCompte() + titre + "/main.csv");
            assertTrue(Files.exists(mainFilePath));
            List<String> lines = Files.readAllLines(mainFilePath);
            assertEquals(1, lines.size());
            assertEquals("TitreTest,CategorieTest,DescriptionTest,10,50.0,5,aucun,aucun,uuidTest,0,0,non", lines.get(0));

            // Vérifier que les fichiers supplémentaires ont été créés
            Path file1Path = Paths.get(DatabasePath.getPathProduitCompte() + titre + "/file1.csv");
            assertTrue(Files.exists(file1Path));
            Path file2Path = Paths.get(DatabasePath.getPathProduitCompte() + titre + "/file2.csv");
            assertTrue(Files.exists(file2Path));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Nettoyer : supprimer le répertoire temporaire
            supprimerDossierTemp(dossierASupprimerApresTest);
        }
    }


    public void supprimerDossierTemp(File dossier) {
        // Vérifier si le dossier existe
        if (dossier.exists()) {
            // Vérifier si c'est un dossier
            if (dossier.isDirectory()) {
                // Liste des fichiers dans le dossier
                File[] fichiers = dossier.listFiles();

                // Supprimer tous les fichiers et sous-dossiers dans le dossier
                if (fichiers != null) {
                    for (File fichier : fichiers) {
                        if (fichier.isDirectory()) {
                            // Appeler récursivement la méthode pour supprimer les sous-dossiers
                            supprimerDossierTemp(fichier);
                        } else {
                            // Supprimer le fichier
                            fichier.delete();
                        }
                    }
                }
            }
            // Supprimer le dossier lui-même
            dossier.delete();
        }
    }
}
