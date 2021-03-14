package test;

import unsw.dungeon.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class FloorSwitchTest {
    @Test
    /**
     * player can get the floorswitch
     */
    public void test1_arriveSwitch() {

        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, 0, 0);
        Floorswitch floorswitch = new Floorswitch(dungeon, 1, 1);
        dungeon.addEntity(floorswitch);

        dungeon.addEntity(player);
        dungeon.setPlayer(player);

        player.moveRight();
        player.moveDown();
        assertEquals(1, player.getX());
        assertEquals(1, player.getY());
    }
}
