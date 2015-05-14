import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class ChrisEnemy {
    private int hungerLevel;
    private int speed;
    private ImageView pic;

    public ChrisEnemy(int hungerLevel, int speed) {
        this.hungerLevel = hungerLevel;
        this.speed = speed;
        Image baseImage = new Image("Angry-Chris.gif");
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
    public void moveX() {
        pic.setTranslateX(pic.getTranslateX() + speed);
    }
    public void moveY() {
        pic.setTranslateY(pic.getTranslateY() + speed);
    }
    public void setX(int x) {
        pic.setTranslateX(x);
    }
    public void setY(int y) {
        pic.setTranslateY(y);
    }
    public boolean isDead() {
        if (hungerLevel <= 0) {
            return true;
        }
        return false;
    }
}
