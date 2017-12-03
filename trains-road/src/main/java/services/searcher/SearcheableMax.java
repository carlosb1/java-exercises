package services.searcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import models.Stop;

public class SearcheableMax extends SearcheableMap<List<List<Stop>>> {
    private final List<List<Stop>> result;
    private final int maxStops;

    public SearcheableMax(HashMap<String, HashMap<String,Double>> paths, final int maxStops) {
        super.paths = paths;
        this.result =  new ArrayList<>();
        this.maxStops = maxStops;
    }
    @Override
    public void updateResult(List<Stop> path) {
        this.result.add(path);

    }

    @Override
    public boolean isWrongPath(List<Stop> newPath) {
        return newPath.size() > maxStops;
    }

    @Override
    public boolean isResultPath(String source, String target, List<Stop> path) {
        return path.size() <= maxStops
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
