package test;

import unsw.dungeon.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class PickTreasureTest {

    @Test
    /*
    Pick only one treasure
     */
    public void test1_PickTreasure() {

        Dungeon dungeon = new Dungeon(3, 3);
        Treasure treasure = new Treasure(dungeon, 1, 1);
        dungeon.addEntity(treasure);

        Player player = new Player(dungeon,0,0);
        assertEquals(0,player.getTreasureCarry());
        dungeon.addEntity(player);
        dungeon.setPlayer(player);

        player.moveRight();
        player.moveDown();
        assertEquals(1,player.getTreasureCarry());
    }

    @Test
    /*
    Pick two treasures
     */
    public void test2_pickTreasureTwice() {
        Dungeon dungeon = new Dungeon(3, 3);
        Treasure treasure1 = new Treasure(dungeon, 1, 1);
        Treasure treasure2 = new Treasure(dungeon, 2, 1);
        dungeon.addEntity(treasure1);
        dungeon.addEntity(treasure2);

        Player player = new Player(dungeon,0,0);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);

        player.moveRight();
        player.moveDown();
        assertEquals(1,player.getTreasureCarry());
        player.moveRight();
        assertEquals(2,player.getTreasureCarry());

    }


}
