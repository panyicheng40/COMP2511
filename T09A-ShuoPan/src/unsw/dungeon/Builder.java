package unsw.dungeon;

import javafx.stage.Stage;

import java.io.IOException;

public abstract class Builder {
    protected Stage stage;
    protected String title;

    public Builder(Stage stage, String title) {
        this.stage = stage;
        this.title = title;
    }

    public abstract void start() throws IOException;

}
