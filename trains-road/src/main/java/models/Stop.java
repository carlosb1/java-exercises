package models;

public class Stop {
    private final String target;
    private final double cost;
    public Stop (String target, double cost) {
        this.target = target;
        this.cost  = cost;
    }
    public String getTarget() {
        return target;
    }
    public double getCost() {
        return cost;
    }
}
