package main.java.pl.currenda;

import java.util.ArrayList;
import java.util.List;

public class Rocket implements SpaceShip {

    protected int cost;
    protected int weight;
    protected int maxWeight;
    protected int cargoCarried = 0;
    protected int cargoLimit;
    protected List<Item> cargo = new ArrayList<>();

    public Rocket(int cost, int weight, int maxWeight) {
        this.cost = cost;
        this.weight = weight;
        this.maxWeight = maxWeight;
        cargoLimit = maxWeight - weight;
    }

    @Override
    public boolean launch() {
        return true;
    }

    @Override
    public boolean land() {
        return true;
    }

    @Override
    public boolean canCarry(Item item) {
        return (cargoCarried + item.getWeight() <= cargoLimit);
    }

    @Override
    public void carry(Item item) {
        cargo.add(item);
        cargoCarried += item.getWeight();
    }

    public int getCost() {
        return cost;
    }
}
