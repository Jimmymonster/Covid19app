package app.covid19app;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class MainPage_Staff extends AnchorPane {
    private final StackPane st = new StackPane();
    private final HBox hb = new HBox(); //for crop vbox
    private final VBox[] box = {new VBox(10), new VBox()}; // 0 = left nev bar , 1 = content box
    private final Button[] btn = {new Button("News"), new Button("Information"), new Button("Treatment"), new Button("Sign out")};

    private final HBox[] leftbox = {new HBox(),new HBox()};
    private final Text toptxt = new Text("Staff");


    private final String colorTheme = "#4F8EDB";
    private final String highlightedColor = "#376bab";
    private final String activeBTN = "-fx-background-color: " + highlightedColor + ";" +
            "-fx-background-radius: 50PX 0 0 50PX;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 20;";
    private final String non_activeBTN = "-fx-background-color: " + colorTheme + ";" +
            "-fx-background-radius: 50PX 0 0 50PX;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 20;";

    public MainPage_Staff() {
        setStyle("-fx-background-color: transparent;");
        AnchorPane.setRightAnchor(st, 10.0);
        AnchorPane.setLeftAnchor(st, 10.0);
        AnchorPane.setTopAnchor(st, 10.0);
        AnchorPane.setBottomAnchor(st, 10.0);
        st.setEffect(new DropShadow());

        box[0].setStyle("-fx-background-color: " + colorTheme + ";");
        box[0].setPrefWidth(1280 * 0.2);
        box[0].setPadding(new Insets(200, 0, 0, 0));
        box[0].setAlignment(Pos.TOP_CENTER);
        box[0].getChildren().addAll(btn);

        for (int i = 0; i < 4; i++) {
            btn[i].setPrefWidth(1280 * 0.2);
            btn[i].setPrefHeight(50);
            if (i == 0) btn[i].setStyle(activeBTN);
            else btn[i].setStyle(non_activeBTN);
        }

        leftbox[0].setStyle("-fx-background-color: #7cb3f7;");
        leftbox[0].setPrefHeight(720*0.2);
        leftbox[0].setAlignment(Pos.CENTER);
        toptxt.setStyle("-fx-font-size: 40;" +
                "-fx-font-family: QuickSand;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: white;");
        leftbox[0].getChildren().add(toptxt);

        box[1].setStyle("-fx-background-color: white;");
        box[1].setVgrow(leftbox[1],Priority.ALWAYS);
        box[1].getChildren().addAll(leftbox[0]);

        //hb.setHgrow(box[0], Priority.ALWAYS);
        hb.setHgrow(box[1], Priority.ALWAYS);
        hb.getChildren().addAll(box[0], box[1]);

        st.getChildren().addAll(hb);

        getChildren().addAll(st);

        //action event
        for (int i = 0; i < 4; i++) {
            int idx = i;
            btn[i].setOnAction(e -> {
                btnAction(idx);
            });
        }
    }

    private void btnAction(int idx) {
        box[1].getChildren().clear();
        box[1].getChildren().add(leftbox[0]);
        for (int i = 0; i < 4; i++) {
            btn[i].setPrefWidth(1280 * 0.2);
            btn[i].setPrefHeight(50);
            if (i == idx) btn[i].setStyle(activeBTN);
            else btn[i].setStyle(non_activeBTN);
        }
        if (idx == 0) {

        } else if (idx == 1) {

        } else if (idx == 2) {

        } else if (idx == 3) {
            reset();
            Covid19App.changeScene("login");
        }
    }

    private void reset() {
        btnAction(0);
    }
}
