import java.util.Arrays;
import java.util.List;
import models.TrainPath;
import org.junit.Test;
import services.searcher.TrainSearchService;

import static org.junit.Assert.assertEquals;

public class SearchServiceTest {

    @Test
    public void find_route_for_one_path() {
        TrainSearchService searchService = new TrainSearchService();
        searchService.addPath("0","1",0.);
        List<String> paths = searchService.findRoute("0","1");
        assertEquals(Arrays.asList("0","1"),paths);
    }

    @Test
    public void find_route_with_one_wrong_path() {
        TrainSearchService searchService = new TrainSearchService();
        searchService.addPath("0","1",0.);
        searchService.addPath("1","2",1.);
        List<String> paths = searchService.findRoute("0","1");
        assertEquals( Arrays.asList("0","1"),paths);
    }

    @Test
    public void find_route_with_two_stops() {
        TrainSearchService searchService = new TrainSearchService();
        searchService.addPath("0","1",0.);
        searchService.addPath("1","2",1.);
        List<String> paths = searchService.findRoute("0","2");
        assertEquals( Arrays.asList("0","1","2"),paths);
    }

    @Test
    public void find_route_with_multiple_stops() {
        TrainSearchService searchService = new TrainSearchService();
        searchService.addPath("0","1",0.);
        searchService.addPath("1","2",1.);
        searchService.addPath("2","3",1.);
        List<String> paths = searchService.findRoute("0","3");
        assertEquals(Arrays.asList("0","1","2","3"),paths);
    }

    @Test
    public void find_route_with_multiple_stops_and_diff_bifurs() {
        TrainSearchService searchService = new TrainSearchService();
        searchService.addPath("0","1",0.);
        searchService.addPath("1","2",1.);
        searchService.addPath("2","3",1.);
        searchService.addPath("2","4",1.);
        List<String> paths = searchService.findRoute("0","3");
        assertEquals(Arrays.asList("0","1","2","3"),paths);
    }

    @Test
    public void find_route_with_multiple_stops_and_find_shortest() {
        TrainSearchService searchService = new TrainSearchService();
        searchService.addPath("0","1",0.);
        searchService.addPath("1","2",1.);
        searchService.addPath("2","3",1.);
        searchService.addPath("3","4",6.);
        searchService.addPath("2","4",1.);
        List<String> paths = searchService.findRoute("0","4");
        assertEquals(Arrays.asList("0","1","2","4"),paths);
    }



}
