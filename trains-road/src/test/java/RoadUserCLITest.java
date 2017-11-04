import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


public class RoadUserCLITest {
    private RoadOrganizer roadOrganizer;


    @Before
    public void setUp() {
        roadOrganizer = new RoadOrganizer();
    }

    @Test
    public void should_be_initialised_correctly() {
        //TODO  add optional for not distance
        int dist = roadOrganizer.distance("A","B");

    }
    @Test
    public void should_be_get_correctly_path_in_one_step()  {
        roadOrganizer.addPath(new TrainRoadPath("A","B",1));
        //TODO wrong test, it is necessary include weights
        int dist = roadOrganizer.distance("A","B");
        assertEquals(1,dist);
    }
    @Test
    public void should_be_correctly_path_in_two_steps() {
        roadOrganizer.addPath(new TrainRoadPath("A","B",1));
        roadOrganizer.addPath(new TrainRoadPath("B","C",2));
        int dist = roadOrganizer.distance("A","C");
        assertEquals(3,dist);
    }

    @Test
    public void should_not_find_path_not_exist_target() {
        roadOrganizer.addPath(new TrainRoadPath("A","B",1));
        int dist = roadOrganizer.distance("A","C");
        assertEquals(-1,dist);
    }


    @Test
    public void should_not_find_path_not_exist_source() {
        roadOrganizer.addPath(new TrainRoadPath("A","B",1));
        int dist = roadOrganizer.distance("C","A");
        assertEquals(-1,dist);
    }


    @Test
    public void should_be_correctly_path_in_two_steps_with_different_paths() {
        roadOrganizer.addPath(new TrainRoadPath("A","B",1));
        roadOrganizer.addPath(new TrainRoadPath("B","C",2));
        roadOrganizer.addPath(new TrainRoadPath("B","D",2));
        int dist = roadOrganizer.distance("A","C");
        assertEquals(3,dist);
    }


    @Test
    public void should_be_correctly_path_in_two_steps_with_different_paths_and_large_number_of_steps() {
        roadOrganizer.addPath(new TrainRoadPath("A","B",1));
        roadOrganizer.addPath(new TrainRoadPath("B","C",2));
        roadOrganizer.addPath(new TrainRoadPath("C","D",2));
        roadOrganizer.addPath(new TrainRoadPath("D","E",2));
        roadOrganizer.addPath(new TrainRoadPath("B","D",2));
        roadOrganizer.addPath(new TrainRoadPath("A","S",2));
        roadOrganizer.addPath(new TrainRoadPath("S","V",2));
        roadOrganizer.addPath(new TrainRoadPath("R","D",2));
        roadOrganizer.addPath(new TrainRoadPath("E","F",2));
        int dist = roadOrganizer.distance("A","F");
        assertEquals(9,dist);
    }







}
