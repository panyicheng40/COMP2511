package unsw.dungeon;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu extends Builder{
    private MenuController controller;
    private Scene scene;

    public Menu(Stage stage) throws IOException {
        super(stage,"Menu");
        controller = new MenuController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        scene = new Scene(root);
    }

    @Override
    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public MenuController getController() {
        return controller;
    }

}