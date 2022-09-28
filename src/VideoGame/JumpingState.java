package VideoGame;

/**
 *
 * @author yaw
 */
public class JumpingState implements VideoGameState {

    private VideoGame videoGame;

    public JumpingState(VideoGame m) {
        videoGame = m;
    }

    @Override
    public void pressR() {
        System.out.println("Continued Running (RunningState)");
        videoGame.setState(videoGame.getRunningState());
    }

    @Override
    public void pressUp() {
        System.out.println("You're already in the air.... (JumpingState)");
    }

    @Override
    public void reset() {
        System.out.println(".... (Can't reset unless dead)");
    }

    @Override
    public void fireball() {
        System.out.println("You dodged the fireball! (Standing State)");
        videoGame.setState(videoGame.getStandingState());
    }

}
