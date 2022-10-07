package VideoGame;

/**
 *
 * @author yaw
 */
public class VideoGameDriver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        VideoGame videoGame = new VideoGame();

        videoGame.pressR();
        videoGame.pressUp();
        videoGame.fireball();
        videoGame.reset();
        videoGame.fireball();
        videoGame.pressUp();
        videoGame.pressR();
        videoGame.reset();
        videoGame.pressR();
        videoGame.fireball();
        videoGame.pressR();

    }
}
