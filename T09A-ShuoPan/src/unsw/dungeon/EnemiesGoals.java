package unsw.dungeon;

public class EnemiesGoals {

    private String name;
    private int level;

    /**
     * 
     * @param name  the goal name
     * @param level the goal level of enemy
     */
    public EnemiesGoals(String name, int level){
        this.name = name;
        this.level = level;
    }

    /**
     * 
     * @return the goal name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return the goal level of enemy
     */
    public int getLevel() {
        return level;
    }


}
