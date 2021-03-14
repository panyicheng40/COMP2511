package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i), i);
        }
        JSONObject jsonGoals = json.getJSONObject("goal-condition");
        Goals newgoals = new Goals(dungeon);

        loadGoals(jsonGoals,0,newgoals);
        dungeon.setGoals(newgoals);
        dungeon.getGoals().setDungeon(dungeon);
        return dungeon;
    }



    private void loadGoals(JSONObject json,int level,Goals newgoals) {
        String goaltype = json.getString("goal");
        if (goaltype.equals("AND")){
            level++;
            JSONArray jsonGoal = json.getJSONArray("subgoals");
            for (int i = 0; i < jsonGoal.length(); i++) {
                loadGoals(jsonGoal.getJSONObject(i),level,newgoals);
                level++;
            }
        }
        if (goaltype.equals("OR")){
            level++;
            JSONArray jsonGoal = json.getJSONArray("subgoals");
            for (int i = 0; i < jsonGoal.length(); i++) {
                loadGoals(jsonGoal.getJSONObject(i),level,newgoals);
            }
        }
        else{
            if (goaltype.equals("exit")){
                ExitGoal goal1 = new ExitGoal("exit",level);
                newgoals.addSubGoalsList(goal1.getName(), goal1.getLevel());
            }else if (goaltype.equals("enemies")){
                EnemiesGoals goal2 = new EnemiesGoals("enemies",level);
                newgoals.addSubGoalsList(goal2.getName(), goal2.getLevel());
            }else if (goaltype.equals("boulders")){
                BouldersGoals goal3 = new BouldersGoals("boulders",level);
                newgoals.addSubGoalsList(goal3.getName(), goal3.getLevel());
            }else if (goaltype.equals("treasure")){
                TreasureGoals goal4 = new TreasureGoals("treasure",level);
                newgoals.addSubGoalsList(goal4.getName(), goal4.getLevel());
            }else if (goaltype.equals("hounds")){
                EnemiesGoals goal2 = new EnemiesGoals("hounds",level);
                newgoals.addSubGoalsList(goal2.getName(), goal2.getLevel());
            }
        }

    }


    private void loadEntity(Dungeon dungeon, JSONObject json, int No) {
        int id = 0;
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");
        if(json.getString("type").equals("door")){
            id = json.getInt("id");
        }
        if(json.getString("type").equals("portal")){
            id = json.getInt("id");
        }
        if(json.getString("type").equals("key")){
            id = json.getInt("id");
        }


        Entity entity = null;
        switch (type) {
            case "player":
                Player player = new Player(dungeon, x, y, No);
                dungeon.setPlayer(player);
                onLoad(player);
                entity = player;
                break;
            case "wall":
                Wall wall = new Wall(x, y, No);
                onLoad(wall);
                entity = wall;
                break;
            case "exit":
                Exit exit = new Exit(dungeon, x, y, No);
                onLoad(exit);
                entity = exit;
                break;
            case "treasure":
                Treasure treasure= new Treasure(dungeon, x, y, No);
                onLoad(treasure);
                entity = treasure;
                break;
            case "door":
                Door door= new Door(dungeon, x, y, id, No);
                onLoad(door);
                entity = door;
                break;
            case "key":
                Key key= new Key(dungeon, x, y, id, No);
                onLoad(key);
                entity = key;
                break;
            case "boulder":
                Boulder boulder= new Boulder(dungeon, x, y, No);
                onLoad(boulder);
                entity = boulder;
                break;
            case "gnome":
                Gnome gnome= new Gnome(dungeon, x, y, No);
                onLoad(gnome);
                dungeon.addGnome(gnome);
                entity = gnome;
                break;
            case "switch":
                Floorswitch floorswitch= new Floorswitch(dungeon, x, y, No);
                onLoad(floorswitch);
                entity = floorswitch;
                break;
            case "portal":
                Portal portal= new Portal(dungeon, x, y, id, No);
                onLoad(portal);
                entity = portal;
                break;
            case "enemy":
                Enemy enemy= new Enemy(dungeon, x, y, No);
                onLoad(enemy);
                dungeon.addEnemy(enemy);
                entity = enemy;
                break;
            case "hound":
                Hound hound= new Hound(dungeon, x, y, No);
                onLoad(hound);
                dungeon.addHound(hound);
                entity = hound;
                break;
            case "sword":
                Sword sword= new Sword(dungeon, x, y, No);
                onLoad(sword);
                entity = sword;
                break;
            case "invincibility":
                Invincibilitypotion invincibilitypotion= new Invincibilitypotion(dungeon, x, y, No);
                onLoad(invincibilitypotion);
                entity = invincibilitypotion;
                break;
            case "stop":
                StopPotion stopPotion = new StopPotion(dungeon, x, y, No);
                onLoad(stopPotion);
                entity = stopPotion;
                break;

            // TODO Handle other possible entities
        }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);

    public abstract void onLoad(Exit exit);

    public abstract void onLoad(Door door);

    public abstract void onLoad(Key key);

    public abstract void onLoad(Boulder boulder);

    public abstract void onLoad(Floorswitch floorswitch);

    public abstract void onLoad(Portal portal);

    public abstract void onLoad(Enemy enemy);

    public abstract void onLoad(Sword sword);

    public abstract void onLoad(Invincibilitypotion invincibilitypotion);

    public abstract void onLoad(Treasure treasure);

    public abstract void onLoad(Hound hound);
    
    public abstract void onLoad(Gnome gnome);  

    public abstract void onLoad(StopPotion stopPotion);

    // TODO Create additional abstract methods for the other entities

}
