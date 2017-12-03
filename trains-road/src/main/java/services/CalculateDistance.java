package services;

import java.util.HashMap;

public class CalculateDistance {
    private final HashMap<String,HashMap<String,Double>> paths;
    public CalculateDistance(final HashMap<String,HashMap<String,Double>> paths) {
        this.paths = paths;
    }

    public double findDistances (String ... stops ) {
        double costDistance = 0;
        for (int indexStop = 0; indexStop < stops.length -1 ; indexStop++) {
            String source = stops[indexStop];
            String target = stops[indexStop+1];
            /* not contains source*/
            if (!this.paths.containsKey(source)) {
                return -1;
            }
            if (!this.paths.get(source).containsKey(target)) {
                return -1;
            }
            costDistance+=this.paths.get(source).get(target);

        }
        return costDistance;

    }

}