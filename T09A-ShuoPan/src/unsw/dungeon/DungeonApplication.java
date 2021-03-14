package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        Instruction instruction = new Instruction(stage);
        GameStart gameStart = new GameStart(stage);
        Menu menu = new Menu(stage);
        menu.getController().setInstruction(instruction);
        menu.getController().setGameStart(gameStart);
        instruction.getController().setMenu(menu);


        menu.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
