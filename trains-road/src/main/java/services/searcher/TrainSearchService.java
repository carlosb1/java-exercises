package services.searcher;



import java.util.*;


public class TrainSearchService {
    private final Map<String,Map<String,Double>> paths;
    public TrainSearchService () {
        this.paths = new HashMap<> ();
    }


    public void addPath(String source, String target, Double cost) {
        if (!this.paths.containsKey(source)) {
            this.paths.put(source,new HashMap<String, Double>());
        }
        this.paths.get(source).put(target, cost);


        if (!this.paths.containsKey(target)) {
            this.paths.put(target,new HashMap<>());
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
                Map<String, Double> adjacents  = this.paths.get(nameStop);
                for (String adjacent : adjacents.keySet()) {
                    List<Stop> newPath = new ArrayList<>(path);
                    newPath.add(new Stop(adjacent,adjacents.get(adjacent)));
                    candidates.push(newPath);
                }
            }
        }
        return Arrays.asList();
    }

    public double findDistance(String ... stops) {
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

    public List<List<Stop>> availableTrips(String source, String target, int maxStops) {
        maxStops = maxStops +1; //it is included source and target
        List<List<Stop>> possibleTrips = new ArrayList<>();
        Stack<List<Stop>>  candidates = new Stack<>();
        candidates.push(Arrays.asList(new Stop(source,0)));

        while (!candidates.isEmpty()) {
            List<Stop> path = candidates.pop();
            /*  condition to leave*/
            if (path.size() <= maxStops
                    && path.size() >=2
                    && path.get(0).name.equals(source)
                    && path.get(path.size()-1).name.equals(target)
                    ){
                    possibleTrips.add(new ArrayList<>(path));

            }
            String nameStop = path.get(path.size()-1).getName();

            if (this.paths.containsKey(nameStop) && path.size() < maxStops) {
                Map<String, Double> adjacents  = this.paths.get(nameStop);
                for (String adjacent : adjacents.keySet()) {
                    List<Stop> newPath = new ArrayList<>(path);
                    newPath.add(new Stop(adjacent,adjacents.get(adjacent)));
                    candidates.push(newPath);
                }
            }
        }
        return possibleTrips;


    }



    public static class Stop {
        private final String name;
        private final double cost;
        public Stop (String name, double cost) {
            this.name = name;
            this.cost  = cost;
        }
        public String getName() {
            return name;
        }
        public double getCost() {
            return cost;
        }
    }
}
