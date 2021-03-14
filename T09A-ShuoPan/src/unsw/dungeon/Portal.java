package unsw.dungeon;

public class Portal extends Entity {
    private int uniqueID;
    private Dungeon dungeon;

    public Portal(Dungeon dungeon, int x, int y, int uniqueID, int No) {
        super(x, y, No);
        this.dungeon = dungeon;
        this.uniqueID = uniqueID;
    }
    public int getUniqueID(){
        return uniqueID;
    }
}
