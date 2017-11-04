
import org.apache.commons.io.IOUtils;

import java.io.*;

/**
 * Hello world!
 *
 */
public class App 
{





    public static void main( String[] args ) throws IOException {

        RoadOrganizer roadOrganizer = new RoadOrganizer();
        UserCLI app = new UserCLI(roadOrganizer,IOUtils.toInputStream("AB5 BC4 CD8 DC8 DE6 AD5 CE2 EB3 AE7","UTF-8"));
        app.update();
        int distance = roadOrganizer.distance("A","D");

    }
}
