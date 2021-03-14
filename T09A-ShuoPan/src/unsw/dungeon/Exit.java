package unsw.dungeon;

public class Exit extends Entity{

    private Dungeon dungeon;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Exit(Dungeon dungeon, int x, int y, int No) {
        super(x, y, No);
        this.dungeon = dungeon;
    }

}
