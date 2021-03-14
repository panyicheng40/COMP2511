package unsw.dungeon;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Instruction extends Builder{
    private instructionController controller;
    private Scene scene;

    public Instruction(Stage stage) throws IOException {
        super(stage,"instruction");
        controller = new instructionController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Instruction.fxml"));
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

    public instructionController getController() {
        return controller;
    }
}
