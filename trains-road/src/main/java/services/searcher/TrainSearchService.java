package services.searcher;



import java.util.*;
import java.util.stream.Collectors;


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

    public List<List<Stop>> availableTripsExactStops(String source, String target, int exactStops) {
        exactStops = exactStops +1; //it is included source and target
        List<List<Stop>> possibleTrips = new ArrayList<>();
        Stack<List<Stop>>  candidates = new Stack<>();
        candidates.push(Arrays.asList(new Stop(source,0)));

        while (!candidates.isEmpty()) {
            List<Stop> path = candidates.pop();
            /*  condition to leave*/
            if (path.size() == exactStops
                    && path.size() >=2
                    && path.get(0).name.equals(source)
                    && path.get(path.size()-1).name.equals(target)
                    ){
                possibleTrips.add(new ArrayList<>(path));

            }
            String nameStop = path.get(path.size()-1).getName();

            if (this.paths.containsKey(nameStop) && path.size() < exactStops) {
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
    public List<Stop> shortestPath(String source, String target) {
        List<List<Stop>> possibleTrips = new ArrayList<>();
        Stack<List<Stop>>  candidates = new Stack<>();
        candidates.push(Arrays.asList(new Stop(source,0)));

        List<Stop> shortest = new ArrayList<>();
        while (!candidates.isEmpty()) {
            List<Stop> path = candidates.pop();
            /*  condition to leave*/
            if ((getDistance(path) <= getDistance(shortest) || shortest.isEmpty())
                    && path.size() >=2
                    && path.get(0).name.equals(source)
                    && path.get(path.size()-1).name.equals(target)
                    ){
                shortest =  path;
            }
            String nameStop = path.get(path.size()-1).getName();

            //checkear distancia!
            if (this.paths.containsKey(nameStop)) {
                Map<String, Double> adjacents  = this.paths.get(nameStop);
                for (String adjacent : adjacents.keySet()) {
                    List<Stop> newPath = new ArrayList<>(path);
                    newPath.add(new Stop(adjacent,adjacents.get(adjacent)));
                    if (!shortest.isEmpty() && getDistance(newPath) >= getDistance(shortest)) {
                        continue;
                    }
                    candidates.push(newPath);
                }
            }
        }
        return shortest;
    }

    public List<List<Stop>> availableTripsMoreStops(String source, String target, double moreStops) {
            moreStops = moreStops +1; //it is included source and target
            List<List<Stop>> possibleTrips = new ArrayList<>();
            Stack<List<Stop>>  candidates = new Stack<>();
            candidates.push(Arrays.asList(new Stop(source,0)));

            while (!candidates.isEmpty()) {
                List<Stop> path = candidates.pop();
            /*  condition to leave*/
                if (getDistance(path) <= moreStops
                        && path.size() >=2
                        && path.get(0).name.equals(source)
                        && path.get(path.size()-1).name.equals(target)
                        ){
                    possibleTrips.add(new ArrayList<>(path));
                }
                String nameStop = path.get(path.size()-1).getName();

                //checkear distancia!
                if (this.paths.containsKey(nameStop)) {
                    Map<String, Double> adjacents  = this.paths.get(nameStop);
                    for (String adjacent : adjacents.keySet()) {
                        List<Stop> newPath = new ArrayList<>(path);
                        newPath.add(new Stop(adjacent,adjacents.get(adjacent)));
                        if (getDistance(newPath) > moreStops) {
                            continue;
                        }
                        candidates.push(newPath);
                    }
                }
            }
            return possibleTrips;
    }

    private double getDistance(List<Stop> path) {
        return path.stream().mapToDouble(stop -> stop.getCost()).sum();
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Stop stop = (Stop) o;

            if (Double.compare(stop.cost, cost) != 0) return false;
            return name != null ? name.equals(stop.name) : stop.name == null;
        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            result = name != null ? name.hashCode() : 0;
            temp = Double.doubleToLongBits(cost);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            return result;
        }
    }

}
