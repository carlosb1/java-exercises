import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import services.searcher.TrainSearchService;

import static org.junit.Assert.assertEquals;

public class SearchServiceTest {

    @Test
    public void find_route_for_one_path() {
        TrainSearchService searchService = new TrainSearchService();
        searchService.addPath("0","1",0.);
        List<TrainSearchService.Stop> paths = searchService.findRoute("0","1");
        List<String> nameStops = paths.stream().map(stop-> stop.getName()).collect(Collectors.toList());
        assertEquals(Arrays.asList("0","1"),nameStops);
    }

    @Test
    public void find_route_with_one_wrong_path() {
        TrainSearchService searchService = new TrainSearchService();
        searchService.addPath("0","1",0.);
        searchService.addPath("1","2",1.);
        List<TrainSearchService.Stop> paths = searchService.findRoute("0","1");
        List<String> nameStops = paths.stream().map(stop-> stop.getName()).collect(Collectors.toList());
        assertEquals( Arrays.asList("0","1"),nameStops);
    }

    @Test
    public void find_route_with_two_stops() {
        TrainSearchService searchService = new TrainSearchService();
        searchService.addPath("0","1",0.);
        searchService.addPath("1","2",1.);
        List<TrainSearchService.Stop> paths = searchService.findRoute("0","2");
        List<String> nameStops = paths.stream().map(stop-> stop.getName()).collect(Collectors.toList());
        assertEquals( Arrays.asList("0","1","2"),nameStops);
    }

    @Test
    public void find_route_with_multiple_stops() {
        TrainSearchService searchService = new TrainSearchService();
        searchService.addPath("0","1",0.);
        searchService.addPath("1","2",1.);
        searchService.addPath("2","3",1.);
        List<TrainSearchService.Stop> paths = searchService.findRoute("0","3");
        List<String> nameStops = paths.stream().map(stop-> stop.getName()).collect(Collectors.toList());

        assertEquals(Arrays.asList("0","1","2","3"),nameStops);
    }

    @Test
    public void find_route_with_multiple_stops_and_diff_bifurs() {
        TrainSearchService searchService = new TrainSearchService();
        searchService.addPath("0","1",0.);
        searchService.addPath("1","2",1.);
        searchService.addPath("2","3",1.);
        searchService.addPath("2","4",1.);
        List<TrainSearchService.Stop> paths = searchService.findRoute("0","3");
        List<String> nameStops = paths.stream().map(stop-> stop.getName()).collect(Collectors.toList());

        assertEquals(Arrays.asList("0","1","2","3"),nameStops);
    }

    @Test
    public void find_route_with_multiple_stops_and_find_shortest() {
        TrainSearchService searchService = new TrainSearchService();
        searchService.addPath("0","1",0.);
        searchService.addPath("1","2",1.);
        searchService.addPath("2","3",1.);
        searchService.addPath("3","4",6.);
        searchService.addPath("2","4",1.);
        List<TrainSearchService.Stop> paths = searchService.findRoute("0","4");
        List<String> nameStops = paths.stream().map(stop-> stop.getName()).collect(Collectors.toList());
        assertEquals(Arrays.asList("0","1","2","4"),nameStops);
    }

//    AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
    @Test
    public void find_distance_input1() {
        TrainSearchService searchService = setUpTestMap();

        double distance = searchService.findDistance("A","B","C");

        Assert.assertEquals(9,distance,0.01);

    }

    @Test
    public void find_distance_input2() {
        TrainSearchService searchService = setUpTestMap();

        double distance = searchService.findDistance("A","B","C");

        Assert.assertEquals(9,distance,0.01);

    }

    @Test
    public void find_distance_input3() {
        TrainSearchService searchService = setUpTestMap();

        double distance = searchService.findDistance("A","D");

        Assert.assertEquals(5,distance,0.01);

    }

    @Test
    public void find_distance_input4() {
        TrainSearchService searchService = setUpTestMap();

        double distance = searchService.findDistance("A","E","B","C","D");

        Assert.assertEquals(22,distance,0.01);

    }


    @Test
    public void find_distance_input5() {
        TrainSearchService searchService = setUpTestMap();

        double distance = searchService.findDistance("A","E","D");

        Assert.assertEquals(-1,distance,0.01);

    }

    @Test
    public void available_trips_max_stops() {
        TrainSearchService searchService = setUpTestMap();
        List<List<TrainSearchService.Stop>>  result = searchService.availableTrips("C","C",3);
        //TODO refactor with correct assert
        Assert.assertEquals(2,result.size());
    }

    @Test
    public void available_trips_exact_stops () {
        TrainSearchService searchService = setUpTestMap();
        List<List<TrainSearchService.Stop>>  result = searchService.availableTripsExactStops("A","C",4);
        //TODO refactor with correct assert
        Assert.assertEquals(3,result.size());
    }

//    @Test
//    public void shortest_path_between_A_C() {
//        TrainSearchService searchService = setUpTestMap();
//        List<TrainSearchService.Stop>  result = searchService.shortestPath("A","C");
//    }



    private TrainSearchService setUpTestMap() {
        TrainSearchService searchService = new TrainSearchService();
        searchService.addPath("A","B",5.);
        searchService.addPath("B","C",4.);
        searchService.addPath("C","D",8.);
        searchService.addPath("D","C",8.);
        searchService.addPath("D","E",6.);
        searchService.addPath("A","D",5.);
        searchService.addPath("C","E",2.);
        searchService.addPath("E","B",3.);
        searchService.addPath("A","E",7.);
        return searchService;
    }

}
