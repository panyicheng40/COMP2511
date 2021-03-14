package unsw.dungeon;

public class Key extends Entity{

    private Dungeon dungeon;
    private int uniqueID;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Key(Dungeon dungeon, int x, int y, int uniqueID, int No) {
        super(x, y, No);
        this.dungeon = dungeon;
        this.uniqueID = uniqueID;
    }
    public int getUniqueID(){
        return uniqueID;
    }
}
