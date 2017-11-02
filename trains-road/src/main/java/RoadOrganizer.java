import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RoadOrganizer {

    private List<TrainRoadPath> stops;
    public RoadOrganizer () {
        this.stops = new ArrayList<>();
    }



    public int distance(String source, String target) {
        return this.stops.get(0).getWeight();
    }

    public void addPath(TrainRoadPath stop) {
        this.stops.add(stop);
    }
}
