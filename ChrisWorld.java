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
public class ChrisWorld extends Application{
    private int time = 0;
    private Tank tankOne = new Tank(100, 3, 1);
    private Timeline gameLoop;
    private Group root;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        initialize(primaryStage);
        gameLoop.play();
        primaryStage.show();
    }
    public void initialize(final Stage primaryStage) {
        root = new Group();
        tankOne.getImage().localToScene(0, 0);
        Image backgroundImg = new Image("background.jpg");
        ImageView background = new ImageView();
        background.setImage(backgroundImg);
        root.getChildren().add(background);
        root.getChildren().add(tankOne.getImage());
        Scene mapScene = new Scene(root);
        primaryStage.setScene(mapScene);
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
    public void waveOne() {
        time++;
        System.out.println(time);
        tankOne.getImage().setTranslateY(0 + time);

    }
}
