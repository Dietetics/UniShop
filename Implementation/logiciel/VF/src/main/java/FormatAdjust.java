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




}
