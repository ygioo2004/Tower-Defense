package TowerDefense.Sprites;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * Represents an ArtilleryShell
 *
 * @author Victor Law
 * @version 1.0
 */
public class ArtilleryShell {
    private ImageView shellImage;
    /**
     * Constructs an ArtilleryShell
     */
    public ArtilleryShell() {
        Image base = new Image("bomb.gif");
        shellImage = new ImageView();
        shellImage.setImage(base);
    }
    /**
     * Gets the imageview of the ArtilleryShell object
     * @return the ImageView of this ArtilleryShell
     */
    public ImageView getShellImage() {
        return shellImage;
    }
}
