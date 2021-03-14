package unsw.dungeon;

public class Enemy extends Entity {
    private Dungeon dungeon;
    private boolean alive;
    private EnemyMove enemyMove;
    private EnemyChase enemyChase;
    private EnemyEscape enemyEscape;

    /**
     *
     * @param dungeon the background dungeon
     * @param x the x site of enemy
     * @param y the y site of enemy
     */
    public Enemy(Dungeon dungeon, int x, int y, int No) {
        super(x, y, No);
        this.dungeon = dungeon;
        this.enemyMove = new EnemyMove(this, dungeon);
        this.enemyChase = new EnemyChase(this, enemyMove, dungeon);
        this.enemyEscape = new EnemyEscape(this, enemyMove, dungeon);
        alive = true;
    }

    public void updateCatch() {
        if (this.isAlive() == true) {
            if (dungeon.getPlayer().getX() == getX() && dungeon.getPlayer().getY() == getY()) {
                if(dungeon.getPlayer().getSwordCapable() > 0) {
                    this.setAlive(false);
                    dungeon.getPlayer().setSwordCapable(dungeon.getPlayer().getSwordCapable() - 1);
                    dungeon.removeEnemy(this);
                    // System.out.println("ENEMY DEAD");
                }
                else {
                    dungeon.getPlayer().setAlive(false);
                    // System.out.println("YOU DEAD");
                }
                // System.out.println("Enemy Alive: " + this.isAlive());
                // System.out.println("Player Alive: " + dungeon.getPlayer().isAlive());
            }
        }
    }

    /**
     *
     * @param dungeon the background dungeon
     * @param targetx the x site to be checked
     * @param targety the y site to be checked
     * @return
     */
    public boolean doNotTouch(Dungeon dungeon, int targetx, int targety) {
        for (Entity entity : dungeon.getEntites()) {
            if (entity.getX() == targetx && entity.getY() == targety) {
                // add here if need to create more another entities
                if (entity instanceof Wall)
                    return false;
                if (entity instanceof Exit)
                    return false;
                if (entity instanceof Door)
                    return false;
                if (entity instanceof Boulder)
                    return false;
                if (entity instanceof Enemy)
                    return false;
                if (entity instanceof Invincibilitypotion)
                    return false;
                if (entity instanceof Hound)
                    return false;
                if (entity instanceof Gnome)
                    return false;
                if (entity instanceof StopPotion)
                    return false;
            }
        }
        return true;
    }

    /**
     *
     * @return the state whether the player is alive
     */
    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void move(int x, int y) {
        if (dungeon.getPlayer().isAlive() && alive) {
            int ex = getX();
            int ey = getY();
            if (dungeon.getPlayer().getInvistime() == 0) {
                enemyChase.move(x, y, ex, ey);
            }
            else if (dungeon.getPlayer().getInvistime() > 0) {
                enemyEscape.move(x, y, ex, ey);
            }
        }
    }
}