package services.searcher;



import models.TrainPath;

import java.util.ArrayList;
import java.util.List;

public class TrainSearchService implements SearchService<TrainPath> {
    private final List<TrainPath> paths;
    public TrainSearchService () {
        this.paths = new ArrayList<TrainPath>();
    }


    @Override
    public void addPath(TrainPath newPath) {
        this.paths.add(newPath);
    }

    @Override
    public List<TrainPath> findRoute(String source, String target) {
        return this.paths;
    }
}
