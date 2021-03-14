package unsw.dungeon;

public class Sword extends Entity{

    private Dungeon dungeon;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Sword(Dungeon dungeon, int x, int y, int No) {
        super(x, y, No);
        this.dungeon = dungeon;
    }

}
