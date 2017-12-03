package services.searcher;

import java.util.HashMap;
import java.util.List;
import models.Stop;

import java.util.ArrayList;

public class SearcheableShortest extends SearcheableMap<List<Stop>> {
    private  List<Stop> shortest;
    public SearcheableShortest (HashMap<String, HashMap<String, Double>> paths) {
        super.paths = paths;
        this.shortest = new ArrayList<>();
    }
    @Override
    public void updateResult(List<Stop> path) {
        shortest = path;
    }

    @Override
    public boolean isWrongPath(java.util.List<Stop> newPath) {
        return !shortest.isEmpty() && getDistance(newPath) >= getDistance(shortest);
    }

    @Override
    public boolean isResultPath(String source, String target, java.util.List<Stop> path) {
        return (getDistance(path) <= getDistance(shortest) || shortest.isEmpty())
                && path.size() >=2
                && path.get(0).getName().equals(source)
                && path.get(path.size()-1).getName().equals(target);
    }

    @Override
    public List<Stop> getResult() {
        return shortest;
    }
    private double getDistance(List<Stop> path) {
        return path.stream().mapToDouble(stop -> stop.getCost()).sum();
    }

}
