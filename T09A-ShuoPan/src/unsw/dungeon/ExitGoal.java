package unsw.dungeon;

public class ExitGoal {
    private String name;
    private int level;

    /**
     * 
     * @param name
     * @param level
     */
    public ExitGoal(String name, int level){
        this.name = name;
        this.level = level;
    }

    /**
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return
     */
    public int getLevel() {
        return level;
    }

}
