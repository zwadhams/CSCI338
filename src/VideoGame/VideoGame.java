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


}
