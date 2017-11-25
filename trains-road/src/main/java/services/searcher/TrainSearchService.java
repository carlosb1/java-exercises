package services.searcher;



import models.TrainPath;
import java.util.*;
import java.util.stream.Collectors;


public class TrainSearchService implements SearchService {
    private final Map<String,List<TrainPath>> paths;
    public TrainSearchService () {
        this.paths = new HashMap<String,List<TrainPath>> ();
    }


    @Override
    public void addPath(TrainPath newPath) {
        if (!this.paths.containsKey(newPath.getSource())) {
            this.paths.put(newPath.getSource(),new ArrayList<>());
        }
        this.paths.get(newPath.getSource()).add(newPath);

        if (!this.paths.containsKey(newPath.getTarget())) {
            this.paths.put(newPath.getTarget(),new ArrayList<>());
        }
    }

    private final class InfoVertice {
        private final List<TrainPath> paths;
        private  double cost;

        InfoVertice (List<TrainPath> paths) {
            this.paths = paths;
            this.cost = Double.POSITIVE_INFINITY;
        }

        public double getCost() {
            return cost;
        }

        public List<TrainPath> getPaths() {
            return paths;
        }
    }
    @Override
    public List<String> findRoute(String source, String target) {
        //TODO add tests to check that it doesn't work with no source and target
/*
        if (!paths.containsKey(source)) {
            return Arrays.asList();
        }
        if (!paths.containsKey(target)) {
            return Arrays.asList();
        }
*/

        Map<String, InfoVertice>  statusVertices = this.paths.entrySet().stream().collect(
                Collectors.toMap(
                        e->e.getKey(),
                        e->new InfoVertice(e.getValue())
                        ));

        List<String> shortestPath = new ArrayList<String>();
        InfoVertice currentStop = statusVertices.get(source);
        currentStop.cost = 0;
        shortestPath.add(source);
        currentStop.paths.stream().forEach(path -> statusVertices.get(path.getTarget()).cost=path.getWeight());

        boolean exit = false;
        while (!exit) {
            Optional<TrainPath> foundPath = currentStop.paths.stream().filter(path -> !shortestPath.contains(path.getTarget())).min(Comparator.comparing(path -> statusVertices.get(path.getTarget()).cost));
            if (foundPath.isPresent()) {
                String nameStop = foundPath.get().getTarget();
                shortestPath.add(nameStop);

                if (nameStop.equals(target)) {
                    exit = true;
                    continue;
                }

                currentStop = statusVertices.get(nameStop);
                final double offsetCost = currentStop.cost;
                currentStop.paths.stream().forEach(path -> statusVertices.get(path.getTarget()).cost=(path.getWeight()+offsetCost));
            } else {
                exit = true;
            }
        }

        if (!shortestPath.get(shortestPath.size()-1).equals(target)) {
            return Arrays.asList();
        }

        return shortestPath;

    }
}
