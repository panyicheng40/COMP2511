package unsw.dungeon;

public class Boulder extends Entity{

    private Dungeon dungeon;

    /**
     *
     * @param dungeon
     * @param x
     * @param y
     */
    public Boulder(Dungeon dungeon, int x, int y, int No) {
        super(x, y, No);
        this.dungeon = dungeon;
    }
}
