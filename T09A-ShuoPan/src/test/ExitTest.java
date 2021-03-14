package test;

import unsw.dungeon.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExitTest {
    @Test
    /**
     * player can get the exit
     */
    public void test1_arriveExit() {

        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, 0, 0);
        Exit exit = new Exit(dungeon, 1, 1);
        dungeon.addEntity(exit);

        dungeon.addEntity(player);
        dungeon.setPlayer(player);
        player.moveRight();
        player.moveDown();
        assertEquals(1, player.getX());
        assertEquals(1, player.getY());
    }
}
