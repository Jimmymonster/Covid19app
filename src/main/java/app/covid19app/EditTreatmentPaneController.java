package app.covid19app;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class EditTreatmentPaneController implements Initializable {
    private String pressed = "-fx-font-family: Quicksand;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 20;" +
            "-fx-background-color: #376bab;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 100px;";
    private String released = "-fx-font-family: Quicksand;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 20;" +
            "-fx-background-color: #4F8EDB;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 100px;";
    @FXML public Button backbtn;
    @FXML public TextField descriptionfield;
    @FXML public TextField firstdayfield;
    @FXML public TextField locationfield;
    @FXML public TextField medicinefield;
    @FXML private Text namesurnamefield;
    @FXML public TextField quarantinefield;
    @FXML public Button savebtn;
    @FXML void backpressed(MouseEvent event) {backbtn.setStyle(pressed);}
    @FXML void backreleased(MouseEvent event) {backbtn.setStyle(released);}
    @FXML void savepressed(MouseEvent event) {savebtn.setStyle(pressed);}
    @FXML void savereleased(MouseEvent event) {savebtn.setStyle(released);}
    private String username;
    private String location;
    private String firstday;
    private String quarantine;
    private String medicine;
    private String description;
    private String status;
    @FXML public ComboBox combobox;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        combobox.getItems().add("wait for diagnosis");
        combobox.getItems().add("recovering");
        combobox.getItems().add("cured");
        combobox.getItems().add("death");
    }
    public void update(String username){
        combobox.getSelectionModel().clearSelection();

        this.username = username;
        Connection connection = DbConnect.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from UserTreatment where Username = '"+this.username+"'");
            if(resultSet.next()){
                location = resultSet.getString(2);
                firstday = resultSet.getString(3);
                quarantine  = resultSet.getString(4);
                medicine = resultSet.getString(5);
                description = resultSet.getString(6);
            }
            else System.out.println("Edit treatment pane cane find user!!!");
            resultSet = statement.executeQuery("select * from UserInfo where Username = '"+username+"'");
            if(resultSet.next()){
                namesurnamefield.setText(resultSet.getString(2)+" "+resultSet.getString(3));
                status = resultSet.getString(13);
            }
            connection.close();
            locationfield.setText(location);
            firstdayfield.setText(firstday);
            quarantinefield.setText(quarantine);
            medicinefield.setText(medicine);
            descriptionfield.setText(description);
            if(status.equals("diagnosis")) combobox.getSelectionModel().select(0);
            else if(status.equals("recovering")) combobox.getSelectionModel().select(1);
            else if(status.equals("cured")) combobox.getSelectionModel().select(2);
            else if(status.equals("death")) combobox.getSelectionModel().select(3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
