package services.searcher;


import java.util.List;

public interface SearchService<T extends Path> {


    void addPath(T trainRoadPath);

    List<T> findRoute(String source, String target);
}
