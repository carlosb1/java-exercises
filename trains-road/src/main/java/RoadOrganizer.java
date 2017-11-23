import models.TrainPath;

import java.util.*;

public class RoadOrganizer {
    private HashMap<String,ArrayList<TrainPath>> mapStops ;
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


        List<TrainPath> steps = this.mapStops.get(source);
        boolean exit = false;

        Stack<TrainPath> pendingToVisit = new Stack();
        Stack<TrainPath> visited = new Stack();

        while (!exit) {
            TrainPath currentStop = null;
            for (TrainPath stop: steps) {
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

    private boolean existAvailablePaths(Stack<TrainPath> pendingToVisit, TrainPath currentStop) {
        return currentStop != null || !pendingToVisit.empty();
    }

    private boolean isAvailable(String target) {
        return this.mapStops.containsKey(target);
    }

    private boolean isTarget(String target, TrainPath currentStop) {
        return target.equals(currentStop.getTarget());
    }

    public void addPath(TrainPath stop) {
        updateSourcePath(stop);
        updateTargetPath(stop);
    }

    private void updateTargetPath(TrainPath stop) {
        if (!this.mapStops.containsKey(stop.getTarget())) {
            this.mapStops.put(stop.getTarget(), new ArrayList<>());
        }
    }

    private void updateSourcePath(TrainPath stop) {
        ArrayList<TrainPath> pathsFromSources;
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
