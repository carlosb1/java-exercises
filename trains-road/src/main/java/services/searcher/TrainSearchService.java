package services.searcher;



import models.TrainPath;
import java.util.*;
import java.util.stream.Collectors;


public class TrainSearchService implements SearchService {
    private final Map<String,List<TrainPath>> paths;
    public TrainSearchService () {
        this.paths = new HashMap<> ();
    }


    //TODO refactor all this method
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

    public List<String> findPath(String source, String target) {
        Stack<List<String>>  queue = new Stack<List<String>>();
        queue.push(Arrays.asList(source));
        while (!queue.isEmpty()) {
            List<String> path = queue.pop();
            /*  condition to leave*/
            String node = path.get(path.size()-1);
            if (node.equals(target)) {
                return path;
            }

            if (this.paths.containsKey(node)) {
                for (TrainPath adjacent : this.paths.get(node)) {
                    List<String> newPath = new ArrayList<>(path);
                    newPath.add(adjacent.getTarget());
                    queue.push(newPath);
                }
            }

        }
        return Arrays.asList();
    }

}
