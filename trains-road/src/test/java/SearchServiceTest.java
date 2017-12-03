import java.util.List;

import models.Stop;
import models.TrainMap;
import org.junit.Assert;
import org.junit.Test;
import services.CalculateDistance;
import services.searcher.*;


public class SearchServiceTest {

//    AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
    @Test
    public void find_distance_input1() {
        double distance = new CalculateDistance(setUpTestMap().getMap()).findDistances("A","B","C");

        Assert.assertEquals(9,distance,0.01);

    }

    @Test
    public void find_distance_input2() {
        double distance = new CalculateDistance(setUpTestMap().getMap()).findDistances("A","B","C");
        Assert.assertEquals(9,distance,0.01);

    }

    @Test
    public void find_distance_input3() {
        double distance = new CalculateDistance(setUpTestMap().getMap()).findDistances("A","D");
        Assert.assertEquals(5,distance,0.01);

    }

    @Test
    public void find_distance_input4() {
        double distance = new CalculateDistance(setUpTestMap().getMap()).findDistances("A","E","B","C","D");
        Assert.assertEquals(22,distance,0.01);

    }


    @Test
    public void find_distance_input5() {
        double distance = new CalculateDistance( setUpTestMap().getMap()).findDistances("A","E","D");
        Assert.assertEquals(-1,distance,0.01);

    }

    @Test
    public void available_trips_max_stops() {
        List<List<Stop>>  result2 = new SearcheableMax( setUpTestMap().getMap(),3+1).search("C","C");
        Assert.assertEquals(2,result2.size());
    }

    @Test
    public void available_trips_exact_stops () {
        List<List<Stop>> result2 = new SearcheableExact( setUpTestMap().getMap(),4+1).search("A","C");
        Assert.assertEquals(3,result2.size());
    }
    @Test
    public void available_trips_more_stops () {
        List<List<Stop>> result2 = new SearcheableLessThan( setUpTestMap().getMap(),30).search("C","C");
        Assert.assertEquals(7,result2.size());
    }

    @Test
    public void shortest_trip_A_C () {
        List<Stop> result2= new SearcheableShortest( setUpTestMap().getMap()).search("A","C");
        Assert.assertEquals(3,result2.size());
    }

    @Test
    public void shortest_trip_B_B () {
        List<Stop> result2= new SearcheableShortest( setUpTestMap().getMap()).search("B","B");
        Assert.assertEquals(4,result2.size());
    }



    private TrainMap setUpTestMap() {
        TrainMap map = new TrainMap();
        map.addPath("A","B",5.);
        map.addPath("B","C",4.);
        map.addPath("C","D",8.);
        map.addPath("D","C",8.);
        map.addPath("D","E",6.);
        map.addPath("A","D",5.);
        map.addPath("C","E",2.);
        map.addPath("E","B",3.);
        map.addPath("A","E",7.);
        return map;
    }

}
