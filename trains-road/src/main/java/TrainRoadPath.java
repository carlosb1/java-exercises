import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class TrainRoadPath {
    private final String source;
    private final String target;
    private final int weight;

}
