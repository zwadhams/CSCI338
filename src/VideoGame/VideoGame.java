package VideoGame;

/**
 *
 * @author yaw
 */
public class VideoGame {
    //private instance variables for each possible state
    private VideoGameState standingState;
    private VideoGameState jumpingState;
    private VideoGameState runningState;
    private VideoGameState deadState;


    //current state the machine is in
    private VideoGameState currentState;



    public VideoGame() {
        //initialize possible state instance variables
        standingState = new StandingState(this);
        jumpingState = new JumpingState(this);
        runningState = new RunningState(this);
        deadState = new DeadState(this);


        
        //set initial current state
        currentState = standingState;
    }
    
    public void setState(VideoGameState state) {
        this.currentState = state;
    }

    //STATES
    public VideoGameState getStandingState() {return standingState;}

    public VideoGameState getRunningState() {return runningState;}

    public VideoGameState getJumpingState() {return jumpingState;}

    public VideoGameState getDeadState() {return deadState;}

    //TRANSITIONS
    public void pressR() { currentState.pressR(); }

    public void pressUp() { currentState.pressUp(); }

    public void reset() { currentState.reset(); }

    public void fireball() { currentState.fireball(); }

}
