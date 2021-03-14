package test;

import unsw.dungeon.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class SwordTest {

    @Test
    public void pickUpOneSword() {

        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, 1, 1);
        dungeon.setPlayer(player);
        dungeon.addEntity(player);
        Sword sword = new Sword(dungeon, 1, 2);
        dungeon.addEntity(sword);

        // test whether player can pick up sword
        player.moveDown();
        assertEquals(player.getSwordCapable(), 5);

    }

    @Test
    public void pickUpTwoSword() {

        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, 1, 0);
        dungeon.setPlayer(player);
        dungeon.addEntity(player);
        Sword sword1 = new Sword(dungeon, 1, 1);
        dungeon.addEntity(sword1);
        Enemy enemy= new Enemy(dungeon, 1, 2);
        dungeon.addEnemy(enemy);
        dungeon.addEntity(enemy);
        Sword sword2 = new Sword(dungeon, 1, 3);
        dungeon.addEntity(sword2);

        // test whether player can pick up two swords
        player.moveDown();
        assertEquals(player.getSwordCapable(), 5);
        player.moveDown();
        assertEquals(player.getSwordCapable(), 4);
        player.moveDown();
        assertEquals(player.getSwordCapable(), 4);
        assertEquals(sword2.getX(), 1);
        assertEquals(sword2.getY(), 3);
        
    }
    
}