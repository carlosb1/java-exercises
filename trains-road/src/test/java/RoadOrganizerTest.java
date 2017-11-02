import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;

import static junit.framework.TestCase.assertEquals;


public class RoadOrganizerTest {
    private RoadOrganizer roadOrganizer;


    @Before
    public void setUp() {
        roadOrganizer = new RoadOrganizer();
    }
    @Test
    public  void should_be_initialised_correctly () {
        roadOrganizer.addPath("A");
        int dist = roadOrganizer.distance("A","A");
        assertEquals(0,dist);

    }

    @Test
    public void should_be_get_correctly_path_in_one_step()  {
        roadOrganizer.addPath(TrainRoadPaths.asList("A","B"));
        //TODO wrong test, it is necessary include weights
        int dist = roadOrganizer.distance("A","B");
        assertEquals(1,dist);


    }
}
