package models;

import services.searcher.Path;

public final class TrainPath implements Path {
    private final String source;
    private final String target;
    private final int weight;

    public TrainPath(String source, String target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    public int getWeight() {
        return weight;
    }
}
