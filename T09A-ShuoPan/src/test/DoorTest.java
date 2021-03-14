package test;

import unsw.dungeon.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class DoorTest {
    @Test
    /**
     * touch the door without key
     */
    public void test1_touchDoorwithoutkey() {
        Dungeon dungeon = new Dungeon(3, 3);

        Player player = new Player(dungeon,0,0);
        Door door = new Door(dungeon,1,0,0);
        dungeon.addEntity(player);
        dungeon.addEntity(door);
        dungeon.setPlayer(player);

        player.moveRight();
        assertEquals(0,player.getX());
        assertEquals(0,player.getY());
    }
    @Test
    /**
     * touch the door with right key
     */
    public void test2_touchDoorwithRightKey() {
        Dungeon dungeon = new Dungeon(3, 3);

        Player player = new Player(dungeon,0,0);
        Key key = new Key(dungeon,1,0,0);
        Door door = new Door(dungeon,1,1,0);
        assertEquals(door.getUniqueID(),key.getUniqueID());
        dungeon.addEntity(player);
        dungeon.addEntity(door);
        dungeon.addEntity(key);
        dungeon.setPlayer(player);

        player.moveRight();
        player.moveDown();
        assertEquals(1,player.getX());
        assertEquals(1,player.getY());
    }
    @Test
    /**
     * touch the door with wrong key
     */
    public void test3_touchDoorWithWrongKey() {
        Dungeon dungeon = new Dungeon(3, 3);

        Player player = new Player(dungeon,0,0);
        Key key = new Key(dungeon,1,0,1);
        Door door = new Door(dungeon,1,1,0);
        dungeon.addEntity(player);
        dungeon.addEntity(door);
        dungeon.addEntity(key);
        dungeon.setPlayer(player);

        player.moveRight();
        player.moveDown();
        assertEquals(1,player.getX());
        assertEquals(0,player.getY());
    }
}
