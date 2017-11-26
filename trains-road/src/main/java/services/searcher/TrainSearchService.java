package services.searcher;



import models.TrainPath;
import java.util.*;
import java.util.stream.Collectors;


public class TrainSearchService implements SearchService {
    private final Map<String,List<TrainPath>> paths;
    public TrainSearchService () {
        this.paths = new HashMap<> ();
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
        Map<String, Double>  distances = this.paths.entrySet().stream().collect(
                Collectors.toMap(
                        e->e.getKey(),
                        e->Double.POSITIVE_INFINITY
                ));

        Map<String, String> finalPaths = new HashMap<>();

        List<String> shortestPath = new ArrayList<String>();

        distances.put(source,0.);
        String stop = source;
        boolean exit = false;
        while (!exit) {
            shortestPath.add(stop);
            this.paths.get(stop).stream().forEach(
                    path -> {
                        final double acumLength = (path.getWeight() + distances.get(path.getSource()));
                        if (distances.get(path.getTarget()) >acumLength ) {
                            distances.put(path.getTarget(),acumLength);
                            finalPaths.put(path.getTarget(),path.getSource());
                        }

                    }

            );
            Optional<String> possibleNextStop = distances.entrySet().stream().filter(entry -> !shortestPath.contains(entry.getKey())).min(Comparator.comparing(entry -> entry.getValue())).map(entry -> entry.getKey());
            if (!possibleNextStop.isPresent()) {
                exit = true;
                continue;
            }
            stop = possibleNextStop.get();

        }

        List<String> foundPath =  new ArrayList<>();
        foundPath.add(target);
        String indexPath = target;
        do {
            indexPath = finalPaths.get(indexPath);
            foundPath.add(indexPath);

        } while (indexPath!=source);
        Collections.reverse(foundPath);
        return foundPath;
    }

}
