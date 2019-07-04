package main.java.pl.currenda;

public class U2 extends Rocket {

    public U2() {
        super(120, 18000, 29000);
    }

    @Override
    public boolean launch() {
        boolean rocketSafelyLaunched = Math.random() > (0.04 * cargoCarried / cargoLimit);
        return rocketSafelyLaunched;
    }

    @Override
    public boolean land() {
        boolean rocketSafelyLanded = Math.random() > (0.08 * cargoCarried / cargoLimit);
        return rocketSafelyLanded;
    }
}
