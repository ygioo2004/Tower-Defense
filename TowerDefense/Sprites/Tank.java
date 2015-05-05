package TowerDefense.Sprites;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * Represents a Tank
 *
 * @author Victor Law
 * @version 1.0
 */
public class Tank {
    private int defense;
    private Image tank;
    private ImageView tankImage;
    private int imageX;
    private int imageY;
    private int atkDamage;
    private int health;
    private int vertSpeed;
    private int id;
    /**
     * Constructs a Tank Object with the specified health, defense, and id.
     * @param health the health capacity of the tank
     * @param defense defense of the tank
     * @param id the ID of the tank
     */
    public Tank(int health, int defense, int id) {
        tank = new Image("tank.png");
        tankImage = new ImageView();
        tankImage.setImage(tank);
        imageX = 0;
        imageY = 0;
        atkDamage = 1;
        vertSpeed = 10;
        this.id = id;
        this.health = health;
        this.defense = defense;
    }
    /**
     * gets the current health of the tank
     * @return the health of tank
     */
    public int getHealth() {
        return health;
    }
    /**
     * gets the ID of the tank
     * @return the ID of the tank
     */
    public int getID() {
        return id;
    }
    /**
     * this does damage to the tank
     * @param damage the amount dealt to the tank
     */
    public void hurt(int damage) {
        if (damage >= defense && damage < health) {
            health = health - (damage - defense);
        } else if (damage < 0) {
            health = health - damage;
        }
        if (health - damage <= 0) {
            health = 0;
        }
    }
    /**
     * this gets the ImageView of the tank
     * @return the ImageView of the tank object
     */
    public ImageView getImage() {
        return tankImage;
    }
    /**
     * gets the Attack amount of the tank
     * @return the amount of damage the tank can deal
     */
    public int getAtkDamage() {
        return atkDamage;
    }
    /**
     * checks to see if the tank is dead or not
     * @return whether or not the tank is dead or not
     */
    public boolean isDead() {
        if (health <= 0) {
            return true;
        }
        return false;
    }
    /**
     * Overrides the equals method to see if two tanks are equal
     * @return whether two tanks are equal to each other
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Tank)) {
            return false;
        }
        Tank that = (Tank) other;
        return that.id == that.id;
    }
    /**
     * overrides the hashCode
     * @return a generated hashCode
     */
    @Override
    public int hashCode() {
        int result = 19;
        return result * 31 + id;
    }
}
