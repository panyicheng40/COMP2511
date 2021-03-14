package unsw.dungeon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import unsw.dungeon.Menu;

public class instructionController {
    private Menu menu;

    public void setMenu(Menu menu) { this.menu = menu; }

    @FXML
    private Button backbutton;

    @FXML
    void backtoMenu(ActionEvent event) { menu.start(); }
}
