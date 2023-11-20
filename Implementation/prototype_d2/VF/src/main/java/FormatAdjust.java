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





}
