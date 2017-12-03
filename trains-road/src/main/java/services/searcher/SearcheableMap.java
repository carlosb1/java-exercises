package services.searcher;

import models.Stop;

import java.util.*;

public abstract class SearcheableMap<T> {
        protected HashMap<String, HashMap<String, Double>> paths;

        public abstract void updateResult(List<Stop> path);
        public abstract boolean isWrongPath(List<Stop> newPath);
        public abstract boolean isResultPath(String source, String target,  List<Stop> path);
        public abstract T getResult();

        public T search (String source, String target) {
            Stack<List<Stop>> candidates = new Stack<>();
            candidates.push(Arrays.asList(new Stop(source,0)));
            while (!candidates.isEmpty()) {
                List<Stop> path = candidates.pop();
                if (isResultPath(source, target, path) ){
                    updateResult(path);
                }
                String nameStop = path.get(path.size()-1).getName();
                if (this.paths.containsKey(nameStop) ) {
                    Map<String, Double> adjacents  = this.paths.get(nameStop);
                    for (String adjacent : adjacents.keySet()) {
                        List<Stop> newPath = new ArrayList<>(path);
                        newPath.add(new Stop(adjacent,adjacents.get(adjacent)));

                        if (isWrongPath(newPath)) {
                            continue;
                        }
                        candidates.push(newPath);
                    }
                }
            }
            return getResult();

        }
}
