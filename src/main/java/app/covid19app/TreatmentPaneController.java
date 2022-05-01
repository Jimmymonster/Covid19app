package app.covid19app;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TreatmentPaneController implements Initializable {
    @FXML private Text description;
    @FXML private Text firstdate;
    @FXML private Text location;
    @FXML private Text medicine;
    @FXML private Text quarantine;
    private String locationstr;
    private String firstdatestr;
    private String quarantinestr;
    private String medicinestr;
    private String descriptionstr;
    private String username;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserHolder userHolder = UserHolder.getInstance();
        User user = userHolder.getUser();
        this.username=user.getUsername();
        Connection connection = DbConnect.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from UserTreatment where Username = '"+username+"'");
            if(resultSet.next()){
                locationstr=resultSet.getString(2);
                firstdatestr=resultSet.getString(3);
                quarantinestr=resultSet.getString(4);
                medicinestr=resultSet.getString(5);
                descriptionstr=resultSet.getString(6);
                if(locationstr==null||locationstr.isEmpty()||locationstr.isBlank()) location.setText("Wait for diagnosis");
                else location.setText(locationstr);
                if(firstdatestr==null||firstdatestr.isEmpty()||firstdatestr.isBlank()) firstdate.setText("Wait for diagnosis.");
                else firstdate.setText(firstdatestr);
                if(quarantinestr==null||quarantinestr.isEmpty()||quarantinestr.isBlank()) quarantine.setText("Wait for diagnosis.");
                else quarantine.setText(quarantinestr);
                if(medicinestr==null||medicinestr.isEmpty()||medicinestr.isBlank()) medicine.setText("Wait for diagnosis.");
                else medicine.setText(medicinestr);
                if(descriptionstr==null||descriptionstr.isEmpty()||descriptionstr.isBlank()) description.setText("Wait for diagnosis.");
                else description.setText(descriptionstr);
            }
            else System.out.println("Load data error!!!");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
