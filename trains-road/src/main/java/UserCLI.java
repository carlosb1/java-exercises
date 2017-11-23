import models.TrainPath;

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
        List<TrainPath> paths = Utils.Parse(input);
        for (TrainPath path : paths) {
            this.roadOrganizer.addPath(path);
        }
    }


}
