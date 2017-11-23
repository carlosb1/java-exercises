package services.searcher;


import java.util.List;

public interface SearchService {

    int getNumberOfStops();

    void addPath(Path trainRoadPath);

    List<Path> findRoute(String source, String target);
}
