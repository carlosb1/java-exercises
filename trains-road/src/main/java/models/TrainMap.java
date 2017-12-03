package models;

import java.util.HashMap;

public class TrainMap {
    private HashMap<String,HashMap<String, Double>> paths;
    public TrainMap () {
        this.paths = new HashMap<>();
    }

    public HashMap<String, HashMap<String, Double>> getMap() {
        return paths;
    }

    public void addPath(String source, String target, Double cost) {
        if (!this.paths.containsKey(source)) {
            this.paths.put(source,new HashMap<>());
        }
        this.paths.get(source).put(target, cost);


        if (!this.paths.containsKey(target)) {
            this.paths.put(target,new HashMap<>());
        }
    }
}
