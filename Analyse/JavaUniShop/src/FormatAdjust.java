import java.util.ArrayList;
import java.util.List;

public class FormatAdjust {


    public static List<String[]> transformList(List<String> stringList) {
        List<String[]> resultList = new ArrayList<>();

        for (String line : stringList) {
            // Split each line into an array based on commas
            String[] values = line.split(",");
            resultList.add(values);
        }

        return resultList;
    }


}
