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

    public List<String> findRoute(String source, String target) {
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
                for (Stop adjacent : this.paths.get(node)) {
                    List<String> newPath = new ArrayList<>(path);
                    newPath.add(adjacent.getTarget());
                    queue.push(newPath);
                }
            }

        }
        return Arrays.asList();
    }

}
