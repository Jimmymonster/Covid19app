package app.covid19app;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SignUpPageController implements Initializable {
    @FXML private TextField address;
    @FXML private TextField age;
    @FXML private TextField birthdate;
    @FXML private TextField bloodgroup;
    @FXML private PasswordField confirmpassword;
    @FXML private Text error;
    @FXML private Text error2;
    @FXML private TextField gender;
    @FXML private TextField name;
    @FXML private TextField numberid;
    @FXML private PasswordField password;
    @FXML private Button signupbtn;
    @FXML private TextField surname;
    @FXML private TextField tel;
    @FXML private TextField username;
    @FXML void signin(MouseEvent event) {
        Covid19App.switchScene("SignInPage.fxml");
    }
    @FXML
    void signup(MouseEvent event) {
        Connection connection = DbConnect.getInstance().getConnection();
        error.setText("");
        error2.setText("");
        try {
            boolean ch = true;
            String username = this.username.getText();
            String password = this.password.getText();
            String confirmpassword = this.confirmpassword.getText();
            String name = this.name.getText();
            String surname = this.surname.getText();
            String numberid = this.numberid.getText();
            String age = this.age.getText();
            String tel = this.tel.getText();
            String birthdate = this.birthdate.getText();
            String gender = this.gender.getText();
            String bloodgroup = this.bloodgroup.getText();
            String address = this.address.getText();
            if(username.isBlank()||username.isEmpty()||password.isBlank()||password.isEmpty()||confirmpassword.isBlank()){
                error.setText("Each field must be fill!!!");
                ch=false;
            }
            else if(!confirmpassword.equals(password)){
                error.setText("Password isn't match!!!");
                ch=false;
            }
            if(name.isBlank()||name.isEmpty()||surname.isBlank()||surname.isEmpty()||age.isBlank()||age.isEmpty()||
                    numberid.isBlank()||numberid.isEmpty()||tel.isEmpty()||tel.isBlank()||birthdate.isEmpty()||birthdate.isBlank()||
                    gender.isEmpty()||gender.isBlank()||bloodgroup.isEmpty()||bloodgroup.isBlank()||address.isEmpty()||address.isBlank()){
                error2.setText("Each field must be fill!!!");
                ch=false;
            }
            else if(!isAlpha(name)){
                error2.setText("Name must not contain digits!!!");
                ch=false;
            }
            else if(!isAlpha(surname)){
                error2.setText("Surname must not contain digits!!!");
                ch=false;
            }
            else if(!isInt(age)){
                error2.setText("Age must be number!!!");
                ch=false;
            }
            else if(!isInt(numberid)){
                error2.setText("Number ID must be number!!!");
                ch=false;
            }
            else if(numberid.length()!=13){
                error2.setText("Number ID must have 13 digits");
                ch=false;
            }
            else if(!(gender.equals("Male")||gender.equals("Female"))){
                error2.setText("Gender must be Male or Female!!!");
                ch=false;
            }
            else if(!chBirthdate(birthdate)){
                error2.setText("Birth date must be correct form!!!");
                ch=false;
            }
            else if(!isInt(tel)){
                error2.setText("Telephone number must be number!!!");
                ch=false;
            }
            else if(!chBloodgroup(bloodgroup)){
                error2.setText("Blood Group must be A B AB O (Upper case only)!!!");
                ch=false;
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from UserAccount where Username = '"+username+"'");
            if(resultSet.next() ){
                error.setText("Username is already used!!!");
                ch=false;
            }
            if(!ch) {
                connection.close();
                return;
            }
            int status = statement.executeUpdate("insert into UserAccount (Username,Password,Rank) values('"+username+"','"+password+"','Member')");
            int status2 = statement.executeUpdate("insert into UserInfo (Username,Name,Surname,Age,Gender,BirthDate,BloodGroup,NumberID,Tel,Address) " +
                    "values('"+username+"','"+name+"','"+surname+"','"+age+"','"+gender+"','"+birthdate+"','"+bloodgroup+"','"+numberid+"','"+tel+"','"+address+"')");
            if(status>0&&status2>0){
                System.out.println("Registered!!!");
                Covid19App.switchScene("SignInPage.fxml");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void signuppressed(MouseEvent event) {
        signupbtn.setStyle("-fx-font-family: Quicksand;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 14;" +
                "-fx-background-color: #376bab;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 100px;");
    }

    @FXML
    void signupreleased(MouseEvent event) {
        signupbtn.setStyle("-fx-font-family: Quicksand;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 14;" +
                "-fx-background-color: #4F8EDB;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 100px;");
    }

    private boolean isInt(String name) {
        char[] chars = name.toCharArray();
        for (char c : chars) {
            if(!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
    private boolean isAlpha(String name) {
        char[] chars = name.toCharArray();
        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }
    private boolean chBirthdate(String bd){
        if(bd.length()!=10) return false;
        else if(bd.charAt(2)!='/'||bd.charAt(5)!='/') return false;
        else return true;
    }
    private boolean chBloodgroup(String bg){
        if(bg.equals("A")||bg.equals("B")||bg.equals("AB")||bg.equals("O")) return true;
        else return false;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
