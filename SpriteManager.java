import java.util.ArrayList;

public class SpriteManager {
    private ArrayList<ChrisEnemy> enemyList = new ArrayList<>();
    private ArrayList<Tower> towerList = new ArrayList<>();
    public void addChris(ChrisEnemy enemy) {
        enemyList.add(enemy);
    }

    public void removeChris(ChrisEnemy enemy) {
        enemyList.remove(enemy);
    }
}
