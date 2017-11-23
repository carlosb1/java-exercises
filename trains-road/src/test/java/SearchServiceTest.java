import java.util.List;
import models.TrainPath;
import org.junit.Test;
import services.searcher.Path;
import services.searcher.SearchService;
import services.searcher.TrainSearchService;

import static org.junit.Assert.assertEquals;

public class SearchServiceTest {

    @Test
    public void start_search_service_without_stops() {
        SearchService searchService = new TrainSearchService();
        assertEquals(searchService.getNumberOfStops(),0);
    }

    @Test
    public void add_correctly_one_stop() {
        SearchService searchService = new TrainSearchService();
        searchService.addPath( new TrainPath("0","1",0));
        assertEquals(searchService.getNumberOfStops(),1);
    }

    @Test
    public void find_route_for_one_path() {
        SearchService searchService = new TrainSearchService();
        Path path = new TrainPath("0","1",0);
        searchService.addPath(path);
        //TODO add generics
        List<Path> paths = searchService.findRoute("0","1");
        //TODO refactor to abstract assert
        assertEquals(paths.size(),1);
        assertEquals(paths.get(0),path);

    }


}
