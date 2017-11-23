

import models.TrainPath;

import java.util.List;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;


public class Utils {

    public static List<TrainPath> Parse(InputStream input) {
        Scanner scanner = new Scanner(input);
        scanner.useDelimiter("\\s");

        List<TrainPath> result = new ArrayList<TrainPath>();

        while (scanner.hasNext()) {
            String possibleStop = scanner.next();
            if (possibleStop.length()!=3) {
                continue;
            }
            String source = possibleStop.substring(0,1);
            String target = possibleStop.substring(1,2);
            String weight = possibleStop.substring(2,3);

            try {
                Integer formattedWeight = Integer.parseInt(weight);
            } catch(Exception excp) {
                continue;
            }
            TrainPath trainRoadPath = new TrainPath(source,target,Integer.parseInt(weight));
            result.add(trainRoadPath);
        }
        return result;

    }
}