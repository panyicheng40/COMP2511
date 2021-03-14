package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    protected int uniqueID;
    protected int No;

    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y, int No) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.uniqueID = -1;
        this.No = No;

    }

    /**
     * 
     * @return
     */
    public IntegerProperty x() {
        return x;
    }

    /**
     * 
     * @return
     */
    public IntegerProperty y() {
        return y;
    }

    /**
     * 
     * @return
     */
    public int getY() {
        return y().get();
    }

    /**
     * 
     * @return
     */
    public int getX() {
        return x().get();
    }

    public void setX(int x) {
        this.x.set(x);
    }

    public void setY(int y) {
        this.y.set(y);
    }

    /**
     * 
     * @return
     */
    public int getUniqueID(){
        return uniqueID;
    }

    /**
     * 
     * @return
     */
    public int getNo(){
        return No;
    }
    
}
