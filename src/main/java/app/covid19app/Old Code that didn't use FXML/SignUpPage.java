/*package app.covid19app;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SignUpPage extends AnchorPane{
    private final StackPane st = new StackPane();
    //hbox for decorate
    private final HBox hb = new HBox();
    private final VBox[] decorate = {new VBox(),new VBox()};
    //hbox at center
    private final HBox hbcenter = new HBox();
    //vertical box in horizontal box center
    private final VBox[] vbin = {new VBox(35),new VBox(35)};
    private final Text txt = new Text("Sign up");
    //username box
    private final HBox usernameBox = new HBox();
    private final TextField username = new TextField("");
    //password box
    private final HBox passwordBox = new HBox();
    private final PasswordField password = new PasswordField();
    //confirm password box
    private final HBox confirmpasswordBox = new HBox();
    private final PasswordField confirmpassword = new PasswordField();

    private final Button SignUpBTN = new Button("Sign up");
    //sign in box
    private final HBox SignInBox = new HBox(10);
    private final Text txt2 = new Text("Already have an Account?");
    private final Button SignInBTN = new Button("Sign in");
    //Name and surname box
    private final Text txt3 = new Text("Infomation");
    private final HBox NameAndSurnameBox = new HBox(10);
    private final HBox NameBox = new HBox(10);
    private final TextField Name = new TextField();
    private final HBox SurnameBox = new HBox(10);
    private final TextField Surname = new TextField();
    //Age number-ID
    private final HBox AgeandNumberIDBox = new HBox(10);
    private final HBox AgeBox = new HBox(10);
    private final TextField Age = new TextField();
    private final HBox NumberIDBox = new HBox(10);
    private final TextField NumberID = new TextField();
    //Tel
    private final HBox TelBox = new HBox(10);
    private final TextField Tel = new TextField();

    private final Text error = new Text();
    private final Text error2 = new Text();

    private final String colorTheme = "#4F8EDB";

    public SignUpPage(){
        //UI things
        setStyle("-fx-background-color: transparent;");
        AnchorPane.setRightAnchor(st,10.0);
        AnchorPane.setLeftAnchor(st,10.0);
        AnchorPane.setTopAnchor(st,10.0);
        AnchorPane.setBottomAnchor(st,10.0);
        st.setEffect(new DropShadow());

        decorate[0].setStyle("-fx-background-color: "+colorTheme+";");
        decorate[0].setPrefWidth(USE_COMPUTED_SIZE);
        decorate[0].setPrefHeight(USE_COMPUTED_SIZE);
        decorate[1].setStyle("-fx-background-color: white;");
        decorate[1].setPrefWidth(USE_COMPUTED_SIZE);
        decorate[1].setPrefHeight(USE_COMPUTED_SIZE);
        hb.getChildren().addAll(decorate[0],decorate[1]);
        hb.setHgrow(decorate[0],Priority.ALWAYS);
        hb.setHgrow(decorate[1],Priority.ALWAYS);
        hb.setPrefWidth(-1);
        hb.setPrefHeight(-1);
        hb.setStyle("-fx-background-color: transparent;");

        HBox.setMargin(hbcenter, new Insets(45,45,45,45));
        hbcenter.setAlignment(Pos.CENTER);
        hbcenter.setEffect(new DropShadow());
        for(int i=0;i<2;i++) {
            vbin[i].setPrefHeight(350);
            vbin[i].setPrefWidth(350);
            vbin[i].setMaxWidth(USE_PREF_SIZE);
            vbin[i].setMaxHeight(USE_PREF_SIZE);
            vbin[i].setStyle("-fx-background-color: white;");
            vbin[i].setAlignment(Pos.TOP_CENTER);
        }

        txt.setStyle("-fx-font-size: 24;" +
                "-fx-font-family: QuickSand;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: black;");

        UI.TextFieldUI(username,usernameBox,"username");
        UI.PasswordFieldUI(password,passwordBox,"password");
        UI.PasswordFieldUI(confirmpassword,confirmpasswordBox,"confirm password");
        UI.ButtonUI(SignUpBTN,colorTheme);

        error.setStyle("-fx-fill: red;" +
                "-fx-font-size: 14;" +
                "-fx-font-weight: bold;");

        SignInBox.setPadding(new Insets(10,10,10,10));
        SignInBTN.setStyle("-fx-text-fill: "+colorTheme+";" +
                "-fx-font-family: QuickSand;" +
                "-fx-font-weight: bold;" +
                "-fx-background-color: transparent;");
        txt2.setStyle("-fx-font-family: QuickSand;" +
                "-fx-font-weight: bold;");
        SignInBox.setAlignment(Pos.BOTTOM_CENTER);
        SignInBox.getChildren().addAll(txt2,SignInBTN);

        txt3.setStyle("-fx-font-size: 24;" +
                "-fx-font-family: QuickSand;" +
                "-fx-font-weight: bold;" +
                "-fx-fill: black;");

        UI.TextFieldUI(Name, NameBox, "name");
        UI.TextFieldUI(Surname, SurnameBox, "surname");
        NameAndSurnameBox.getChildren().addAll(NameBox,SurnameBox);
        UI.TextFieldUI(Age,AgeBox,"Age");
        UI.TextFieldUI(NumberID,NumberIDBox,"Number ID");
        AgeandNumberIDBox.getChildren().addAll(AgeBox,NumberIDBox);
        UI.TextFieldUI(Tel,TelBox,"Tel.");

        error2.setStyle("-fx-fill: red;" +
                "-fx-font-size: 14;" +
                "-fx-font-weight: bold;");

        vbin[0].getChildren().addAll(txt,usernameBox,passwordBox,confirmpasswordBox,SignUpBTN,error,SignInBox);
        vbin[1].setPrefHeight(437);
        vbin[1].getChildren().addAll(txt3,NameAndSurnameBox,AgeandNumberIDBox,TelBox,error2);
        vbin[0].setPadding(new Insets(10,10,10,10));
        vbin[1].setPadding(new Insets(10,10,10,10));

        hbcenter.getChildren().addAll(vbin[1],vbin[0]);
        st.getChildren().addAll(hb,hbcenter);
        getChildren().add(st);

        //Action event
        SignUpBTN.setOnAction(e->{SignUpBTNAction();});
        SignInBTN.setOnAction(e->{SignInBTNAction();});
    }
    private void SignUpBTNAction(){
        Connection connection = DbConnect.getInstance().getConnection();
        try {
            boolean ch = true;
            String username = this.username.getText();
            String password = this.password.getText();
            String confirmpassword = this.confirmpassword.getText();
            String name = this.Name.getText();
            String surname = this.Surname.getText();
            String age= this.Age.getText();
            String numberID = this.NumberID.getText();
            String tel = this.Tel.getText();

            //create statement
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.ex//check error
            if(username.isBlank()||username.isEmpty()||password.isBlank()||password.isEmpty()||confirmpassword.isBlank()){
                error.setText("Each field must be fill!!!");
                ch=false;
            }
            else if(!confirmpassword.equals(password)){
                error.setText("Password isn't match!!!");
                ch=false;
            }
            if(name.isBlank()||name.isEmpty()||surname.isBlank()||surname.isEmpty()||age.isBlank()||age.isEmpty()||numberID.isBlank()||numberID.isEmpty()||tel.isEmpty()||tel.isBlank()){
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
            else if(isInt(name)){
                error2.setText("Name must not contain digits!!!");
                ch=false;
            }
            else if(!isInt(age)){
                error2.setText("Age must be number!!!");
                ch=false;
            }
            else if(!isInt(numberID)){
                error2.setText("Number ID must be number!!!");
                ch=false;
            }
            else if(numberID.length()!=13){
                error2.setText("Number ID must have 13 digits");
                ch=false;
            }
            else if(!isInt(tel)){
                error2.setText("Telephone number must be number!!!");
                ch=false;
            }
            if(!ch) {
                connection.close();
                return;
            }
            ResultSet resultSet = ecuteQuery("select * from UserAccount where Username = '"+username+"'");
            if(resultSet.next() ){
                error.setText("Username is already used!!!");
                connection.close();
                return;
            }
            int status = statement.executeUpdate("insert into UserAccount (Username,Password,Rank) values('"+username+"','"+password+"','Member')");
            int status2 = statement.executeUpdate("insert into UserInfo (Username,Name,Surname,Age,NumberID,Tel) values('"+username+"','"+name+"','"+surname+"','"+age+"','"+numberID+"','"+tel+"')");
            if(status>0&&status2>0){
                System.out.println("User registered");
                //go to sign in
                clearField();
               // Covid19App.changeScene("login");
                connection.close();
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void SignInBTNAction() {
        //go to sign in
        clearField();
       // Covid19App.changeScene("login");
    }
    private void clearField(){
        error.setText("");
        error2.setText("");
        username.setText("");
        password.setText("");
        confirmpassword.setText("");
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
}*/
