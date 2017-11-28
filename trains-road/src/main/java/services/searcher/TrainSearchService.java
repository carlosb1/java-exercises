package services.searcher;



import models.Stop;

import java.util.*;


public class TrainSearchService {
    private final Map<String,List<Stop>> paths;
    public TrainSearchService () {
        this.paths = new HashMap<> ();
    }


    public void addPath(String source, String target, Double cost) {
        if (!this.paths.containsKey(source)) {
            this.paths.put(source,new ArrayList<Stop>());
        }
        this.paths.get(source).add(new Stop(target, cost));


        if (!this.paths.containsKey(target)) {
            this.paths.put(target,new ArrayList<>());
        }
    }

    public List<Stop> findRoute(String source, String target) {
        Stack<List<Stop>>  candidates = new Stack<>();
        candidates.push(Arrays.asList(new Stop(source,0)));
        while (!candidates.isEmpty()) {
            List<Stop> path = candidates.pop();
            /*  condition to leave*/
            String nameStop = path.get(path.size()-1).getName();
            if (nameStop.equals(target)) {
                return path;
            }

            if (this.paths.containsKey(nameStop)) {
                for (Stop adjacent : this.paths.get(nameStop)) {
                    List<Stop> newPath = new ArrayList<>(path);
                    newPath.add(adjacent);
                    candidates.push(newPath);
                }
            }
        }
        return Arrays.asList();
    }

    public double findDistance(String source, String target) {

        List<Stop> stops = this.findRoute(source,target);
        Double distance = stops.stream().mapToDouble(stop -> stop.getCost()).sum();
        return distance;
    }



}
