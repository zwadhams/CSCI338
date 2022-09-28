package VideoGame;

import GumballMachine.GumballMachine;

/**
 *
 * @author yaw
 */
public class StandingState implements VideoGameState {

    private VideoGame videoGame;

    public StandingState(VideoGame m) {
        videoGame = m;
    }

    @Override
    public void pressR() {
        System.out.println("Started Running (RunningState)");
        videoGame.setState(videoGame.getRunningState());
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
