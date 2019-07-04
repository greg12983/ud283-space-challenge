package main.java.pl.currenda;

public class U1 extends Rocket {

    public U1() {
        super(100, 10000, 18000);
    }

    @Override
    public boolean launch() {
        boolean rocketSafelyLaunched = Math.random() > (0.05 * cargoCarried / cargoLimit );
        return rocketSafelyLaunched;
    }

    @Override
    public boolean land() {
        boolean rocketSafelyLanded = Math.random() > (0.01 * cargoCarried / cargoLimit );
        return rocketSafelyLanded;
    }
}
