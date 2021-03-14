package test;

import unsw.dungeon.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class PushBolderTest {

    @Test
    /*
    player can push the boulder successfully
     */
    public void test1_push_boulderSucc() {

        Dungeon dungeon = new Dungeon(10, 10);
        Boulder boulder1 = new Boulder(dungeon, 1, 1);
        dungeon.addEntity(boulder1);
        Player player = new Player(dungeon,0,0);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);
        player.moveRight();
        player.moveDown();
        assertEquals(1,player.getX());
        assertEquals(1,player.getY());
        assertEquals(1,boulder1.getX());
        assertEquals(2,boulder1.getY());
    }

    @Test
    /*
    player can not push double boudlers
     */
    public void test2_push_doubleBoudlers() {

        Dungeon dungeon = new Dungeon(10, 10);
        Boulder boulder1 = new Boulder(dungeon, 1, 1);
        Boulder boulder2 = new Boulder(dungeon, 1, 2);
        dungeon.addEntity(boulder1);
        dungeon.addEntity(boulder2);
        Player player = new Player(dungeon,0,0);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);
        player.moveRight();
        player.moveDown();
        assertEquals(1,player.getX());
        assertEquals(0,player.getY());
        assertEquals(1,boulder1.getX());
        assertEquals(1,boulder1.getY());
        assertEquals(1,boulder2.getX());
        assertEquals(2,boulder2.getY());

    }
    @Test
    /*
    player can not push boudler to wall
     */
    public void test3_push_doubletowall() {

        Dungeon dungeon = new Dungeon(10, 10);
        Boulder boulder1 = new Boulder(dungeon, 1, 1);
        Wall wall = new Wall(1, 2);
        dungeon.addEntity(boulder1);
        dungeon.addEntity(wall);
        Player player = new Player(dungeon,0,0);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);
        player.moveRight();
        player.moveDown();
        assertEquals(1,player.getX());
        assertEquals(0,player.getY());
        assertEquals(1,boulder1.getX());
        assertEquals(1,boulder1.getY());
        assertEquals(1,wall.getX());
        assertEquals(2,wall.getY());

    }
    @Test
    /*
    player can not push boudler to closeddoor
     */
    public void test4_push_doubletoClosedDoor() {

        Dungeon dungeon = new Dungeon(10, 10);
        Boulder boulder1 = new Boulder(dungeon, 1, 1);
        Door door = new Door(dungeon,1, 2,0);
        dungeon.addEntity(boulder1);
        dungeon.addEntity(door);
        Player player = new Player(dungeon,0,0);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);
        player.moveRight();
        player.moveDown();
        assertEquals(1,player.getX());
        assertEquals(0,player.getY());
        assertEquals(1,boulder1.getX());
        assertEquals(1,boulder1.getY());
        assertEquals(1,door.getX());
        assertEquals(2,door.getY());

    }
    @Test
    /*
    player can not push boudler to open door
     */
    public void test5_push_doubletoOpenDoor() {

        Dungeon dungeon = new Dungeon(10, 10);
        Key key = new Key(dungeon, 1, 0, 0);
        Boulder boulder1 = new Boulder(dungeon, 1, 1);
        Door door = new Door(dungeon,1, 2,0);
        dungeon.addEntity(key);
        dungeon.addEntity(boulder1);
        dungeon.addEntity(door);
        Player player = new Player(dungeon,0,0);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);
        player.moveRight();
        player.moveRight();
        player.moveDown();
        player.moveDown();
        player.moveLeft();
        player.moveLeft();
        player.moveUp();
        player.moveUp();
        player.moveRight();
        player.moveDown();
        assertEquals(1,player.getX());
        assertEquals(1,player.getY());
        assertEquals(1,boulder1.getX());
        assertEquals(2,boulder1.getY());

    }
}
