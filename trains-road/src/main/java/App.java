
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{

     public static class Organizer {
         private final RoadOrganizer roadOrganizer;

         private final InputStream input;
         public Organizer (RoadOrganizer roadOrganizer, InputStream input) {
                this.roadOrganizer = roadOrganizer;
                this.input = input;
        }
        public void update () {
             List<TrainRoadPath> paths = Utils.Parse(input);
             for (TrainRoadPath path : paths) {
                 this.roadOrganizer.addPath(path);
             }
         }


    }

    public static void main( String[] args ) throws IOException {
        RoadOrganizer roadOrganizer = new RoadOrganizer();
        StringBuffer info = new StringBuffer();
        info.append("AB5 BC4 CD8 DC8 DE6 AD5 CE2 EB3 AE7");
        InputStream in = IOUtils.toInputStream(info, "UTF-8");
        Organizer app = new Organizer(roadOrganizer,in);
        app.update();
        int distance = roadOrganizer.distance("A","D");

    }
}
