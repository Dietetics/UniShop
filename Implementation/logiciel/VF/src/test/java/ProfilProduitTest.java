import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProfilProduitTest {

    @Test
    public void testCalculMoyen() {
        List<String> liste = List.of("10", "2", "33", "42", "5");
        double resultat = ProfilProduit.calculMoyen(liste);

        // Précision de la comparaison des nombres à virgule flottante
        double precision = 0.0001;

        // Vérifier que la moyenne est correcte
        assertEquals(18.4, resultat, precision);
    }


    @Test
    public void testCalculMoyenVide() {
        List<String> liste = List.of();
        double resultat = ProfilProduit.calculMoyen(liste);

        // Précision de la comparaison des nombres à virgule flottante
        double precision = 0.0001;

        // Vérifier que la moyenne est correcte
        assertEquals(0, resultat, precision);
    }

    @Test
    public void testCalculMoyenAvecChaineNonNombre() {
        List<String> liste = List.of("2", "deux", "2", "2", "2");
        double resultat = ProfilProduit.calculMoyen(liste);

        // Précision de la comparaison des nombres à virgule flottante
        double precision = 0.0001;

        // Vérifier que la moyenne est correcte malgré la chaîne non nombre
        assertEquals(1.6, resultat, precision);
    }
}