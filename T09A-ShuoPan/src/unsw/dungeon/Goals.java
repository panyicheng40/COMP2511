package unsw.dungeon;

import java.util.ArrayList;

/**
 * Goals store different subgoals in different level
 * if the subgoals in same level, the relation of them is 'Or'
 * if the subgoals in different level, the relation of them is 'And'
 */
public class Goals {
    private Dungeon dungeon;
    private ArrayList <String> level0;
    private ArrayList <String> level1;
    private ArrayList <String> level2;
    private ArrayList <String> level3;
    private ArrayList <String> level4;
    private boolean complete;

    public Goals(Dungeon dungeon) {
        this.dungeon = dungeon;
        level0 = new ArrayList<>();
        level1 = new ArrayList<>();
        level2 = new ArrayList<>();
        level3 = new ArrayList<>();
        level4 = new ArrayList<>();
        complete = false;
    }

    public void setDungeon(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    /**
     * be called in DungeonLoader to set the Goal
     * @param name Subgoals name
     * @param level Subgoals Level
     */
    public void addSubGoalsList(String name, int level){
        if(name == null){}
        else if(level==0){
            level0.add(name);
        } else if(level==1){
            if(level0.size()==0) {
                level0.add(name);
            }else{
                level1.add(name);
            }
        } else if(level==2){
            if(level1.size()==0) {
                level1.add(name);
            }else{
                level2.add(name);
            }
        } else if(level==3){
            if(level2.size()==0) {
                level2.add(name);
            }else{
                level3.add(name);
            }
        } else if(level==4){
            if(level3.size()==0) {
                level3.add(name);
            }else {
                level4.add(name);
            }
        }
    }

    public boolean isComplete() {
        System.out.println(complete);
        return complete;
    }

    /**
     * use to check whether all subgoals has been done
     */
    public void setComplete(){
        complete = checkLevel(level0)&&checkLevel(level1)&&checkLevel(level2)&&checkLevel(level3)&&checkLevel(level4);
    }

    /**
     * check subgoals in specified level
     * @param level every level of Goal
     * @return subgoals in this level has been completed or not
     */
    public boolean checkLevel(ArrayList <String> level){
        boolean checkExit = false;
        boolean checkTreasure = false;
        boolean checkEnemy = false;
        boolean checkBoulder = false;
        if (level.size() == 0){
            return true;
        } else{
            for(String name : level){
                if (name.equals("exit")){
                    checkExit = checkExit();
                }if (name.equals("treasure")){
                    checkTreasure = checkTreasure();
                }if (name.equals("enemies")){
                    checkEnemy = checkEnemy();
                }if (name.equals("boulders")){
                    checkBoulder = checkBoulder();
                }
            }
        }
        boolean result = checkBoulder || checkEnemy || checkExit || checkTreasure;
        return result;
    }

    /**
     *
     * @return check whether the subgoal of treasure has been completed
     */
    public boolean checkTreasure(){
        for (Entity entity:dungeon.getEntites()){
            if(entity instanceof Treasure){
                // System.out.println("TreauseFalse");
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @return check whether the subgoal of enemy has been completed
     */
    public boolean checkEnemy(){
        for (Entity entity:dungeon.getEntites()){
            if(entity instanceof Enemy){
                // System.out.println("EnemyFalse");
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @return check whether the subgoal of boulder has been completed
     */
    public boolean checkBoulder(){
        for (Entity entity:dungeon.getEntites()){
            if(entity instanceof Floorswitch){
                boolean check = false;
                for (Entity entity1:dungeon.getEntites()) {
                    if (entity.getX() == entity1.getX() && entity.getY() == entity1.getY() && entity1 instanceof Boulder) {
                        check = true;
                        break;
                    }
                }
                if(check == false){
                    // System.out.println("FloorFalse");
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *
     * @return check whether the subgoal of exit has been completed
     */
    public boolean checkExit(){
        for(Entity entity:dungeon.getEntites()){
            if(entity instanceof Exit){
                if(entity.getX() == dungeon.getPlayer().getX() && entity.getY() == dungeon.getPlayer().getY()){
                    return true;
                }
            }
        }
        return false;
    }


}
