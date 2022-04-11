package app.covid19app;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Node node;

    public SceneController(){
        stage.setTitle("Covid19App!");
        stage.setResizable(false);
    }
}
