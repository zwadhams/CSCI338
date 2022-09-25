/**
 *
 * @author yaw
 */
public class EmptyState implements State {

    private GumballMachine gumballMachine;

    public EmptyState(GumballMachine m) {
        gumballMachine = m;
    }

    @Override
    public void insertQuarter() { System.out.println("There's already a quarter in there");
    }

    @Override
    public void removeQuarter() {
        System.out.println("No quarter to remove");
    }

    @Override
    public void turnCrank() {
        System.out.println("It's empty");
    }

    @Override
    public void refill() { //function to refill the gumball reservoir
        gumballMachine.gumballsLeft = 5;
        System.out.println("Refilled gumball reservoir");
        gumballMachine.setState(gumballMachine.getHasQuarterState());
    }
}
