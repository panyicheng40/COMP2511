package unsw.dungeon;

public class Floorswitch extends Entity {
    private Dungeon dungeon;

    public Floorswitch(Dungeon dungeon, int x, int y, int No) {
        super(x, y, No);
        this.dungeon = dungeon;
    }
}
