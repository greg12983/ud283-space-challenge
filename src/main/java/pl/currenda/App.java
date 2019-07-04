package main.java.pl.currenda;

import java.util.List;

public class App {

    public void run() {
        Simulation simulation = new Simulation();

        List<Item> phase1items = simulation.loadItems((byte) 1);
        List<Item> phase2items = simulation.loadItems((byte) 2);

        List<Rocket> u1rocketsPhase1 = simulation.loadU1(phase1items);
        List<Rocket> u1rocketsPhase2 = simulation.loadU1(phase2items);

        int budget4u1phase1 = simulation.runSimulation(u1rocketsPhase1);
        int budget4u1phase2 = simulation.runSimulation(u1rocketsPhase2);
        int totalU1budget = budget4u1phase1 + budget4u1phase2;
        int u1launched = simulation.getRocketsLaunched();
        int u1crashed = simulation.getRocketsCrashed();

        List<Rocket> u2rocketsPhase1 = simulation.loadU2(phase1items);
        List<Rocket> u2rocketsPhase2 = simulation.loadU2(phase2items);

        int budget4u2phase1 = simulation.runSimulation(u2rocketsPhase1);
        int budget4u2phase2 = simulation.runSimulation(u2rocketsPhase2);
        int totalU2budget = budget4u2phase1 + budget4u2phase2;
        int u2launched = simulation.getRocketsLaunched();
        int u2crashed = simulation.getRocketsCrashed();

        String message = "U1 rocket phase 1 would cost %s, phase 2 would cost %s, for a total of %s mln polish pesos (%s rockets launched, of whitch %s lost)%n";
        message += "U2 rocket phase 1 would cost %s, phase 2 would cost %s, for a total of %s mln polish pesos (%s rockets launched, of whitch %s lost)";
        message = String.format(message, budget4u1phase1, budget4u1phase2, totalU1budget,
                u1launched, u1crashed, budget4u2phase1, budget4u2phase2, totalU2budget, u2launched, u2crashed);

        System.out.println(message);

    }
}
