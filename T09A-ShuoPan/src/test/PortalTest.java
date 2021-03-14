package test;

import unsw.dungeon.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class PortalTest {
    @Test
    /**
    player can not get the portal if the portal not be paired
     */
    public void test1_PortalNotPair() {

        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon,0,0);

        Portal portal = new Portal(dungeon, 1, 1,0);
        dungeon.addEntity(portal);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);
        player.moveRight();
        player.moveDown();
        assertEquals(1,player.getX());
        assertEquals(0,player.getY());
    }

    @Test
    /**
    player can get the portal if the portal be paired
    */
    public void test2_PortalBePair() {

        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon,0,0);

        Portal portal1 = new Portal(dungeon, 1, 1,0);
        Portal portal2 = new Portal(dungeon, 2, 2,0);
        dungeon.addEntity(portal1);
        dungeon.addEntity(portal2);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);
        player.moveRight();
        player.moveDown();
        assertEquals(2,player.getX());
        assertEquals(2,player.getY());
    }
    @Test
    /*
    player can use different pairs of portals successfully
     */
    public void test3_4Portalsare2Pair() {

        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon,0,0);

        Portal portal1 = new Portal(dungeon, 1, 1,0);
        Portal portal2 = new Portal(dungeon, 2, 2,0);
        Portal portal4 = new Portal(dungeon, 3, 2,1);
        Portal portal3 = new Portal(dungeon, 4, 1,1);
        dungeon.addEntity(portal1);
        dungeon.addEntity(portal2);
        dungeon.addEntity(portal3);
        dungeon.addEntity(portal4);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);
        player.moveRight();
        player.moveDown();
        assertEquals(2,player.getX());
        assertEquals(2,player.getY());
        player.moveRight();
        assertEquals(4,player.getX());
        assertEquals(1,player.getY());
    }
}
