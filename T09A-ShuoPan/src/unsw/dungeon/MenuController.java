package unsw.dungeon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MenuController {
    private Instruction instruction;
    private GameStart gameStart;

    @FXML
    private Button startbutton;

    @FXML
    private Button instructionbutton;


    public void setInstruction(Instruction instruction) { this.instruction = instruction; }

    public void setGameStart(GameStart gameStart){
        this.gameStart = gameStart;
    }

    @FXML
    void gotoInstruction(ActionEvent event) { instruction.start(); }

    @FXML
    void startGame(ActionEvent event) {gameStart.start();}

}
