package unsw.dungeon;

import java.util.Random;

public class Gnome extends Entity{

    private Dungeon dungeon;
    private int round;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Gnome(Dungeon dungeon, int x, int y, int No) {
        super(x, y, No);
        this.dungeon = dungeon;
        round = 0;
    }

	public void move() {
        if (round == 10) {
            Random rand = new Random();
            int x = rand.nextInt(dungeon.getWidth());
            int y = rand.nextInt(dungeon.getHeight());
            while (!doNotTouch(dungeon, x, y)) {
                x = rand.nextInt(dungeon.getWidth());
                y = rand.nextInt(dungeon.getHeight());
            }
            setX(x);
            setY(y);
            round = 0;
        }
        else round++;
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
                if (entity instanceof Player)
                    return false;
                if (entity instanceof Gnome)
                    return false;
                if (entity instanceof Key)
                    return false;
                if (entity instanceof Treasure)
                    return false;
                if (entity instanceof Floorswitch)
                    return false;
                if (entity instanceof Sword)
                    return false;
                if (entity instanceof Exit)
                    return false;
                if (entity instanceof Portal)
                    return false;
                if (entity instanceof StopPotion)
                    return false;
            }
        }
        return true;
    }

    public void updateCatch() {
        if (dungeon.getPlayer().getX() == getX() && dungeon.getPlayer().getY() == getY()) {
                    dungeon.removeGnome(this);
        }
    }
    
}