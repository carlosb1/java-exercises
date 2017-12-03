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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stop stop = (Stop) o;

        if (Double.compare(stop.cost, cost) != 0) return false;
        return name != null ? name.equals(stop.name) : stop.name == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}