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
        //setupInput(primaryStage);

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
            list.get(0).setX(-100);
            list.get(1).setX(0);
            list.get(2).setX(100);
            root.getChildren().add(list.get(0).getImage());
            root.getChildren().add(list.get(1).getImage());
            root.getChildren().add(list.get(2).getImage());

            /*for (ChrisEnemy enemy: list) {
                System.out.println(spriteManage.getEnemyList().size());
                enemy.setX(start);
                enemy.setY(-100);
                start =+ spacing;
                root.getChildren().add(enemy.getImage());
            }
            for (int h = 0; h < list.size(); h++) {
                list.get(h).setX(start);
                list.get(h).setY(-100);
                start =- spacing;
                root.getChildren().add(list.get(h).getImage());
            }*/
        }
        Scene mapScene = new Scene(root);
        return mapScene;
    }

    public void waveOne() {
        time++;
        System.out.println(spriteManage.getEnemyList().size());
        ArrayList<ChrisEnemy> enemyList = spriteManage.getEnemyList();
        for (int j = 0; j < enemyList.size(); j++) {
            System.out.println(enemyList.get(j).getImage().getTranslateX());
            System.out.println(enemyList.get(j).getImage().getTranslateY());
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

        System.out.println(time);
    }
}
