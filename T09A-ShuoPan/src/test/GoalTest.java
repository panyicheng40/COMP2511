package test;

import unsw.dungeon.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class GoalTest {
    @Test
    /**
    player can achieve the goals about only "exit"
     */
    public void test1_exitGoal() {
        ExitGoal goal = new ExitGoal("exit",0);
        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, 0, 0);
        Goals goals = new Goals(dungeon);
        goals.addSubGoalsList(goal.getName(),goal.getLevel());
        dungeon.setGoals(goals);
        Exit exit = new Exit(dungeon, 1, 1);
        dungeon.addEntity(exit);

        dungeon.addEntity(player);
        dungeon.setPlayer(player);
        assertFalse(dungeon.checkComplete());
        player.moveRight();
        player.moveDown();
        assertEquals(1, player.getX());
        assertEquals(1, player.getY());
        assertTrue(dungeon.checkComplete());
    }

    @Test
    /**
    player can achieve the goals about only "boulder"
     */
    public void test2_boulderGoal() {
        BouldersGoals goal = new BouldersGoals("boulders",0);
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 0, 0);
        Goals goals = new Goals(dungeon);
        goals.addSubGoalsList(goal.getName(),goal.getLevel());
        dungeon.setGoals(goals);
        Boulder boulder1 = new Boulder(dungeon, 0, 1);
        Boulder boulder2 = new Boulder(dungeon, 1, 0);
        Floorswitch floorswitch1 = new Floorswitch(dungeon,2,0);
        Floorswitch floorswitch2 = new Floorswitch(dungeon,0,2);
        dungeon.addEntity(boulder1);
        dungeon.addEntity(boulder2);
        dungeon.addEntity(floorswitch1);
        dungeon.addEntity(floorswitch2);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);
        assertFalse(dungeon.checkComplete());
        player.moveRight();
        assertFalse(dungeon.checkComplete());
        player.moveLeft();
        player.moveDown();
        assertTrue(dungeon.checkComplete());
    }

    @Test
    /**
     player can achieve the goals about only "enemy"
     */
    public void test3_enemy() {
        EnemiesGoals goal = new EnemiesGoals("enemies",0);
        Dungeon dungeon = new Dungeon(0, 10);
        Player player = new Player(dungeon, 0, 0);
        Goals goals = new Goals(dungeon);
        goals.addSubGoalsList(goal.getName(),goal.getLevel());
        dungeon.setGoals(goals);
        Sword sword = new Sword(dungeon, 0, 1);
        Enemy enemy = new Enemy(dungeon,0,5);
        dungeon.addEntity(sword);
        dungeon.addEntity(enemy);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);
        assertFalse(dungeon.checkComplete());
        player.moveDown();
        enemy.move(player.getX(),player.getY());
        assertFalse(dungeon.checkComplete());
        player.moveDown();
        enemy.move(player.getX(),player.getY());
        assertFalse(dungeon.checkComplete());
        player.moveDown();
        enemy.move(player.getX(),player.getY());
        assertTrue(dungeon.checkComplete());
    }


    @Test
    /**
    player can achieve the goals about only "treasure"
     */
    public void test4_treasureGoal() {
        TreasureGoals goal = new TreasureGoals("treasure",0);
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 0, 0);
        Goals goals = new Goals(dungeon);
        goals.addSubGoalsList(goal.getName(),goal.getLevel());
        dungeon.setGoals(goals);
        Treasure treasure1 = new Treasure(dungeon,0,1);
        Treasure treasure2 = new Treasure(dungeon,0,2);
        Treasure treasure3 = new Treasure(dungeon,0,3);
        dungeon.addEntity(treasure1);
        dungeon.addEntity(treasure2);
        dungeon.addEntity(treasure3);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);
        assertFalse(dungeon.checkComplete());
        player.moveDown();
        assertFalse(dungeon.checkComplete());
        player.moveDown();
        assertFalse(dungeon.checkComplete());
        player.moveDown();
        assertTrue(dungeon.checkComplete());
    }

    @Test
    /**
     * player can achieve the goals "enemy" and "boulder" and "exit" and "treasure"
     */
    public void test5_allGoal() {
        TreasureGoals goal1 = new TreasureGoals("treasure",1);
        ExitGoal goal2 = new ExitGoal("exit",2);
        BouldersGoals goal3 = new BouldersGoals("boulders",3);
        EnemiesGoals goal4 = new EnemiesGoals("enemies",4);
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 0, 0);
        Goals goals = new Goals(dungeon);
        goals.addSubGoalsList(goal1.getName(),goal1.getLevel());
        goals.addSubGoalsList(goal2.getName(),goal2.getLevel());
        goals.addSubGoalsList(goal3.getName(),goal3.getLevel());
        goals.addSubGoalsList(goal4.getName(),goal4.getLevel());
        dungeon.setGoals(goals);
        Treasure treasure = new Treasure(dungeon,1,2);
        Boulder boulder = new Boulder(dungeon,1,1);
        Enemy enemy = new Enemy(dungeon,0,3);
        Sword sword = new Sword(dungeon,0,1);
        Exit exit = new Exit(dungeon,2,1);
        Floorswitch floorswitch = new Floorswitch(dungeon,1,0);
        dungeon.addEntity(floorswitch);
        dungeon.addEntity(exit);
        dungeon.addEntity(treasure);
        dungeon.addEntity(boulder);
        dungeon.addEntity(enemy);
        dungeon.addEntity(sword);
        dungeon.addEntity(player);
        dungeon.setPlayer(player);
        player.moveDown();
        player.callEnemy();
        player.moveDown();
        enemy.move(player.getX(),player.getY());
        player.moveRight();
        enemy.move(player.getX(),player.getY());
        player.moveUp();
        enemy.move(player.getX(),player.getY());
        player.moveRight();
        assertTrue(dungeon.checkComplete());

    }
}
