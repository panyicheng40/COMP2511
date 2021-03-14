package test;

import unsw.dungeon.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class EnemyTest {

    @Test
    public void PlayerRushToEnemyWithoutSword() {

        Dungeon dungeon = new Dungeon(3, 2);
        Player player = new Player(dungeon, 1, 1);
        dungeon.setPlayer(player);
        dungeon.addEntity(player);
        Enemy enemy= new Enemy(dungeon, 2, 1);
        dungeon.addEnemy(enemy);
        dungeon.addEntity(enemy);

        // Test whether the Player without Sword touch Enemy will be killed
        player.moveRight();
        assertFalse(player.isAlive());
        assertTrue(enemy.isAlive());

    }

    @Test
    public void PlayerCatchedByEnemyWithoutSword() {

        Dungeon dungeon = new Dungeon(3, 2);
        Player player = new Player(dungeon, 1, 1);
        dungeon.setPlayer(player);
        dungeon.addEntity(player);
        Enemy enemy= new Enemy(dungeon, 2, 1);
        dungeon.addEnemy(enemy);
        dungeon.addEntity(enemy);
        EnemyMove enemyMove = new EnemyMove(enemy, dungeon);

        // Test whether the Player without Sword catched by Enemy will be killed
        enemyMove.moveLeft();
        assertFalse(player.isAlive());
        assertTrue(enemy.isAlive()); 

    }

    @Test
    public void PlayerRushToEnemyWithSword() {

        Dungeon dungeon = new Dungeon(3, 2);
        Player player = new Player(dungeon, 1, 1);
        dungeon.setPlayer(player);
        dungeon.addEntity(player);
        Enemy enemy= new Enemy(dungeon, 2, 1);
        dungeon.addEnemy(enemy);
        dungeon.addEntity(enemy);
        player.setSwordCapable(5);

        // Test whether the Player with Sword can kill the enemy
        player.moveRight();
        assertTrue(player.isAlive());
        assertFalse(enemy.isAlive());
        assertEquals(player.getSwordCapable(), 4); 

    }

    @Test
    public void PlayerCatchedByEnemyWithSword() {

        Dungeon dungeon = new Dungeon(3, 2);
        Player player = new Player(dungeon, 1, 1);
        dungeon.setPlayer(player);
        dungeon.addEntity(player);
        Enemy enemy= new Enemy(dungeon, 2, 1);
        dungeon.addEnemy(enemy);
        dungeon.addEntity(enemy);
        player.setSwordCapable(5);
        EnemyMove enemyMove = new EnemyMove(enemy, dungeon);

        // Test whether the Player with Sword can rekill the enemy
        enemyMove.moveLeft();
        assertTrue(player.isAlive());
        assertFalse(enemy.isAlive()); 
        assertEquals(player.getSwordCapable(), 4); 

    }
    
}