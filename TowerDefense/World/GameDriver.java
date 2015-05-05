package TowerDefense.World;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 * Represents a GameDriver
 *
 * @author Victor Law
 * @version 1.0
 */
public abstract class GameDriver {

    private Scene surfaceScene;
    private Group sceneNodes;
    private static Timeline gameLoop;
    private final int framesPerSecond;
    private final String title;

    protected final Game gameManager = new Game();
    /**
     * Constructs a GameDriver object
     * @param fps the frames per second
     * @param title title of the game
     */
    public GameDriver(final int fps, final String title) {
        framesPerSecond = fps;
        this.title = title;
        buildSetGameLoop();
    }
    /**
     * builds a loop for the game
     */
    protected final void buildSetGameLoop() {
        final Duration oneFrameAmt = Duration
            .millis(1000 / this.getFramesPerSecond());
        final KeyFrame oneFrame = new KeyFrame(oneFrameAmt,
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    updateTanks();
                    gameManager.cleanUpTanks();
                    }
                });
        this.setGameLoop(TimelineBuilder.create()
                        .cycleCount(Animation.INDEFINITE)
                        .keyFrames(oneFrame)
                        .build());
    }
    /**
     * abstract method to initialize the game
     * @param primaryStage the Stage for the game
     */
    public abstract void initialize(final Stage primaryStage);
    /**
     * Starts the timeline, or the game loop
     */
    public void startGameLoop() {
        getGameLoop().play();
    }
    /**
     * Updates the tanks
     */
    public void updateTanks() {
        for (Tank tank: gameManager.getTanks()) {
            handleUpdate(tank);
        }
    }
    /**
     * updates the handle
     */
    protected void handleUpdate(Tank tank) {
    }
    /**
     * Gets the frames per second for the game.
     * @return the frames per second
     */
    public int getFramesPerSecond() {
        return framesPerSecond;
    }
    /**
     * gets the title of the game
     * @return the title of the game
     */
    public String getWindowTitle() {
        return title;
    }
    /**
     * Gets the timeline of the game
     * @return timeline of the game
     */
    protected static Timeline getGameLoop() {
        return gameLoop;
    }
    /**
     * sets the timeline of the game
     * @param gameLoop the timeline to be set for the game
     */
    protected static void setGameLoop(Timeline gameLoop) {
        GameDriver.gameLoop = gameLoop;
    }
    /**
     * gets the Game or the gameManager as I call it of the game
     * @return the Game aka gameManager of the game
     */
    protected Game getGameManager() {
        return gameManager;
    }
    /**
     * Gets the scene of the game
     * @return the scene of the game
     */
    public Scene getSurfaceScene() {
        return surfaceScene;
    }
    /**
     * Gets the Group of the game
     * @return the group of the game;
     */
    public Group getSceneNodes() {
        return sceneNodes;
    }
    /**
     * Sets the Scene of the game
     * @param scene the scene to be set
     */
    protected void setGameSurface(Scene scene) {
        surfaceScene = scene;
    }
    /**
     * Sets the Group for the game
     * @param sceneNodes the Group to be set
     */
    protected void setSceneNodes(Group sceneNodes) {
        this.sceneNodes = sceneNodes;
    }
}
