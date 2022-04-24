package app.covid19app;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ChangeInfoPageController implements Initializable {
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
    public String imgAddresstmp;
    @FXML public TextField address;
    @FXML public TextField age;
    @FXML public Button backbtn;
    @FXML private Text birthdate;
    @FXML private Text bloodgroup;
    @FXML private Button uploadpic;
    @FXML private Text gender;
    @FXML public TextField name;
    @FXML private Text numberid;
    @FXML public Button savebtn;
    @FXML public TextField surname;
    @FXML public TextField tel;
    @FXML private ImageView userimg;
    @FXML public Text error;
    private String pressed = "-fx-font-family: Quicksand;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 16;" +
            "-fx-background-color: #376bab;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 100px;";
    private String released = "-fx-font-family: Quicksand;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 16;" +
            "-fx-background-color: #4F8EDB;" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 100px;";
    @FXML void backpressed(MouseEvent event) {backbtn.setStyle(pressed);}
    @FXML void backreleased(MouseEvent event) {backbtn.setStyle(released);}
    @FXML void savepressed(MouseEvent event) {savebtn.setStyle(pressed);}
    @FXML void savereleased(MouseEvent event) {savebtn.setStyle(released);}
    @FXML void uploadclicked(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("IMAGE FILES", "*.jpg", "*.png", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if(selectedFile!=null){
            imgAddresstmp=selectedFile.getPath();
            try {
                FileInputStream input = new FileInputStream(imgAddresstmp);
                userimg.setImage(new Image(input));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("error");
        }
    }
    @FXML void uploadpressed(MouseEvent event) {uploadpic.setStyle(pressed);}
    @FXML void uploadreleased(MouseEvent event) {uploadpic.setStyle(released);}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        reset();
    }
    public void reset(){
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
            connection.close();
            name.setText(namedb);
            surname.setText(surnamedb);
            age.setText(agedb);
            gender.setText(genderdb);
            birthdate.setText(birthdatedb);
            bloodgroup.setText(bloodgroupdb);
            numberid.setText(numberiddb);
            tel.setText(teldb);
            address.setText(addressdb);
            if(imgAddress==null||imgAddress.equals("null")||imgAddress.isEmpty()||imgAddress.isBlank()){
                try {
                    FileInputStream input = new FileInputStream("src\\main\\userImages\\missing_user_img.jpg");
                    userimg.setImage(new Image(input));
                    input.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                try {
                    FileInputStream input = new FileInputStream(imgAddress);
                    userimg.setImage(new Image(input));
                    input.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
