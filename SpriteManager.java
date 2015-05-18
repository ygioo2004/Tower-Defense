import java.util.ArrayList;

public class SpriteManager {
    private ArrayList<ChrisEnemy> enemyList = new ArrayList<>();
    private ArrayList<Tower> towerList = new ArrayList<>();
    public void addChris(int num) {
        for (int i = 0; i < num; i++) {
            enemyList.add(new ChrisEnemy(100, .5));
        }

    }

    public void removeChris(ChrisEnemy enemy) {
        if (enemyList.contains(enemy)) {
            enemyList.remove(enemy);
        }
    }

    public ArrayList<ChrisEnemy> getEnemyList() {
        return enemyList;
    }
}
