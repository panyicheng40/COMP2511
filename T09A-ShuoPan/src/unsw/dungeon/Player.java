package unsw.dungeon;

import javafx.scene.image.Image;
import java.io.File;
import java.util.Random;
//import sun.rmi.transport.ObjectTable;

/**
 * The player entity
 * @author T09A
 *
 */
public class Player extends Entity {
    private Dungeon dungeon;
    private int keyCarry;
    private int treasureCarry;
    private int swordCapable;
    private int invistime;
    private boolean alive;

    /**
     * Create an player positioned in square (x,y)
     *
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon,int x, int y, int No) {
        super(x, y, No);
        this.dungeon = dungeon;
        this.keyCarry = -1;
        this.treasureCarry = 0;
        this.swordCapable = 0;
        this.invistime = 0;
        this.alive = true;
    }


    public int getKeyCarry() {
        return keyCarry;
    }

    public void setKeyCarry(int keyCarry) {
        this.keyCarry = keyCarry;
    }

    public int getTreasureCarry() {
        return treasureCarry;
    }

    public void setTreasureCarry(int treasureCarry) {
        this.treasureCarry = treasureCarry;
    }

    public int getSwordCapable() {
        return swordCapable;
    }

    public void setSwordCapable(int swordCapable) {
        this.swordCapable = swordCapable;
    }

    public int getInvistime() {
        return invistime;
    }

    public void setInvistime(int invistime) {
        this.invistime = invistime;
    }

    public boolean isAlive() {
        return alive;
    }

    /**
     * every round the invisible time should minus one
     */
    public void reduceInvistime() {
        if (invistime > 0) {
            setInvistime(invistime - 1);
            // System.out.println("InvistTime: " + getInvistime());
        }
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

   /**
     * use for start enemy move
     */
    public void callEnemy() {
        if (dungeon.getStop() == 0) {
            for (Enemy enemy : dungeon.getEnemy()) {
                enemy.move(dungeon.getPlayer().getX(), dungeon.getPlayer().getY());
            }
        }
        else dungeon.reduceStop();
    }

    /**
     * use for start hound move
     */
    public void callHound() {
        if (dungeon.getStop() == 0) {
            for (Hound hound : dungeon.getHound()) {
                hound.move(dungeon.getPlayer().getX(), dungeon.getPlayer().getY());
            }
        }
        else dungeon.reduceStop();
    }

    /**
     * use for start hound move
     */
    public void callGnome() {
        if (dungeon.getStop() == 0) {
            for (Gnome gnome : dungeon.getGnome()) {
                gnome.move();
            }
        }
        else dungeon.reduceStop();
    }

    /**
     * check the statuses of enemy and player after fight
     */
    public void checkfight() {
        for (Enemy enemy : dungeon.getEnemy()) {
            enemy.updateCatch();
        }
        for (Hound hound : dungeon.getHound()) {
            hound.updateCatch();
        }
        for (Gnome gnome : dungeon.getGnome()) {
            gnome.updateCatch();
        }
    }


    public void moveUp() {
        if (isAlive() && getY() > 0 && CanBeReach(getX(), getY() - 1, getX(), getY() - 2)) {
            y().set(getY() - 1);
            checkfight();
        }
        // System.out.println(dungeon.checkComplete());
        // System.out.println("InvistTime: " + getInvistime());
    }

    public void moveDown() {
        if (isAlive() && getY() < dungeon.getHeight() - 1 && CanBeReach(getX(), getY() + 1, getX(), getY() + 2)) {
            y().set(getY() + 1);
            checkfight();
        }
        // System.out.println(dungeon.checkComplete());
        // System.out.println("InvistTime: " + getInvistime());
    }

    public void moveLeft() {
        if (isAlive() && getX() > 0 && CanBeReach(getX() - 1, getY(), getX() - 2, getY())) {
            x().set(getX() - 1);
            checkfight();
        }
        // System.out.println(dungeon.checkComplete());
        // System.out.println("InvistTime: " + getInvistime());
    }

    public void moveRight() {
        if (isAlive() && getX() < dungeon.getWidth() - 1 && CanBeReach(getX() + 1, getY(), getX() + 2, getY())) {
            x().set(getX() + 1);
            checkfight();
        }
        // System.out.println(dungeon.checkComplete());
        // System.out.println("InvistTime: " + getInvistime());
    }

    /**
     *
     * @param targetx target position x Coordinate
     * @param targety target position y Coordinate
     * @param checkx only useful when pushing boulder, it is the x coordinate of boulder is going to move.
     * @param checky only useful when pushing boulder, it is the y coordinate of boulder is going to move.
     * @return whether people can move
     */
    public boolean CanBeReach(int targetx, int targety, int checkx, int checky) {
        for (Entity entity : dungeon.getEntites()) {
            if (entity.getX() == targetx && entity.getY() == targety) {

                if (entity instanceof Wall) {
                    return false;
                }

                if (entity instanceof Door && getKeyCarry() != entity.getUniqueID()) {
                    return false;
                }

                if (entity instanceof Portal) {
                    for (Entity checkPortal : dungeon.getEntites()) {
                        if (checkPortal instanceof Portal && checkPortal.getUniqueID()==entity.getUniqueID()) {
                            if(checkPortal.getX()!=entity.getX()||checkPortal.getY()!=entity.getY()){
                                x().set(checkPortal.getX());
                                y().set(checkPortal.getY());
                            }

                        }
                    }
                    return false;
                }

                if (entity instanceof Exit) {
                    return true;
                    //exist goal complete
                }

                if (entity instanceof Treasure) {
                    dungeon.afterpick(targetx, targety);//Treasure disappear
                    setTreasureCarry(getTreasureCarry() + 1);
                    dungeon.changeImage(entity.getNo(), new Image((new File("")).toURI().toString()));
                    return true;
                }

                if (entity instanceof Key) {
                    if (getKeyCarry() == -1) {
                        dungeon.afterpick(targetx, targety);//key disappear
                        setKeyCarry(entity.getUniqueID());
                        dungeon.changeImage(entity.getNo(), new Image((new File("")).toURI().toString()));
                    }
                    return true;
                }

                if (entity instanceof Door && getKeyCarry() == entity.getUniqueID()) {
                    setKeyCarry(-1);
                    dungeon.afterpick(targetx,targety);
                    dungeon.changeImage(entity.getNo(), new Image((new File("images/open_door.png")).toURI().toString()));
                    return true;
                }

                if (entity instanceof Enemy) {
                    if (getSwordCapable() > 0) {
                        dungeon.afterpick(targetx, targety);
                        // write in Enemy.java
                        // setSwordCapable(getSwordCapable() - 1);
                        return true;
                    } else {
                        setAlive(false);
                        return true;//Player DDDDDDEAD!!!
                    }
                }

                if (entity instanceof Hound) {
                    if (getSwordCapable() > 0) {
                        dungeon.afterpick(targetx, targety);
                        // write in Enemy.java
                        // setSwordCapable(getSwordCapable() - 1);
                        return true;
                    } else {
                        setAlive(false);
                        return true;//Player DDDDDDEAD!!!
                    }
                }

                if (entity instanceof Gnome) {
                    dungeon.afterpick(targetx, targety);
                    return true;
                }

                if (entity instanceof Sword) {

                    dungeon.afterpick(targetx, targety);
                    setSwordCapable(5);//and sword disapper
                    dungeon.changeImage(entity.getNo(), new Image((new File("")).toURI().toString()));
                    return true;

                }

                if (entity instanceof Invincibilitypotion) {
                    dungeon.afterpick(targetx, targety);
                    setInvistime(getInvistime() + 5);//potion disappear
                    dungeon.changeImage(entity.getNo(), new Image((new File("")).toURI().toString()));
                    // System.out.println("InvistTime: " + getInvistime());
                    return true;
                }

                if (entity instanceof StopPotion) {
                    dungeon.afterpick(targetx, targety);
                    dungeon.setStop();//potion disappear
                    dungeon.changeImage(entity.getNo(), new Image((new File("")).toURI().toString()));
                    // System.out.println("InvistTime: " + getInvistime());
                    return true;
                }

                if (entity instanceof Boulder) {
                    if (checkx < 0 || checky < 0 || checkx > dungeon.getWidth() || checky > dungeon.getHeight()) {
                        return false;
                    }
                    for (Entity checkentity : dungeon.getEntites()) {
                        if (checkentity.getX() == checkx && checkentity.getY() == checky) {
                            if (checkentity instanceof Wall||checkentity instanceof Boulder||checkentity instanceof Door) {
                                return false;
                            }
                        }
                    }
                    for (Entity rock : dungeon.getEntites()) {
                        if (rock.getX() == targetx && rock.getY() == targety && rock instanceof Boulder) {
                            rock.setX(checkx);
                            rock.setY(checky);
                            break;
                        }
                    }
                }
            }
        }
        for (Entity entity1 : dungeon.getEntites()) {
            if (entity1 instanceof Floorswitch) {
                return true;
            }
        }
        return true;
    }

    public void teleport() {
        if (dungeon.getTeleport()) {
            Random rand = new Random();
            int x = rand.nextInt(dungeon.getWidth());
            int y = rand.nextInt(dungeon.getHeight());
            while (!doNotTouch(dungeon, x, y)) {
                x = rand.nextInt(dungeon.getWidth());
                y = rand.nextInt(dungeon.getHeight());
            }
            setX(x);
            setY(y);
            dungeon.changeTeleport();
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
            }
        }
        return true;
    }
}