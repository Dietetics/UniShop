import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FormatAdjust {

    /**
     * Convertit une liste de chaines de caracteres en une liste de tableaux de chaines de caracteres.
     *
     * @param stringList La liste de chaines de caracteres a convertir.
     * @return La liste de tableaux de chaines de caracteres resultant de la conversion.
     */
    public static List<String[]> transformList(List<String> stringList) {

        List<String[]> resultList = new ArrayList<>();

        for (String line : stringList) {
            // Divise chaque ligne en un tableau en fonction des virgules
            String[] values = line.split(",");
            resultList.add(values);
        }
        return resultList;
    }


    public static String convertToCSV(String... values) {
        StringBuilder csvLine = new StringBuilder();
        for (String value : values) {
            // Si la valeur contient une virgule, entoure-la de guillemets
            if (value.contains(",")) {
                csvLine.append("\"").append(value).append("\",");
            } else {
                csvLine.append(value).append(",");
            }
        }
        // Supprime la virgule finale et retourne la ligne CSV complète
        return csvLine.substring(0, csvLine.length() - 1);
    }


    public static List<String> convertStringToList(String input) {
        // Diviser la chaîne en une liste de chaînes en utilisant le point-virgule comme séparateur
        String[] elements = input.split(";");

        // Convertir le tableau en une liste
        List<String> resultList = Arrays.asList(elements);

        return resultList;
    }

    public static String transformVersString(List<String> stringList){
        String resultat = String.join(", ", stringList);
        return resultat;
    }


    public static List<String> convertirListeTableauxEnListeChaines(List<String[]> listeTableaux) {
        List<String> listeChaines = new ArrayList<>();

        // Itérer sur chaque tableau de la liste
        for (String[] tableau : listeTableaux) {
            // Ajouter les éléments du tableau à la liste de chaînes
            listeChaines.addAll(Arrays.asList(tableau));
        }

        return listeChaines;
    }

    public static String listToString(List<String[]> list) {
        StringBuilder sb = new StringBuilder();
        for (String[] array : list) {
            sb.append(String.join(", ", array)).append(System.lineSeparator());
        }
        return sb.toString();
    }




}
