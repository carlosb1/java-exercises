import java.util.Arrays;
import java.util.List;
import models.TrainPath;
import org.junit.Test;
import services.searcher.Path;
import services.searcher.SearchService;
import services.searcher.TrainSearchService;

import static org.junit.Assert.assertEquals;

public class SearchServiceTest {

    @Test
    public void find_route_for_one_path() {
        SearchService searchService = new TrainSearchService();
        Path path = new TrainPath("0","1",0);
        searchService.addPath(path);
        List<Path> paths = searchService.findRoute("0","1");
        assertEquals(paths, Arrays.asList(path));
    }

    @Test
    public void find_route_for_two_path() {
        SearchService searchService = new TrainSearchService();
        Path path = new TrainPath("0","1",0);
        Path pathTwo = new TrainPath("1","2",1);
        searchService.addPath(path);
        searchService.addPath(pathTwo);
        List<Path> paths = searchService.findRoute("0","1");
        assertEquals(paths, Arrays.asList(path));
    }



}
