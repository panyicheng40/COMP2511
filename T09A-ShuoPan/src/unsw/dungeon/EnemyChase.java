package unsw.dungeon;

public class EnemyChase {
    private Dungeon dungeon;
    private Enemy enemy;
    private EnemyMove enemyMove;

    /**
     * 
     * @param enemy the enemy to take action
     * @param enemyMove control the enemy move
     * @param dungeon the background dungeon
     */
    public EnemyChase(Enemy enemy, EnemyMove enemyMove, Dungeon dungeon) {
        this.dungeon = dungeon;
        this.enemy = enemy;
        this.enemyMove = enemyMove;
    }

    public void move(int x, int y, int ex, int ey) {
        if ( Math.abs(ex - x) > Math.abs(ey - y)) {
            if ((ex - x) == 0 && enemy.doNotTouch(dungeon, ex - 1, ey))
                enemyMove.moveLeft();
            if ((ex - x) == 0 && enemy.doNotTouch(dungeon, ex + 1, ey))
                enemyMove.moveRight();
            else if ((ex - x) > 0 && enemy.doNotTouch(dungeon, ex - 1, ey))
                enemyMove.moveLeft();
            else if ((ex - x) < 0 && enemy.doNotTouch(dungeon, ex + 1, ey))
                enemyMove.moveRight();
            else if ((ey - y) > 0 && enemy.doNotTouch(dungeon, ex, ey - 1))
                enemyMove.moveUp();
            else if ((ey - y) < 0 && enemy.doNotTouch(dungeon, ex, ey + 1))
                enemyMove.moveDown();
        }
        else {
            if ((ey - y) == 0 && enemy.doNotTouch(dungeon, ex, ey - 1))
                enemyMove.moveDown();
            if ((ey - y) == 0 && enemy.doNotTouch(dungeon, ex, ey + 1))
                enemyMove.moveUp();
            else if ((ey - y) > 0 && enemy.doNotTouch(dungeon, ex, ey - 1))
                enemyMove.moveUp();
            else if ((ey - y) < 0 && enemy.doNotTouch(dungeon, ex, ey + 1))
                enemyMove.moveDown();
            else if ((ex - x) > 0 && enemy.doNotTouch(dungeon, ex + 1, ey))
                enemyMove.moveRight();
            else if ((ex - x) < 0 && enemy.doNotTouch(dungeon, ex - 1, ey))
                enemyMove.moveLeft();
        }
    }
}