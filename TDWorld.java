import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.geometry.Point2D;
/**
 * Represents a TDWorld
 *
 * @author Victor Law
 * @version 1.0
 */
public class TDWorld extends GameDriver {
    private int verifier = 0;
    private int wave = 1;
    private int time = 0;
    private Base homeBase = new Base();
    private Timeline gameLoop;
    private int towerID = 0;
    private int towerNum = 0;
    private double shortX;
    private double shortY;
    private double dist;
    /**
     * Constructs a TDWorld Object with the specified health, defense, and id.
     * @param fps the specified frames per second
     */
    public TDWorld(int fps) {
        super(fps, "Tower Defense");
    }
    /**
     * Overrides the method that initializes the stage
     * @param primaryStage input of the Stage
     */
    @Override
    public void initialize(final Stage primaryStage) {
        primaryStage.setTitle(getWindowTitle());
        Image background = new Image("background.jpg");
        ImageView backgroundPic = new ImageView();
        backgroundPic.setImage(background);
        setSceneNodes(new Group());
        setGameSurface(new Scene(getSceneNodes(), 690, 480));
        getSceneNodes().getChildren().add(backgroundPic);
        Image basePic = new Image("base.png");
        ImageView baseImage = new ImageView();
        baseImage.setImage(basePic);
        baseImage.setTranslateX(250);
        baseImage.setTranslateY(-40);
        getSceneNodes().getChildren().add(baseImage);
        primaryStage.setScene(getSurfaceScene());
        setupInput(primaryStage);
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.getKeyFrames().add(
                new KeyFrame(Duration.seconds(.01),
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        if (wave == 1) {
                            waveOne();
                        }
                        else if (wave == 2) {
                            waveTwo();
                        }
                    }
                }));
        setGameLoop(gameLoop);
    }
    /**
     * fires the Tower turrets and damages the tanks
     */
    public void fireTowers() {
        ArrayList<Tower> towers = gameManager.getCurrentTowers();
        ArrayList<Tank> tanks = gameManager.getTanks();
        int counter = 0;
        double towerX;
        double towerY;
        double tankX = 0;
        double tankY = 0;
        double newDist;
        Tank target = new Tank(100, 0, 1);
        if (!(towers.isEmpty())) {
            for (int i = 0; i < towers.size() - 1; i++) {
                Point2D posTower = towers.get(i).getImage().localToScene(0, 0);
                ArtilleryShell shell = new ArtilleryShell();
                towerX = posTower.getX();
                towerY = posTower.getY();
                shell.getShellImage().setTranslateX(towerX);
                shell.getShellImage().setTranslateY(towerY);
                getSceneNodes().getChildren().add(shell
                    .getShellImage());
                for (int j = 0; i < tanks.size() - 1; i++) {
                    Point2D posTank = tanks.get(i).getImage()
                        .localToScene(0, 0);
                    tankX = posTank.getX();
                    tankY = posTank.getY();
                    if (j == 0) {
                        shortX = Math.abs(tankX - towerX);
                        shortY = Math.abs(tankY - towerY);
                        dist = Math.sqrt((shortX * shortX)
                            + (shortY * shortY));
                    } else {
                        shortX = Math.abs(tankX - towerX);
                        shortY = Math.abs(tankY - towerY);
                        newDist = Math.sqrt((shortX * shortX)
                            + (shortY * shortY));
                        if (newDist < dist) {
                            target = tanks.get(i);
                            dist = newDist;
                        }
                        System.out.println(dist);
                    }
                }
                if (dist < 40) {
                    towers.get(i).attack(target);
                    if (target.isDead()) {
                        gameManager.removeTank(target);
                    }
                    shell.getShellImage().setTranslateX(tankX);
                    shell.getShellImage().setTranslateY(tankY);
                }
            }
        }
    }
    /**
     * creates the first wave
     */
    public void waveOne() {
        time++;
        Tank tankOne = new Tank(100, 3, 1);
        Tank tankTwo = new Tank(100, 3, 2);
        Tank tankThree = new Tank(100, 3, 3);
        ArrayList<Tank> list = gameManager.getTanks();
        int ind = 0;
        if (verifier == 0) {
            tankOne.getImage().localToScene(0, 0);
            tankTwp.getImage().localToScene(0, 0);
            tankThree.getImage().localToScene(0, 0);
            tankOne.getImage().setTranslateX(305);
            tankOne.getImage().setTranslateY(600);
            tankTwo.getImage().setTranslateX(305);
            tankTwo.getImage().setTranslateY(600);
            tankThree.getImage().setTranslateX(305);
            tankThree.getImage().setTranslateY(800);
            getSceneNodes().getChildren().add(tankOne.getImage());
            getSceneNodes().getChildren().add(tankTwo.getImage());
            getSceneNodes().getChildren().add(tankThree.getImage());
            gameManager.addTank(tankOne, tankTwo, tankThree);
            verifier++;
        }
        if (time % 100 == 0) {
            fireTowers();
        }
        if (!tankOne.isDead()) {
            if (time < 700) {
                tankOne.getImage().setTranslateY(500 - time / 2);
                //tankOne.getImage().setTranslateX(305);
                getSceneNodes().getChildren().add(tankOne.getImage());
                tankOne.getImage().localToScene(0, 0);
            } else {
                homeBase.hurt(tankOne.getAtkDamage());
            }
        }
        if (!tankTwo.isDead()) {
            if (time > 200 && time < 1100) {
                tankTwo.getImage().setTranslateY(700 - time / 2);
                tankTwo.getImage().setTranslateX(305);
                getSceneNodes().getChildren().add(tankTwo.getImage());
            } else if (time > 1100) {
                homeBase.hurt(tankTwo.getAtkDamage());
            }
        }
        if (!tankThree.isDead()) {
            if (time > 500 & time < 1460) {
                tankThree.getImage().setTranslateY(900 - time / 2);
                tankThree.getImage().setTranslateX(305);
                getSceneNodes().getChildren().add(tankThree.getImage());
            } else if (time > 1460) {
                homeBase.hurt(tankThree.getAtkDamage());
            }
        }
        if (list.isEmpty()) {
            wave++;
            gameManager.cleanUpTanks();
            time = 0;
        }
        if (!homeBase.isAlive()) {
            gameLoop.stop();
            getSceneNodes().getChildren().add(new Label("YOU LOST!!"
                + " GAME OVER"));
        }
    }
    /**
     * creates the second wave
     */
    public void waveTwo() {
        time++;
        Tank tankFour = new Tank(100, 5, 1);
        Tank tankFive = new Tank(100, 5, 2);
        Tank tankSix = new Tank(100, 5, 3);
        ArrayList<Tank> list = gameManager.getTanks();
        int ind = 0;
        if (verifier == 0) {
            tankFour.getImage().setTranslateX(305);
            tankFour.getImage().setTranslateY(600);
            tankFive.getImage().setTranslateX(305);
            tankFive.getImage().setTranslateY(600);
            tankSix.getImage().setTranslateX(305);
            tankSix.getImage().setTranslateY(800);
            getSceneNodes().getChildren().add(tankFour.getImage());
            getSceneNodes().getChildren().add(tankFive.getImage());
            getSceneNodes().getChildren().add(tankSix.getImage());
            gameManager.addTank(tankFour, tankFive, tankSix);
            verifier++;
        }
        if (time % 100 == 0) {
            fireTowers();
        }
        if (!tankFour.isDead()) {
            if (time < 700) {
                tankFour.getImage().setTranslateY(500 - time / 2);
                tankFour.getImage().setTranslateX(305);
                getSceneNodes().getChildren().add(tankFour.getImage());
                tankFour.getImage().localToScene(0, 0);
            } else {
                homeBase.hurt(tankFour.getAtkDamage());
            }
        }
        if (!tankFive.isDead()) {
            if (time > 200 && time < 1100) {
                tankFive.getImage().setTranslateY(700 - time / 2);
                tankFive.getImage().setTranslateX(305);
                getSceneNodes().getChildren().add(tankFive.getImage());
            } else if (time > 1100) {
                homeBase.hurt(tankFive.getAtkDamage());
            }
        }
        if (!tankSix.isDead()) {
            if (time > 400 && time < 1460) {
                tankSix.getImage().setTranslateY(900 - time / 2);
                tankSix.getImage().setTranslateX(305);
                getSceneNodes().getChildren().add(tankSix.getImage());
            } else if (time > 1460) {
                homeBase.hurt(tankSix.getAtkDamage());
            }
        }
        if (list.isEmpty()) {
            wave++;
            gameManager.cleanUpTanks();
        }
        if (!homeBase.isAlive()) {
            gameLoop.stop();
            getSceneNodes().getChildren().add(new Label("YOU LOST!!"
                + " GAME OVER"));
        }
    }
    /**
     * gets the input of the mouse click to place towers
     * @param primaryStage the stage to put the input in
     */
    private void setupInput(Stage primaryStage) {
        EventHandler place = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    Tower tower = new Tower(towerID);
                    gameManager.addTower(tower);
                    tower.getImage().setTranslateX(event.getX() - 20);
                    tower.getImage().setTranslateY(event.getY() - 20);
                    getSceneNodes().getChildren().add(tower.getImage());
                    towerID++;
                    towerNum++;
                }
            }
        };
        primaryStage.getScene().setOnMousePressed(place);
    }
}
