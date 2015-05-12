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

public class BurgerWorld extends Application {
    private int time = 0;
    private Tank tankOne = new Tank(100, 3, 1);
    private Timeline gameLoop;
    private Group root;
    private Stage stage;
    private Button playButton;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        initialize(primaryStage);
        stage.show();
        if (playButton.isArmed()) {
            gameLoop.play();
            System.out.println(playButton.isArmed());
        }
        System.out.println(playButton.isArmed());

    }
    public void initialize(final Stage primaryStage) {
        primaryStage.setScene(this.titleScreen());
        System.out.println(playButton.isArmed());
        //setupInput(primaryStage);

        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.getKeyFrames().add(
                new KeyFrame(Duration.seconds(.01),
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        //if (wave == 1) {
                            waveOne();
                        //}
                        //else if (wave == 2) {
                            //waveTwo();
                        //}
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
        playButton.setOnAction(e -> stage.setScene(this.levelMapScene()));
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
        Scene mapScene = new Scene(root);
        return mapScene;
    }

    public void waveOne() {
        time++;
        System.out.println(time);
        tankOne.getImage().setTranslateY(0 + time);

    }
}
