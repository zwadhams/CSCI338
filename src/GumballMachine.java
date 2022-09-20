/**
 *
 * @author yaw
 */
public class GumballMachine {
    //private instance variables for each possible state
    private State hasQuarterState;
    private State noQuarterState;
    
    //current state the machine is in
    private State currentState;
    
    public GumballMachine() {
        //initialize possible state instance variables
        hasQuarterState = new HasQuarterState(this);
        noQuarterState = new NoQuarterState(this);
        
        //set initial current state
        currentState = noQuarterState;
    }
    
    public void setState(State state) {
        this.currentState = state;
    }
    
    public State getHasQuarterState() {
        return hasQuarterState;
    }
    
    public State getNoQuarterState() {
        return noQuarterState;
    }
    
    public void insertQuarter() {
        currentState.insertQuarter();
    }
    
    public void removeQuarter() {
        currentState.removeQuarter();
    }
    
    public void turnCrank() {
        currentState.turnCrank();
    }
}
