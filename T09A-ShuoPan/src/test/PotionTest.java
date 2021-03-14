package test;

import unsw.dungeon.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class PotionTest {
    @Test
    /*
    test potion left time
     */
    public void test1_exitGoal() {
        Dungeon dungeon = new Dungeon(3, 3);
        Invincibilitypotion invincibilitypotion = new Invincibilitypotion(dungeon,1,0);
        Player player = new Player(dungeon, 0, 0);
        Enemy enemy = new Enemy(dungeon,3,3);
        dungeon.addEntity(invincibilitypotion);
        dungeon.addEntity(enemy);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);
        player.moveRight();
        player.moveLeft();
        player.reduceInvistime();
        player.moveLeft();
        player.reduceInvistime();
        player.moveLeft();
        player.reduceInvistime();
        assertEquals(2, player.getInvistime());
    }
}
