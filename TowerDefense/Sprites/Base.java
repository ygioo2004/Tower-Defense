package TowerDefense.Sprites;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * Represents a Base
 *
 * @author Victor Law
 * @version 1.0
 */
public class Base {
    private int health;
    private ImageView image;
    /**
     * Constructs a Base
     */
    public Base() {
        health = 1000;
        Image backImage = new Image("base.png");
        image = new ImageView();
        image.setImage(backImage);
    }
    /**
     * hurts the base by a certain amount
     * @param damage the amount to damage the base
     */
    public void hurt(int damage) {
        health -= damage;
    }
    /**
     * gets the ImageView of the base object
     * @return the ImageView of the base
     */
    public ImageView getImage() {
        return image;
    }
    /**
     * gets the current health of the base
     * @return the amount of health the base currently has
     */
    public int getHealth() {
        return health;
    }
    /**
     * this method shows whether or not the base is still alive
     * @return if the base is alive or not
     */
    public boolean isAlive() {
        if (health > 0) {
            return true;
        }
        return false;
    }
}
