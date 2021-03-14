package unsw.dungeon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import unsw.dungeon.DungeonControllerLoader;

import java.io.IOException;

public class GameStart extends Builder{
    private DungeonController controller;
    private Menu menu;
    private Scene scene;

    /**
     * default constructor with no file specified
     * @param stage
     * @throws IOException
     */

    public GameStart(Stage stage) throws IOException {
        super(stage, "Dungeon");
        this.stage = stage;

        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("map1.json");
        Menu menu = new Menu(stage);
        this.menu = menu;
        DungeonController controller = dungeonLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView1.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        scene = new Scene(root);


    }


    public void setController(DungeonController controller) { this.controller = controller; }

    @Override
    public void start() {
        scene.getRoot().requestFocus();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }



    public DungeonController getController() {
        return controller;
    }

}
