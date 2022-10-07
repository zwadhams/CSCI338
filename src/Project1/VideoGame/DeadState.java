package VideoGame;


/**
 *
 * @author yaw
 */
public class DeadState implements VideoGameState {

    private VideoGame videoGame;

    public DeadState(VideoGame m) {
        videoGame = m;
    }

    @Override
    public void pressR() {
        System.out.println("...you are dead (DeadState)");
    }

    @Override
    public void pressUp() {
        System.out.println("...you are dead (DeadState)");
    }

    @Override
    public void reset() {
        System.out.println("Restarting Game (StandingState)");
        videoGame.setState(videoGame.getStandingState());
    }

    @Override
    public void fireball() {
        System.out.println("...you are dead (DeadState)");
    }

}
