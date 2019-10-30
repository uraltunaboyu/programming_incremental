package model;

import java.io.Serializable;
import java.util.Objects;

public abstract class Purchasable implements Serializable {
    private String name;
    private double cost;
    private double income;

    public Purchasable(String name, int cost, double income) {
        this.name = name;
        this.cost = cost;
        this.income = income;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Purchasable that = (Purchasable) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
