import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class BurgerController {

    private final ArrayList<ChrisEnemy> chrisList = new ArrayList<>();
    private final ArrayList<Tower> towerList = new ArrayList<>();
    private final Set cleanUpChris = new HashSet<>();
    /**
     * gets the arraylist of tanks in the game
     * @return arraylist of tanks
     */
    public ArrayList<Tank> getChris() {
        return chrisList;
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
    public void addChris(ChrisEnemy... chris) {
        chrisList.addAll(Arrays.asList(chris));
    }
    /**
     * remove tanks from the tank list
     * @param tanks a variable argument of tanks to remove from the tank list
     */
    public void removeTank(ChrisEnemy... chris) {
        chrisList.removeAll(Arrays.asList(chris));
    }
    /**
     * gets the removed set of tanks
     * @return a set of the tanks that were removed
     */
    public Set getRemovedChris() {
        return cleanUpChris;
    }
    /**
     * makes sure the tanklist has removed the tanks that is supposed to be
     * removed
     */
    public void cleanUpChris() {
        tankList.removeAll(cleanUpChris);
        cleanUpChris.clear();
    }
    /**
     * gets the current list of towers
     * @return arraylist of towers
     */
    public ArrayList<Tower> getCurrentTowers() {
        return towerList;
    }

}
