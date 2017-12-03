package services.searcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import models.Stop;

public class SearcheableLessThan extends SearcheableMap<List<List<Stop>>> {
    private final double moreStops;
    private final List<List<Stop>> result;

    public SearcheableLessThan(HashMap<String, HashMap<String,Double>> paths, final double moreStops) {
        super.paths = paths;
        this.result =  new ArrayList<>();
        this.moreStops = moreStops;
    }
    @Override
    public void updateResult(List<Stop> path) {
        result.add(new ArrayList<>(path));

    }

    @Override
    public boolean isWrongPath(List<Stop> newPath) {
        return getDistance(newPath) >= moreStops;
    }

    @Override
    public boolean isResultPath(String source, String target, List<Stop> path) {
        return getDistance(path) <= moreStops
                && path.size() >=2
                && path.get(0).getName().equals(source)
                && path.get(path.size()-1).getName().equals(target);
    }

    @Override
    public List<List<Stop>> getResult() {
        return result;
    }

    private double getDistance(List<Stop> path) {
        return path.stream().mapToDouble(stop -> stop.getCost()).sum();
    }


}
