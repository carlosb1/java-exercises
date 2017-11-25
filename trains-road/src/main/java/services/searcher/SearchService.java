package services.searcher;


import models.TrainPath;

import java.util.List;

public interface SearchService {


    void addPath(TrainPath trainRoadPath);

    List<String> findRoute(String source, String target);
}
