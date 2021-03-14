/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;
    private List<Enemy> enemies;
    private List<Hound> hounds;
    private List<Gnome> gnomes;
    private Goals goals;
    private DungeonController dungeonController;
    private boolean teleport;
    private int stop;

    /**
     *
     * @param width
     * @param height
     */
    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.goals = null;
        this.player = null;
        this.enemies = new ArrayList<Enemy>();
        this.hounds = new ArrayList<Hound>();
        this.gnomes = new ArrayList<Gnome>();
        this.dungeonController = null;
        this.teleport = false;
        this.stop = 0;
    }

    /**
     *
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     *
     * @return
     */
    public List<Entity> getEntites(){
        return entities;
    }

    /**
     *
     * @return
     */
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setGoals(Goals goals) {
        this.goals = goals;
    }

    /**
     *
     * @return
     */
    public Goals getGoals() {
        return goals;
    }

    /**
     * 
     * @return
     */
    public boolean getTeleport() {
        return teleport;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void addHound(Hound hound) {
        hounds.add(hound);
    }

    public void addGnome(Gnome gnome) {
        gnomes.add(gnome);
    }

    public void afterpick(int x, int y){
        for(int i = 0; i < entities.size(); i++) {
            if (entities.get(i).getX() == x && entities.get(i).getY() == y) {
                entities.remove(i);
                //entities.get(i).setX(0);
                //entities.get(i).setY(0);
                break;
            }
        }
    }

    /**
     *
     * @return
     */
    public List<Enemy> getEnemy() {
        return enemies;
    }

    /**
     *
     * @return
     */
    public List<Hound> getHound() {
        return hounds;
    }

    /**
     * 
     * @return
     */
    public List<Gnome> getGnome() {
		return gnomes;
    }

    public void removeEnemy(Enemy enemy) {
        entities.remove(enemy);
        changeImage(enemy.getNo(), new Image((new File("")).toURI().toString()));
    }

    public void removeHound(Hound hound) {
        entities.remove(hound);
        changeImage(hound.getNo(), new Image((new File("")).toURI().toString()));
    }

    public void removeGnome(Gnome gnome) {
        entities.remove(gnome);
        teleport = true;
        changeImage(gnome.getNo(), new Image((new File("")).toURI().toString()));
    }

    /**
     *
     * @return
     */
    public boolean checkComplete(){
        goals.setComplete();
        return goals.isComplete();
    }

    public void setController(DungeonController dungeonController) {
        this.dungeonController = dungeonController;
    }

    public void addImage(Image image, int x, int y){
        dungeonController.addSquare(new ImageView(image), x, y);
    }

    public void changeImage(int index, Image image) {

        dungeonController.changeImage(index, image);
    }

    public void changeTeleport() {
        teleport = false;
    }

	public void setStop() {
        stop = 10;
        System.out.println(stop);
    }
    
    public int getStop() {
        return stop;
    }

    public void reduceStop() {
        stop--;
        System.out.println(stop);
    }

}
