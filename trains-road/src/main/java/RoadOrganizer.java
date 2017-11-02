import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RoadOrganizer {

    private List<String> stops;
    public RoadOrganizer () {
        this.stops = new ArrayList<>();
    }


    public void addPath(String stop) {
        this.stops.add(stop);
    }

    public int distance(String source, String target) {
        if (this.stops.size()<=1) {
            return 0 ;
        }
        return 1;
    }

    public void addPath(List<String> stops) {
        this.stops.addAll(stops);
    }
}
