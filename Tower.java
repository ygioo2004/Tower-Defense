import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * Represents the tower class.
 *
 * @author Victor Law
 * @version 1.0
 */
public class Tower {
    private int atkdmg;
    private int range;
    private ImageView image;
    /**
     * Constructs a Tower Object.
     * @param id the id of the tower
     */
    public Tower() {
        atkdmg = 40;
        range = 150;
        Image pic = new Image("launcher.png");
        image = new ImageView();
        image.setImage(pic);
    }
    /**
     * This method lets the tower attack a certain enemy
     * @param enemy the enemy to be attacked
     */
    public void attack(ChrisEnemy enemy) {
        //enemy.hurt(atkdmg);
    }
    /**
     * gets the image of the tower object
     * @return the ImageView of this object
     */
    public ImageView getImage() {
        return image;
    }
}
