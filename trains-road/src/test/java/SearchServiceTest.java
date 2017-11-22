import org.junit.Test;
import services.searcher.SearchService;
import services.searcher.TrainSearchService;

import static org.junit.Assert.assertEquals;

public class SearchServiceTest {

    @Test
    public void should_start_search_service() {
        SearchService searchService = new TrainSearchService();

        assertEquals(searchService.getAllStops(),0);


        //searchService.addStop(new TrainRoadPath("0,""1",1));
       // searchService.addNewStop(new TrainRoadPath("0","1",1));
    }

}
