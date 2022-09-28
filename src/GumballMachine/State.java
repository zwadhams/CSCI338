package GumballMachine;

/**
 *
 * @author yaw
 */
public interface State {
    public void insertQuarter();
    public void removeQuarter();
    public void turnCrank();
    public void refill();
}