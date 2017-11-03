import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RoadOrganizer {

    private List<TrainRoadPath> stops;
    public RoadOrganizer () {
        this.stops = new ArrayList<>();
    }



    public int distance(String source, String target) {
        int distance = 0;
        for (TrainRoadPath stop: stops) {
            distance+=stop.getWeight();
        }
        return distance;
    }

    public void addPath(TrainRoadPath stop) {
        this.stops.add(stop);
    }
}
