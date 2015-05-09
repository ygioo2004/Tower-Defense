import javafx.application.Application;
import javafx.stage.Stage;
/**
 * Represents the Driver class, the class that runs the game
 *
 * @author Victor Law
 * @version 1.0
 */
public class Driver extends Application {
    private GameDriver gameWorld = new TDWorld(60);
    /**
     * the Main method
     * @param args string array of args input through the command line
     */
    public static void main(String[] args) {
        launch(args);
    }
    /**
     * gets the GameDriver
     * @return the GameDriver for this Driver
     */
    public GameDriver getGameDriver() {
        return gameWorld;
    }
    /**
     * Overrides the start method and starts the game
     * @param primaryStage the Stage to be taken in
     */
    @Override
    public void start(Stage primaryStage) {
        gameWorld.initialize(primaryStage);

        gameWorld.startGameLoop();

        primaryStage.show();
    }

}
