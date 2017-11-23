package services.searcher;



import models.TrainPath;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;


public class TrainSearchService implements SearchService<TrainPath> {
    private final HashMap<String,List<TrainPath>> paths;
    private final List<TrainPath> previousPath;
    public TrainSearchService () {
        this.paths = new HashMap<String,List<TrainPath>> ();
        this.previousPath = new LinkedList<>();
    }


    @Override
    public void addPath(TrainPath newPath) {
        if (!this.paths.containsKey(newPath.getSource())) {
            this.paths.put(newPath.getSource(),new ArrayList<>());
        }
        this.paths.get(newPath.getSource()).add(newPath);
        previousPath.add(newPath);
    }

    @Override
    public List<TrainPath> findRoute(String source, String target) {

        HashMap<String,Integer> vertices = new HashMap();
        for (List<TrainPath> values:  paths.values())
            for (TrainPath path: values){
                if (!vertices.containsKey(path.getTarget())) {
                    vertices.put(path.getTarget(),-1);
                }
                if (!vertices.containsKey(path.getSource())) {
                    vertices.put(path.getSource(),-1);
                }
        }

        /*  TODO check case not exists source*/
        if (!vertices.containsKey(source)) {
            return Arrays.asList();
        }
        vertices.put(source,0);
        List<String> verticesShortPath = new ArrayList<>();
        String currentStop = source;
        boolean found = false;
        while (!found) {
            Optional<TrainPath> minPath = this.paths.get(currentStop).stream().filter(path -> !verticesShortPath.contains(path.getTarget())).min(Comparator.comparing(path -> path.getWeight()));
            if (!minPath.isPresent()) {
                found = false;
            } else {
                currentStop = minPath.get().getTarget();
                int incrCost = minPath.get().getWeight();
                verticesShortPath.add(currentStop);

                //Update all distance
            }
        }




        return previousPath;
    }
}
