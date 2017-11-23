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


}
