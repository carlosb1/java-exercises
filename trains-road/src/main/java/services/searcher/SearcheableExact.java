package services.searcher;

import models.Stop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearcheableExact extends SearcheableMap<List<List<Stop>>>{
    private final List<List<Stop>> result;
    private final int exactStops;

    public SearcheableExact(HashMap<String, HashMap<String,Double>> paths, final int exactStops) {
        super.paths = paths;
        this.result =  new ArrayList<>();
        this.exactStops = exactStops;
    }


    @Override
    public void updateResult(List<Stop> path) {
        result.add(new ArrayList<>(path));

    }

    @Override
    public boolean isWrongPath(List<Stop> newPath) {
        return newPath.size() > exactStops;
    }

    @Override
    public boolean isResultPath(String source, String target, List<Stop> path) {
        return path.size() == exactStops
                && path.size() >=2
                && path.get(0).getName().equals(source)
                && path.get(path.size()-1).getName().equals(target)
                ;
    }

    @Override
    public List<List<Stop>> getResult() {
        return result;
    }
}
