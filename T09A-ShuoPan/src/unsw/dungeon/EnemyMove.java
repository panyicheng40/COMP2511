package unsw.dungeon;

public class EnemyMove{
    private Dungeon dungeon;
    private Enemy enemy;
    
    /**
     * 
     * @param enemy the enemy to take action
     * @param dungeon the background dungeon
     */
    public EnemyMove(Enemy enemy, Dungeon dungeon) {
        this.enemy = enemy;
        this.dungeon = dungeon;
    }

    public void moveUp() {
        if (enemy.getY() > 0) {
            enemy.setY(enemy.getY() - 1);
        }
        enemy.updateCatch();
    }

    public void moveDown() {
        if (enemy.getY() < dungeon.getHeight() - 1) {
            enemy.setY(enemy.getY() + 1);
        }
        enemy.updateCatch();
    }

    public void moveLeft() {
        if (enemy.getX() > 0) {
            enemy.setX(enemy.getX() - 1);
        }
        enemy.updateCatch();
    }

    public void moveRight() {
        if (enemy.getX() < dungeon.getWidth() - 1) {
            enemy.setX(enemy.getX() + 1);
        }
        enemy.updateCatch();
    }
}