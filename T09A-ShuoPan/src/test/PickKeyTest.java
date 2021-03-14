package test;

import unsw.dungeon.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class PickKeyTest {

    @Test
    /*
    Pick only one key
     */
    public void test1_pickFirst() {
        Dungeon dungeon = new Dungeon(3, 3);
        Key key = new Key(dungeon, 1, 1, 0);
        dungeon.addEntity(key);

        Player player = new Player(dungeon,0,0);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);

        player.moveRight();
        player.moveDown();
        assertEquals(0,player.getKeyCarry());
    }

    @Test
    /*
    Pick two keys, the second key cannot pick
     */
    public void test2_pickTwice() {
        Dungeon dungeon = new Dungeon(3, 3);
        Key key1 = new Key(dungeon, 1, 1, 0);
        Key key2 = new Key(dungeon, 2, 2, 1);
        dungeon.addEntity(key1);
        dungeon.addEntity(key2);

        Player player = new Player(dungeon,0,0);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);

        player.moveRight();
        player.moveDown();
        assertEquals(0,player.getKeyCarry());
        player.moveRight();
        player.moveDown();
        assertEquals(0,player.getKeyCarry());
    }

}