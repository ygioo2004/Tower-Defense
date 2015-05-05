package TowerDefense.World;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
/**
 * Represents a Game
 *
 * @author Victor Law
 * @version 1.0
 */
public class Game {
    private final ArrayList<Tank> tankList = new ArrayList<>();
    private final ArrayList<Tower> towerList = new ArrayList<>();
    private final Set cleanUpTank = new HashSet<>();
    /**
     * gets the arraylist of tanks in the game
     * @return arraylist of tanks
     */
    public ArrayList<Tank> getTanks() {
        return tankList;
    }
    /**
     * adds a tower to the tower list
     * @param towers takes in a variable argument of towers
     */
    public void addTower(Tower... towers) {
        towerList.addAll(Arrays.asList(towers));
    }
    /**
     * adds a tank to the tank list
     * @param tanks a variable argument of tanks to take it
     */
    public void addTank(Tank... tanks) {
        tankList.addAll(Arrays.asList(tanks));
    }
    /**
     * remove tanks from the tank list
     * @param tanks a variable argument of tanks to remove from the tank list
     */
    public void removeTank(Tank... tanks) {
        tankList.removeAll(Arrays.asList(tanks));
    }
    /**
     * gets the removed set of tanks
     * @return a set of the tanks that were removed
     */
    public Set getRemovedTanks() {
        return cleanUpTank;
    }
    /**
     * makes sure the tanklist has removed the tanks that is supposed to be
     * removed
     */
    public void cleanUpTanks() {
        tankList.removeAll(cleanUpTank);
        cleanUpTank.clear();
    }
    /**
     * gets the current list of towers
     * @return arraylist of towers
     */
    public ArrayList<Tower> getCurrentTowers() {
        return towerList;
    }
}
