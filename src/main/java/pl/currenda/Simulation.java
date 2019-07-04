package main.java.pl.currenda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Simulation {

    public int getRocketsLaunched() {
        return rocketsLaunched;
    }

    private int rocketsLaunched = 0;
    private int rocketsLanded = 0;

    public int getRocketsCrashed() {
        // reset after read
        int temp = rocketsLaunched - rocketsLanded;
        rocketsLaunched = 0;
        rocketsLanded = 0;
        return temp;
    }

    public List<Item> loadItems(byte phase) {

        List<String> lines = new ArrayList<>();
        List<Item> items = new ArrayList<>();
        String phaseStr = "Phase-1.txt";
        if (phase == 2) phaseStr = "Phase-2.txt";

        try {
            lines = Files.readAllLines(Paths.get("src", "main", "resources", phaseStr));
        } catch (
                IOException e) {
            System.out.println("File not found");
            return null;
        }
        for (String line : lines) {
            String[] item = line.split("=");
            items.add(new Item(item[0], Integer.parseInt(item[1])));
        }
        return items;
    }

    public List<Rocket> loadU1(List<Item> items) {
        List<Rocket> rockets = new ArrayList<>();
        Rocket rocket = new U1();

        for (Item item : items) {
            if (rocket.canCarry(item)) {
                rocket.carry(item);
            } else {
                rockets.add(rocket);
                rocket = new U1();
                if (rocket.canCarry(item)) {
                    rocket.carry(item);
                } else {
                    System.out.println("Huston, we have a problem! Item is too big for U1 rocket. We need to call SpaceX.");
                    return null;
                }
            }
        }

        // make sure that last rocket was added to List
        if (rockets.get(rockets.size() - 1) != rocket) {
            rockets.add(rocket);
        }
        return rockets;
    }

    public List<Rocket> loadU2(List<Item> items) {
        List<Rocket> rockets = new ArrayList<>();
        Rocket rocket = new U2();

        for (Item item : items) {
            if (rocket.canCarry(item)) {
                rocket.carry(item);
            } else {
                rockets.add(rocket);
                rocket = new U2();
                if (rocket.canCarry(item)) {
                    rocket.carry(item);
                } else {
                    System.out.println("Huston, we have a problem! Item is too big for U2 rocket. We need to call SpaceX.");
                    return null;
                }
            }
        }

        // make sure that last rocket was added to List
        if (rockets.get(rockets.size() - 1) != rocket) {
            rockets.add(rocket);
        }

        return rockets;
    }

    public int runSimulation(List<Rocket> rockets) {
        int totalBudget = 0;

        // rocket must safely launch and land for it's mission to be successful
        // if it's successful, we get 0 + 1 - 1 = 0 crashed
        // if we get 2 crashed, we will have 0 + 3 - 1 = 2 crashed
        for (Rocket rocket : rockets) {
            do {
                totalBudget += rocket.getCost();
                rocketsLaunched++;
            } while (!(rocket.launch() && rocket.land()));
            rocketsLanded++;
        }

        return totalBudget;
    }
}
