import java.util.*;

public class RoadOrganizer {
    private HashMap<String,List<TrainRoadPath>> mapStops ;
    public RoadOrganizer () {
        this.mapStops= new HashMap<>();
    }



    public int distance(String source, String target) {
        //TODO add distance
        if (!isAvailable(target)) {
            return -1;
        }
        if (!isAvailable(source)) {
            return -1;
        }


        List<TrainRoadPath> steps = this.mapStops.get(source);

        int distance = 0;
        boolean exit = false;

        Stack<TrainRoadPath> pendingToVisit = new Stack();
        Stack<TrainRoadPath> visited = new Stack();

        while (!exit) {
            //TODO check if currentstop is null
            TrainRoadPath currentStop = null;
            for (TrainRoadPath stop: steps) {
                currentStop  = stop;
                visited.push(stop);
                if (isTarget(target,currentStop)) {
                    break;
                }
            }
            if (isTarget(target, currentStop)) {
                exit = true;
            } else {
                steps = this.mapStops.get(currentStop.getTarget());
            }
        }

        distance = visited.stream().mapToInt( newStop -> newStop.getWeight()).sum();
        return distance;
    }

    private boolean isAvailable(String target) {
        return this.mapStops.containsKey(target);
    }

    private boolean isTarget(String target, TrainRoadPath currentStop) {
        return target.equals(currentStop.getTarget());
    }

    public void addPath(TrainRoadPath stop) {
       this.mapStops.put(stop.getSource(), Arrays.asList(stop));this.mapStops.put(stop.getTarget(),Arrays.asList());
    }
}
