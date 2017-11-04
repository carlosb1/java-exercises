import java.util.*;

public class RoadOrganizer {
    private HashMap<String,ArrayList<TrainRoadPath>> mapStops ;
    public RoadOrganizer () {
        this.mapStops= new HashMap<>();
    }



    public int distance(String source, String target) {
        if (!isAvailable(target)) {
            return -1;
        }
        if (!isAvailable(source)) {
            return -1;
        }


        List<TrainRoadPath> steps = this.mapStops.get(source);
        boolean exit = false;

        Stack<TrainRoadPath> pendingToVisit = new Stack();
        Stack<TrainRoadPath> visited = new Stack();

        while (!exit) {
            TrainRoadPath currentStop = null;
            for (TrainRoadPath stop: steps) {
                if (currentStop == null) {
                    currentStop = stop;
                    visited.push(stop);
                } else {
                    pendingToVisit.push(stop);
                }
            }

            if (!existAvailablePaths(pendingToVisit, currentStop)) {
                return -1;
            }

            if (currentStop == null && !pendingToVisit.empty()) {
                    currentStop = pendingToVisit.pop();
                    visited.push(currentStop);
            }
            /* is found or next searching */
            if (isTarget(target, currentStop)) {
                exit = true;
                continue;
            }
            /* get next stops to visit */
            steps = this.mapStops.get(currentStop.getTarget());
        }

        int distance = visited.stream().mapToInt( newStop -> newStop.getWeight()).sum();
        return distance;
    }

    private boolean existAvailablePaths(Stack<TrainRoadPath> pendingToVisit, TrainRoadPath currentStop) {
        return currentStop != null || !pendingToVisit.empty();
    }

    private boolean isAvailable(String target) {
        return this.mapStops.containsKey(target);
    }

    private boolean isTarget(String target, TrainRoadPath currentStop) {
        return target.equals(currentStop.getTarget());
    }

    public void addPath(TrainRoadPath stop) {
        updateSourcePath(stop);
        updateTargetPath(stop);
    }

    private void updateTargetPath(TrainRoadPath stop) {
        if (!this.mapStops.containsKey(stop.getTarget())) {
            this.mapStops.put(stop.getTarget(), new ArrayList<>());
        }
    }

    private void updateSourcePath(TrainRoadPath stop) {
        ArrayList<TrainRoadPath> pathsFromSources;
        if (this.mapStops.containsKey(stop.getSource())) {
           pathsFromSources = this.mapStops.get(stop.getSource());
           pathsFromSources.add(stop);
       } else {
            pathsFromSources = new ArrayList();
            pathsFromSources.add(stop);
        }
        this.mapStops.put(stop.getSource(), pathsFromSources);
    }
}
