package unsw.dungeon;

public class Hound extends Entity {
    private Dungeon dungeon;
    private boolean alive;
    private int dir;

    /**
     *
     * @param dungeon the background dungeon
     * @param x the x site of hound
     * @param y the y site of hound
     */
    public Hound(Dungeon dungeon, int x, int y, int No) {
        super(x, y, No);
        this.dungeon = dungeon;
        alive = true;
        dir = 0;
    }

    public void updateCatch() {
        if (this.isAlive() == true) {
            if (dungeon.getPlayer().getX() == getX() && dungeon.getPlayer().getY() == getY()) {
                if(dungeon.getPlayer().getSwordCapable() > 0) {
                    this.setAlive(false);
                    dungeon.getPlayer().setSwordCapable(dungeon.getPlayer().getSwordCapable() - 1);
                    dungeon.removeHound(this);
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
                if (dir == 0) {
                    if (doNotTouch(dungeon, ex + 1, ey))
                        moveRight();
                    else dir = 1;
                }

                if (dir == 1) {
                    if (doNotTouch(dungeon, ex - 1, ey))
                        moveLeft();
                    else dir = 2;
                }

                if (dir == 2) {
                    if (doNotTouch(dungeon, ex, ey - 1))
                        moveUp();
                    else dir = 3;
                }

                if (dir == 3) {
                    if (doNotTouch(dungeon, ex, ey + 1))
                        moveDown();
                    else dir = 0;
                }
            }
            else if (dungeon.getPlayer().getInvistime() > 0) {
                // stop
            }
        }
    }

    public void moveUp() {
        if (this.getY() > 0) {
            this.setY(this.getY() - 1);
        }
        this.updateCatch();
    }

    public void moveDown() {
        if (this.getY() < dungeon.getHeight() - 1) {
            this.setY(this.getY() + 1);
        }
        this.updateCatch();
    }

    public void moveLeft() {
        if (this.getX() > 0) {
            this.setX(this.getX() - 1);
        }
        this.updateCatch();
    }

    public void moveRight() {
        if (this.getX() < dungeon.getWidth() - 1) {
            this.setX(this.getX() + 1);
        }
        this.updateCatch();
    }

}