package app.covid19app;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class InformationPaneController implements Initializable {
    private String username;
    private String namedb;
    private String surnamedb;
    private String agedb;
    private String genderdb;
    private String birthdatedb;
    private String bloodgroupdb;
    private String numberiddb;
    private String teldb;
    private String addressdb;
    private String imgAddress;
    @FXML private Text address;
    @FXML private Text age;
    @FXML private Text birthdate;
    @FXML private Text bloodgroup;
    @FXML public Button btn;
    @FXML private Text gender;
    @FXML private Text name;
    @FXML private Text numberid;
    @FXML private Text surname;
    @FXML private Text tel;
    @FXML private ImageView userimg;
    @FXML void btnclicked(MouseEvent event) {

    }

    @FXML void btnpressed(MouseEvent event) {
        btn.setStyle("-fx-font-family: Quicksand;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 14;" +
                "-fx-background-color: #d9d9d9;" +
                "-fx-text-fill: #4F8EDB;" +
                "-fx-background-radius: 100px;");
    }
    @FXML void btnreleased(MouseEvent event) {
        btn.setStyle("-fx-font-family: Quicksand;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 14;" +
                "-fx-background-color: white;" +
                "-fx-text-fill: #4F8EDB;" +
                "-fx-background-radius: 100px;");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserHolder holder = UserHolder.getInstance();
        User u = holder.getUser();
        username = u.getUsername();
        Connection connection = DbConnect.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from UserInfo where Username = '"+username+"'");
            if(resultSet.next()){
                namedb = resultSet.getString(2);
                surnamedb = resultSet.getString(3);
                agedb = resultSet.getString(4);
                genderdb = resultSet.getString(5);
                birthdatedb = resultSet.getString(6);
                bloodgroupdb = resultSet.getString(7);
                numberiddb = resultSet.getString(9);
                teldb = resultSet.getString(10);
                addressdb = resultSet.getString(11);
                imgAddress = resultSet.getString(12);
            }
            else{
                System.out.println("Error missing data!?!?");
            }
            name.setText(namedb);
            surname.setText(surnamedb);
            age.setText(agedb);
            gender.setText(genderdb);
            birthdate.setText(birthdatedb);
            bloodgroup.setText(bloodgroupdb);
            numberid.setText(numberiddb);
            tel.setText(teldb);
            address.setText(addressdb);
            if(imgAddress==null||imgAddress.isBlank()||imgAddress.isEmpty()){
                try {
                    FileInputStream input = new FileInputStream("src\\main\\userImages\\missing_user_img.jpg");
                    userimg.setImage(new Image(input));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else{
                try {
                    FileInputStream input = new FileInputStream(imgAddress);
                    userimg.setImage(new Image(input));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
