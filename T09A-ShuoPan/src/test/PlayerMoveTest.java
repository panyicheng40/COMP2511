package test;

import unsw.dungeon.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerMoveTest {

    @Test
    public void testKey() {

        Dungeon dungeon = new Dungeon(10, 10);
        Player player = new Player(dungeon, 5, 5);
        dungeon.setPlayer(player);
        dungeon.addEntity(player);
        
        // Test Player move up
        player.moveUp();
        assertEquals(player.getX(), 5);
        assertEquals(player.getY(), 4);

        // Test Player move down
        player.moveDown();
        assertEquals(player.getX(), 5);
        assertEquals(player.getY(), 5);

        // Test Player move left
        player.moveLeft();
        assertEquals(player.getX(), 4);
        assertEquals(player.getY(), 5);

        // Test Player move right
        player.moveRight();
        assertEquals(player.getX(), 5);
        assertEquals(player.getY(), 5);

    }

    @Test
    public void testHitWall() {

        Dungeon dungeon = new Dungeon(2, 2);
        Player player = new Player(dungeon, 1, 1);
        dungeon.setPlayer(player);
        dungeon.addEntity(player);

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (x != 1 || y != 1) {
                    Wall wall = new Wall(x, y);
                    dungeon.addEntity(wall);
                }
            }
        }

        // Test whether the Player can go through the wall
        player.moveUp();
        assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 1);
        player.moveDown();
        assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 1);
        player.moveLeft();
        assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 1);
        player.moveRight();
        assertEquals(player.getX(), 1);
        assertEquals(player.getY(), 1);

    }

}