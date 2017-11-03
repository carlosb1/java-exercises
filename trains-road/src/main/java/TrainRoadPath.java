
public final class TrainRoadPath {
    private final String source;
    private final String target;
    private final int weight;
    public TrainRoadPath(String source, String target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    public int getWeight() {
        return this.weight;
    }

}
