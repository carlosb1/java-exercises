import org.junit.Before;
import org.junit.Test;

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
}
