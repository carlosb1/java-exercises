import java.util.*;

public class RoadOrganizer {
    private HashMap<String,List<TrainRoadPath>> mapStops ;
    public RoadOrganizer () {
        this.mapStops= new HashMap<>();
    }



    public int distance(String source, String target) {
        //TODO add distance
        if (!this.mapStops.containsKey(target)) {
            return -1;
        }
        if (!this.mapStops.containsKey(source)) {
            return -1;
        }


        List<TrainRoadPath> steps = this.mapStops.get(source);

        int distance = 0;
        boolean exit = false;
        while (!exit) {
            TrainRoadPath currentStop = null;
            for (TrainRoadPath stop: steps) {
                currentStop  = stop;
                distance+=stop.getWeight();
                if (stop.getTarget().equals(target)) {
                    break;
                }
            }

            if (currentStop == null) {
                if (!this.mapStops.containsKey(currentStop.getTarget())) {
                    //Not found route
                    return -1;
                }
            }
            if (target.equals(currentStop.getTarget())) {
                return distance;
            }
            steps = this.mapStops.get(currentStop.getTarget());
        }




        return distance;
    }

    public void addPath(TrainRoadPath stop) {
       this.mapStops.put(stop.getSource(), Arrays.asList(stop));this.mapStops.put(stop.getTarget(),Arrays.asList());
    }
}
