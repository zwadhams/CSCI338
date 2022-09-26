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

}
