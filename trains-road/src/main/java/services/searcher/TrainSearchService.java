package services.searcher;



import java.util.ArrayList;
import java.util.List;

public class TrainSearchService implements SearchService {
    private final List<Path> paths;
    public TrainSearchService () {
        this.paths = new ArrayList<Path>();
    }

    @Override
    public int getNumberOfStops() {
        return paths.size();
    }

    @Override
    public void addPath(Path newPath) {
        this.paths.add(newPath);
    }

    @Override
    public List<Path> findRoute(String source, String target) {
        return this.paths;
    }
}
