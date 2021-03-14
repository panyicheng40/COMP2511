package unsw.dungeon;

public class Door extends Entity{
    private int uniqueID;
    private Dungeon dungeon;

    /**
     * 
     * @param dungeon
     * @param x
     * @param y
     * @param uniqueID
     */
    public Door(Dungeon dungeon, int x, int y, int uniqueID, int No) {
        super(x, y, No);
        this.dungeon = dungeon;
        this.uniqueID = uniqueID;
    }

    /**
     * 
     */
    public int getUniqueID(){
        return uniqueID;
    }

}
