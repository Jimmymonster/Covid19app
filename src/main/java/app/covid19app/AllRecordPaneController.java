package app.covid19app;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AllRecordPaneController implements Initializable {
    @FXML VBox recordbox;
    HBox[] list= new HBox[10000];
    HBox[][] columns = new HBox[10000][4];
    String[] usernamelist= new String[10000];
    Text[][] txt = new Text[10000][3];
    Button[] btnlist= new Button[10000];
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection connection = DbConnect.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from UserInfo order by Name ASC");
            int i=0;
            while(resultSet.next()) {
                String username = resultSet.getString(1);
                //infomation
                usernamelist[i] = username;
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                String status = resultSet.getString(13);
                if (status.equals("diagnosis")) status = "wait for diagnosis";

                //javafx
                list[i] = new HBox();
                for (int j = 0; j < 4; j++) {
                    columns[i][j] = new HBox();
                    list[i].setHgrow(columns[i][j], Priority.ALWAYS);
                    columns[i][j].setStyle("-fx-border-color: #a2a2a2;" +
                            "-fx-border-width: 1px 1px 1px 1px;");
                    columns[i][j].setPrefWidth(230);
                    columns[i][j].setPrefHeight(40);
                    columns[i][j].setAlignment(Pos.CENTER);
                    if (j != 3) {
                        txt[i][j] = new Text();
                        txt[i][j].setStyle("-fx-font-size: 16;" +
                                "-fx-font-family: Quicksand;" +
                                // "-fx-font-weight: bold;" +
                                "-fx-fill:  black;");
                    }
                }
                btnlist[i] = new Button("Edit case");
                btnlist[i].setStyle("-fx-background-radius:  100;" +
                        "-fx-font-family: Quicksand;" +
                        "-fx-font-size: 16;" +
                        //"-fx-font-weight: bold;" +
                        "-fx-text-fill: black;");
                txt[i][0].setText(name);
                txt[i][1].setText(surname);
                txt[i][2].setText(status);
                columns[i][0].getChildren().add(txt[i][0]);
                columns[i][1].getChildren().add(txt[i][1]);
                columns[i][2].getChildren().add(txt[i][2]);
                columns[i][3].getChildren().add(btnlist[i]);
                list[i].getChildren().addAll(columns[i]);
                recordbox.getChildren().add(list[i]);
                recordbox.setPrefHeight(40 * (i + 1));
                i++;
            }
            for (int j = 0; j < 4; j++) {
                columns[i-1][j].setStyle("-fx-border-color: #a2a2a2;" +
                        "-fx-border-width: 1px 1px 2px 1px;");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
