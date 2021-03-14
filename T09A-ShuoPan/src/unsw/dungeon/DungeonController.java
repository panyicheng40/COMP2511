package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    private GridPane squares;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;

    private Menu menu;
    final Timeline timeline = new Timeline();

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        dungeon.setController(this);

        timeline.setCycleCount(Timeline.INDEFINITE);
        Duration time = new Duration(200);
        KeyFrame keyFrame = new KeyFrame(time, e->handle(e,dungeon));
        timeline.getKeyFrames().add(keyFrame);
    }
    @FXML
    public void handle(ActionEvent event, Dungeon dungeon) {
        System.out.println("0");
        dungeon.checkComplete();
        if (dungeon.checkComplete() && dungeon.getPlayer().isAlive()) {
            System.out.println("1");
            warningAlert(true);
        } else if (!dungeon.checkComplete() && !dungeon.getPlayer().isAlive()) {
            System.out.println("2");
            warningAlert(false);
        }
    }
    @FXML
    private Button backtomenu;

    @FXML
    void btoMenu(ActionEvent event) { menu.start(); }

    @FXML
    public void initialize() {
        Image ground = new Image((new File("images/dirt_0_new.png")).toURI().toString());

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);

    }

    public void setMenu(Menu menu) { this.menu = menu; }

    /*public void startDungeon() {
        timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        dungeon.checkComplete();
                        if (dungeon.checkComplete() && dungeon.getPlayer().isAlive()) {
                            warningAlert(true);
                        } else if (!dungeon.checkComplete() && !dungeon.getPlayer().isAlive()) {
                            warningAlert(false);
                        }

                    }
                }));
    }

     */

    @FXML
    public void handleKeyPress(KeyEvent event) {
        player.reduceInvistime();
        switch (event.getCode()) {
            case UP:
                player.moveUp();
                break;
            case DOWN:
                player.moveDown();
                break;
            case LEFT:
                player.moveLeft();
                break;
            case RIGHT:
                player.moveRight();
                break;
            default:
                break;
        }
        player.callEnemy();
        player.callHound();
        player.callHound();
        player.callGnome();
        dungeon.checkComplete();
        player.teleport();
        // System.out.println("Goal: "+dungeon.checkComplete());
    }

    @FXML
    public void warningAlert(Boolean completed) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game over!");
        if (completed) {
            alert.setHeaderText("You Win!");
        }else{
            alert.setHeaderText("You Lose!");
        }
        timeline.pause();
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);
        stage.toFront();
        alert.show();
    }

    public void changeImage(int index, Image image) {
        this.initialEntities.get(index).setImage(image);
    }

    public void addSquare(ImageView image, int x, int y) {
        this.squares.add(image, x, y);
    }


}
