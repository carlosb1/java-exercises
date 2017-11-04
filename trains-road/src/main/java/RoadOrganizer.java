import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RoadOrganizer {

    private List<TrainRoadPath> stops;
    private HashMap<String,List<String>> mapStops ;
    public RoadOrganizer () {
        this.mapStops= new HashMap<>();
        this.stops = new ArrayList<>();
    }



    public int distance(String source, String target) {
        if (!this.mapStops.containsKey(target)) {
            return -1;
        }
        if (!this.mapStops.containsKey(source)) {
            return -1;
        }
        int distance = 0;

        /*
        String currentPoint = source
        boolean exit = false;
        while (!exit) {
            if (!this.mapStops.containsKey())

        }
        */

        for (TrainRoadPath stop: stops) {
            distance+=stop.getWeight();
        }
        return distance;
    }

    public void addPath(TrainRoadPath stop) {
       this.mapStops.put(stop.getSource(), Arrays.asList(stop.getTarget()));
        this.mapStops.put(stop.getTarget(),Arrays.asList());
        this.stops.add(stop);
    }
}
