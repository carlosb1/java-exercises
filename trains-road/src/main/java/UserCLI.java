import java.io.InputStream;
import java.util.List;

public  class UserCLI {
    private final RoadOrganizer roadOrganizer;

    private final InputStream input;
    public UserCLI(RoadOrganizer roadOrganizer, InputStream input) {
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
