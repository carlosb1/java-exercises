import java.util.Arrays;
import java.util.List;
import models.TrainPath;
import org.junit.Test;
import services.searcher.SearchService;
import services.searcher.TrainSearchService;

import static org.junit.Assert.assertEquals;

public class SearchServiceTest {

    @Test
    public void find_route_for_one_path() {
        SearchService searchService = new TrainSearchService();
        TrainPath path = new TrainPath("0","1",0);
        searchService.addPath(path);
        List<String> paths = searchService.findRoute("0","1");
        assertEquals(Arrays.asList("0","1"),paths);
    }

    @Test
    public void find_route_with_one_wrong_path() {
        SearchService searchService = new TrainSearchService();
        TrainPath path = new TrainPath("0","1",0);
        TrainPath pathTwo = new TrainPath("1","2",1);
        searchService.addPath(path);
        searchService.addPath(pathTwo);
        List<String> paths = searchService.findRoute("0","1");
        assertEquals( Arrays.asList("0","1"),paths);
    }

    @Test
    public void find_route_with_two_stops() {
        SearchService searchService = new TrainSearchService();
        TrainPath path = new TrainPath("0","1",0);
        TrainPath pathTwo = new TrainPath("1","2",1);
        searchService.addPath(path);
        searchService.addPath(pathTwo);
        List<String> paths = searchService.findRoute("0","2");
        assertEquals( Arrays.asList("0","1","2"),paths);
    }

    @Test
    public void find_route_with_multiple_stops() {
        SearchService searchService = new TrainSearchService();
        TrainPath path = new TrainPath("0","1",0);
        TrainPath pathTwo = new TrainPath("1","2",1);
        TrainPath pathThree = new TrainPath("2","3",1);
        searchService.addPath(path);
        searchService.addPath(pathTwo);
        searchService.addPath(pathThree);
        List<String> paths = searchService.findRoute("0","3");
        assertEquals(Arrays.asList("0","1","2","3"),paths);
    }

    @Test
    public void find_route_with_multiple_stops_and_diff_bifurs() {
        SearchService searchService = new TrainSearchService();
        TrainPath path = new TrainPath("0","1",0);
        TrainPath pathTwo = new TrainPath("1","2",1);
        TrainPath pathThree = new TrainPath("2","3",1);
        TrainPath pathFour = new TrainPath("2","4",1);
        searchService.addPath(path);
        searchService.addPath(pathTwo);
        searchService.addPath(pathThree);
        searchService.addPath(pathFour);
        List<String> paths = searchService.findRoute("0","3");
        assertEquals(Arrays.asList("0","1","2","3"),paths);
    }

    @Test
    public void find_route_with_multiple_stops_and_find_shortest() {
        SearchService searchService = new TrainSearchService();
        TrainPath path = new TrainPath("0","1",0);
        TrainPath pathTwo = new TrainPath("1","2",1);
        TrainPath pathThree = new TrainPath("2","3",1);
        TrainPath pathFour = new TrainPath("3","4",6);
        TrainPath pathFive = new TrainPath("2","4",1);
        searchService.addPath(path);
        searchService.addPath(pathTwo);
        searchService.addPath(pathThree);
        searchService.addPath(pathFour);
        searchService.addPath(pathFive);
        List<String> paths = searchService.findRoute("0","4");
        assertEquals(Arrays.asList("0","1","2","4"),paths);
    }



}
