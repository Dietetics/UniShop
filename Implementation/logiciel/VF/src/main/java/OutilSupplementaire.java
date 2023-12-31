import java.util.ArrayList;
import java.util.List;

public class OutilSupplementaire {

    public static String removeSpacesBetweenCommas(String input) {
        // Utiliser une expression régulière pour supprimer les espaces entre deux virgules
        return input.replaceAll(",\\s+", ",");
    }

}
