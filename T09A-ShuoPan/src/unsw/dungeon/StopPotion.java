package unsw.dungeon;


public class StopPotion extends Entity {

    private Dungeon dungeon;

    /**
     * Create a player positioned in square (x,y)
     * 
     * @param x
     * @param y
     */
    public StopPotion(Dungeon dungeon, int x, int y, int No) {
        super(x, y, No);
        this.dungeon = dungeon;
    }

}
