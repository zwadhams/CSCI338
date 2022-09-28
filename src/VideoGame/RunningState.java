package VideoGame;

import GumballMachine.GumballMachine;
import GumballMachine.State;

/**
 *
 * @author yaw
 */
public class RunningState implements VideoGameState {

    private VideoGame videoGame;

    public RunningState(VideoGame m) {
        videoGame = m;
    }

    @Override
    public void pressR() {
        System.out.println("Stopped Running (StandingState)");
        videoGame.setState(videoGame.getStandingState());
    }

    @Override
    public void pressUp() {
        System.out.println("Jumped (JumpingState)");
        videoGame.setState(videoGame.getJumpingState());
    }

    @Override
    public void reset() {
        System.out.println(".... (Can't reset unless dead)");
    }

    @Override
    public void fireball() {
        System.out.println("You got hit with a fireball and died (DeadState)");
        videoGame.setState(videoGame.getDeadState());
    }

}
