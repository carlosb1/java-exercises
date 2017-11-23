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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrainPath trainPath = (TrainPath) o;

        if (weight != trainPath.weight) return false;
        if (source != null ? !source.equals(trainPath.source) : trainPath.source != null) return false;
        return target != null ? target.equals(trainPath.target) : trainPath.target == null;
    }

    @Override
    public int hashCode() {
        int result = source != null ? source.hashCode() : 0;
        result = 31 * result + (target != null ? target.hashCode() : 0);
        result = 31 * result + weight;
        return result;
    }
}
