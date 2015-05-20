import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.fxml.Initializable;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import java.util.ArrayList;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
public class BurgerWorld extends Application {
    private int time = 0;
    private ChrisEnemy chrisOne = new ChrisEnemy(100, 10);
    private Timeline gameLoop;
    private Group root;
    private Stage stage;
    private Button playButton;
    private boolean isButtonClicked = false;
    private int wave = 1;
    private SpriteManager spriteManage = new SpriteManager();
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        initialize(primaryStage);
        stage.show();
    }
    public void initialize(final Stage primaryStage) {
        primaryStage.setScene(this.titleScreen());

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
                            //waveTwo();
                        }
                    }
                }));
    }
    public Scene titleScreen() {
        Image backgroundImg = new Image("Title-Screen.png");
        ImageView background = new ImageView();
        background.setImage(backgroundImg);
        StackPane pane = new StackPane();
        pane.getChildren().add(background);
        playButton = new Button("Play Now!");
        playButton.setOnAction(e -> {
            stage.setScene(this.levelMapScene());
            setupInput(stage);
            gameLoop.play();
        });
        pane.getChildren().add(playButton);
        Scene titleScreen = new Scene(pane);
        return titleScreen;
    }

    public Scene levelMapScene() {
        root = new Group();
        Image backgroundImg = new Image("Level-One-Map.png");
        ImageView background = new ImageView();
        background.setImage(backgroundImg);
        root.getChildren().add(background);
        int spacing = 150;
        int start = -300;

        if (wave == 1) {
            spriteManage.addChris(3);
            ArrayList<ChrisEnemy> list = spriteManage.getEnemyList();
            for (ChrisEnemy enemy: list) {
                enemy.setX(start);
                enemy.setY(-100);
                start += spacing;
                root.getChildren().add(enemy.getImage());
            }
        }
        Scene mapScene = new Scene(root);
        return mapScene;
    }
    public void setupInput(Stage primaryStage) {
        EventHandler place = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (e.getButton() == MouseButton.PRIMARY) {
                    Tower tower = new BurgerTower();
                    tower.getImage().setTranslateX(e.getX() - 20);
                    tower.getImage().setTranslateY(e.getY() - 20);
                    root.getChildren().add(tower.getImage());
                }
            }
        };
        primaryStage.getScene().setOnMousePressed(place);
    }
    public void waveOne() {
        time++;
        ArrayList<ChrisEnemy> enemyList = spriteManage.getEnemyList();
        for (int j = 0; j < enemyList.size(); j++) {
        //    System.out.println(enemyList.get(j).getImage().getTranslateX());
        //    System.out.println(enemyList.get(j).getImage().getTranslateY());
            if (!enemyList.get(j).isDead() && enemyList.get(j).getImage()
                .getTranslateX() <= 165) {
                enemyList.get(j).moveX(true);
            } else if (!enemyList.get(j).isDead() && enemyList.get(j).getImage()
                .getTranslateY() <= 200 && enemyList.get(j).getImage()
                .getTranslateX() == 165.5) {
                enemyList.get(j).moveY(true);
            } else if (!enemyList.get(j).isDead() && enemyList.get(j).getImage()
                .getTranslateX() < 360) {
                enemyList.get(j).moveX(true);
            } else if (!enemyList.get(j).isDead() && enemyList.get(j).getImage()
                .getTranslateY() > -50) {
                enemyList.get(j).moveY(false);
            }
        }
    }
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
}
