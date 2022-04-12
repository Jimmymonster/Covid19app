package app.covid19app;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.HashMap;

public class SceneController {
    private HashMap<String, Node> screenMap = new HashMap<>();
    private Scene main;

    public SceneController(Scene main){
        this.main = main;
    }
    protected void addScreen(String name, Node node){
        screenMap.put(name,node);
    }
    protected void removeScreen(String name){
        screenMap.remove(name);
    }
    protected void activate(String name){
        main.setRoot((Parent) screenMap.get(name));
    }

}
