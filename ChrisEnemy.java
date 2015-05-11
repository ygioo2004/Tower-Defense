import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class ChrisEnemy {
    private int hungerLevel;
    private int speed;
    private ImageView pic;
    public ChrisEnemy(int hungerLevel, int speed) {
        this.hungerLevel = hungerLevel;
        this.speed = speed;
        Image baseImage = new Image("Angry Chris.gif");
        pic = new ImageView();
        pic.setImage(baseImage);
    }

    public ImageView getImage() {
        return pic;
    }
    public int getHungerLevel() {
        return hungerLevel;
    }
    public int getSpeed() {
        return speed;
    }

}
