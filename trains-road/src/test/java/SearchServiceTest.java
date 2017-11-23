import org.junit.Test;
import services.searcher.SearchService;
import services.searcher.TrainSearchService;

import static org.junit.Assert.assertEquals;

public class SearchServiceTest {

    @Test
    public void start_search_service_without_stops() {
        SearchService searchService = new TrainSearchService();
        assertEquals(searchService.getAllStops(),0);
    }

    @Test
    public void add_correctly_a_stop() {
        SearchService searchService = new TrainSearchService();
        searchService.addPath( new TrainRoadPath("0","1",0));
        assertEquals(searchService.getAllStops(),1);
    }


}
