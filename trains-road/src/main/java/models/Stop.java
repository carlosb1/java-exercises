package models;

public class Stop {
    private final String name;
    private final double cost;
    public Stop (String name, double cost) {
        this.name = name;
        this.cost  = cost;
    }
    public String getName() {
        return name;
    }
    public double getCost() {
        return cost;
    }
}
