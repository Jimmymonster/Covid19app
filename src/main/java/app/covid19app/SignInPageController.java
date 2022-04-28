package app.covid19app;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SignInPageController implements Initializable {
    @FXML
    private Text error;
    @FXML
    private PasswordField password;
    @FXML
    private Button signinbtn;
    @FXML
    private TextField username;
    @FXML
    void signin(MouseEvent event) {
        Connection connection = DbConnect.getInstance().getConnection();
        try {
            String username = this.username.getText();
            String password = this.password.getText();
            //check error
            if (username.isBlank() || username.isEmpty() || password.isBlank() || password.isEmpty()) {
                error.setText("Each field must be fill!!!");
                connection.close();
                return;
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from UserAccount where Username = '"+username+"' and Password = '"+password+"'");
            if(resultSet.next()){
                String rank=resultSet.getString(3);
                //set user data
                User u = new User(username);
                UserHolder holder = UserHolder.getInstance();
                holder.setUser(u);

                if(rank.equals("Staff")){
                    //go to Staff page
                    Covid19App.switchScene("MainPageStaff.fxml");
                }
                else if(rank.equals("Member")){
                    //go to Member page
                    Covid19App.switchScene("MainPageMember.fxml");
                }
                System.out.println("login complete!!!");
            }
            else{
                error.setText("Username or password is wrong!!!");
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void signinpressed(MouseEvent event) {
        signinbtn.setStyle("-fx-font-family: Quicksand;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 14;" +
                "-fx-background-color: #376bab;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 100px;");
    }
    @FXML
    void signinreleased(MouseEvent event) {
        signinbtn.setStyle("-fx-font-family: Quicksand;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 14;" +
                "-fx-background-color: #4F8EDB;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 100px;");
    }

    @FXML
    void signup(MouseEvent event) {
        Covid19App.switchScene("SignUpPage.fxml");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
